package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.Consumer;

public class DDRecipeProvider extends FabricRecipeProvider {
    public DDRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        buildCraftingRecipes(exporter);
        DDRecipeCookingProvider.buildSmeltingRecipes(exporter);
    }

    public void buildCraftingRecipes(Consumer<FinishedRecipe> exporter) {
        createCleaver(exporter, DDItems.FLINT_CLEAVER.get(), Items.FLINT);
        createCleaver(exporter, DDItems.IRON_CLEAVER.get(), Items.IRON_INGOT);
        createCleaver(exporter, DDItems.GOLDEN_CLEAVER.get(), Items.GOLD_INGOT);
        createCleaver(exporter, DDItems.DIAMOND_CLEAVER.get(), Items.DIAMOND);
        netheriteSmithing(exporter, DDItems.DIAMOND_CLEAVER.get(), RecipeCategory.COMBAT, DDItems.NETHERITE_CLEAVER.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.TERROR_PRETA.get(), 2)
                .define('#', DDTags.ItemT.ROTTEN_FLESHES).define('!', Items.BONE).define('@', Items.MUD)
                .pattern("!#")
                .pattern("#@")
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(DDTags.ItemT.ROTTEN_FLESHES))
                .unlockedBy(getHasName(Items.MUD), has(Items.MUD))
                .save(exporter);
    }

    public static void createCleaver(Consumer<FinishedRecipe> exporter, ItemLike output, Item ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .define('!', Items.STICK).define('#', ingredient)
                .pattern("##")
                .pattern("#!")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(exporter);
    }

    public static void simpleCookingRecipe(Consumer<FinishedRecipe> exporter, String m, RecipeSerializer<? extends AbstractCookingRecipe> s, int time, ItemLike ingredient, ItemLike result, float exp) {
        SimpleCookingRecipeBuilder.generic(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, time, s)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, getItemName(result) + "_from_" + m);
    }
}
