package net.yirmiri.dungeonsdelight.block.entity.container;

import com.mojang.datafixers.util.Pair;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlotItemHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.entity.DungeonPotBlockEntity;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDScreenHandlers;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.RecipeWrapper;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Objects;

public class DungeonPotMenu extends AbstractRecipeScreenHandler<RecipeWrapper, CookingPotRecipe> {
    public static final Identifier EMPTY_CONTAINER_SLOT_BOWL = Identifier.of(DungeonsDelight.MOD_ID, "item/empty_container_slot_bowl");

    public final DungeonPotBlockEntity blockEntity;
    public final ItemStackHandlerContainer inventory;
    private final PropertyDelegate cookingPotData;
    private final ScreenHandlerContext canInteractWithCallable;
    protected final World level;

    public DungeonPotMenu(final int windowId, final PlayerInventory playerInventory, final BlockPos data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), new ArrayPropertyDelegate(4));
    }

    public DungeonPotMenu(final int windowId, final PlayerInventory playerInventory, final DungeonPotBlockEntity blockEntity, PropertyDelegate cookingPotDataIn) {
        super(DDScreenHandlers.DUNGEON_POT_MENU, windowId);
        this.blockEntity = blockEntity;
        this.inventory = blockEntity.getInventory();
        this.cookingPotData = cookingPotDataIn;
        this.level = playerInventory.player.getWorld();
        this.canInteractWithCallable = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());

        // Ingredient Slots - 2 Rows x 3 Columns
        int startX = 8;
        int startY = 18;
        int inputStartX = 30;
        int inputStartY = 17;
        int borderSlotSize = 18;
        for (int row = 0; row < 2; ++row) {
            for (int column = 0; column < 3; ++column) {
                this.addSlot(new SlotItemHandler(inventory, (row * 3) + column,
                        inputStartX + (column * borderSlotSize),
                        inputStartY + (row * borderSlotSize)));
            }
        }

        // Meal Display
        this.addSlot(new DungeonPotMealSlot(inventory, 6, 124, 26));

        // Bowl Input
        this.addSlot(new SlotItemHandler(inventory, 7, 92, 55)
        {
            public Pair<Identifier, Identifier> getBackgroundSprite() {
                return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, EMPTY_CONTAINER_SLOT_BOWL);
            }
        });

        // Bowl Output
        this.addSlot(new DungeonPotResultSlot(playerInventory.player, blockEntity, inventory, 8, 124, 55));

        // Main Player Inventory
        int startPlayerInvY = startY * 4 + 12;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * borderSlotSize),
                        startPlayerInvY + (row * borderSlotSize)));
            }
        }

        // Hotbar
        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, startX + (column * borderSlotSize), 142));
        }

        this.addProperties(cookingPotDataIn);
    }

    private static DungeonPotBlockEntity getTileEntity(final PlayerInventory playerInventory, final BlockPos data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final BlockEntity tileAtPos = playerInventory.player.getWorld().getBlockEntity(data);
        if (tileAtPos instanceof DungeonPotBlockEntity) {
            return (DungeonPotBlockEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    @Override
    public boolean canUse(PlayerEntity playerIn) {
        return canUse(canInteractWithCallable, playerIn, DDBlocks.DUNGEON_POT);
    }

    @Override
    public ItemStack quickMove(PlayerEntity playerIn, int index) {
        int indexMealDisplay = 6;
        int indexContainerInput = 7;
        int indexOutput = 8;
        int startPlayerInv = indexOutput + 1;
        int endPlayerInv = startPlayerInv + 36;
        ItemStack slotStackCopy = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack slotStack = slot.getStack();
            slotStackCopy = slotStack.copy();
            if (index == indexOutput) {
                if (!this.insertItem(slotStack, startPlayerInv, endPlayerInv, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index > indexOutput) {
                boolean isValidContainer = slotStack.isIn(ModTags.SERVING_CONTAINERS) || slotStack.isOf(blockEntity.getContainer().getItem());
                if (isValidContainer && !this.insertItem(slotStack, indexContainerInput, indexContainerInput + 1, false)) {
                    return ItemStack.EMPTY;
                } else if (!this.insertItem(slotStack, 0, indexMealDisplay, false)) {
                    return ItemStack.EMPTY;
                } else if (!this.insertItem(slotStack, indexContainerInput, indexOutput, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(slotStack, startPlayerInv, endPlayerInv, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.setStackNoCallbacks(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (slotStack.getCount() == slotStackCopy.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(playerIn, slotStack);
        }
        return slotStackCopy;
    }

    public int getCookProgressionScaled() {
        int i = this.cookingPotData.get(0);
        int j = this.cookingPotData.get(1);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public boolean isHeated() {
        return blockEntity.isHeated();
    }

    @Override
    public void populateRecipeFinder(RecipeMatcher helper) {
        for (int i = 0; i < inventory.getSlotCount(); i++) {
            helper.addUnenchantedInput(inventory.getStackInSlot(i));
        }
    }

    @Override
    public void clearCraftingSlots() {
        for (int i = 0; i < 6; i++) {
            this.inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    @Override
    public boolean matches(RecipeEntry<CookingPotRecipe> recipe) {
        return recipe.value().matches(new RecipeWrapper(inventory), level);
    }

    @Override
    public int getCraftingResultSlotIndex() {
        return 7;
    }

    @Override
    public int getCraftingWidth() {
        return 3;
    }

    @Override
    public int getCraftingHeight() {
        return 2;
    }

    @Override
    public int getCraftingSlotCount() {
        return 7;
    }

    @Override
    public RecipeBookCategory getCategory() {
        return RecipeBookCategory.valueOf("FARMERSDELIGHT_COOKING");
    }

    @Override
    public boolean canInsertIntoSlot(int slot) {
        return slot < (getCraftingWidth() * getCraftingHeight());
    }
}
