package net.yirmiri.dungeonsdelight.core.mixin.client;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.Recipe;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    @Inject(method = "getCategory", at = @At("HEAD"), cancellable = true)
    private static void dundelight$potCat(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookCategories> cir) {
        if (recipe instanceof MonsterCookingRecipe potRec) {
            cir.setReturnValue(potRec.getRecipeTab());
        }
    }
}