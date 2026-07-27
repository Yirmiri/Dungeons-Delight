package net.yirmiri.dungeonsdelight.common.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

public class SoulPeppersCropBlock extends BushBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    public static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0F, 0.0F, 0.0F, 16.0F, 2.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F), Block.box(0.0F, 0.0F, 0.0F, 16.0F, 6.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 8.0F, 16.0F), Block.box(0.0F, 0.0F, 0.0F, 16.0F, 10.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 12.0F, 16.0F), Block.box(0.0F, 0.0F, 0.0F, 16.0F, 14.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F)};

    public SoulPeppersCropBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any()
                .setValue(AGE, 0)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.SOUL_SAND);
    }

    public int getMaxAge() {
        return 7;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < getMaxAge();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        int ageRate = 12;

        if (nearbyLava(level, pos)) {
            ageRate = 8;
        }

        if (age < getMaxAge() && random.nextInt(ageRate) == 0) {
            state = state.setValue(AGE, age + 1);
            level.setBlock(pos, state, 2);
        }
    }

    private static boolean nearbyLava(LevelReader level, BlockPos pos) {
        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (level.getFluidState(blockpos).is(FluidTags.LAVA)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(DDItems.SOUL_PEPPER_SEEDS.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public static boolean emitsLight(BlockState state) {
        return state.getValue(AGE) < 4;
    }
}
