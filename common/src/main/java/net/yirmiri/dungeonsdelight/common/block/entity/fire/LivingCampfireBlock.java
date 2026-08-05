package net.yirmiri.dungeonsdelight.common.block.entity.fire;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

import java.util.stream.Stream;

public class LivingCampfireBlock extends CampfireBlock {
    protected static final VoxelShape SHAPE = Stream.of(
            Block.box(0, 5, -0.5, 16, 17, 0.5),
            Block.box(0, 5, 15.5, 16, 17, 16.5),
            Block.box(15.5, 5, 0, 16.5, 17, 16),
            Block.box(-0.5, 5, 0, 0.5, 17, 16),
            Block.box(0, 0, 0, 16, 5, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();;

    public LivingCampfireBlock(Properties properties) {
        super(false, 1, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            if (random.nextInt(10) == 0) {
                level.playLocalSound((double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + random.nextFloat(), random.nextFloat() * 0.7F + 0.6F, false);
            }

            if (random.nextInt(5) == 0) {
                for(int i = 0; i < random.nextInt(1) + 1; ++i) {
                    level.addParticle(DDParticles.LIVING_LAVA.get(), (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, random.nextFloat() / 2.0F, 5.0E-5, random.nextFloat() / 2.0F);
                }
            }
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        boolean isLit = level.getBlockState(pos).getValue(LivingCampfireBlock.LIT);
        if (isLit && (!entity.isSteppingCarefully() || entity.fireImmune()) && entity instanceof LivingEntity) {
            if (!entity.fireImmune()) {
                entity.hurt(DDDamageTypes.getDamageSource(level, DDDamageTypes.IN_LIVING_FIRE), 0.5F);
                ((LivingEntity) entity).hurtTime = 40;
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
        super.entityInside(state, level, pos, entity);
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
