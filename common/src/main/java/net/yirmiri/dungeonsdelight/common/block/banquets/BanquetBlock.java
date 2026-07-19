package net.yirmiri.dungeonsdelight.common.block.banquets;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Supplier;

public abstract class BanquetBlock extends Block {
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 4);
    private static Supplier<Item> servingItem;

    public BanquetBlock(Supplier<Item> servingItem, Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(getServingsProperty(), getMaxServings())
        );
        BanquetBlock.servingItem = servingItem;
    }

    protected IntegerProperty getServingsProperty() {
        return SERVINGS;
    }

    public int getMaxServings() {
        return 4;
    }

    public boolean isEmpty(BlockState state) {
        return state.getValue(getServingsProperty()) == 0;
    }

    public boolean canTakeServing(BlockState state) {
        return !isEmpty(state);
    }

    public void removeServing(Level level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(getServingsProperty(), state.getValue(getServingsProperty()) - 1), 3);
    }

    public ItemStack getServingItem() {
        return new ItemStack(servingItem.get());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack servingStack = getServingItem();
        ItemStack handStack = player.getItemInHand(hand);
        ItemStack containerItem = servingStack.getItem().getCraftingRemainingItem().getDefaultInstance();

        if ((containerItem.isEmpty() ? handStack.isEmpty() : handStack.is(containerItem.getItem())) && canTakeServing(state)) {
            if (!level.isClientSide) {
                if (!containerItem.isEmpty()) {
                    handStack.shrink(1);
                }
                if (handStack.isEmpty()) {
                    player.setItemInHand(hand, servingStack);
                } else {
                    player.addItem(servingStack);
                }
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, 0.0, 0.0, 0.0, 0.001D);
                }
                player.level().playSound(player, pos, SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.BLOCKS, 1.0F, 1.0F);
                removeServing(level, pos, state);
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SERVINGS);
    }
}