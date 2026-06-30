package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class EmbeddedEggsBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;

    public EmbeddedEggsBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any()
                .setValue(getAgeProperty(), 0)
        );
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getAge(BlockState state) {
        return state.getValue(getAgeProperty());
    }

    public static int getMaxAge() {
        return 4;
    }

    public final boolean isMaxAge(BlockState state) {
        return getAge(state) >= getMaxAge();
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !isMaxAge(state);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        float chance = 0.07F;
        if (isMaxAge(state)) return;

        chance += (15 - level.getMaxLocalRawBrightness(pos)) * 0.0025F;

        if (level.isNight()) {
            chance += level.getMoonBrightness() * 0.02F;
        }

        if (random.nextFloat() < chance) {
            level.setBlock(pos, state.setValue(AGE, getAge(state) + 1), 2);
        }
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
        if (dropExperience && isMaxAge(state)) {
            tryDropExperience(level, pos, stack, ConstantInt.of(1));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
