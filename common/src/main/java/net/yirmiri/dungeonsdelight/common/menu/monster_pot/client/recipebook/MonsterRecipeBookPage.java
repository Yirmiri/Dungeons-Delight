package net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook;

import net.minecraft.client.gui.components.StateSwitchingButton;
import net.minecraft.client.gui.screens.recipebook.OverlayRecipeComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeButton;

import java.util.ArrayList;
import java.util.List;

public class MonsterRecipeBookPage extends RecipeBookPage implements ICustomRecipeBook {
    public MonsterRecipeBookPage() {
        super();
    }

    @Override
    public OverlayRecipeComponent dungeonsdelight$modifyOverlay() {
        return new MonsterOverlayRecipeComponent();
    }

    @Override
    public void dungeonsdelight$modifyInitProducts(List<RecipeButton> buttons, StateSwitchingButton forwardButton, StateSwitchingButton backButton) {
        int amountRepo = 0;
        for (RecipeButton button : buttons) { amountRepo++; }

        List<RecipeButton> temp = new ArrayList<>();
        MonsterRecipeButton ptr;
        for (int i = 0; i < amountRepo; ++i) {
            ptr = new MonsterRecipeButton();
            ptr.setPosition(buttons.get(i).getX(), buttons.get(i).getY());
            temp.add(ptr);
        }
        buttons.clear();
        buttons.addAll(temp);

        forwardButton.initTextureValues(1, 208, 13, 18, MonsterPotRecipeBookComponent.RECIPE_BOOK);
        backButton.initTextureValues(1, 208, 13, 18, MonsterPotRecipeBookComponent.RECIPE_BOOK);
    }
}