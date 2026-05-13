package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.Consumer;

public class DDRecipeCookingProvider {
    public static void buildSmeltingRecipes(Consumer<FinishedRecipe> exporter) {
        cookRecipes(exporter, "smelting", RecipeSerializer.SMELTING_RECIPE, 200);
        cookRecipes(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);
        cookRecipes(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);
    }

    private static void cookRecipes(Consumer<FinishedRecipe> exporter, String m, RecipeSerializer<? extends AbstractCookingRecipe> s, int time) {
        DDRecipeProvider.simpleCookingRecipe(exporter, m, s, time, DDItems.SPIDER_MEAT.get(), DDItems.COOKED_SPIDER_MEAT.get(), 0.35F);
        DDRecipeProvider.simpleCookingRecipe(exporter, m, s, time, DDItems.SNIFFER_SHANK.get(), DDItems.COOKED_SNIFFER_SHANK.get(), 0.35F);
    }
}
