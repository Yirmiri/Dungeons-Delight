package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class PolterghastPizzaBlock extends EXPPieBlock {
    protected static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 2, 16);

    public PolterghastPizzaBlock(Properties properties, int experience, Supplier<Item> pieSlice) {
        super(properties, experience, pieSlice);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
