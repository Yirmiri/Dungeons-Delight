package net.yirmiri.dungeonsdelight.core.mixin.client.recipe_book;

import net.minecraft.client.gui.components.StateSwitchingButton;
import net.minecraft.client.gui.screens.recipebook.OverlayRecipeComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeButton;
import net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook.ICustomRecipeBook;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RecipeBookPage.class)
public class RecipeBookPageMixin {
    @Shadow @Final private List<RecipeButton> buttons;
    @Shadow private StateSwitchingButton forwardButton;
    @Shadow private StateSwitchingButton backButton;
    @Shadow @Final @Mutable private OverlayRecipeComponent overlay;

    @Inject(method = "init", at = @At("TAIL"))
    private void dungeonsDelight$initStopThat(CallbackInfo ci) {
        if ((Object)this instanceof ICustomRecipeBook r) {
            r.dungeonsdelight$modifyInitProducts(this.buttons, this.forwardButton, this.backButton);

            OverlayRecipeComponent mutable = r.dungeonsdelight$modifyOverlay();
            if (mutable != null) {
                this.overlay = mutable;
            }
        }
    }
}
