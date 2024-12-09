package net.yirmiri.dungeonsdelight.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.registry.DDParticles;
import net.yirmiri.dungeonsdelight.util.DDDamageTypes;
import vectorwing.farmersdelight.common.block.StoveBlock;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;

public class DungeonStoveBlock extends StoveBlock {
    public DungeonStoveBlock(Settings properties) {
        super(properties);
    }

    @Override
    public void onSteppedOn(World level, BlockPos pos, BlockState state, Entity entity) {
        boolean isLit = level.getBlockState(pos).get(StoveBlock.LIT);
        if (isLit && !entity.bypassesSteppingEffects() && entity instanceof LivingEntity) {
            entity.damage(ModDamageTypes.getSimpleDamageSource(level, DDDamageTypes.DUNGEON_STOVE), 3.0F);
        }

        super.onSteppedOn(level, pos, state, entity);
    }

    @Override
    public void randomDisplayTick(BlockState stateIn, World level, BlockPos pos, Random rand) {
        if (stateIn.get(CampfireBlock.LIT)) {
            double x = (double) pos.getX() + 0.5D;
            double y = pos.getY();
            double z = (double) pos.getZ() + 0.5D;
            if (rand.nextInt(10) == 0) {
                level.playSound(x, y, z, ModSounds.BLOCK_STOVE_CRACKLE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = stateIn.get(HorizontalFacingBlock.FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double horizontalOffset = rand.nextDouble() * 0.6D - 0.3D;
            double xOffset = direction$axis == Direction.Axis.X ? (double) direction.getOffsetX() * 0.52D : horizontalOffset;
            double yOffset = rand.nextDouble() * 6.0D / 16.0D;
            double zOffset = direction$axis == Direction.Axis.Z ? (double) direction.getOffsetZ() * 0.52D : horizontalOffset;
            level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
            level.addParticle(DDParticles.DUNGEON_FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
        }
    }
}
