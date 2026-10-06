package net.yirmiri.dungeonsdelight.core.mixin.client.recipe_book;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.recipebook.RecipeButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook.ICustomRecipeButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RecipeButton.class)
public abstract class RecipeButtonMixin extends AbstractWidget
{
    public RecipeButtonMixin(int x, int y, int width, int height, Component message) { super(x, y, width, height, message); }

    @WrapOperation(method = "renderWidget", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V")
    )
    private void dungeonsDelight$blitInterception(GuiGraphics instance, ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight, Operation<Void> original) {
        if ((Object)this instanceof ICustomRecipeButton r && x == this.getX() && y == this.getY() && uWidth == this.width && vHeight == this.height) {
            r.dungeonsDelight$interceptRenderingBlit(instance, atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
        }
        else original.call(instance, atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
    }
}
