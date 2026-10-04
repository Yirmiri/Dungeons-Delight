package net.yirmiri.dungeonsdelight.core.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookCategories;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(RecipeBookCategories.class)
public class RecipeBookCategoryMixin {
    @Shadow @Final public static Map<RecipeBookCategories, List<RecipeBookCategories>> AGGREGATE_CATEGORIES;

    @Inject(method = "getCategories", at = @At("HEAD"), cancellable = true)
    private static void dungeonsDelight$getCategories(RecipeBookType type, CallbackInfoReturnable<List<RecipeBookCategories>> cir) {
        if (type.equals(DDRecipeBookTypes.DD_MONSTERPOT)) cir.setReturnValue(DDRecipeBookCategories.MONSTER_POT_CAGTEGORIES);
    }

    // Needed to make recipes register in the book properly
    static {
        Map<RecipeBookCategories, List<RecipeBookCategories>> preGo = new HashMap<>(Map.copyOf(AGGREGATE_CATEGORIES));
        preGo.put(
                DDRecipeBookCategories.DD_MONSTERPOT_SEARCH,
                List.of(
                        DDRecipeBookCategories.DD_MONSTERPOT_FOOD,
                        DDRecipeBookCategories.DD_MONSTERPOT_DRINKS,
                        DDRecipeBookCategories.DD_MONSTERPOT_MISC
                )
        );
        AGGREGATE_CATEGORIES = ImmutableMap.copyOf(preGo);
    }
}