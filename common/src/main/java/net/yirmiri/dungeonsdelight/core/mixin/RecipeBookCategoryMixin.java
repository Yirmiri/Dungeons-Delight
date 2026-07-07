package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookCategories;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(RecipeBookCategories.class)
public class RecipeBookCategoryMixin {
    @Inject(method = "getCategories", at = @At("HEAD"), cancellable = true)
    private static void dungeonsDelight$getCategories(RecipeBookType type, CallbackInfoReturnable<List<RecipeBookCategories>> cir) {
        if (type.equals(DDRecipeBookTypes.DD_MONSTERPOT)) cir.setReturnValue(DDRecipeBookCategories.MONSTER_POT_CAGTEGORIES);
    }
}