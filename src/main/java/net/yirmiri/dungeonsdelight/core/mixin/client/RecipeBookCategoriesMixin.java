package net.yirmiri.dungeonsdelight.core.mixin.client;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotRecipeCategories;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(RecipeBookCategories.class)
public class RecipeBookCategoriesMixin {

    @Inject(method = "getCategories", at = @At("HEAD"), cancellable = true)
    private static void dungeonsDelight$getCategories(RecipeBookType type, CallbackInfoReturnable<List<RecipeBookCategories>> cir) {
        if (type.equals(MonsterPotRecipeCategories.MONSTER_COOKING))
            cir.setReturnValue(List.of(
                    MonsterPotRecipeCategories.MONSTER_SEARCH,
                    MonsterPotRecipeCategories.MONSTER_MEALS,
                    MonsterPotRecipeCategories.MONSTER_DRINKS,
                    MonsterPotRecipeCategories.MONSTER_MISC
            ));
    }
}
