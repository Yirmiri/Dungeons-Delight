package net.yirmiri.dungeonsdelight.common.entity.living.treasure_bug.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class ClimbingFleePlayerGoal extends Goal {
    private final PathfinderMob mob;
    private final double range;
    private final double speed;
    private final Random random = new Random();
    private Player player;
    private BlockPos target;
    private int repathCooldown;

    public ClimbingFleePlayerGoal(PathfinderMob mob, double speed, double detectionRange) {
        this.mob = mob;
        this.speed = speed;
        this.range = detectionRange;
        setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    private boolean canFleeFrom(Player player) {
        return player != null && player.isAlive() && !player.isCreative();
    }

    @Override
    public boolean canUse() {
        player = mob.level().getNearestPlayer(mob, range);

        if (!canFleeFrom(player)) {
            player = null;
            return false;
        }
        target = findEscapePosition();
        return target != null;
    }

    @Override
    public boolean canContinueToUse() {
        return canFleeFrom(player) && mob.level().getNearestPlayer(mob, range) != null && (target == null || !mob.blockPosition().closerThan(target, 1.5D));
    }

    @Override
    public void start() {
        repathCooldown = 0;
        move();
    }

    @Override
    public void tick() {
        if (--repathCooldown > 0) return;
        repathCooldown = 3;
        BlockPos newTarget = findEscapePosition();

        if (newTarget != null && !newTarget.equals(target)) {
            target = newTarget;
            move();
        }
    }

    private void move() {
        if (target == null) return;
        mob.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), speed * mob.getAttributeValue(Attributes.MOVEMENT_SPEED) * 5);
    }

    private BlockPos findEscapePosition() {
        Vec3 humanHater = mob.position().subtract(player.position()).normalize();
        List<BlockPos> candidates = new ArrayList<>(420);

        for (int i = 5; i <= 14; i++) {
            Vec3 forward = humanHater.scale(i);

            for (int j = -3; j <= 3; j++) {
                Vec3 offset = mob.position().add(forward).add(new Vec3(-humanHater.z, 0, humanHater.x).scale(j));

                for (int y = 0; y <= 5; y++) {
                    candidates.add(BlockPos.containing(
                            offset.add(random.nextDouble() - 0.5, 0, random.nextDouble() - 0.5)
                    ).above(y));
                }
            }
        }

        candidates.sort(Comparator.comparingDouble(this::posWeight));

        for (BlockPos pos : candidates) {
            if (!validPosition(pos)) continue;

            if (mob.getNavigation().createPath(pos, 1) != null) {
                return pos;
            }
        }
        return null;
    }

    private double posWeight(BlockPos pos) {
        Vec3 away = mob.position().subtract(player.position()).normalize();

        return -(away.dot(Vec3.atCenterOf(pos).subtract(mob.position()).normalize()) * 1000 + pos.distSqr(player.blockPosition())
                + (pos.getY() - mob.getBlockY()) * 15 + (isOneBlockGap(pos) ? 500 : 0));
    }

    private boolean validPosition(BlockPos pos) {
        return mob.level().getBlockState(pos).isAir() && mob.level().getBlockState(pos.above()).isAir() && mob.level().getBlockState(pos.below()).isSolid();
    }

    private boolean isOneBlockGap(BlockPos pos) {
        return mob.level().getBlockState(pos).isAir() && mob.level().getBlockState(pos.above()).isAir() && !mob.level().getBlockState(pos.above(2)).isAir();
    }

    @Override
    public void stop() {
        player = null;
        target = null;
        mob.getNavigation().stop();
    }
}