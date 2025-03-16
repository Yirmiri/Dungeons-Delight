package net.yirmiri.dungeonsdelight.common.block.entity.container;

import com.mojang.datafixers.util.Pair;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDMenuTypes;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMealSlot;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Objects;

public class MonsterPotMenu extends RecipeBookMenu<RecipeWrapper> {
    public static final ResourceLocation EMPTY_CONTAINER_SLOT_BOWL = new ResourceLocation(DungeonsDelight.MOD_ID, "item/empty_container_slot_bowl");
    public final MonsterPotBlockEntity blockEntity;
    public final ItemStackHandler inventory;
    private final ContainerData cookingPotData;
    private final ContainerLevelAccess canInteractWithCallable;
    protected final Level level;

    public MonsterPotMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), new SimpleContainerData(4));
    }

    public MonsterPotMenu(int windowId, Inventory playerInventory, MonsterPotBlockEntity blockEntity, ContainerData monsterPotData) {
        super(DDMenuTypes.MONSTER_POT.get(), windowId);
        this.blockEntity = blockEntity;
        this.inventory = blockEntity.getInventory();
        this.cookingPotData = monsterPotData;
        this.level = playerInventory.player.level();
        this.canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        int startX = 8;
        int startY = 18;
        int inputStartX = 30;
        int inputStartY = 17;
        int borderSlotSize = 18;

        int startPlayerInvY;
        int column;
        for(startPlayerInvY = 0; startPlayerInvY < 2; ++startPlayerInvY) {
            for(column = 0; column < 3; ++column) {
                this.addSlot(new SlotItemHandler(this.inventory, startPlayerInvY * 3 + column, inputStartX + column * borderSlotSize, inputStartY + startPlayerInvY * borderSlotSize));
            }
        }

        this.addSlot(new CookingPotMealSlot(this.inventory, 6, 124, 26));
        this.addSlot(new SlotItemHandler(this.inventory, 7, 92, 55) {
            @OnlyIn(Dist.CLIENT)
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, MonsterPotMenu.EMPTY_CONTAINER_SLOT_BOWL);
            }
        });
        this.addSlot(new MonsterPotResultSlot(playerInventory.player, blockEntity, this.inventory, 8, 124, 55));

        //INVENTORY
        int startPlayerInvY2 = startY * 4 + 12;
        for (int row = 0; row < 3; ++row) {
            for (int column2 = 0; column2 < 9; ++column2) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column2, startX + (column2 * borderSlotSize), startPlayerInvY2 + (row * borderSlotSize)));
            }
        }

        //HOTBAR
        for (int column2 = 0; column2 < 9; ++column2) {
            this.addSlot(new Slot(playerInventory, column2, startX + (column2 * borderSlotSize), 142));
        }

        this.addDataSlots(monsterPotData);
    }

    private static MonsterPotBlockEntity getTileEntity(Inventory playerInventory, FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof MonsterPotBlockEntity) {
            return (MonsterPotBlockEntity)tileAtPos;
        } else {
            throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
        }
    }

    public boolean stillValid(Player playerIn) {
        return stillValid(this.canInteractWithCallable, playerIn, DDBlocks.MONSTER_POT.get());
    }

    public ItemStack quickMoveStack(Player playerIn, int index) {
        int indexMealDisplay = 6;
        int indexContainerInput = 7;
        int indexOutput = 8;
        int startPlayerInv = indexOutput + 1;
        int endPlayerInv = startPlayerInv + 36;
        ItemStack slotStackCopy = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            slotStackCopy = slotStack.copy();
            if (index == indexOutput) {
                if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index <= indexOutput) {
                if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                boolean isValidContainer = slotStack.is(ModTags.SERVING_CONTAINERS) || slotStack.is(this.blockEntity.getContainer().getItem());
                if (isValidContainer && !this.moveItemStackTo(slotStack, indexContainerInput, indexContainerInput + 1, false)) {
                    return ItemStack.EMPTY;
                }

                if (!this.moveItemStackTo(slotStack, 0, indexMealDisplay, false)) {
                    return ItemStack.EMPTY;
                }

                if (!this.moveItemStackTo(slotStack, indexContainerInput, indexOutput, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == slotStackCopy.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, slotStack);
        }

        return slotStackCopy;
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookProgressionScaled() {
        int i = this.cookingPotData.get(0);
        int j = this.cookingPotData.get(1);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isHeated() {
        return this.blockEntity.isHeated();
    }

    public void fillCraftSlotsStackedContents(StackedContents helper) {
        for(int i = 0; i < this.inventory.getSlots(); ++i) {
            helper.accountSimpleStack(this.inventory.getStackInSlot(i));
        }

    }

    public void clearCraftingContent() {
        for(int i = 0; i < 6; ++i) {
            this.inventory.setStackInSlot(i, ItemStack.EMPTY);
        }

    }

    public boolean recipeMatches(Recipe<? super RecipeWrapper> recipe) {
        return recipe.matches(new RecipeWrapper(this.inventory), this.level);
    }

    public int getResultSlotIndex() {
        return 7;
    }

    public int getGridWidth() {
        return 3;
    }

    public int getGridHeight() {
        return 2;
    }

    public int getSize() {
        return 7;
    }

    public RecipeBookType getRecipeBookType() {
        return FarmersDelight.RECIPE_TYPE_COOKING;
    }

    public boolean shouldMoveToInventory(int slot) {
        return slot < this.getGridWidth() * this.getGridHeight();
    }
}
