package net.yirmiri.dungeonsdelight.core.mixin.client.recipe_book;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.Recipe;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookCategories;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    @Inject(method = "getCategory", at = @At("HEAD"), cancellable = true)
    private static void dundelight$potCat(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookCategories> cir) {
        if (recipe instanceof MonsterCookingRecipe potRec) {
            switch (potRec.getRecipeTab()) {
                case FOOD:      cir.setReturnValue(DDRecipeBookCategories.DD_MONSTERPOT_FOOD);      break;
                case DRINK:     cir.setReturnValue(DDRecipeBookCategories.DD_MONSTERPOT_DRINKS);    break;
                case MISC:      cir.setReturnValue(DDRecipeBookCategories.DD_MONSTERPOT_MISC);      break;
                default:        throw new IllegalArgumentException();
            }
        }
    }
}