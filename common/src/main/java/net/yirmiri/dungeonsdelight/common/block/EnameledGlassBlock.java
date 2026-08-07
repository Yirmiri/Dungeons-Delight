package net.yirmiri.dungeonsdelight.common.block;

import net.azurune.runiclib.common.publicized.PublicHalfTransparentBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.Locale;

public class EnameledGlassBlock extends PublicHalfTransparentBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<ConnectionType> CONNECTION = EnumProperty.create("connection", ConnectionType.class);
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public EnameledGlassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(CONNECTION, ConnectionType.SINGLE)
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH)
        );
    }

    private BlockState updateConnectionState(BlockState state, LevelAccessor level, BlockPos pos) {
        boolean up = level.getBlockState(pos.above()).getBlock() instanceof EnameledGlassBlock;
        boolean down = level.getBlockState(pos.below()).getBlock() instanceof EnameledGlassBlock;

        boolean north = level.getBlockState(pos.north()).getBlock() instanceof EnameledGlassBlock;
        boolean east = level.getBlockState(pos.east()).getBlock() instanceof EnameledGlassBlock;
        boolean south = level.getBlockState(pos.south()).getBlock() instanceof EnameledGlassBlock;
        boolean west = level.getBlockState(pos.west()).getBlock() instanceof EnameledGlassBlock;

        boolean horizontalLeft = north || west;
        boolean horizontalRight = south || east;

        ConnectionType connectionType;

        if (!up && !down && !horizontalLeft && !horizontalRight) {
            connectionType = ConnectionType.SINGLE;
        } else if (up && down && horizontalLeft && horizontalRight) {
            connectionType = ConnectionType.CENTER;
        } else if (up && !down && horizontalLeft && horizontalRight) {
            connectionType = ConnectionType.CENTER_BOTTOM;
        } else if (!up && down && horizontalLeft && horizontalRight) {
            connectionType = ConnectionType.CENTER_TOP;
        } else if (up && down && horizontalLeft && !horizontalRight) {
            connectionType = ConnectionType.CENTER_RIGHT;
        } else if (up && down && !horizontalLeft && horizontalRight) {
            connectionType = ConnectionType.CENTER_LEFT;
        } else if (up && !down && horizontalLeft && !horizontalRight) {
            connectionType = ConnectionType.CENTER_BOTTOM_RIGHT;
        } else if (up && !down && !horizontalLeft && horizontalRight) {
            connectionType = ConnectionType.CENTER_BOTTOM_LEFT;
        } else if (!up && down && horizontalLeft && !horizontalRight) {
            connectionType = ConnectionType.CENTER_TOP_RIGHT;
        } else if (!up && down && !horizontalLeft && horizontalRight) {
            connectionType = ConnectionType.CENTER_TOP_LEFT;
        } else if (up && down) {
            connectionType = ConnectionType.VERTICAL_MIDDLE;
        } else if (up) {
            connectionType = ConnectionType.VERTICAL_BOTTOM;
        } else if (down) {
            connectionType = ConnectionType.VERTICAL_TOP;
        } else if (horizontalLeft && horizontalRight) {
            connectionType = ConnectionType.HORIZONTAL_MIDDLE;
        } else if (horizontalLeft) {
            connectionType = ConnectionType.HORIZONTAL_RIGHT;
        } else {
            connectionType = ConnectionType.HORIZONTAL_LEFT;
        }
        return this.defaultBlockState().setValue(CONNECTION, connectionType).setValue(WATERLOGGED, state.getValue(WATERLOGGED));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return world.getMaxLightLevel() / 4;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState newState = super.getStateForPlacement(ctx);
        newState = newState.setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER);

        return updateConnectionState(newState, ctx.getLevel(), ctx.getClickedPos()).setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockState newState = updateConnectionState(state, level, pos);
        if (newState.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return newState;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, CONNECTION);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public enum ConnectionType implements StringRepresentable {
        SINGLE,
        VERTICAL_BOTTOM,
        VERTICAL_MIDDLE,
        VERTICAL_TOP,
        HORIZONTAL_LEFT,
        HORIZONTAL_MIDDLE,
        HORIZONTAL_RIGHT,
        CENTER,
        CENTER_LEFT,
        CENTER_RIGHT,
        CENTER_BOTTOM,
        CENTER_TOP,
        CENTER_TOP_LEFT,
        CENTER_TOP_RIGHT,
        CENTER_BOTTOM_LEFT,
        CENTER_BOTTOM_RIGHT;

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}