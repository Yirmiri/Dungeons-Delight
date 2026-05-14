package net.yirmiri.dungeonsdelight.common.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

public class BleetsBlock extends RottenCropBlock implements BonemealableBlock {
    public static final int MAX_AGE = 5;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public BleetsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, HALF);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return DDItems.BLEET_SEEDS.get();
    }

    private boolean isMature(BlockState state) {
        return state.getValue(AGE) >= MAX_AGE;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER && !isMature(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(AGE, 0);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                preventCreativeDropFromBottomPart(level, pos, state, player);
            } else {
                dropResources(state, level, pos, null, player, player.getMainHandItem());
            }
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        if (!isLowerHalf(state)) {
            return world.getBlockState(pos.below()).is(this);
        } else {
            int age = state.getValue(AGE);

            if (age >= 4) {
                BlockState above = world.getBlockState(pos.above());
                return world.getBlockState(pos.below()).is(DDBlocks.TERROR_PRETA.get())
                        && isUpperHalf(above);
            }

            return world.getBlockState(pos.below()).is(DDBlocks.TERROR_PRETA.get());
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    protected static float getGrowthSpeed(Block block, BlockGetter level, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState state = level.getBlockState(blockpos.offset(i, 0, j));
                if (state.is(DDBlocks.TERROR_PRETA.get())) {
                    f1 = 1.0F;
                    if (state.getValue(TerrorPretaBlock.MOISTURE) > 0 && state.getValue(TerrorPretaBlock.SHADED) > 6) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = level.getBlockState(blockpos3).is(block) || level.getBlockState(blockpos4).is(block);
        boolean flag1 = level.getBlockState(blockpos1).is(block) || level.getBlockState(blockpos2).is(block);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = level.getBlockState(blockpos3.north()).is(block) || level.getBlockState(blockpos4.north()).is(block) || level.getBlockState(blockpos4.south()).is(block) || level.getBlockState(blockpos3.south()).is(block);
            if (flag2) {
                f /= 2.0F;
            }
        }
        return f;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, net.minecraft.util.RandomSource random) {
        if (random.nextInt((int) (25.0F / getGrowthSpeed(this, world, pos)) + 1) == 0) {
            tryGrow(world, state, pos);
        }
    }

    private void tryGrow(ServerLevel world, BlockState state, BlockPos pos) {
        int i = Math.min(state.getValue(AGE) + 1, MAX_AGE);
        if (canGrow(world, pos, state, i)) {
            world.setBlock(pos, state.setValue(AGE, i), 2);
            if (i >= 3) {
                BlockPos blockPos = pos.above();
                world.setBlock(blockPos, defaultBlockState().setValue(AGE, i).setValue(HALF, DoubleBlockHalf.UPPER), 3);
            }
        }
    }

    private static boolean canGrowAt(LevelReader world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isAir() || blockState.is(DDBlocks.BLEETS.get());
    }

    private static boolean isLowerHalf(BlockState state) {
        return state.is(DDBlocks.BLEETS.get()) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    private static boolean isUpperHalf(BlockState state) {
        return state.is(DDBlocks.BLEETS.get()) && state.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    private boolean canGrow(LevelReader world, BlockPos pos, BlockState state, int age) {
        return !isMature(state) && (age < 3 || canGrowAt(world, pos.above()));
    }

    private BleetsBlock.LowerHalfContext getLowerHalfContext(LevelReader world, BlockPos pos, BlockState state) {
        if (isLowerHalf(state)) {
            return new BleetsBlock.LowerHalfContext(pos, state);
        } else {
            BlockPos blockPos = pos.below();
            BlockState blockState = world.getBlockState(blockPos);
            return isLowerHalf(blockState) ? new BleetsBlock.LowerHalfContext(blockPos, blockState) : null;
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
        BleetsBlock.LowerHalfContext lowerHalfContext = getLowerHalfContext(world, pos, state);
        return lowerHalfContext != null && !isMature(lowerHalfContext.state);
    }

    @Override
    public boolean isBonemealSuccess(Level world, net.minecraft.util.RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        BleetsBlock.LowerHalfContext lowerHalfContext = getLowerHalfContext(world, pos, state);
        if (lowerHalfContext != null) {
            tryGrow(world, lowerHalfContext.state, lowerHalfContext.pos);
        }
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(world, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, tool);
    }

    @Override
    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }

    private record LowerHalfContext(BlockPos pos, BlockState state) {
    }
}