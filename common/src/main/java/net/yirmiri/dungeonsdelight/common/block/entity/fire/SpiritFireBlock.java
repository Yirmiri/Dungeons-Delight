package net.yirmiri.dungeonsdelight.common.block.entity.fire;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;

public class SpiritFireBlock extends BaseFireBlock implements EntityBlock {
    public SpiritFireBlock(BlockBehaviour.Properties properties) {
        super(properties, 2.0F);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor accessor, BlockPos pos, BlockPos pos1) {
        return this.canSurvive(state, accessor, pos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return canSurviveOnBlock(reader.getBlockState(pos.below()));
    }

//    public static boolean canSurviveOnBlock(BlockState state) {
//        return state.is(DDTags.BlockT.LIVING_FIRE_BASE_BLOCKS);
//    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(BlockTags.SOUL_FIRE_BASE_BLOCKS);
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return false;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Player player) {
            if (player.totalExperience > 0 && player.isAlive() && !player.getAbilities().instabuild) {
                if (level.getBlockEntity(pos) instanceof LivingFireBlockEntity blockEntity && blockEntity.canStoreExperience()) {
                    if (level.getGameTime() % 10 == 0 && !level.isClientSide) {
                        player.giveExperiencePoints(-12);
                        player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.75F, -1.0F);

                        blockEntity.addExperience(12);

                        player.hurt(DDDamageTypes.getDamageSource(level, DDDamageTypes.LIFE_STEAL), 1.0F);
                    }
                }
            }
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof LivingFireBlockEntity blockEntity) {
                if (level.isClientSide || blockEntity.getStoredExperience() <= 0) return;

                ExperienceOrb.award((ServerLevel) level, Vec3.atCenterOf(pos), (blockEntity.getStoredExperience() * 3) / 4);
                blockEntity.setStoredExperience(0);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LivingFireBlockEntity(pos, state);
    }
}