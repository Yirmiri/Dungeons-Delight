package net.yirmiri.dungeonsdelight.common.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlightedBeetrootBlock extends GenericRottenCropBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0F, 0.0F, 0.0F, 16.0F, 2.0F, 16.0F), Block.box(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F), Block.box(0.0F, 0.0F, 0.0F, 16.0F, 6.0F, 16.0F), Block.box(0.0F, 0.0F, 0.0F, 16.0F, 8.0F, 16.0F)};

    public BlightedBeetrootBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext ctx) {
        return SHAPE_BY_AGE[3];
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(Items.BEETROOT_SEEDS);
    }
}
