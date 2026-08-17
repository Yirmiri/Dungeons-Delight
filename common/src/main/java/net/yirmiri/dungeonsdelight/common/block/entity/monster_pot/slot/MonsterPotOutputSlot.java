package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.slot;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlockEntity;

public class MonsterPotOutputSlot extends Slot {
    private final Player player;
    private int removeCount;

    public MonsterPotOutputSlot(Player player, Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.player = player;
    }

    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    public ItemStack remove(int amount) {
        if (this.hasItem()) this.removeCount += Math.min(amount, this.getItem().getCount());
        return super.remove(amount);
    }

    public void onTake(Player player, ItemStack stack) {
        this.checkTakeAchievements(stack);
        super.onTake(player, stack);
    }

    protected void onQuickCraft(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    protected void checkTakeAchievements(ItemStack stack) {
        stack.onCraftedBy(this.player.level(), this.player, this.removeCount);

        if (this.player instanceof ServerPlayer serverplayer && this.container instanceof MonsterPotBlockEntity blockentity) {
            blockentity.doAwardsAndExp(serverplayer);
        }
        this.removeCount = 0;
    }
}
