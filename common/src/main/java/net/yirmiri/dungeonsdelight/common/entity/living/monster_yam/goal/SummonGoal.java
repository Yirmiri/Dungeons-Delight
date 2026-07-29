package net.yirmiri.dungeonsdelight.common.entity.living.monster_yam.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.entity.living.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

import java.util.ArrayList;
import java.util.List;

public class SummonGoal extends Goal {
    private final MonsterYamEntity monsterYam;
    private final List<SummonEntry> summonMobs;
    private final int summonMin;
    private final int summonedMax;
    private int summonedAmount;
    private final List<Integer> summonTimes = new ArrayList<>();
    private final int summonCooldown = 300;

    public SummonGoal(MonsterYamEntity yam, List<SummonEntry> summonMobs, int summonMin, int summonedMax) {
        this.monsterYam = yam;
        this.summonMobs = summonMobs;
        this.summonMin = summonMin;
        this.summonedMax = summonedMax;
    }

    public boolean canUse() {
        return monsterYam.getTarget() != null && monsterYam.getSummonCooldown() <= 0 && !monsterYam.getIsSummoning() && monsterYam.getRandom().nextInt(120) == 0;
    }

    public boolean canContinueToUse() {
        return monsterYam.getIsSummoning();
    }

    public void start() {
        this.summonedAmount = 0;
        this.summonTimes.clear();

        for (int i = 0; i < summonMin + monsterYam.getRandom().nextInt(summonedMax - summonMin + 1); i++) {
            this.summonTimes.add(monsterYam.getRandom().nextInt(70));
        }

        this.summonTimes.sort(Integer::compareTo);

        monsterYam.setIsSummoning(true);
        monsterYam.setSummonTimer(70);
        monsterYam.getNavigation().stop();
    }

    public void tick() {
        monsterYam.getNavigation().stop();
        LivingEntity target = monsterYam.getTarget();

        if (target != null) {
            monsterYam.getLookControl().setLookAt(target, 30.0F, 30.0F);
        }

        for (int i = summonedAmount; i < summonTimes.size(); i++) {
            if (summonTimes.get(i) <= 70 - monsterYam.getSummonTimer()) {
                spawnMob();
                summonedAmount++;
            } else {
                break;
            }
        }

        if (monsterYam.getSummonTimer() > 0) {
            monsterYam.setSummonTimer(monsterYam.getSummonTimer() - 1);
        } else {
            monsterYam.setIsSummoning(false);
            monsterYam.setSummonCooldown(summonCooldown);
        }
    }

    private void spawnMob() {
        if (!(monsterYam.level() instanceof ServerLevel serverlevel)) return;
        LivingEntity target = monsterYam.getTarget();
        Vec3 center;

        if (target != null) {
            Vec3 facing = target.getLookAngle().normalize();

            if (monsterYam.getRandom().nextFloat() < 0.66F) {
                center = target.position().subtract(facing.scale(3.0D + monsterYam.getRandom().nextDouble() * 5.0D));
            } else {
                double angle = monsterYam.getRandom().nextDouble() * Math.PI * 2.0D;
                double distance = 2.0D + monsterYam.getRandom().nextDouble() * 4.0D;
                center = monsterYam.position().add(Math.cos(angle) * distance, 0.0D, Math.sin(angle) * distance);
            }
        } else {
            double angle = monsterYam.getRandom().nextDouble() * Math.PI * 2.0D;
            double distance = 2.0D + monsterYam.getRandom().nextDouble() * 4.0D;
            center = monsterYam.position().add(Math.cos(angle) * distance, 0.0D, Math.sin(angle) * distance);
        }

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos((int) Math.floor(center.x), (int) Math.floor(center.y), (int) Math.floor(center.z));

        while (pos.getY() > serverlevel.getMinBuildHeight() && !serverlevel.getBlockState(pos).isSolid()) {
            pos.move(Direction.DOWN);
        }
        if (!serverlevel.getBlockState(pos).isSolid()) return;

        int combinedWeights = 0;
        for (SummonEntry entry : summonMobs) {
            combinedWeights += entry.weight();
        }
        int roll = monsterYam.getRandom().nextInt(combinedWeights);
        SummonEntry chosen = summonMobs.get(0);

        for (SummonEntry entry : summonMobs) {
            roll -= entry.weight();

            if (roll < 0) {
                chosen = entry;
                break;
            }
        }
        Mob mob = chosen.type().create(monsterYam.level());

        if (mob != null) {
            double x = pos.getX() + 0.5D;
            double y = pos.getY() + 1.0D;
            double z = pos.getZ() + 0.5D;

            mob.moveTo(x, y, z, monsterYam.getRandom().nextFloat() * 360.0F, 0.0F);
            mob.finalizeSpawn(serverlevel, monsterYam.level().getCurrentDifficultyAt(pos), MobSpawnType.MOB_SUMMONED, null, null);
            serverlevel.addFreshEntity(mob);
            Vec3 spawnPos = mob.position();
            double angle = monsterYam.getRandom().nextDouble() * Math.PI * 2.0D;

            serverlevel.sendParticles(ParticleTypes.POOF, spawnPos.x, spawnPos.y + 0.5D, spawnPos.z,
                    12, 0.25D, 0.25D, 0.25D, 0.02D);

            for (int i = 0; i < 6; i++) {
                double radius = mob.getBbWidth() * 0.6D + 0.15D;

                serverlevel.sendParticles(DDParticles.LIVING_FLAME.get(),
                        spawnPos.x + Math.cos(angle) * radius,
                        spawnPos.y + mob.getBbHeight() * (0.25D + monsterYam.getRandom().nextDouble() * 0.5D),
                        spawnPos.z + Math.sin(angle) * radius, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }

            for (int i = 0; i < 4; i++) {
                double horizontalSpeed = 0.25D + monsterYam.getRandom().nextDouble() * 0.45D;
                double verticalSpeed = 0.8D + monsterYam.getRandom().nextDouble() * 0.8D;

                serverlevel.sendParticles(DDParticles.ROTTEN_RESIDUE.get(), spawnPos.x, spawnPos.y, spawnPos.z, 1,
                        Math.cos(angle) * horizontalSpeed, verticalSpeed, Math.sin(angle) * horizontalSpeed, 0.0D);
            }
        }
    }

    public void stop() {
        monsterYam.setIsSummoning(false);
    }

    public record SummonEntry(EntityType<? extends Mob> type, int weight) {
    }
}