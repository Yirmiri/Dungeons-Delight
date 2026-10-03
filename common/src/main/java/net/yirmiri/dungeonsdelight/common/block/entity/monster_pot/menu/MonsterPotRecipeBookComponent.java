package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;

import java.util.List;

public class MonsterPotRecipeBookComponent extends RecipeBookComponent {
    protected static final ResourceLocation RECIPE_BOOK = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/gui/monster_recipe_book.png");
    //TODO: 1.21.1
    //protected static final WidgetSprites RECIPE_BOOK_BUTTONS = new WidgetSprites(
    //        RunicLib.customid(DungeonsDelight.MOD_ID, "recipe_book/monster_cooking_pot_enabled"),
    //        RunicLib.customid(DungeonsDelight.MOD_ID, "recipe_book/monster_cooking_pot_disabled"),
    //        RunicLib.customid(DungeonsDelight.MOD_ID, "recipe_book/monster_cooking_pot_enabled_highlighted"),
    //        RunicLib.customid(DungeonsDelight.MOD_ID, "recipe_book/monster_cooking_pot_disabled_highlighted"));

    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(152, 41, 28, 18, RECIPE_BOOK);
    }

    public void hide() { this.setVisible(false); }

    @Override
    protected Component getRecipeFilterName() { return Component.translatable("gui.recipebook.dungeonsdelight.toggleRecipes.monsterpot"); }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        //if (this.isVisible()) {
        //    guiGraphics.pose().pushPose();
        //    guiGraphics.pose().translate(0.0F, 0.0F, 100.0F);
        //    int i = (this.width - 147) / 2 - this.xOffset;
        //    int j = (this.height - 166) / 2;
        //    guiGraphics.blit(RECIPE_BOOK, i, j, 1, 1, 147, 166);
        //    this.searchBox.render(guiGraphics, mouseX, mouseY, partialTick);
        //    Iterator iterator = this.tabButtons.iterator();

        //    while(iterator.hasNext()) {
        //        RecipeBookTabButton recipebooktabbutton = (RecipeBookTabButton)iterator.next();
        //        recipebooktabbutton.render(guiGraphics, mouseX, mouseY, partialTick);
        //    }

        //    this.filterButton.render(guiGraphics, mouseX, mouseY, partialTick);
        //    this.recipeBookPage.render(guiGraphics, i, j, mouseX, mouseY, partialTick);
        //    guiGraphics.pose().popPose();
        //}
    }

    @Override
    public void setupGhostRecipe(Recipe<?> recipe, List<Slot> slots) {
        ItemStack resultStack = recipe.getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(recipe);
        if (slots.get(6).getItem().isEmpty()) {
            this.ghostRecipe.addIngredient(Ingredient.of(resultStack), (slots.get(6)).x, (slots.get(6)).y);
        }

        if (recipe instanceof MonsterCookingRecipe monsterPotRecipe) {
            ItemStack containerStack = monsterPotRecipe.getContainer();
            if (!containerStack.isEmpty()) {
                this.ghostRecipe.addIngredient(Ingredient.of(containerStack), (slots.get(7)).x, (slots.get(7)).y);
            }
        }

        this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.getIngredients().iterator(), 0);
    }
}
