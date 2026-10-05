package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;

import java.util.Iterator;
import java.util.List;

public class MonsterPotRecipeBookComponent extends RecipeBookComponent implements ICustomRecBkRender {
    protected static final ResourceLocation RECIPE_BOOK = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/gui/monster_recipe_book.png");
    //TODO: 1.21.1
    //protected static final WidgetSprites RECIPE_BOOK_BUTTONS = new WidgetSprites(
    //        RunicLib.customid(DungeonsDelight.MOD_ID, "recipe_book/monster_cooking_pot_enabled"),
    //        RunicLib.customid(DungeonsDelight.MOD_ID, "recipe_book/monster_cooking_pot_disabled"),
    //        RunicLib.customid(DungeonsDelight.MOD_ID, "recipe_book/monster_cooking_pot_enabled_highlighted"),
    //        RunicLib.customid(DungeonsDelight.MOD_ID, "recipe_book/monster_cooking_pot_disabled_highlighted"));

    @Override
    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(152, 41, 28, 18, RECIPE_BOOK);
    }

    public void hide() { this.setVisible(false); }

    @Override
    protected Component getRecipeFilterName() { return Component.translatable("gui.recipebook.dungeonsdelight.toggleRecipes.monsterpot"); }

    @Override
    public void duungeonsdelight$renderingPiercePrivate(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int xOffset, int width, int height, EditBox searchBox, List<RecipeBookTabButton> tabButtons, RecipeBookPage recipeBookPage) {
        if (this.isVisible()) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0.0F, 0.0F, 100.0F);
            int i = (width - 147) / 2 - xOffset;
            int j = (height - 166) / 2;
            guiGraphics.blit(RECIPE_BOOK, i, j, 1, 1, 147, 166);
            searchBox.render(guiGraphics, mouseX, mouseY, partialTick);

            Iterator iterator = tabButtons.iterator();
            while (iterator.hasNext()) {
                RecipeBookTabButton recipebooktabbutton = (RecipeBookTabButton)iterator.next();
                recipebooktabbutton.render(guiGraphics, mouseX, mouseY, partialTick);
            }

            this.filterButton.render(guiGraphics, mouseX, mouseY, partialTick);
            recipeBookPage.render(guiGraphics, i, j, mouseX, mouseY, partialTick);
            guiGraphics.pose().popPose();
        }
    }

    @Override
    public void setupGhostRecipe(Recipe<?> recipe, List<Slot> slots) {
        ItemStack resultStack = recipe.getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(recipe);

        if (recipe instanceof MonsterCookingRecipe monsterPotRecipe) {
            ItemStack containerStack = monsterPotRecipe.getContainer();

            // Container
            if (!containerStack.isEmpty()) {
                this.ghostRecipe.addIngredient(Ingredient.of(containerStack), (slots.get(MonsterPotBlockEntity.BOWL_SLOT)).x, (slots.get(MonsterPotBlockEntity.BOWL_SLOT)).y);
            }

            // Result
            if (slots.get(MonsterPotBlockEntity.OUTPUT_SLOT).getItem().isEmpty()) {
                this.ghostRecipe.addIngredient(Ingredient.of(resultStack), (slots.get(MonsterPotBlockEntity.OUTPUT_SLOT)).x, (slots.get(MonsterPotBlockEntity.OUTPUT_SLOT)).y);
            }
        }

        this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.getIngredients().iterator(), 0);
    }

    @Override
    public void renderGhostRecipe(GuiGraphics guiGraphics, int leftPos, int topPos, boolean p_283495_, float partialTick)
    {
        super.renderGhostRecipe(guiGraphics, leftPos, topPos, p_283495_, partialTick);
    }
}
