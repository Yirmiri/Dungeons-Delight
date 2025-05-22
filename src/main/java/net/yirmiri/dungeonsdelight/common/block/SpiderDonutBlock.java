package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;

public class SpiderDonutBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty DONUTS = IntegerProperty.create("donuts", 0, 4);
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public SpiderDonutBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(this.getServingsProperty(), 0).setValue(HANGING, false));
    }

    public IntegerProperty getServingsProperty() {
        return DONUTS;
    }

    public int getMaxServings() {
        return 4;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(HANGING)) {
            return switch (state.getValue(DONUTS)) {
                case 0 -> Block.box(4, 10, 4, 12, 12, 12);
                case 1 -> Block.box(4, 5, 4, 12, 12, 12);
                case 2 -> Block.box(4, 2, 4, 12, 12, 12);
                default -> Block.box(4, 1, 4, 12, 13, 12);
            };
        } else {
            return switch (state.getValue(DONUTS)) {
                case 0 -> Block.box(4, 0, 4, 12, 2, 12);
                case 1 -> Block.box(4, 0, 4, 12, 4, 12);
                case 2 -> Block.box(4, 0, 4, 12, 6, 12);
                default -> Block.box(4, 0, 4, 12, 8, 12);
            };
        }
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return level.isClientSide && this.takeServing(level, pos, state, player, hand).consumesAction() ? InteractionResult.SUCCESS : this.takeServing(level, pos, state, player, hand);
    }

    protected InteractionResult takeServing(LevelAccessor level, BlockPos pos, BlockState state, Player player, InteractionHand hand) {
        int servings = state.getValue(this.getServingsProperty());

        ItemStack heldStack = player.getItemInHand(hand);
        if (!heldStack.is(this.asItem()) && player.canEat(false)) {
            player.getFoodData().eat(3, 0.3F);
            player.addEffect(new MobEffectInstance(DDEffects.POUNCING.get(), 900, 1));
            level.gameEvent(player, GameEvent.EAT, pos);
            if (servings == 0) {
                level.removeBlock(pos, false);
            } else {
                level.setBlock(pos, state.setValue(DONUTS, servings - 1), 2);
            }
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && state.getValue(DONUTS) < getMaxServings() || super.canBeReplaced(state, context);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.setValue(DONUTS, Math.min(blockstate.getValue(DONUTS) + 1, getMaxServings()));
        } else {
            for (Direction direction : context.getNearestLookingDirections()) {
                if (direction.getAxis() == Direction.Axis.Y) {
                    return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(HANGING, direction == Direction.UP);
                }
            }
        }
        return null;
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(HANGING)) {
            return facing == Direction.UP && !stateIn.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
        } else {
            return facing == Direction.DOWN && !stateIn.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
        }
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(HANGING)) {
            return level.getBlockState(pos.above()).isSolid();
        } else {
            return level.getBlockState(pos.below()).isSolid();
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, DONUTS, HANGING);
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return blockState.getValue(this.getServingsProperty());
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}
