package net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.recipebook.OverlayRecipeComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;

public class MonsterOverlayRecipeComponent extends OverlayRecipeComponent implements ICustomRecipeOverlay {
    public MonsterOverlayRecipeComponent() {
    }

    @Override public boolean dungeonsdelight$implementsCustomOverlay() { return true; }
    @Override public OverlayRecipeButton dungeonsDelight$customOverlayButton(int x, int y, Recipe<?> rec, boolean craftable) { return new MonsterOverlayRecipeButton(x, y, rec, craftable);}

    @Override
    public void dungeonsDelight$interceptRenderingBlit(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, int width, int height, int sliceSize, int uOffset, int vOffset, int textureWidth, int textureHeight) {
        guiGraphics.blitNineSliced(MonsterPotRecipeBookComponent.RECIPE_BOOK, x, y, width, height, sliceSize, uOffset, vOffset, textureWidth, textureHeight);
    }

    class MonsterOverlayRecipeButton extends OverlayRecipeButton implements ICustomOverlayButton {
        public MonsterOverlayRecipeButton(int x, int y, Recipe<?> recipe, boolean isCraftable) {
            super(x, y, recipe, isCraftable);
        }

        @Override
        protected void calculateIngredientsPositions(Recipe<?> recipe) {
            NonNullList<Ingredient> list = recipe.getIngredients();
            if (recipe instanceof MonsterCookingRecipe c) {
                ItemStack stack = c.getContainer();
                if (!stack.isEmpty()) {
                    list.remove(list.size() - 1);
                    this.ingredientPos.add(new OverlayRecipeButton.Pos(10, 17, new ItemStack[]{stack}));
                }
            }

            this.placeRecipe(3, 3, -1, recipe, list.iterator(), 0);
        }

        @Override
        public void dungeonsDelight$interceptRenderingBlit(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
            guiGraphics.blit(MonsterPotRecipeBookComponent.RECIPE_BOOK, x, y, uOffset, vOffset, uWidth, vHeight);
        }
    }
}