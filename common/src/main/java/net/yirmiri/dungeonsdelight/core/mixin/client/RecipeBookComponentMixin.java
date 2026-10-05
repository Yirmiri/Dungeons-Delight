package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu.recipe_book.ICustomRecBkRender;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookCategories;
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

    @Inject(method = "initVisuals", at = @At("TAIL"))
    private void dungeonsDelight$initStopThat(CallbackInfo ci) {
        if ((Object)this instanceof ICustomRecBkRender r) {
            r.dungeonsdelight$modifyTableSystems(this.searchBox, this.tabButtons, this.recipeBookPage);
        }
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void dungeonsDelight$stopRenderingBecauseBad(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if ((Object)this instanceof ICustomRecBkRender r) {
            r.dungeonsdelight$renderingPiercePrivate(guiGraphics, mouseX, mouseY, partialTick, this.xOffset, this.width, this.height, this.searchBox, this.tabButtons, this.recipeBookPage);
            ci.cancel();
        }
    }

    @WrapOperation(method = "updateTabs", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookTabButton;getCategory()Lnet/minecraft/client/RecipeBookCategories;",
            ordinal = 0)
    )
    // Masks the DD_MONSTERPOT_SEARCH as CRAFTING_SEARCH so the upcoming "if" statement fails out, therefore making it appear even when there's no recipes
    private RecipeBookCategories dungeonsDelight$updateMPotTab(RecipeBookTabButton instance, Operation<RecipeBookCategories> original) {
        RecipeBookCategories cat = original.call(instance);
        if (cat == DDRecipeBookCategories.DD_MONSTERPOT_SEARCH) cat = RecipeBookCategories.CRAFTING_SEARCH;
        return cat;
    }
}
