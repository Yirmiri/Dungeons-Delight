package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.registry.DDItems;

import java.util.concurrent.CompletableFuture;

public class DDRecipeGen extends FabricRecipeProvider {
    public DDRecipeGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> future) {
        super(output, future);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, DDItems.BREEZE_CREAM_CONE, 1)
                .input('#', Items.PHANTOM_MEMBRANE).input('@', Items.SLIME_BALL).input('!', Items.SPIDER_EYE).input('%', Items.WIND_CHARGE)
                .pattern(" @!")
                .pattern("%%@")
                .pattern("#% ")
                .criterion(hasItem(Items.WIND_CHARGE), conditionsFromItem(Items.WIND_CHARGE))
                .offerTo(exporter, Identifier.of(getRecipeName(DDItems.BREEZE_CREAM_CONE)));
    }
}