//
//Based on the original version from Farmer's Delight here:
//https://github.com/vectorwing/FarmersDelight/blob/1.20/src/main/java/vectorwing/farmersdelight/common/block/CookingPotBlock.java
//

package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import net.yirmiri.dungeonsdelight.common.block.entity.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.state.CookingPotSupport;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.MathUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class MonsterPotBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<CookingPotSupport> SUPPORT = EnumProperty.create("support", CookingPotSupport.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 10.0, 14.0);
    protected static final VoxelShape SHAPE_WITH_TRAY = Shapes.or(SHAPE, Block.box(0.0, -1.0, 0.0, 16.0, 0.0, 16.0));

    public MonsterPotBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SUPPORT, CookingPotSupport.NONE).setValue(WATERLOGGED, false));
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (heldStack.isEmpty() && player.isShiftKeyDown()) {
            level.setBlockAndUpdate(pos, state.setValue(SUPPORT, (state.getValue(SUPPORT)).equals(CookingPotSupport.HANDLE) ? this.getTrayState(level, pos) : CookingPotSupport.HANDLE));
            level.playSound(null, pos, SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 0.7F, 1.0F);
        } else if (!level.isClientSide) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof MonsterPotBlockEntity) {
                MonsterPotBlockEntity monsterPotEntity = (MonsterPotBlockEntity)tileEntity;
                ItemStack servingStack = monsterPotEntity.useHeldItemOnMeal(heldStack);
                if (servingStack != ItemStack.EMPTY) {
                    if (!player.getInventory().add(servingStack)) {
                        player.drop(servingStack, false);
                    }
                    level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.BLOCKS, 1.0F, 1.0F);
                } else {
                    NetworkHooks.openScreen((ServerPlayer)player, monsterPotEntity, pos);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (state.getValue(SUPPORT)).equals(CookingPotSupport.TRAY) ? SHAPE_WITH_TRAY : SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        FluidState fluid = level.getFluidState(context.getClickedPos());
        BlockState state = (this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())).setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
        return context.getClickedFace().equals(Direction.DOWN) ? state.setValue(SUPPORT, CookingPotSupport.HANDLE) : state.setValue(SUPPORT, this.getTrayState(level, pos));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return facing.getAxis().equals(Direction.Axis.Y) && !(state.getValue(SUPPORT)).equals(CookingPotSupport.HANDLE) ? state.setValue(SUPPORT, this.getTrayState(level, currentPos)) : state;
    }

    private CookingPotSupport getTrayState(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModTags.TRAY_HEAT_SOURCES) ? CookingPotSupport.TRAY : CookingPotSupport.NONE;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        ItemStack stack = super.getCloneItemStack(level, pos, state);
        MonsterPotBlockEntity monsterPotEntity = (MonsterPotBlockEntity)level.getBlockEntity(pos);
        if (monsterPotEntity != null) {
            CompoundTag nbt = monsterPotEntity.writeMeal(new CompoundTag());
            if (!nbt.isEmpty()) {
                stack.addTagElement("BlockEntityTag", nbt);
            }

            if (monsterPotEntity.hasCustomName()) {
                stack.setHoverName(monsterPotEntity.getCustomName());
            }
        }
        return stack;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof MonsterPotBlockEntity) {
                MonsterPotBlockEntity monsterPotEntity = (MonsterPotBlockEntity)tileEntity;
                Containers.dropContents(level, pos, monsterPotEntity.getDroppableInventory());
                monsterPotEntity.getUsedRecipesAndPopExperience(level, Vec3.atCenterOf(pos));
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, level, tooltip, flagIn);
        CompoundTag nbt = stack.getTagElement("BlockEntityTag");
        ItemStack mealStack = MonsterPotBlockEntity.getMealFromItem(stack);
        MutableComponent textServingsOf;
        if (!mealStack.isEmpty()) {
            textServingsOf = mealStack.getCount() == 1 ? TextUtils.getTranslation("tooltip.cooking_pot.single_serving") : TextUtils.getTranslation("tooltip.cooking_pot.many_servings", new Object[]{mealStack.getCount()});
            tooltip.add(textServingsOf.withStyle(ChatFormatting.GRAY));
            MutableComponent textMealName = mealStack.getHoverName().copy();
            tooltip.add(textMealName.withStyle(mealStack.getRarity().getStyleModifier()));
        } else {
            textServingsOf = TextUtils.getTranslation("tooltip.cooking_pot.empty");
            tooltip.add(textServingsOf.withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, SUPPORT, WATERLOGGED);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof MonsterPotBlockEntity) {
                ((MonsterPotBlockEntity)tileEntity).setCustomName(stack.getHoverName());
            }
        }
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof MonsterPotBlockEntity monsterPotEntity) {
            if (monsterPotEntity.isHeated()) {
                SoundEvent boilSound = !monsterPotEntity.getMeal().isEmpty() ? ModSounds.BLOCK_COOKING_POT_BOIL_SOUP.get() : ModSounds.BLOCK_COOKING_POT_BOIL.get();
                double x = pos.getX() + 0.5;
                double y = pos.getY();
                double z = pos.getZ() + 0.5;
                if (random.nextInt(10) == 0) {
                    level.playLocalSound(x, y, z, boilSound, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.2F + 0.9F, false);
                }
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
        if (tileEntity instanceof MonsterPotBlockEntity) {
            ItemStackHandler inventory = ((MonsterPotBlockEntity)tileEntity).getInventory();
            return MathUtils.calcRedstoneFromItemHandler(inventory);
        } else {
            return 0;
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return DDBlockEntities.MONSTER_COOKING_POT.get().create(pos, state);
    }

    @Override @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return level.isClientSide ? createTickerHelper(blockEntity, DDBlockEntities.MONSTER_COOKING_POT.get(), MonsterPotBlockEntity::animationTick) :
                createTickerHelper(blockEntity, DDBlockEntities.MONSTER_COOKING_POT.get(), MonsterPotBlockEntity::cookingTick);
    }
}
