package net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.recipebook.OverlayRecipeComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

public interface ICustomRecipeOverlay {
    void dungeonsDelight$interceptRenderingBlit(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, int width, int height, int sliceSize, int uOffset, int vOffset, int textureWidth, int textureHeight);
    default OverlayRecipeComponent.OverlayRecipeButton dungeonsDelight$customOverlayButton(int x, int y, Recipe<?> rec, boolean craftable) { return null; }
    default boolean dungeonsdelight$implementsCustomOverlay() { return false; }
}