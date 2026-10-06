package net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.recipebook.RecipeButton;
import net.minecraft.resources.ResourceLocation;

public class MonsterRecipeButton extends RecipeButton implements ICustomRecipeButton {
    public MonsterRecipeButton() {
    }

    @Override
    public void dungeonsDelight$interceptRenderingBlit(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
        guiGraphics.blit(MonsterPotRecipeBookComponent.RECIPE_BOOK, x, y, uOffset, vOffset, uWidth, vHeight);
    }
}