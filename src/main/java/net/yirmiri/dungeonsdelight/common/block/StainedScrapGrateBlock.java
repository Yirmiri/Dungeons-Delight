package net.yirmiri.dungeonsdelight.common.block;

import net.azurune.runiclib.common.publicized.PublicHalfTransparentBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StainedScrapGrateBlock extends PublicHalfTransparentBlock {
    public StainedScrapGrateBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext ctx) {
        if (ctx instanceof EntityCollisionContext entityCtx && entityCtx.getEntity() instanceof ItemEntity) {
            return Shapes.empty();
        }
        return super.getCollisionShape(state, worldIn, pos, ctx);
    }
}
