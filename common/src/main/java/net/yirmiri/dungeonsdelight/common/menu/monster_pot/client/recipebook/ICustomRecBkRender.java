package net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;

import java.util.List;

public interface ICustomRecBkRender {
    void dungeonsdelight$renderingPiercePrivate(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int xOffset, int width, int height, EditBox searchBox, List<RecipeBookTabButton> tabButtons, RecipeBookPage recipeBookPage);
    void dungeonsdelight$modifyTableSystems(EditBox searchBox, List<RecipeBookTabButton> tabButtons);
    default RecipeBookPage dungeonsdelight$modifyRecipePage() { return null; }
}
