package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;
import net.yirmiri.dungeonsdelight.common.recipe.datagen.MonsterBookCategory;
import net.yirmiri.dungeonsdelight.common.recipe.datagen.MonsterPotRecipeBuilder;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class DDFoodRecipesProvider {
    public static void buildMonsterPotRecipes(Consumer<FinishedRecipe> exporter) {
        basicPotRecipe(MonsterBookCategory.MEALS, Items.BONE, DDItems.FOUL_SKEWER.get(), 1, MonsterCookingRecipe.DEFAULT_COOKING_TIME, 1F, 0.2F)
                .addIngredient(DDItems.ROTTEN_TRIPE.get())
                .addIngredient(DDItems.ROTTEN_TRIPE.get())
                .addIngredient(DDItems.ROTTEN_TRIPE.get())
                .group("foul_skewer")
                .unlockedBy(RecipeProvider.getHasName(DDItems.ROTTEN_TRIPE.get()), RecipeProvider.has(DDItems.ROTTEN_TRIPE.get()))
                .save(exporter);
    }

    ////////////////////////////////////////////////////////

    public static void buildSmeltingRecipes(Consumer<FinishedRecipe> exporter) {
        cookRecipes(exporter, "smelting", RecipeSerializer.SMELTING_RECIPE, 200);
        cookRecipes(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);
        cookRecipes(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);
    }

    private static void cookRecipes(Consumer<FinishedRecipe> exporter, String m, RecipeSerializer<? extends AbstractCookingRecipe> s, int time) {
        DDRecipeProvider.simpleCookingRecipe(exporter, m, s, time, DDItems.SPIDER_MEAT.get(), DDItems.COOKED_SPIDER_MEAT.get(), 0.35F);
        DDRecipeProvider.simpleCookingRecipe(exporter, m, s, time, DDItems.SNIFFER_SHANK.get(), DDItems.COOKED_SNIFFER_SHANK.get(), 0.35F);
    }

    ////////////////////////////////////////////////////////

    private static MonsterPotRecipeBuilder basicPotRecipe(MonsterBookCategory category, @Nullable ItemLike container, ItemLike result, int amount, int cookingTime, float successChance, float exp) {
        return MonsterPotRecipeBuilder.create(
                RecipeCategory.FOOD,
                category,
                container,
                result,
                amount,
                cookingTime,
                successChance,
                exp

        );
    }
}
