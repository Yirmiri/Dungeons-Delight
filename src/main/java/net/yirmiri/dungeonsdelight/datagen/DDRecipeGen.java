package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class DDRecipeGen extends FabricRecipeProvider {
    public DDRecipeGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> future) {
        super(output, future);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, DDItems.BREEZE_CREAM_CONE, 1)
                .input('#', Items.PHANTOM_MEMBRANE).input('@', DDItems.SLIME_SLAB).input('!', Items.SPIDER_EYE)
                .input('%', Items.WIND_CHARGE).input('^', ModItems.MILK_BOTTLE.get())
                .pattern(" ! ")
                .pattern("%@%")
                .pattern("^#^")
                .criterion(hasItem(Items.WIND_CHARGE), conditionsFromItem(Items.WIND_CHARGE))
                .offerTo(exporter, Identifier.of(getRecipeName(DDItems.BREEZE_CREAM_CONE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, DDItems.SLIME_SLAB, 1)
                .input(Items.SLIME_BALL).input(Items.SLIME_BALL).input(ModItems.CANVAS.get())
                .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                .offerTo(exporter, Identifier.of((getRecipeName(DDItems.SLIME_SLAB))));
    }
}