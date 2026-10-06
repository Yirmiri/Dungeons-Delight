package net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public interface ICustomRecipeButton {
    void dungeonsDelight$interceptRenderingBlit(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight);
}
