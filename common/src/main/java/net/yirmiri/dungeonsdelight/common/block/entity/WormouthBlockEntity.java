package net.yirmiri.dungeonsdelight.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappings;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

import java.util.Objects;

public class WormouthBlockEntity extends BlockEntity implements ContainerSingleItem {
    private ItemStack stack = ItemStack.EMPTY;

    public WormouthBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.WORMOUTH.get(), pos, blockState);
    }

    @Override public boolean canPlaceItem(int index, ItemStack stack) { return WormouthMappings.test(stack) != null; }
    @Override public boolean canTakeItem(Container target, int index, ItemStack stack) {
        return false;
    }
    @Override public ItemStack getItem(int i) { return this.stack; }
    @Override public void setItem(int i, ItemStack itemStack) { this.stack = itemStack; }
    @Override public boolean stillValid(Player player) { return Container.stillValidBlockEntity(this, player); }

    @Override public ItemStack removeItem(int i, int i1) {
        ItemStack stack2 = Objects.requireNonNullElse(this.stack, ItemStack.EMPTY);
        this.stack = ItemStack.EMPTY;
        return stack2;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.stack.isEmpty()) tag.put("item", this.stack.save(new CompoundTag()));
    }
    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("item")) this.stack = ItemStack.of(tag.getCompound("item"));
        else this.stack = ItemStack.EMPTY;
    }
}