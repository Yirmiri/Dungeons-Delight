package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.common.block.entity.LivingCampfireBlockEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

public class LivingCampfireBlock extends CampfireBlock {
    public LivingCampfireBlock(Properties properties) {
        super(false, 1, properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!entity.fireImmune() && state.getValue(CampfireBlock.LIT)) {
            if (entity instanceof Player player && player.totalExperience > 0 && player.hurtTime == 0 && player.isAlive() && !player.getAbilities().instabuild) {
                player.giveExperiencePoints(-3);
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.75F, -1.0F);
            }
        }
        super.entityInside(state, level, pos, entity);
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
