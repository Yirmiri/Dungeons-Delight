package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

public class EmbeddedEggsBlock extends Block {
    public static IntegerProperty SCULKING = IntegerProperty.create("sculking", 0, 4);

    public EmbeddedEggsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(super.defaultBlockState().setValue(SCULKING, 0));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SCULKING);
        super.createBlockStateDefinition(builder);
    }

    public int getMaxCompostingStage() {
        return 4;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) {
            float chance = 0.0F;
            int maxLight = 0;

            for (BlockPos neighborPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
                BlockState neighborState = level.getBlockState(neighborPos);
                if (neighborState.is(DDTags.BlockT.SCULKING_ACTIVATORS) || neighborState.is(DDTags.BlockT.MONSTER_HEAT_SOURCES)) {
                    chance += 0.02F;
                }

                int light = level.getBrightness(LightLayer.SKY, neighborPos.above());
                if (light > maxLight && level.isNight()) {
                    maxLight = light;
                }
            }
//TODO: grow faster based on moon phase
            chance += maxLight > 7 ? 0.1F : 0.05F;
            if (level.getRandom().nextFloat() <= chance) {
                if (state.getValue(SCULKING) == this.getMaxCompostingStage()) {
                    level.setBlock(pos, DDBlocks.HEAP_OF_ANCIENT_EGGS.get().defaultBlockState(), 3);
                } else {
                    level.setBlock(pos, state.setValue(SCULKING, state.getValue(SCULKING) + 1), 3);
                }
            }
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return this.getMaxCompostingStage() + 1 - blockState.getValue(SCULKING);
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) == 0) {
            level.addParticle(ParticleTypes.MYCELIUM, (double)pos.getX() + (double)random.nextFloat(), (double)pos.getY() + 1.1, (double)pos.getZ() + (double)random.nextFloat(), 0.0, 0.0, 0.0);
        }
    }
}
