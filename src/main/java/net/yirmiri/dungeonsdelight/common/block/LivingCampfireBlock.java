package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.block.entity.DungeonStoveBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.LivingCampfireBlockEntity;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.utility.ItemUtils;

public class LivingCampfireBlock extends CampfireBlock {
    public LivingCampfireBlock(Properties properties) {
        super(false, 1, properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        boolean isLit = level.getBlockState(pos).getValue(LivingCampfireBlock.LIT);
        if (isLit && (!entity.isSteppingCarefully() || entity.fireImmune()) && entity instanceof LivingEntity) {
            if (!entity.fireImmune()) {
                entity.hurt(ModDamageTypes.getSimpleDamageSource(level, DDDamageTypes.LIVING_ESSENCE), 2.0F);
            }
            if (entity instanceof Player player && player.totalExperience > 0 && player.isAlive() && !player.getAbilities().instabuild) {
                if (level.getBlockEntity(pos) instanceof LivingCampfireBlockEntity blockEntity && blockEntity.canStoreExperience()) {
                    if (!level.isClientSide) {
                        blockEntity.addExperience(1);
                    }
                    player.giveExperiencePoints(-1);
                    player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.75F, -1.0F);
                }
            }
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof LivingCampfireBlockEntity blockEntity) {
                if (level.isClientSide || blockEntity.getStoredExperience() <= 0) return;

                ExperienceOrb.award((ServerLevel) level, Vec3.atCenterOf(pos), (blockEntity.getStoredExperience() * 3) / 4);
                blockEntity.setStoredExperience(0);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LivingCampfireBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return state.getValue(LIT) ? createTickerHelper(type, DDBlockEntities.LIVING_CAMPFIRE.get(), LivingCampfireBlockEntity::particleTick) : null;
        } else {
            return state.getValue(LIT) ? createTickerHelper(type, DDBlockEntities.LIVING_CAMPFIRE.get(), LivingCampfireBlockEntity::cookTick) : createTickerHelper(type, DDBlockEntities.LIVING_CAMPFIRE.get(), LivingCampfireBlockEntity::cooldownTick);
        }
    }
}
