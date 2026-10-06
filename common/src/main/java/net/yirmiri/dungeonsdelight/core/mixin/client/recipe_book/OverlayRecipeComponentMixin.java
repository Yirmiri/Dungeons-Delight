package net.yirmiri.dungeonsdelight.core.mixin.client.recipe_book;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.recipebook.OverlayRecipeComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.item.crafting.Recipe;
import net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook.ICustomRecipeOverlay;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.List;

@Mixin(OverlayRecipeComponent.class)
public class OverlayRecipeComponentMixin {
    @Shadow @Final private List<OverlayRecipeComponent.OverlayRecipeButton> recipeButtons;
    @Shadow private int x;
    @Shadow private int y;
    @Shadow private boolean isFurnaceMenu;

    @Inject(method = "init", at = @At("TAIL"))
    private void dungeonsdelight$interceptAndChangeOverlay(Minecraft minecraft, RecipeCollection collection, int x, int y, int p_100199_, int p_100200_, float p_100201_, CallbackInfo ci) {
        if ((Object)this instanceof ICustomRecipeOverlay r && r.dungeonsdelight$implementsCustomOverlay()) {
            this.isFurnaceMenu = false;
            this.recipeButtons.clear();

            // Copied from init, may have to adjust()
            boolean flag = minecraft.player.getRecipeBook().isFiltering((RecipeBookMenu)minecraft.player.containerMenu);
            List<Recipe<?>> list = collection.getDisplayRecipes(true);
            List<Recipe<?>> filtering = flag ? Collections.emptyList() : collection.getDisplayRecipes(false);
            int i = list.size();
            int j = i + filtering.size();
            int k = j <= 16 ? 4 : 5;

            OverlayRecipeComponent.OverlayRecipeButton ov;
            for(int i1 = 0; i1 < j; ++i1) {
                boolean flag1 = i1 < i;
                Recipe<?> recipe = flag1 ? list.get(i1) : filtering.get(i1 - i);
                int j1 = this.x + 4 + 25 * (i1 % k);
                int k1 = this.y + 5 + 25 * (i1 / k);

                ov = r.dungeonsDelight$customOverlayButton(j1, k1, recipe, flag1);
                this.recipeButtons.add(ov);
            }
        }
    }

    @WrapOperation(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blitNineSliced(Lnet/minecraft/resources/ResourceLocation;IIIIIIIII)V")
    )
    private void dungeonsDelight$blitNSInterception(GuiGraphics instance, ResourceLocation atlasLocation, int x, int y, int width, int height, int sliceSize, int uOffset, int vOffset, int textureWidth, int textureHeight, Operation<Void> original) {
        if ((Object)this instanceof ICustomRecipeOverlay r && x == this.x && y == this.y) {
            r.dungeonsDelight$interceptRenderingBlit(instance, atlasLocation, x, y, width, height, sliceSize, uOffset, vOffset, textureWidth, textureHeight);
        }
        else original.call(instance, atlasLocation, x, y, width, height, sliceSize, uOffset, vOffset, textureWidth, textureHeight);
    }
}