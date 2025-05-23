package net.yirmiri.dungeonsdelight.common.block.entity.container;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.block.entity.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDRecipeRegistries;

public class MonsterFoodServingRecipe extends CustomRecipe {
    public MonsterFoodServingRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack monsterPotStack = ItemStack.EMPTY;
        ItemStack containerStack = ItemStack.EMPTY;
        ItemStack secondStack = ItemStack.EMPTY;

        for (int index = 0; index < container.getContainerSize(); ++index) {
            ItemStack selectedStack = container.getItem(index);
            if (!selectedStack.isEmpty()) {
                if (monsterPotStack.isEmpty()) {
                    ItemStack mealStack = MonsterPotBlockEntity.getMealFromItem(selectedStack);
                    if (!mealStack.isEmpty()) {
                        monsterPotStack = selectedStack;
                        containerStack = MonsterPotBlockEntity.getContainerFromItem(selectedStack);
                        continue;
                    }
                }
                if (secondStack.isEmpty()) {
                    secondStack = selectedStack;
                } else {
                    return false;
                }
            }
        }

        return !monsterPotStack.isEmpty() && !secondStack.isEmpty() && secondStack.is(containerStack.getItem());
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack selectedStack = container.getItem(i);
            if (!selectedStack.isEmpty() && selectedStack.is(DDBlocks.MONSTER_POT.get().asItem())) {
                ItemStack resultStack = MonsterPotBlockEntity.getMealFromItem(selectedStack).copy();
                resultStack.setCount(1);
                return resultStack;
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer container) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < remainders.size(); ++i) {
            ItemStack selectedStack = container.getItem(i);
            if (selectedStack.hasCraftingRemainingItem()) {
                remainders.set(i, selectedStack.getCraftingRemainingItem());
            } else if (selectedStack.is(DDBlocks.MONSTER_POT.get().asItem())) {
                MonsterPotBlockEntity.takeServingFromItem(selectedStack);
                ItemStack newCookingPotStack = selectedStack.copy();
                newCookingPotStack.setCount(1);
                remainders.set(i, newCookingPotStack);
                break;
            }
        }

        return remainders;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DDRecipeRegistries.MONSTER_FOOD_SERVING.get();
    }
}
