package net.yirmiri.dungeonsdelight.common.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.common.block.crops.RotbulbCropBlock;
import net.yirmiri.dungeonsdelight.common.block.crops.RottenCropBlock;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

public class RunToRotbulbGoal extends MoveToBlockGoal {
    private final PathfinderMob mob;

    public RunToRotbulbGoal(PathfinderMob mob) {
        super(mob, 1.25F, 16, 6);
        this.mob = mob;
    }

    private boolean canGoToRotbulb() {
        return !mob.hasEffect(DDEffects.PUTRID_SCENT.get()) && !mob.hasEffect(MobEffects.DAMAGE_BOOST) && !mob.hasEffect(MobEffects.MOVEMENT_SPEED)
                && !mob.hasEffect(DDEffects.DECISIVE.get()) && !mob.hasEffect(DDEffects.POUNCING.get()) && !mob.hasEffect(MobEffects.DAMAGE_RESISTANCE);
    }

    @Override
    public boolean canUse() {
        return super.canUse() && canGoToRotbulb();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && canGoToRotbulb();
    }

    @Override
    public double acceptedDistance() {
        return 2F;
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        BlockState posState = level.getBlockState(pos);

        if (posState.getBlock() instanceof RotbulbCropBlock && !(posState.getValue(RottenCropBlock.AGE) >= 8)) {
            return false;
        }

        return posState.is(DDBlocks.ROTBULB.get()) || posState.is(DDBlocks.WILD_ROTBULB.get());
    }
}
