package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;

import java.util.List;

public interface ICustomRecBkRender {
    void duungeonsdelight$renderingPiercePrivate(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int xOffset, int width, int height, EditBox searchBox, List<RecipeBookTabButton> tabButtons, RecipeBookPage recipeBookPage);
}
