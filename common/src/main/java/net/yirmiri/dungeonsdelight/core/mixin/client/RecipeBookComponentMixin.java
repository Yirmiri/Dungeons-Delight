package net.yirmiri.dungeonsdelight.core.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu.ICustomRecBkRender;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(RecipeBookComponent.class)
public class RecipeBookComponentMixin {
    @Shadow private int xOffset;
    @Shadow private int width;
    @Shadow private int height;
    @Shadow @Nullable private EditBox searchBox;
    @Shadow @Final private List<RecipeBookTabButton> tabButtons;
    @Shadow @Final private RecipeBookPage recipeBookPage;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void dungeonsDelight$stopRenderingBecauseBad(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if ((Object)this instanceof ICustomRecBkRender r) {
            r.duungeonsdelight$renderingPiercePrivate(guiGraphics, mouseX, mouseY, partialTick, this.xOffset, this.width, this.height, this.searchBox, this.tabButtons, this.recipeBookPage);
            ci.cancel();
        }
    }

    @Inject(method = "updateTabs", at = @At("TAIL"))
    private void dungeonsDelight$updateMPotTab(CallbackInfo ci) {

    }
}
