package net.yirmiri.dungeonsdelight.common.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

public class WildCropBlock extends BushBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0F, 0.0F, 0.0F, 16.0F, 2.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F), Block.box(0.0F, 0.0F, 0.0F, 16.0F, 6.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 8.0F, 16.0F), Block.box(0.0F, 0.0F, 0.0F, 16.0F, 10.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 12.0F, 16.0F), Block.box(0.0F, 0.0F, 0.0F, 16.0F, 14.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F)};

    public WildCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[5];
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return super.mayPlaceOn(state, getter, pos) || state.is(DDTags.BlockT.WILD_CROP_GROWABLE_ON);
    }
}
