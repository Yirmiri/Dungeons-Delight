package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class GlowBerryGelatinBlock extends FeastBlock {
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (state) -> 3 * state.getValue(FeastBlock.SERVINGS);

    private static final VoxelShape PLATE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);
    private static final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0, 2.0, 2.0, 14.0, 8.0, 14.0), BooleanOp.OR);

    public GlowBerryGelatinBlock(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }
}
