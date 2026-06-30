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
import net.minecraft.world.level.block.Blocks;
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
        buildStonecuttingRecipes(exporter);
        DDRecipeCookingProvider.buildSmeltingRecipes(exporter);
    }

    public void buildStonecuttingRecipes(Consumer<FinishedRecipe> exporter) {
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

        //FOODS
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDItems.AMETHYST_ROCK_CANDY.get(), 1)
                .define('#', Items.SUGAR).define('!', Items.STICK).define('@', Items.AMETHYST_SHARD)
                .pattern("#")
                .pattern("@")
                .pattern("!")
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SLICORICE.get(), 8)
                .requires(DDBlocks.WORMROOT_STALK.get()).requires(Items.SUGAR).requires(Items.SLIME_BALL)
                .unlockedBy(getItemName(DDBlocks.WORMROOT_STALK.get()), has(DDBlocks.WORMROOT_STALK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.SLICORICE.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.MAGMARONI.get(), 2)
                .requires(DDItems.SLIME_NOODLES.get()).requires(DDItems.SLIME_NOODLES.get()).requires(Items.BLAZE_POWDER)
                .unlockedBy(getItemName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.MAGMARONI.get())));

        //FUNCTION
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.EMBEDDED_EGGS.get(), 1)
                .define('#', Items.EGG).define('@', DDItems.SCULK_POLYP.get())
                .pattern("#@#")
                .pattern("@#@")
                .pattern("#@#")
                .unlockedBy(getHasName(DDItems.SCULK_POLYP.get()), has(DDItems.SCULK_POLYP.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.TERROR_PRETA.get(), 2)
                .define('#', DDTags.ItemT.FLESHES).define('!', Items.BONE).define('@', Items.MUD)
                .pattern("!#")
                .pattern("#@")
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(DDTags.ItemT.FLESHES))
                .unlockedBy(getHasName(Items.MUD), has(Items.MUD))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.MONSTER_POT.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('!', Items.BONE).define('@', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .pattern("@!@")
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.BAMBOO_CLEAVING_BOARD.get(), 1)
                .define('#', Items.BAMBOO)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(Items.BAMBOO), has(Items.BAMBOO))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.WORMWOOD_CLEAVING_BOARD.get(), 1)
                .define('#', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get()))
                .save(exporter);

        //WORMROOT
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOTS_BLOCK.get(), 1)
                .define('#', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get()))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_PLANKS.get(), 2)
                .requires(DDBlocks.WORMROOTS_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.WORMROOTS_BLOCK.get()), has(DDBlocks.WORMROOTS_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.WORMWOOD_PLANKS.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOT_TENDRILS.get(), 4)
                .requires(DDBlocks.WORMROOT_STALK.get())
                .unlockedBy(getItemName(DDBlocks.WORMROOT_STALK.get()), has(DDBlocks.WORMROOT_STALK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.WORMROOT_TENDRILS.get()) + "_from_wormroot_stalk"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 1)
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
        doorBuilder(DDBlocks.STAINED_SCRAP_DOOR.get(), Ingredient.of(DDItems.STAINED_SCRAP.get()))
                .unlockedBy(getHasName(DDBlocks.STAINED_SCRAP_DOOR.get()), has(DDBlocks.STAINED_SCRAP_DOOR.get())).save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_TRAPDOOR.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getHasName(DDBlocks.STAINED_SCRAP_TRAPDOOR.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_PILLAR.get(), 2)
                .define('#', DDBlocks.STAINED_SCRAP_BLOCK.get())
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(DDBlocks.STAINED_SCRAP_BLOCK.get()), has(DDBlocks.STAINED_SCRAP_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getHasName(DDBlocks.STAINED_SCRAP_PILLAR.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_BLOCK.get(), 4)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .pattern("@#")
                .pattern("#@")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_BARS.get(), 16)
                .define('#', DDItems.STAINED_SCRAP.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_GATE.get(), 6)
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ROTTEN_FLESH, 9)
                .requires(DDBlocks.ROTTEN_FLESH_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.ROTTEN_FLESH_BLOCK.get()), has(DDBlocks.ROTTEN_FLESH_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(Items.ROTTEN_FLESH) + "_from_rotten_flesh_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.ROTTEN_FLESH_BLOCK.get(), 1)
                .define('#', Items.ROTTEN_FLESH)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.ROTTEN_FLESH_BLOCK.get()) + "_from_rotten_flesh"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.SCULK_MAYONNAISE.get(), 4)
                .requires(DDBlocks.SCULK_MAYONNAISE_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.SCULK_MAYONNAISE_BLOCK.get()), has(DDBlocks.SCULK_MAYONNAISE_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.SCULK_MAYONNAISE.get()) + "_from_sculk_mayonnaise_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDBlocks.SCULK_MAYONNAISE_BLOCK.get(), 1)
                .requires(DDItems.SCULK_MAYONNAISE.get()).requires(Items.GLASS_BOTTLE).requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE).requires(Items.GLASS_BOTTLE)
                .unlockedBy(getItemName(DDItems.SCULK_MAYONNAISE.get()), has(DDItems.SCULK_MAYONNAISE.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.SCULK_MAYONNAISE_BLOCK.get()) + "_from_sculk_mayonnaise"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.SCULK, 1)
                .define('#', DDItems.SCULK_POLYP.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(DDItems.SCULK_POLYP.get()), has(DDItems.SCULK_POLYP.get()))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.SCULK_APPLE.get(), 1)
                .requires(Items.APPLE).requires(DDItems.SCULK_MAYONNAISE.get())
                .unlockedBy(getItemName(DDItems.SCULK_MAYONNAISE.get()), has(DDItems.SCULK_MAYONNAISE.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.SCULK_APPLE.get())));
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
