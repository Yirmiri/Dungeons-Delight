package net.yirmiri.dungeonsdelight.common.block.entity.cleaving_board;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.Map;

public class CleavingBoardBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    private static final Map<Direction, VoxelShape> SHAPE = Map.of(
            Direction.DOWN, Block.box(1, 15, 1, 15, 16, 15),
            Direction.UP, Block.box(1, 0, 1, 15, 1, 15),
            Direction.NORTH, Block.box(1, 1, 15, 15, 15, 16),
            Direction.EAST, Block.box(0, 1, 1, 1, 15, 15),
            Direction.SOUTH,  Block.box(1, 1, 0, 15, 15, 1),
            Direction.WEST, Block.box(15, 1, 1, 16, 15, 15)
    );

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public CleavingBoardBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER)
                .setValue(FACING, ctx.getClickedFace());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        Direction xd = state.getOptionalValue(FACING).orElse(Direction.DOWN);
        return SHAPE.get(xd);
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
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CleavingBoardBlockEntity(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof Container) {
                Containers.dropContents(level, pos, (Container)blockEntity);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    // todo: replace this in 1.21.1 with useItemOn
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getItemInHand(hand);
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof CleavingBoardBlockEntity chopping && hand == InteractionHand.MAIN_HAND) {
            if (chopping.canInsert()) {
                if (!heldStack.isEmpty()) {
                    ItemStack toSend = heldStack.copy();
                    toSend.setCount(1);

                    if (!player.isCreative()) heldStack.shrink(1);
                    chopping.setItem(0, toSend);
                    chopping.setChanged();

                    level.playSound(null, pos, DDSounds.CLEAVING_BOARD_ADD.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
            else {
                ItemStack onBoard = chopping.getFirstItem();

                if (heldStack.isEmpty()) {
                    ItemStack stack = chopping.removeItem(0, 0);
                    player.setItemInHand(hand, stack);
                    level.playSound(null, pos, DDSounds.CLEAVING_BOARD_REMOVE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    chopping.setChanged();

                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
                else if (ItemStack.isSameItemSameTags(heldStack, onBoard) && onBoard.getCount() < onBoard.getMaxStackSize()) {

                    if (!player.isCreative()) heldStack.shrink(1);
                    onBoard.grow(1);
                    chopping.setChanged();

                    level.playSound(null, pos, DDSounds.CLEAVING_BOARD_ADD.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        super.onProjectileHit(level, state, hit, projectile);
        if (projectile instanceof CleaverEntity cleaver && cleaver.ricochetsLeft == 0) {
            BlockPos pos = hit.getBlockPos();
            Entity owner = cleaver.getOwner();
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof CleavingBoardBlockEntity cleavingBoard && owner instanceof Player player && state.getBlock() instanceof CleavingBoardBlock) {
                cleavingBoard.tryCleaving(cleaver, level, player, state);
            }
        }
    }

    @Override public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}