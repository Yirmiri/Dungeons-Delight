package net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook;

import net.minecraft.client.gui.components.StateSwitchingButton;
import net.minecraft.client.gui.screens.recipebook.OverlayRecipeComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeButton;

import java.util.List;

public interface ICustomRecipeBook {
    void dungeonsdelight$modifyInitProducts(List<RecipeButton> buttons, StateSwitchingButton forwardButton, StateSwitchingButton backButton);
    default OverlayRecipeComponent dungeonsdelight$modifyOverlay() { return null; }
}
