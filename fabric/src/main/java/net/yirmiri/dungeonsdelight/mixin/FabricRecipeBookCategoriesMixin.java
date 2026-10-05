package net.yirmiri.dungeonsdelight.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.RecipeBookCategories;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookCategories;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Forge Side: net.yirmiri.dungeonsdelight.ForgeDungeonsDelightClient
@Mixin(RecipeBookCategories.class)
public class FabricRecipeBookCategoriesMixin {
    @Shadow @Final public static Map<RecipeBookCategories, List<RecipeBookCategories>> AGGREGATE_CATEGORIES;

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