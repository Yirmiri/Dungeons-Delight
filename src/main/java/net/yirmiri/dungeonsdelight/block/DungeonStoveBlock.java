package net.yirmiri.dungeonsdelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yirmiri.dungeonsdelight.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.registry.DDDamageTypes;
import net.yirmiri.dungeonsdelight.registry.DDParticles;
import vectorwing.farmersdelight.common.block.StoveBlock;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;

import javax.annotation.Nullable;

public class DungeonStoveBlock extends StoveBlock {
    public DungeonStoveBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        boolean isLit = level.getBlockState(pos).getValue(LIT);
        if (isLit && !entity.fireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            entity.hurt(ModDamageTypes.getSimpleDamageSource(level, DDDamageTypes.DUNGEON_STOVE_BURN), 2.0F);
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, RandomSource rand) {
        if (stateIn.getValue(CampfireBlock.LIT)) {
            double x = pos.getX() + 0.5;
            double y = pos.getY();
            double z = pos.getZ() + 0.5;
            if (rand.nextInt(10) == 0) {
                level.playLocalSound(x, y, z, ModSounds.BLOCK_STOVE_CRACKLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = stateIn.getValue(HorizontalDirectionalBlock.FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double horizontalOffset = rand.nextDouble() * 0.6 - 0.3;
            double xOffset = direction$axis == Direction.Axis.X ? direction.getStepX() * 0.52 : horizontalOffset;
            double yOffset = rand.nextDouble() * 6.0 / 16.0;
            double zOffset = direction$axis == Direction.Axis.Z ? direction.getStepZ() * 0.52 : horizontalOffset;
            level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
            level.addParticle(DDParticles.DUNGEON_FLAME.get(), x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
        }
    }

    @Override @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return DDBlockEntities.DUNGEON_STOVE.get().create(pos, state);
    }
}
