package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class MonsterPotBlock extends HorizontalDirectionalBlock {
    public MonsterPotBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        return Stream.of(Block.box(2, 0, 2, 14, 6, 14), Block.box(3, 6, 3, 13, 7, 13),
                Block.box(2, 7, 2, 14, 8, 14), Block.box(13, 4, 5, 15, 6, 11),
                Block.box(1, 4, 5, 3, 6, 11)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
