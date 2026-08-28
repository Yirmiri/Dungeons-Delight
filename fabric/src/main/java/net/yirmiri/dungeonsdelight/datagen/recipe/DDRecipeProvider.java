package net.yirmiri.dungeonsdelight.datagen.recipe;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;
import net.yirmiri.dungeonsdelight.common.recipe.datagen.MonsterBookCategory;
import net.yirmiri.dungeonsdelight.common.recipe.datagen.MonsterPotRecipeBuilder;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class DDRecipeProvider extends FabricRecipeProvider {
    public DDRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        buildCraftingRecipes(exporter);
        buildStonecuttingRecipes(exporter);
        buildMonsterPotRecipes(exporter);
        buildSmeltingRecipes(exporter);
    }

    private static void buildMonsterPotRecipes(Consumer<FinishedRecipe> exporter) {
        //-------------------------TIER I FOODS-------------------------
        monsterRecipe(MonsterBookCategory.TIER_1, Items.BONE, DDItems.FOUL_SKEWER.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_1_EXP)
                .addIngredient(Ingredient.of(DDTags.ItemT.FLESHES))
                .addIngredient(Ingredient.of(DDTags.ItemT.FLESHES))
                .addIngredient(Ingredient.of(DDTags.ItemT.FLESHES))
                .addIngredient(DDItems.ENDELVE.get())
                .group("foul_skewer")
                .unlockedBy(RecipeProvider.getHasName(DDItems.ROTTEN_TRIPE.get()), RecipeProvider.has(DDTags.ItemT.FLESHES))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.FOUL_SKEWER.get())));

        monsterRecipe(MonsterBookCategory.TIER_1, Items.BOWL, DDItems.GHOULASH.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_1_EXP)
                .addIngredient(Ingredient.of(DDTags.ItemT.FLESHES))
                .addIngredient(DDItems.SLIME_NOODLES.get())
                .addIngredient(DDItems.BLEET.get())
                .group("ghoulash")
                .unlockedBy(RecipeProvider.getHasName(DDItems.ROTTEN_TRIPE.get()), RecipeProvider.has(DDTags.ItemT.FLESHES))
                .unlockedBy(RecipeProvider.getHasName(DDItems.SLIME_NOODLES.get()), RecipeProvider.has(DDItems.SLIME_NOODLES.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.BLEET.get()), RecipeProvider.has(DDItems.BLEET.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.GHOULASH.get())));

        monsterRecipe(MonsterBookCategory.TIER_1, Items.BONE, DDItems.SPIDER_TANGHULU.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_1_EXP)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.SUGAR)
                .group("spider_tanghulu")
                .unlockedBy(RecipeProvider.getHasName(Items.SPIDER_EYE), RecipeProvider.has(Items.SPIDER_EYE))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.SPIDER_TANGHULU.get())));

        monsterRecipe(MonsterBookCategory.TIER_1, Items.BONE, DDItems.SPIDER_TANGHULU.get(), 3,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_1_EXP)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.HONEY_BOTTLE)
                .addIngredient(Items.BONE)
                .addIngredient(Items.BONE)
                .group("spider_tanghulu")
                .unlockedBy(RecipeProvider.getHasName(Items.SPIDER_EYE), RecipeProvider.has(Items.SPIDER_EYE))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.SPIDER_TANGHULU.get()) + "_from_honey_bottle"));

        monsterRecipe(MonsterBookCategory.TIER_1, Items.BONE, DDItems.SPIDER_PIE.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_1_EXP)
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.EGG)
                .addIngredient(DDItems.SPIDER_EXTRACT.get())
                .group("spider_pie")
                .unlockedBy(RecipeProvider.getHasName(DDItems.SPIDER_MEAT.get()), RecipeProvider.has(DDItems.SPIDER_MEAT.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.SPIDER_EXTRACT.get()), RecipeProvider.has(DDItems.SPIDER_EXTRACT.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.SPIDER_PIE.get())));

        monsterRecipe(MonsterBookCategory.TIER_1, null, DDItems.GHAST_ROLL.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_1_EXP)
                .addIngredient(Ingredient.of(DDTags.ItemT.GHAST_MEATS))
                .addIngredient(Ingredient.of(DDTags.ItemT.FLESHES))
                .addIngredient(Ingredient.of(DDTags.ItemT.FLESHES))
                .addIngredient(Items.TWISTING_VINES)
                .group("ghast_roll")
                .unlockedBy(RecipeProvider.getHasName(DDItems.GHAST_TENTACLE.get()), RecipeProvider.has(DDTags.ItemT.GHAST_MEATS))
                .unlockedBy(RecipeProvider.getHasName(DDItems.ROTTEN_TRIPE.get()), RecipeProvider.has(DDTags.ItemT.FLESHES))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.GHAST_ROLL.get())));

        //-------------------------TIER II FOODS-------------------------
        monsterRecipe(MonsterBookCategory.TIER_2, Items.BOWL, DDItems.SALMAGUNDI.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_2_EXP)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(DDItems.SPIDER_EXTRACT.get())
                .addIngredient(DDItems.MANALLIUM.get())
                .group("salmagundi")
                .unlockedBy(RecipeProvider.getHasName(Items.SPIDER_EYE), RecipeProvider.has(Items.SPIDER_EYE))
                .unlockedBy(RecipeProvider.getHasName(DDItems.SPIDER_MEAT.get()), RecipeProvider.has(DDItems.SPIDER_MEAT.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.SPIDER_EXTRACT.get()), RecipeProvider.has(DDItems.SPIDER_EXTRACT.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.MANALLIUM.get()), RecipeProvider.has(DDItems.MANALLIUM.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.SALMAGUNDI.get())));

        monsterRecipe(MonsterBookCategory.TIER_2, Items.BOWL, DDItems.SILVERFISH_FRIED_RICE.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_2_EXP)
                .addIngredient(DDItems.SILVERFISH_ABDOMEN.get())
                .addIngredient(DDItems.ENDELVE.get())
                .addIngredient(DDItems.ENDELVE.get())
                .addIngredient(Items.EGG)
                .addIngredient(Items.WHEAT)
                .group("silverfish_fried_rice")
                .unlockedBy(RecipeProvider.getHasName(DDItems.SILVERFISH_ABDOMEN.get()), RecipeProvider.has(DDItems.SILVERFISH_ABDOMEN.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.ENDELVE.get()), RecipeProvider.has(DDItems.ENDELVE.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.SILVERFISH_FRIED_RICE.get())));

        monsterRecipe(MonsterBookCategory.TIER_2, Items.BOWL, DDItems.GUNPOWDER_BAKED_ARACHNID.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_2_EXP)
                .addIngredient(DDItems.CREEPERILLA_SQUIB.get())
                .addIngredient(DDItems.CREEPERILLA_SQUIB.get())
                .addIngredient(Items.GUNPOWDER)
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(DDItems.BLEET.get())
                .group("gunpowder_baked_arachnid")
                .unlockedBy(RecipeProvider.getHasName(DDItems.CREEPERILLA_SQUIB.get()), RecipeProvider.has(DDItems.CREEPERILLA_SQUIB.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.SPIDER_MEAT.get()), RecipeProvider.has(DDItems.SPIDER_MEAT.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.BLEET.get()), RecipeProvider.has(DDItems.BLEET.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.GUNPOWDER_BAKED_ARACHNID.get())));

        monsterRecipe(MonsterBookCategory.TIER_2, null, DDItems.DYNAMITE_ROLL.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_2_EXP)
                .addIngredient(DDItems.SILVERFISH_ABDOMEN.get())
                .addIngredient(DDItems.CREEPERILLA_SQUIB.get())
                .addIngredient(DDItems.CREEPERILLA_SQUIB.get())
                .addIngredient(Items.DRIED_KELP)
                .group("dynamite_roll")
                .unlockedBy(RecipeProvider.getHasName(DDItems.SILVERFISH_ABDOMEN.get()), RecipeProvider.has(DDItems.SILVERFISH_ABDOMEN.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.CREEPERILLA_SQUIB.get()), RecipeProvider.has(DDItems.CREEPERILLA_SQUIB.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.SPIDER_EXTRACT.get()), RecipeProvider.has(DDItems.SPIDER_EXTRACT.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.DYNAMITE_ROLL.get())));

        monsterRecipe(MonsterBookCategory.TIER_2, Items.GLASS_BOTTLE, DDItems.TARO_MILK_TEA.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_2_EXP)
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.MILK_BUCKET)
                .addIngredient(DDItems.RANCID_REDUCTION.get())
                .group("taro_milk_tea")
                .unlockedBy(RecipeProvider.getHasName(DDItems.ROTBULB.get()), RecipeProvider.has(DDItems.ROTBULB.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.RANCID_REDUCTION.get()), RecipeProvider.has(DDItems.RANCID_REDUCTION.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.TARO_MILK_TEA.get())));

        monsterRecipe(MonsterBookCategory.TIER_2, Items.GLASS_BOTTLE, DDItems.BUBBLE_EYE_TEA.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_2_EXP)
                .addIngredient(Items.FERMENTED_SPIDER_EYE)
                .addIngredient(Items.FERMENTED_SPIDER_EYE)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.MILK_BUCKET)
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(DDItems.SPIDER_EXTRACT.get())
                .group("bubble_eye_tea")
                .unlockedBy(RecipeProvider.getHasName(Items.FERMENTED_SPIDER_EYE), RecipeProvider.has(Items.FERMENTED_SPIDER_EYE))
                .unlockedBy(RecipeProvider.getHasName(DDItems.SPIDER_EXTRACT.get()), RecipeProvider.has(DDItems.SPIDER_EXTRACT.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.BUBBLE_EYE_TEA.get())));

        monsterRecipe(MonsterBookCategory.TIER_2, Items.GLASS_BOTTLE, DDItems.EGGNOG.get(), 1,
                MonsterCookingRecipe.DEFAULT_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_2_EXP)
                .addIngredient(DDItems.CLEAVED_ANCIENT_EGG.get())
                .addIngredient(DDItems.CLEAVED_ANCIENT_EGG.get())
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.MILK_BUCKET)
                .addIngredient(DDItems.SLICORICE.get())
                .group("eggnog")
                .unlockedBy(RecipeProvider.getHasName(DDItems.CLEAVED_ANCIENT_EGG.get()), RecipeProvider.has(DDItems.CLEAVED_ANCIENT_EGG.get()))
                .unlockedBy(RecipeProvider.getHasName(DDItems.SLICORICE.get()), RecipeProvider.has(DDItems.SLICORICE.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.EGGNOG.get())));

        //-------------------------TIER III FOODS-------------------------
        monsterRecipe(MonsterBookCategory.TIER_3, DDItems.STAINED_SCRAP.get(), DDItems.TELEPOTAGE_BLOCK.get(), 1,
                MonsterCookingRecipe.BANQUET_COOKING_TIME, MonsterCookingRecipe.DEFAULT_SUCCESS, MonsterCookingRecipe.TIER_3_EXP)
                .addIngredient(Items.ENDER_PEARL)
                .addIngredient(Items.ENDER_PEARL)
                .addIngredient(Items.ENDER_PEARL)
                .addIngredient(DDItems.CREEPERILLA_SQUIB.get())
                .addIngredient(Items.SOUL_SAND)
                .addIngredient(Items.CHORUS_FRUIT)
                .group("telepotage_block")
                .unlockedBy(RecipeProvider.getHasName(DDItems.CREEPERILLA_SQUIB.get()), RecipeProvider.has(DDItems.CREEPERILLA_SQUIB.get()))
                .unlockedBy(RecipeProvider.getHasName(Items.CHORUS_FRUIT), RecipeProvider.has(Items.CHORUS_FRUIT))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.TELEPOTAGE_BLOCK.get())));
    }

    ////////////////////////////////////////////////////////

    private static void buildSmeltingRecipes(Consumer<FinishedRecipe> exporter) {
        cookRecipes(exporter, "smelting", RecipeSerializer.SMELTING_RECIPE, 200);
        cookRecipes(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);
        cookRecipes(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);

        smeltRecipes(exporter);
    }

    public static void cookRecipes(Consumer<FinishedRecipe> exporter, String m, RecipeSerializer<? extends AbstractCookingRecipe> s, int time) {
        DDRecipeProvider.simpleCookingRecipe(exporter, m, s, time, DDItems.SPIDER_MEAT.get(), DDItems.COOKED_SPIDER_MEAT.get(), 0.35F);
        DDRecipeProvider.simpleCookingRecipe(exporter, m, s, time, DDItems.SNIFFER_SHANK.get(), DDItems.COOKED_SNIFFER_SHANK.get(), 0.35F);
    }

    public static void smeltRecipes(Consumer<FinishedRecipe> exporter) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(DDBlocks.COBBLED_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, DDBlocks.CRACKED_COBBLED_BRICKS.get(),
                0.1F, 200)
                .unlockedBy(getHasName(DDBlocks.COBBLED_BRICKS.get()), has(DDBlocks.COBBLED_BRICKS.get()))
                .save(exporter);
    }

    ////////////////////////////////////////////////////////

    private static MonsterPotRecipeBuilder monsterRecipe(MonsterBookCategory category, @Nullable ItemLike container, ItemLike result, int amount, int cookingTime, float successChance, float exp) {
        return MonsterPotRecipeBuilder.create(RecipeCategory.FOOD, category, container, result, amount, cookingTime, successChance, exp);
    }

    public void buildStonecuttingRecipes(Consumer<FinishedRecipe> exporter) {
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CHISELED_STAINED_SCRAP.get(), DDBlocks.STAINED_SCRAP_BLOCK.get(), 4);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CHISELED_STAINED_SCRAP.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 1);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP.get(), DDBlocks.STAINED_SCRAP_BLOCK.get(), 4);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 1);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 2);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_GRATE.get(), DDBlocks.STAINED_SCRAP_BLOCK.get(), 4);
        //todo cobbled bricks/tiles
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

        //COBBLED
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.COBBLED_BRICKS.get(), 4)
                .define('#', Blocks.COBBLESTONE).define('@', Blocks.COBBLED_DEEPSLATE)
                .pattern("#@")
                .pattern("@#")
                .unlockedBy(getHasName(Blocks.COBBLESTONE), has(Blocks.COBBLESTONE))
                .unlockedBy(getHasName(Blocks.COBBLED_DEEPSLATE), has(Blocks.COBBLED_DEEPSLATE))
                .save(exporter);
        stairBuilder(DDBlocks.COBBLED_BRICK_STAIRS.get(), Ingredient.of(DDBlocks.COBBLED_BRICKS.get()))
                .unlockedBy(getHasName(DDBlocks.COBBLED_BRICK_STAIRS.get()), has(DDBlocks.COBBLED_BRICK_STAIRS.get())).save(exporter);
        slab(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.COBBLED_BRICK_SLAB.get(), DDBlocks.COBBLED_BRICKS.get());
        wall(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.COBBLED_BRICK_WALL.get(), DDBlocks.COBBLED_BRICKS.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.MOSSY_COBBLED_BRICKS.get(), 1)
                .requires(DDBlocks.COBBLED_BRICKS.get()).requires(Ingredient.of(Blocks.MOSS_BLOCK, Blocks.VINE))
                .unlockedBy(getItemName(Blocks.MOSS_BLOCK), has(Blocks.MOSS_BLOCK))
                .unlockedBy(getItemName(Blocks.VINE), has(Blocks.VINE))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.MOSSY_COBBLED_BRICKS.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.MOSSY_COBBLED_BRICKS.get(), 4)
                .define('#', Blocks.MOSSY_COBBLESTONE).define('@', Blocks.COBBLED_DEEPSLATE)
                .pattern("#@")
                .pattern("@#")
                .unlockedBy(getHasName(Blocks.MOSSY_COBBLESTONE), has(Blocks.MOSSY_COBBLESTONE))
                .unlockedBy(getHasName(Blocks.COBBLED_DEEPSLATE), has(Blocks.COBBLED_DEEPSLATE))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.MOSSY_COBBLED_BRICKS.get()) + "_from_mossy_cobblestone"));
        stairBuilder(DDBlocks.MOSSY_COBBLED_BRICK_STAIRS.get(), Ingredient.of(DDBlocks.MOSSY_COBBLED_BRICKS.get()))
                .unlockedBy(getHasName(DDBlocks.MOSSY_COBBLED_BRICK_STAIRS.get()), has(DDBlocks.MOSSY_COBBLED_BRICK_STAIRS.get())).save(exporter);
        slab(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.MOSSY_COBBLED_BRICK_SLAB.get(), DDBlocks.MOSSY_COBBLED_BRICKS.get());
        wall(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.MOSSY_COBBLED_BRICK_WALL.get(), DDBlocks.MOSSY_COBBLED_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.COBBLED_TILES.get(), 4)
                .define('#', DDBlocks.COBBLED_BRICKS.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(DDBlocks.COBBLED_BRICKS.get()), has(DDBlocks.COBBLED_BRICKS.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getHasName(DDBlocks.COBBLED_TILES.get())));
        stairBuilder(DDBlocks.COBBLED_TILE_STAIRS.get(), Ingredient.of(DDBlocks.COBBLED_TILES.get()))
                .unlockedBy(getHasName(DDBlocks.COBBLED_TILE_STAIRS.get()), has(DDBlocks.COBBLED_TILE_STAIRS.get())).save(exporter);
        slab(exporter, RecipeCategory.BUILDING_BLOCKS, DDBlocks.COBBLED_TILE_SLAB.get(), DDBlocks.COBBLED_TILES.get());

        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, DDBlocks.CHISELED_COBBLE.get(), Ingredient.of(DDBlocks.COBBLED_BRICK_SLAB.get()));

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDBlocks.SCULK_MAYONNAISE_BLOCK.get(), 1)
                .requires(DDItems.SCULK_MAYONNAISE.get()).requires(DDItems.SCULK_MAYONNAISE.get())
                .requires(DDItems.SCULK_MAYONNAISE.get()).requires(DDItems.SCULK_MAYONNAISE.get())
                .unlockedBy(getItemName(DDItems.SCULK_MAYONNAISE.get()), has(DDItems.SCULK_MAYONNAISE.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.SCULK_MAYONNAISE_BLOCK.get()) + "_from_sculk_mayonnaise"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.SCULK_MAYONNAISE.get(), 4)
                .requires(DDBlocks.SCULK_MAYONNAISE_BLOCK.get()).requires(Items.GLASS_BOTTLE).requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE).requires(Items.GLASS_BOTTLE)
                .unlockedBy(getItemName(DDBlocks.SCULK_MAYONNAISE_BLOCK.get()), has(DDBlocks.SCULK_MAYONNAISE_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.SCULK_MAYONNAISE.get()) + "_from_sculk_mayonnaise_block"));

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.BLACK_APPLE.get(), 1)
                .requires(Ingredient.of(Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE)).requires(DDItems.RANCID_REDUCTION.get())
                .unlockedBy(getItemName(DDItems.RANCID_REDUCTION.get()), has(DDItems.RANCID_REDUCTION.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.BLACK_APPLE.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDItems.LIVING_TORCH.get(), 4)
                .define('#', DDItems.STAINED_SCRAP_FRAGMENT.get()).define('@', ItemTags.COALS).define('!', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern("@")
                .pattern("#")
                .pattern("!")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP_FRAGMENT.get()), has(DDItems.STAINED_SCRAP_FRAGMENT.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDItems.LIVING_CANDLE.get(), 1)
                .define('#', DDItems.STAINED_SCRAP_FRAGMENT.get()).define('@', DDItems.GUNK.get()).define('!', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern(" ! ")
                .pattern(" @ ")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP_FRAGMENT.get()), has(DDItems.STAINED_SCRAP_FRAGMENT.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDItems.LIVING_CAMPFIRE.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', ItemTags.COALS).define('!', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern(" @ ")
                .pattern("#!#")
                .pattern(" # ")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.DUNGEON_STOVE.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', ItemTags.COALS).define('$', DDBlocks.MOSSY_COBBLED_BRICKS.get())
                .pattern("###")
                .pattern("$ $")
                .pattern("$@$")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDItems.LIVING_LANTERN.get(), 1)
                .define('#', DDItems.STAINED_SCRAP_FRAGMENT.get()).define('@', DDItems.LIVING_TORCH.get())
                .pattern("###")
                .pattern("#@#")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP_FRAGMENT.get()), has(DDItems.STAINED_SCRAP_FRAGMENT.get()))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.GUNK.get(), 9)
                .requires(DDBlocks.GUNK_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.GUNK_BLOCK.get()), has(DDBlocks.GUNK_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.GUNK.get()) + "_from_gunk_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.GUNK_BLOCK.get(), 1)
                .define('#', DDItems.GUNK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.GUNK.get()), has(DDItems.GUNK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.GUNK_BLOCK.get()) + "_from_gunk"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.BUBBLEGUNK.get(), 1)
                .requires(DDItems.GUNK.get()).requires(DDItems.GUNK.get()).requires(Items.SUGAR).requires(DDTags.ItemT.FLESHES)
                .unlockedBy(getItemName(DDItems.GUNK.get()), has(DDItems.GUNK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.BUBBLEGUNK.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, DDItems.ROT_AND_STEEL.get(), 1)
                .requires(DDItems.STAINED_SCRAP.get()).requires(DDItems.GUNK.get())
                .unlockedBy(getItemName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.ROT_AND_STEEL.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.STAINED_SCRAP_CHAIN.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .pattern("@")
                .pattern("#")
                .pattern("@")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.GUNK_BLOCK.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.ENDELVE.get(), 9)
                .requires(DDBlocks.ENDELVE_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.ENDELVE_BLOCK.get()), has(DDBlocks.ENDELVE_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.ENDELVE.get()) + "_from_endelve_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.ENDELVE_BLOCK.get(), 1)
                .define('#', DDItems.ENDELVE.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.ENDELVE.get()), has(DDItems.ENDELVE.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.ENDELVE_BLOCK.get()) + "_from_endelve"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.BLEET.get(), 9)
                .requires(DDBlocks.BLEET_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.BLEET_BLOCK.get()), has(DDBlocks.BLEET_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.BLEET.get()) + "_from_bleet_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.BLEET_BLOCK.get(), 1)
                .define('#', DDItems.BLEET.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.BLEET.get()), has(DDItems.BLEET.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.BLEET_BLOCK.get()) + "_from_bleet"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.MANALLIUM.get(), 9)
                .requires(DDBlocks.MANALLIUM_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.MANALLIUM_BLOCK.get()), has(DDBlocks.MANALLIUM_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.MANALLIUM.get()) + "_from_manallium_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.MANALLIUM_BLOCK.get(), 1)
                .define('#', DDItems.MANALLIUM.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.MANALLIUM.get()), has(DDItems.MANALLIUM.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.MANALLIUM_BLOCK.get()) + "_from_manallium"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.ROTBULB.get(), 9)
                .requires(DDBlocks.ROTBULB_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.ROTBULB_BLOCK.get()), has(DDBlocks.ROTBULB_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDItems.ROTBULB.get()) + "_from_rotbulb_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.ROTBULB_BLOCK.get(), 1)
                .define('#', DDItems.ROTBULB.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(DDItems.ROTBULB.get()), has(DDItems.ROTBULB.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.ROTBULB_BLOCK.get()) + "_from_rotbulb"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.POISONOUS_POTATO, 9)
                .requires(DDBlocks.POISONOUS_POTATO_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.POISONOUS_POTATO_BLOCK.get()), has(DDBlocks.POISONOUS_POTATO_BLOCK.get()))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(Items.POISONOUS_POTATO) + "_from_poisonous_potato_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.POISONOUS_POTATO_BLOCK.get(), 1)
                .define('#', Items.POISONOUS_POTATO)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(Items.POISONOUS_POTATO), has(Items.POISONOUS_POTATO))
                .save(exporter, RunicLib.customid(DungeonsDelight.MOD_ID, getItemName(DDBlocks.POISONOUS_POTATO_BLOCK.get()) + "_from_poisonous_potato"));
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
