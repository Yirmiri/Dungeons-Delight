package net.yirmiri.dungeonsdelight.datagen;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
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
        buildStonecutting(exporter);
        DDRecipeCookingProvider.buildSmeltingRecipes(exporter);
    }

    public void buildStonecutting(Consumer<FinishedRecipe> exporter) {
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CHISELED_STAINED_SCRAP.get(), DDBlocks.STAINED_SCRAP_BLOCK.get(), 4);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CHISELED_STAINED_SCRAP.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 1);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP.get(), DDBlocks.STAINED_SCRAP_BLOCK.get(), 4);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 1);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 2);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_GRATE.get(), DDBlocks.STAINED_SCRAP_BLOCK.get(), 4);
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.MONSTER_POT.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('!', Items.BONE).define('@', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .pattern("@!@")
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter);

        //WORMROOT
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOTS_BLOCK.get(), 1)
                .define('#', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get()))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOT_TENDRILS.get(), 9)
                .requires(DDBlocks.WORMROOTS_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.WORMROOTS_BLOCK.get()), has(DDBlocks.WORMROOTS_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.WORMROOT_TENDRILS.get()) + "_from_wormroots_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_PLANKS.get(), 2)
                .define('#', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.WORMWOOD_PLANKS.get()) + "_from_wormroots"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOT_TENDRILS.get(), 4)
                .requires(DDBlocks.WORMROOT_STALK.get())
                .unlockedBy(getItemName(DDBlocks.WORMROOT_STALK.get()), has(DDBlocks.WORMROOT_STALK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.WORMROOT_TENDRILS.get()) + "_from_wormroot_stalk"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 2)
                .define('#', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, "stick_from_wormroots"));

        buttonBuilder(DDBlocks.WORMWOOD_BUTTON.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_BUTTON.get()), has(DDBlocks.WORMWOOD_BUTTON.get())).save(exporter);
        doorBuilder(DDBlocks.WORMWOOD_DOOR.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_DOOR.get()), has(DDBlocks.WORMWOOD_DOOR.get())).save(exporter);
        trapdoorBuilder(DDBlocks.WORMWOOD_TRAPDOOR.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_TRAPDOOR.get()), has(DDBlocks.WORMWOOD_TRAPDOOR.get())).save(exporter);
        fenceBuilder(DDBlocks.WORMWOOD_FENCE.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_FENCE.get()), has(DDBlocks.WORMWOOD_FENCE.get())).save(exporter);
        fenceGateBuilder(DDBlocks.WORMWOOD_FENCE_GATE.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_FENCE_GATE.get()), has(DDBlocks.WORMWOOD_FENCE_GATE.get())).save(exporter);
        pressurePlate(exporter, DDBlocks.WORMWOOD_PRESSURE_PLATE.get(), DDBlocks.WORMWOOD_PLANKS.get());
        slab(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_SLAB.get(), DDBlocks.WORMWOOD_PLANKS.get());
        slab(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_MOSAIC_SLAB.get(), DDBlocks.WORMWOOD_MOSAIC.get());
        stairBuilder(DDBlocks.WORMWOOD_STAIRS.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_STAIRS.get()), has(DDBlocks.WORMWOOD_STAIRS.get())).save(exporter);
        stairBuilder(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get(), Ingredient.of(DDBlocks.WORMWOOD_MOSAIC.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get()), has(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get())).save(exporter);
        mosaicBuilder(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_MOSAIC.get(), DDBlocks.WORMWOOD_SLAB.get());

        //STAINED SCRAP
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_BLOCK.get(), 4)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .pattern("@#")
                .pattern("#@")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_BARS.get(), 16)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .pattern("#@#")
                .pattern("#@#")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP.get(), 4)
                .define('#', DDBlocks.STAINED_SCRAP_BLOCK.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(DDBlocks.STAINED_SCRAP_BLOCK.get()), has(DDBlocks.STAINED_SCRAP_BLOCK.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.CHISELED_STAINED_SCRAP.get(), 1)
                .define('#', DDBlocks.CUT_STAINED_SCRAP_SLAB.get())
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(DDBlocks.CUT_STAINED_SCRAP_SLAB.get()), has(DDBlocks.CUT_STAINED_SCRAP_SLAB.get()))
                .save(exporter);

        stairBuilder(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), Ingredient.of(DDBlocks.CUT_STAINED_SCRAP.get()))
                .unlockedBy(getHasName(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get()), has(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get()))
                .save(exporter);
        slab(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDBlocks.CUT_STAINED_SCRAP.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.STAINED_SCRAP_FRAGMENT.get(), 9)
                .requires(DDItems.STAINED_SCRAP.get())
                .unlockedBy(getItemName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.STAINED_SCRAP_FRAGMENT.get()) + "_from_stained_scrap"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDItems.STAINED_SCRAP.get(), 1)
                .define('#', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP_FRAGMENT.get()), has(DDItems.STAINED_SCRAP_FRAGMENT.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.STAINED_SCRAP.get()) + "_from_stained_scrap_fragment"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_GRATE.get(), 4)
                .define('#', DDBlocks.STAINED_SCRAP_BLOCK.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ").unlockedBy(getHasName(DDBlocks.STAINED_SCRAP_BLOCK.get()), has(DDBlocks.STAINED_SCRAP_BLOCK.get()))
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
