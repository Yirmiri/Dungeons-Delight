package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StainedScrapGateBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    public static final EnumProperty<Section> SECTION = EnumProperty.create("section", Section.class);
    public static final EnumProperty<Half> HALF = EnumProperty.create("half", Half.class);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape NS_SHAPE = Block.box(0, 0, 7, 16, 24, 9);
    private static final VoxelShape EW_SHAPE = Block.box(7, 0, 0, 9, 24, 16);

    private static final VoxelShape OUTLINE_NS_SHAPE = Block.box(0, 0, 7, 16, 16, 9);
    private static final VoxelShape OUTLINE_EW_SHAPE = Block.box(7, 0, 0, 9, 16, 16);

    public StainedScrapGateBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(SECTION, Section.SINGLE)
                .setValue(HALF, Half.SINGLE)
                .setValue(OPEN, false)
        );
    } //todo add left/right and opening should open all of them vertically

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        boolean isOpen = state.getValue(OPEN);
        if (isOpen) {
            state = state.setValue(OPEN, false);
            level.setBlock(pos, state, 10);
        } else {
            Direction direction = player.getDirection();
            if (state.getValue(FACING) == direction.getOpposite()) {
                state = state.setValue(FACING, direction);
            }
            state = state.setValue(OPEN, true);
            level.setBlock(pos, state, 10);
        }
        level.playSound(player, pos, isOpen ? SoundEvents.IRON_TRAPDOOR_OPEN : SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
        level.gameEvent(player, isOpen ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if (!state.getValue(OPEN)) {
            if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
                return NS_SHAPE;
            }
            if (state.getValue(FACING) == Direction.EAST || state.getValue(FACING) == Direction.WEST) {
                return EW_SHAPE;
            }
        }
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
            return OUTLINE_NS_SHAPE;
        }
        else return OUTLINE_EW_SHAPE;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, SECTION, WATERLOGGED, OPEN, HALF);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx).setValue(SECTION, determineSection(ctx.getLevel(), ctx.getClickedPos()))
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, Boolean.valueOf(ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        state = super.updateShape(state, direction, neighborState, level, pos, neighborPos);

        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return state.setValue(SECTION, determineSection(level, pos));
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        switch (type) {
            case LAND, AIR -> {
                return state.getValue(OPEN);
            }
            default -> {
                return false;
            }
        }
    }

    private Section determineSection(LevelAccessor level, BlockPos pos) {
        boolean above = level.getBlockState(pos.above()).is(this);
        boolean below = level.getBlockState(pos.below()).is(this);

        if (!above && !below) {
            return Section.SINGLE;
        }

        if (!below) {
            return Section.BOTTOM;
        }

        if (!above) {
            return Section.TOP;
        }
        return Section.MIDDLE;
    }

    public enum Section implements StringRepresentable {
        SINGLE("single"),
        BOTTOM("bottom"),
        MIDDLE("middle"),
        TOP("top");

        private final String id;

        Section(String id) {
            this.id = id;
        }

        @Override
        public String getSerializedName() {
            return this.id;
        }
    }

    public enum Half implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right");

        private final String id;

        Half(String id) {
            this.id = id;
        }

        @Override
        public String getSerializedName() {
            return this.id;
        }
    }
}
