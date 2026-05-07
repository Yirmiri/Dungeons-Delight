package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.Consumer;

public class DDRecipeProvider extends FabricRecipeProvider {
    public DDRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        createCleaver(exporter, DDItems.FLINT_CLEAVER.get(), Items.FLINT);
        createCleaver(exporter, DDItems.IRON_CLEAVER.get(), Items.IRON_INGOT);
        createCleaver(exporter, DDItems.GOLDEN_CLEAVER.get(), Items.GOLD_INGOT);
        createCleaver(exporter, DDItems.DIAMOND_CLEAVER.get(), Items.DIAMOND);
        netheriteSmithing(exporter, DDItems.DIAMOND_CLEAVER.get(), RecipeCategory.COMBAT, DDItems.NETHERITE_CLEAVER.get());
    }

    public void createCleaver(Consumer<FinishedRecipe> exporter, ItemLike output, Item ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .define('!', Items.STICK).define('#', ingredient)
                .pattern("##")
                .pattern("#!")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(exporter);
    }
}
