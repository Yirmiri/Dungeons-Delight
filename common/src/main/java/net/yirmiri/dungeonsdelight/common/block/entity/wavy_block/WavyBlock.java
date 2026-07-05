package net.yirmiri.dungeonsdelight.common.block.entity.wavy_block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WavyBlock extends Block implements EntityBlock {
    public static final BooleanProperty WAVY = BooleanProperty.create("wavy");
    private static final VoxelShape SHAPE = Shapes.join(Block.box(0, 0, 1, 16, 16, 16),
            Block.box(0, 0, 0, 16, 16, 0.9899999999999984), BooleanOp.OR);

    public WavyBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(WAVY, true)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WAVY);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        //if (state.getValue(WAVY)) {
            return SHAPE;
        //} else return Shapes.block();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WavyBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        if (state.getValue(WAVY)) {
            return RenderShape.ENTITYBLOCK_ANIMATED;
        } else return RenderShape.MODEL;
    }
}
