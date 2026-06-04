package net.yirmiri.dungeonsdelight.common.block.entity.item_grate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

import java.util.List;

public class ItemGrateBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, EntityBlock {
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public ItemGrateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext ctx) {
        if (ctx instanceof EntityCollisionContext entityCtx && entityCtx.getEntity() instanceof ItemEntity itemEntity
                && worldIn.getBlockEntity(pos) instanceof ItemGrateBlockEntity itemGrateBlockEntity) {
            if (itemEntity.getItem().is(itemGrateBlockEntity.getStack().getItem()) || itemGrateBlockEntity.canInsert()) {
                return Shapes.empty();
            }
        }
        return super.getCollisionShape(state, worldIn, pos, ctx);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ItemGrateBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ItemGrateBlockEntity itemGrateBlockEntity) {
                itemGrateBlockEntity.dropItem(level, pos);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (level1, pos, state1, blockEntity) -> {
            if (type == DDBlockEntities.ITEM_GRATE.get() && blockEntity instanceof ItemGrateBlockEntity itemGrateBlockEntity && !itemGrateBlockEntity.isWaxed()) {
                itemGrateBlockEntity.tick(pos);
            }
        };
    }

    private void addEnhancement(boolean tool, ItemStack stack, SoundEvent soundEvent, Player player, BlockPos pos) {
        player.level().playSound(player, pos, soundEvent, SoundSource.PLAYERS, 1.0F, 1.0F);
        if (!player.isCreative()) {
            if (!tool) {
                stack.shrink(1);
            } else {
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getItemInHand(hand);
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof ItemGrateBlockEntity grate) {
            if ((!(player.isCrouching() && DungeonsDelight.CONFIG.getItemGrateRequiresSneakingToInsert()))
                    || (player.isCrouching() && DungeonsDelight.CONFIG.getItemGrateRequiresSneakingToInsert())) {
                if (!heldStack.isEmpty()) {
                    if (!grate.canInsert()) {
                        if (heldStack.is(Items.GLOWSTONE_DUST) && !grate.isLarge()) {
                            grate.setLarge(true);
                            addEnhancement(false, heldStack, SoundEvents.POWDER_SNOW_PLACE, player, pos);
                            return InteractionResult.sidedSuccess(level.isClientSide);
                        }

                        if (heldStack.is(ItemTags.AXES) && grate.hasAugs()) {
                            grate.clearAugs();
                            addEnhancement(true, heldStack, SoundEvents.AXE_SCRAPE, player, pos);
                            return InteractionResult.sidedSuccess(level.isClientSide);
                        }

                        if (!grate.isWaxed()) {
                            if (heldStack.is(Items.REDSTONE) && !grate.isFast()) {
                                grate.setFast(true);
                                addEnhancement(false, heldStack, SoundEvents.POWDER_SNOW_PLACE, player, pos);
                                return InteractionResult.sidedSuccess(level.isClientSide);
                            } else if (heldStack.is(Items.HONEYCOMB)) {
                                grate.setWaxed(true);
                                addEnhancement(false, heldStack, SoundEvents.HONEYCOMB_WAX_ON, player, pos);
                                return InteractionResult.sidedSuccess(level.isClientSide);
                            }
                        }
                    } else {
                        grate.insertItem(level, player, hand);
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                } else if (!grate.canInsert()) {
                    grate.takeItem(level, player);
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, level, tooltipComponents, tooltipFlag);
        ItemGrateTooltip.appendHoverText(tooltipComponents);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.is(this) || super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(WATERLOGGED, FACING);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
