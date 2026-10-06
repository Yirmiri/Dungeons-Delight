package net.yirmiri.dungeonsdelight.core.mixin.client.recipe_book;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;
import net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook.ICustomRecBkRender;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookCategories;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
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
    @Shadow @Final @Mutable private RecipeBookPage recipeBookPage;

    @WrapOperation(method = "initVisuals", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;updateCollections(Z)V"))
    // Wraps first update call to ensure it happens prior to it
    private void dungeonsDelight$initStopThat(RecipeBookComponent instance, boolean b, Operation<Void> original) {
        if ((Object)this instanceof ICustomRecBkRender r) {
            r.dungeonsdelight$modifyTableSystems(this.searchBox, this.tabButtons);

            RecipeBookPage mutable = r.dungeonsdelight$modifyRecipePage();
            if (mutable != null) {
                int i = (this.width - 147) / 2 - this.xOffset;
                int j = (this.height - 166) / 2;

                mutable.init(this.recipeBookPage.getMinecraft(), i, j);
                mutable.addListener((RecipeBookComponent)(Object)this);
                this.recipeBookPage = mutable;
            }
        }

        original.call(instance, b);
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void dungeonsDelight$stopRenderingBecauseBad(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if ((Object)this instanceof ICustomRecBkRender r) {
            r.dungeonsdelight$renderingPiercePrivate(guiGraphics, mouseX, mouseY, partialTick, this.xOffset, this.width, this.height, this.searchBox, this.tabButtons, this.recipeBookPage);
            ci.cancel();
        }
    }

    @Inject(method = "checkSearchStringUpdate", at = @At("HEAD"))
    private void dungeonsDelight$checkSearchStringCrap(CallbackInfo ci) {
        if ((Object)this instanceof ICustomRecBkRender r) {
            r.dungeonsDelight$textSearchAppend(this.searchBox);
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
