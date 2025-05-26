package net.yirmiri.dungeonsdelight.common.block;

import net.azurune.runiclib.common.publicized.PublicHalfTransparentBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StainedScrapGrateBlock extends PublicHalfTransparentBlock implements SimpleWaterloggedBlock {
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public StainedScrapGrateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext ctx) {
        if (ctx instanceof EntityCollisionContext entityCtx && entityCtx.getEntity() instanceof ItemEntity) {
            return Shapes.empty();
        }
        return super.getCollisionShape(state, worldIn, pos, ctx);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(WATERLOGGED);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor accessor, BlockPos pos, BlockPos pos1) {
        if (state.getValue(WATERLOGGED)) {
            accessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
        }
        return super.updateShape(state, direction, state1, accessor, pos, pos1);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
}
