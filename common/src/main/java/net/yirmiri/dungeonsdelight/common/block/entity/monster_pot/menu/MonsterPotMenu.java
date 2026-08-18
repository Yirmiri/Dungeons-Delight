package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.slot.MonsterPotContainerSlot;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.slot.MonsterPotOutputSlot;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDMenus;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookTypes;

public class MonsterPotMenu extends RecipeBookMenu<Container> {
    protected final Level level;
    protected final Player player;
    private final Container container;
    private final ContainerLevelAccess access;
    private final ContainerData data;

    public MonsterPotMenu(int id, Inventory playerInv) {
        this(id, playerInv, new SimpleContainer(MonsterPotBlockEntity.MAX_CONT_SIZE), new SimpleContainerData(3), ContainerLevelAccess.NULL);
    }

    public MonsterPotMenu(int id, Inventory playerInv, Container container, ContainerData data, ContainerLevelAccess access) {
        super(DDMenus.MONSTER_POT.get(), id);
        this.access = access;
        checkContainerSize(container, MonsterPotBlockEntity.MAX_CONT_SIZE);
        checkContainerDataCount(data, 3);
        this.container = container;
        this.player = playerInv.player;
        this.level = this.player.level();
        this.data = data;

        // Pot input
        int exi = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                this.addSlot(new Slot(container, exi, 30 + (j * 18), 17 + (i * 18)));
                exi++;
            }
        }

        // Container + Output
        this.addSlot(new MonsterPotContainerSlot(container, MonsterPotBlockEntity.BOWL_SLOT, 127, 57));
        this.addSlot(new MonsterPotOutputSlot(this.player, container, MonsterPotBlockEntity.OUTPUT_SLOT, 126, 26));

        // Playerinv
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Playerhotbar
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInv, i, 8 + i * 18, 142));
        }

        this.addDataSlots(data);
    }

    // Recipe Book
    @Override
    public void fillCraftSlotsStackedContents(StackedContents itemHelper) {
        if (this.container instanceof StackedContentsCompatible stacker) stacker.fillStackedContents(itemHelper);
    }

    @Override public void clearCraftingContent() { this.container.clearContent(); }
    @Override public boolean recipeMatches(Recipe<? super Container> recipe) { return recipe.matches(this.container, this.level); }
    @Override public int getResultSlotIndex() { return MonsterPotBlockEntity.OUTPUT_SLOT; }
    @Override public int getGridWidth() { return 3; }
    @Override public int getGridHeight() { return 2; }
    @Override public int getSize() { return MonsterPotBlockEntity.MAX_CONT_SIZE; }
    public RecipeBookType getRecipeBookType() {
        return DDRecipeBookTypes.DD_MONSTERPOT;
    }
    @Override public boolean shouldMoveToInventory(int index) { return index != MonsterPotBlockEntity.OUTPUT_SLOT; }

    public int getCookProgress() {
        return this.data.get(MonsterPotBlockEntity.DATA_COOK_PROGRESS);
    }

    public int getCookTotal() {
        return this.data.get(MonsterPotBlockEntity.DATA_COOK_TOTAL);
    }

    public boolean isHeated() {
        return this.data.get(MonsterPotBlockEntity.DATA_HEATED) == 1;
    }

    // Container
    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);

        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            if (i == MonsterPotBlockEntity.OUTPUT_SLOT) {
                if (!this.moveItemStackTo(stack, MonsterPotBlockEntity.MAX_CONT_SIZE, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, itemstack);
            } else if (i < MonsterPotBlockEntity.MAX_CONT_SIZE) {
                if (!this.moveItemStackTo(stack, MonsterPotBlockEntity.MAX_CONT_SIZE, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(stack, 0, MonsterPotBlockEntity.MAX_CONT_SIZE, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) { return stillValid(this.access, player, DDBlocks.MONSTER_POT.get()); }
}