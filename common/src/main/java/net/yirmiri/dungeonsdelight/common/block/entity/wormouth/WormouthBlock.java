package net.yirmiri.dungeonsdelight.common.block.entity.wormouth;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
import net.yirmiri.dungeonsdelight.common.entity.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappings;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

import javax.annotation.Nullable;
import java.util.Map;

//idk lets just make it waterlog for fun lol - artyrian
public class WormouthBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    private static final Map<Direction, VoxelShape> SHAPE = Map.of(
            Direction.DOWN, Block.box(1, 2, 1, 15, 16, 15),
            Direction.UP, Block.box(1, 0, 1, 15, 14, 15),
            Direction.NORTH, Block.box(1, 1, 2, 15, 15, 16),
            Direction.EAST, Block.box(0, 1, 1, 14, 15, 15),
            Direction.SOUTH,  Block.box(1, 1, 0, 15, 15, 14),
            Direction.WEST, Block.box(2, 1, 1, 16, 15, 15)
    );

    public static final BooleanProperty EATING = BooleanProperty.create("full");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public WormouthBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.DOWN)
                .setValue(EATING, false)
        );
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        Pair<ResourceLocation, Boolean> loc = WormouthMappings.test(heldItem);

        if (loc != null && level.getBlockEntity(pos) instanceof WormouthBlockEntity wormouth && !state.getValue(WormouthBlock.EATING)) {
            if (!level.isClientSide && level instanceof ServerLevel server) {
                if (wormouth.tryEating(server, pos, heldItem.getItem(), loc.getFirst(), loc.getSecond(), true)) {
                    wormouth.tryExtraDrop(server, pos, heldItem);
                    if (!player.isCreative()) heldItem.shrink(1);
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (level1, pos, state1, blockEntity) -> {
            if (type == DDBlockEntities.WORMOUTH.get() && blockEntity instanceof WormouthBlockEntity wormouth && !level.isClientSide) {
                wormouth.tick((ServerLevel)level, state, pos);
            }
        };
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof WormouthBlockEntity mouth) {
                mouth.emergencyDrop(level, pos, state);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        Direction xd = state.getOptionalValue(FACING).orElse(Direction.DOWN);
        return SHAPE.get(xd);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        super.onProjectileHit(level, state, hit, projectile);
        if (projectile instanceof CleaverEntity cleaver && cleaver.ricochetsLeft == 0) {
            BlockPos pos = hit.getBlockPos();
            Entity owner = cleaver.getOwner();
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (level instanceof ServerLevel && blockentity instanceof WormouthBlockEntity wormouth && owner instanceof ServerPlayer) {
                wormouth.panic(level, pos, level.getBlockState(pos));
            }
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelaccessor = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER).setValue(FACING, context.getClickedFace());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING, EATING);
    }

    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new WormouthBlockEntity(pos, state); }

    @Override public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
