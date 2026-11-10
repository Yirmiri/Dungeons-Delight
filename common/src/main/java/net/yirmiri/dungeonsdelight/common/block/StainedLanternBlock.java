package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

public class StainedLanternBlock extends Block {
    public StainedLanternBlock(Properties properties) { super(properties); }
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) { if (level.random.nextFloat() > 0.8F) spawnParticles(level, pos); }

    private static void spawnParticles(Level level, BlockPos pos) {
        double offset = 0.5625;
        RandomSource randomsource = level.random;

        for (Direction dir : Direction.values()) {
            BlockPos blockpos = pos.relative(dir);
            if (!level.getBlockState(blockpos).isSolidRender(level, blockpos)) {
                Direction.Axis axi = dir.getAxis();
                double xx = (axi == Direction.Axis.X) ? 0.5 + offset * (double)dir.getStepX() : randomsource.nextDouble();
                double yy = (axi == Direction.Axis.Y) ? 0.5 + offset * (double)dir.getStepY() : randomsource.nextDouble();
                double zz = (axi == Direction.Axis.Z) ? 0.5 + offset * (double)dir.getStepZ() : randomsource.nextDouble();
                level.addParticle(
                        DDParticles.LIVING_FLAME.get(),
                        (double)pos.getX() + xx,
                        (double)pos.getY() + yy,
                        (double)pos.getZ() + zz,
                        0.0,
                        0.0,
                        0.0
                );
            }
        }
    }
}
