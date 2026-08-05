package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

import java.util.Collections;
import java.util.stream.Stream;

public class LivingCandleBlock extends AbstractCandleBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;

    public LivingCandleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(LIT, false)
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(FACE, AttachFace.FLOOR)
        );
    }

    @Override
    protected Iterable<Vec3> getParticleOffsets(BlockState state) {
        return Collections.singleton(new Vec3(0.5, 0.8, 0.5));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.getAbilities().mayBuild && player.getItemInHand(hand).isEmpty() && state.getValue(LIT)) {
            extinguish(player, state, level, pos);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if (state.getValue(FACE) == AttachFace.CEILING) {
            return Stream.of(Block.box(6, 3, 6, 10, 11, 10), Block.box(7, 1, 7, 9, 3, 9),
                    Block.box(5, 0, 5, 11, 1, 11)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        }
        if (state.getValue(FACE) == AttachFace.WALL) {
            switch (state.getValue(FACING)) {
                case SOUTH: return Stream.of(
                        Block.box(5, 5, 15, 11, 11, 16),
                        Block.box(6, 6, 9, 10, 8, 15),
                        Block.box(7, 9, 10, 9, 11, 12),
                        Block.box(6, 11, 9, 10, 19, 13),
                        Block.box(5, 8, 8, 11, 9, 14)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                case WEST: return Stream.of(
                        Block.box(0, 5, 5, 1, 11, 11),
                        Block.box(1, 6, 6, 7, 8, 10),
                        Block.box(4, 9, 7, 6, 11, 9),
                        Block.box(3, 11, 6, 7, 19, 10),
                        Block.box(2, 8, 5, 8, 9, 11)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                case NORTH: return Stream.of(
                        Block.box(5, 5, 0, 11, 11, 1),
                        Block.box(6, 6, 1, 10, 8, 7),
                        Block.box(7, 9, 4, 9, 11, 6),
                        Block.box(6, 11, 3, 10, 19, 7),
                        Block.box(5, 8, 2, 11, 9, 8)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                case EAST: return Stream.of(
                        Block.box(15, 5, 5, 16, 11, 11),
                        Block.box(9, 6, 6, 15, 8, 10),
                        Block.box(10, 9, 7, 12, 11, 9),
                        Block.box(9, 11, 6, 13, 19, 10),
                        Block.box(8, 8, 5, 14, 9, 11)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
            }
        }
        return Stream.of(Block.box(6, 3, 6, 10, 11, 10), Block.box(7, 1, 7, 9, 3, 9),
                Block.box(5, 0, 5, 11, 1, 11)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    }

    @Override
    public void animateTick(BlockState p_220697_, Level p_220698_, BlockPos p_220699_, RandomSource p_220700_) {
        if (p_220697_.getValue(LIT)) {
            addParticlesAndSound(p_220698_, new Vec3(0.5, 0.85, 0.5).add(p_220699_.getX(), p_220699_.getY(), p_220699_.getZ()), p_220700_);
        }
    }

    private static void addParticlesAndSound(Level p_220688_, Vec3 p_220689_, RandomSource p_220690_) {
        float f = p_220690_.nextFloat();
        if (f < 0.3F) {
            p_220688_.addParticle(ParticleTypes.SMOKE, p_220689_.x, p_220689_.y, p_220689_.z, 0.0D, 0.0D, 0.0D);
            if (f < 0.17F) {
                p_220688_.playLocalSound(p_220689_.x + 0.5D, p_220689_.y + 0.5D, p_220689_.z + 0.5D, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + p_220690_.nextFloat(), p_220690_.nextFloat() * 0.7F + 0.3F, false);
            }
        }
        p_220688_.addParticle(DDParticles.LIVING_FLAME.get(), p_220689_.x, p_220689_.y, p_220689_.z, 0.0D, 0.0D, 0.0D);
    }

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidstate = ctx.getLevel().getFluidState(ctx.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;

        for (Direction direction : ctx.getNearestLookingDirections()) {
            AttachFace face;
            if(direction.getAxis() == Direction.Axis.Y) {
                face = direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR;
            } else {
                face = AttachFace.WALL;
            }

            Direction facing = direction.getAxis() == Direction.Axis.Y ? ctx.getHorizontalDirection() : direction.getOpposite();
            BlockState state = this.defaultBlockState().setValue(FACE, face).setValue(FACING, facing).setValue(WATERLOGGED, flag);

            if (state.canSurvive(ctx.getLevel(), ctx.getClickedPos())) {
                return state;
            }
        }
        return null;
    }

    public FluidState getFluidState(BlockState p_152844_) {
        return p_152844_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_152844_);
    }

    public boolean placeLiquid(LevelAccessor p_152805_, BlockPos p_152806_, BlockState p_152807_, FluidState p_152808_) {
        if (!p_152807_.getValue(WATERLOGGED) && p_152808_.getType() == Fluids.WATER) {
            BlockState blockstate = p_152807_.setValue(WATERLOGGED, Boolean.valueOf(true));
            if (p_152807_.getValue(LIT)) {
                extinguish(null, blockstate, p_152805_, p_152806_);
            } else {
                p_152805_.setBlock(p_152806_, blockstate, 3);
            }

            p_152805_.scheduleTick(p_152806_, p_152808_.getType(), p_152808_.getType().getTickDelay(p_152805_));
            return true;
        } else {
            return false;
        }
    }

    public static boolean canLight(BlockState p_152846_) {
        return p_152846_.is(BlockTags.CANDLES, (p_152810_) -> {
            return p_152810_.hasProperty(LIT) && p_152810_.hasProperty(WATERLOGGED);
        }) && !p_152846_.getValue(LIT) && !p_152846_.getValue(WATERLOGGED);
    }

    protected boolean canBeLit(BlockState p_152842_) {
        return !p_152842_.getValue(WATERLOGGED) && super.canBeLit(p_152842_);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = switch(state.getValue(FACE)) {
            case FLOOR -> Direction.DOWN;
            case CEILING -> Direction.UP;
            case WALL -> state.getValue(FACING);
        };
        return Block.canSupportCenter(level, pos.relative(direction), direction.getOpposite());
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, WATERLOGGED, FACE, FACING);
        super.createBlockStateDefinition(builder);
    }
}
