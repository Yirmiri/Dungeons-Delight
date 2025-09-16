//
//Based on the original version from Farmer's Delight here:
//https://github.com/vectorwing/FarmersDelight/blob/1.20/src/main/java/vectorwing/farmersdelight/common/block/CookingPotBlock.java
//

package net.yirmiri.dungeonsdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.yirmiri.dungeonsdelight.common.block.entity.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CookingPotSupport;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.MathUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("deprecation")
public class MonsterPotBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
    public static final MapCodec<MonsterPotBlock> CODEC = simpleCodec(MonsterPotBlock::new);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<CookingPotSupport> SUPPORT = EnumProperty.create("support", CookingPotSupport.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    protected static final VoxelShape SHAPE_WITH_TRAY = Shapes.or(SHAPE, Block.box(0.0D, -1.0D, 0.0D, 16.0D, 0.0D, 16.0D));

    public MonsterPotBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(SUPPORT, CookingPotSupport.NONE)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack heldStack, BlockState state, Level level, BlockPos pos, Player player, net.minecraft.world.InteractionHand hand, BlockHitResult hit) {
        if (heldStack.isEmpty() && player.isShiftKeyDown()) {
            level.setBlockAndUpdate(pos, state.setValue(SUPPORT,
                    state.getValue(SUPPORT).equals(CookingPotSupport.HANDLE)
                            ? getTrayState(level, pos) : CookingPotSupport.HANDLE));
            level.playSound(null, pos, SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 0.7F, 1.0F);
        } else if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MonsterPotBlockEntity potEntity) {
                ItemStack servingStack = potEntity.useHeldItemOnMeal(heldStack);
                if (!servingStack.isEmpty()) {
                    if (!player.getInventory().add(servingStack)) {
                        player.drop(servingStack, false);
                    }
                    level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_GENERIC.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
                } else {
                    player.openMenu(potEntity, pos);
                }
            }
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(SUPPORT).equals(CookingPotSupport.TRAY) ? SHAPE_WITH_TRAY : SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        FluidState fluid = level.getFluidState(pos);

        BlockState state = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);

        if (context.getClickedFace().equals(Direction.DOWN)) {
            return state.setValue(SUPPORT, CookingPotSupport.HANDLE);
        }
        return state.setValue(SUPPORT, getTrayState(level, pos));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (facing.getAxis().equals(Direction.Axis.Y) && !state.getValue(SUPPORT).equals(CookingPotSupport.HANDLE)) {
            return state.setValue(SUPPORT, getTrayState(level, currentPos));
        }
        return state;
    }

    private CookingPotSupport getTrayState(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(DDTags.BlockT.MONSTER_TRAY_HEAT_SOURCES)
                ? CookingPotSupport.TRAY
                : CookingPotSupport.NONE;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        ItemStack stack = super.getCloneItemStack(level, pos, state);

        Optional<MonsterPotBlockEntity> cookingPot = level.getBlockEntity(pos, DDBlockEntities.MONSTER_COOKING_POT.get());
        if (cookingPot.isPresent()) {
            stack = cookingPot.get().getAsItem();
        }

        return stack;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof MonsterPotBlockEntity potEntity) {
                Containers.dropContents(level, pos, potEntity.getDroppableInventory());
                potEntity.getUsedRecipesAndPopExperience(level, Vec3.atCenterOf(pos));
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, SUPPORT, WATERLOGGED);
    }

//    @Override
//    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
//        if (stack.hasCustomHoverName()) {
//            BlockEntity tileEntity = level.getBlockEntity(pos);
//            if (tileEntity instanceof MonsterPotBlockEntity potEntity) {
//                potEntity.setCustomName(stack.getHoverName());
//            }
//        }
//    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, ctx, tooltip, tooltipFlag);
        ItemStack mealStack = MonsterPotBlockEntity.getMealFromItem(stack);
        MutableComponent textServingsOf;
        if (!mealStack.isEmpty()) {
            textServingsOf = mealStack.getCount() == 1
                    ? TextUtils.getTranslation("tooltip.cooking_pot.single_serving")
                    : TextUtils.getTranslation("tooltip.cooking_pot.many_servings", mealStack.getCount());
            tooltip.add(textServingsOf.withStyle(ChatFormatting.GRAY));
            MutableComponent textMealName = mealStack.getHoverName().copy();
            tooltip.add(textMealName.withStyle(mealStack.getRarity().getStyleModifier()));
        } else {
            textServingsOf = TextUtils.getTranslation("tooltip.cooking_pot.empty");
            tooltip.add(textServingsOf.withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof MonsterPotBlockEntity potEntity && potEntity.isHeated()) {
            SoundEvent boilSound = !potEntity.getMeal().isEmpty()
                    ? ModSounds.BLOCK_COOKING_POT_BOIL_SOUP.get()
                    : ModSounds.BLOCK_COOKING_POT_BOIL.get();
            double x = pos.getX() + 0.5D;
            double y = pos.getY();
            double z = pos.getZ() + 0.5D;
            if (random.nextInt(10) == 0) {
                level.playLocalSound(x, y, z, boilSound, SoundSource.BLOCKS,
                        0.5F, random.nextFloat() * 0.2F + 0.9F, false);
            }
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof MonsterPotBlockEntity potEntity) {
            ItemStackHandler inventory = potEntity.getInventory();
            return MathUtils.calcRedstoneFromItemHandler(inventory);
        }
        return 0;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return DDBlockEntities.MONSTER_COOKING_POT.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return createTickerHelper(type, DDBlockEntities.MONSTER_COOKING_POT.get(), MonsterPotBlockEntity::animationTick);
        }
        return createTickerHelper(type, DDBlockEntities.MONSTER_COOKING_POT.get(), MonsterPotBlockEntity::cookingTick);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> serverType, BlockEntityType<E> clientType, BlockEntityTicker<? super E> ticker) {
        return clientType == serverType ? (BlockEntityTicker<A>) ticker : null;
    }
}
