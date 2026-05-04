//
//Based on the original version from Farmer's Delight here:
//https://github.com/vectorwing/FarmersDelight/blob/1.20/src/main/java/vectorwing/farmersdelight/common/block/StoveBlock.java
//

package net.yirmiri.dungeonsdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.common.block.entity.DungeonStoveBlockEntity;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import vectorwing.farmersdelight.common.block.AbstractStoveBlock;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;

public class DungeonStoveBlock extends AbstractStoveBlock {
    private static final VoxelShape GRILLING_AREA = Block.box(3.0F, 0.0F, 3.0F, 13.0F, 1.0F, 13.0F);

    public DungeonStoveBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(DungeonStoveBlock::new);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DungeonStoveBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide && state.getValue(LIT) ? createTickerHelper(blockEntityType, DDBlockEntities.DUNGEON_STOVE.get(),
                DungeonStoveBlockEntity::particleTick) : createStoveTicker(level, blockEntityType, DDBlockEntities.DUNGEON_STOVE.get());
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double x = (double) pos.getX() + (double) 0.5F;
            double y = pos.getY();
            double z = (double) pos.getZ() + (double) 0.5F;
            if (random.nextInt(10) == 0) {
                level.playLocalSound(x, y, z, ModSounds.BLOCK_STOVE_CRACKLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = state.getValue(HorizontalDirectionalBlock.FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double horizontalOffset = random.nextDouble() * 0.6 - 0.3;
            double xOffset = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : horizontalOffset;
            double yOffset = random.nextDouble() * (double)6.0F / (double)16.0F;
            double zOffset = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : horizontalOffset;
            level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0F, 0.0F, 0.0F);
            level.addParticle(DDParticles.LIVING_FLAME.get(), x + xOffset, y + yOffset, z + zOffset, 0.0F, 0.0F, 0.0F);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        boolean isLit = level.getBlockState(pos).getValue(DungeonStoveBlock.LIT);
        if (entity.getBoundingBox().intersects(GRILLING_AREA.bounds().move(pos.above()))) {
            if (isLit && (!entity.isSteppingCarefully() || entity.fireImmune()) && entity instanceof LivingEntity) {
                if (!entity.fireImmune()) {
                    entity.hurt(ModDamageTypes.getSimpleDamageSource(level, DDDamageTypes.DUNGEON_STOVE_BURN), 2.0F);
                }
                if (entity instanceof Player player && player.totalExperience > 0 && player.isAlive() && !player.getAbilities().instabuild) {
                    if (level.getBlockEntity(pos) instanceof DungeonStoveBlockEntity stoveBlockEntity && stoveBlockEntity.canStoreExperience()) {
                        if (!level.isClientSide) {
                            stoveBlockEntity.addExperience(1);
                        }
                        player.giveExperiencePoints(-1);
                        player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.75F, -1.0F);
                    }
                }
            }
        }
        super.stepOn(level, pos, state, entity);
    }
}
