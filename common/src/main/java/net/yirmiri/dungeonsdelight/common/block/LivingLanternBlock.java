package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

import java.util.stream.Stream;

public class LivingLanternBlock extends LanternBlock {
    public static final BooleanProperty STAINED = BooleanProperty.create("stained");
    protected static final VoxelShape SHAPE = Stream.of(Block.box(4, 0, 4, 12, 8, 12), Block.box(6, 8, 6, 10, 9, 10), Block.box(5, 9, 5, 11, 10, 11)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public LivingLanternBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HANGING, false)
                .setValue(WATERLOGGED, false)
                .setValue(STAINED, true)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAINED, HANGING, WATERLOGGED);
    }

    private void updateType(BlockState state, LevelAccessor level, BlockPos pos) {
        if (level.getBlockState(pos.above()).getBlock() == Blocks.CHAIN) {
            state.setValue(STAINED, false);
        }
        if (level.getBlockState(pos.above()).getBlock() == DDBlocks.STAINED_SCRAP_CHAIN.get()) {
            state.setValue(STAINED, true);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState state = this.defaultBlockState();
        BlockState above = ctx.getLevel().getBlockState(ctx.getClickedPos().above());

        if (above.is(Blocks.CHAIN)) {
            state = state.setValue(STAINED, false);
        }

        for (Direction direction : ctx.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                state = state.setValue(HANGING, direction == Direction.UP);

                if (state.canSurvive(ctx.getLevel(), ctx.getClickedPos())) {
                    return state.setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER);
                }
            }
        }
        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        updateType(state, level, pos);
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }
}
