package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDRecipeRegistries;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

public class DDRecipeGen extends RecipeProvider implements IConditionBuilder {
    public DDRecipeGen(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        crafting(consumer);
        smelting(consumer);
        cutting(consumer);
        SpecialRecipeBuilder.special(DDRecipeRegistries.MONSTER_FOOD_SERVING.get()).save(consumer, "monster_food_serving");
    }

    private static void crafting(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.MONSTER_POT.get(), 1)
                .define('#', ModItems.COOKING_POT.get()).define('@', DDItems.STAINED_SCRAP.get())
                .define('$', Items.SPIDER_EYE).define('&', Items.BONE)
                .pattern("$&$")
                .pattern("@#@")
                .pattern("@@@").unlockedBy(getHasName(ModItems.COOKING_POT.get()), has(ModItems.COOKING_POT.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SLIME_BAR.get(), 1)
                .requires(DDTags.ItemT.SLIME_BALLS).requires(DDTags.ItemT.SLIME_BALLS).requires(ModItems.CANVAS.get()).requires(Items.DRIED_KELP)
                .unlockedBy(getItemName(Items.SLIME_BALL), has(Items.SLIME_BALL))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.SLIME_BAR.get()) + "_from_shapeless");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.DUNGEON_STOVE.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', Items.TUFF).define('%', Items.CAMPFIRE)
                .pattern("###")
                .pattern("@ @")
                .pattern("@%@").unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDBlocks.LIVING_CANDLE.get(), 4)
                .pattern("W")
                .pattern("G")
                .pattern("S")
                .define('W', DDBlocks.WORMROOTS.get())
                .define('G', DDItems.GUNK.get())
                .define('S', DDItems.STAINED_SCRAP.get())
                .unlockedBy(getHasName(DDItems.GUNK.get()), has(DDItems.GUNK.get()))
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 2)
                .define('#', DDBlocks.WORMROOTS.get())
                .pattern("#")
                .pattern("#").unlockedBy(getHasName(DDBlocks.WORMROOTS.get()), has(DDBlocks.WORMROOTS.get()))
                .save(consumer, "dungeonsdelight:stick_from_wormroots");

        buttonBuilder(DDBlocks.WORMWOOD_BUTTON.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_BUTTON.get()), has(DDBlocks.WORMWOOD_BUTTON.get())).save(consumer);
        doorBuilder(DDBlocks.WORMWOOD_DOOR.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_DOOR.get()), has(DDBlocks.WORMWOOD_DOOR.get())).save(consumer);
        trapdoorBuilder(DDBlocks.WORMWOOD_TRAPDOOR.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_TRAPDOOR.get()), has(DDBlocks.WORMWOOD_TRAPDOOR.get())).save(consumer);
        fenceBuilder(DDBlocks.WORMWOOD_FENCE.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_FENCE.get()), has(DDBlocks.WORMWOOD_FENCE.get())).save(consumer);
        fenceGateBuilder(DDBlocks.WORMWOOD_FENCE_GATE.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_FENCE_GATE.get()), has(DDBlocks.WORMWOOD_FENCE_GATE.get())).save(consumer);
        pressurePlate(consumer, DDBlocks.WORMWOOD_PRESSURE_PLATE.get(), DDBlocks.WORMWOOD_PLANKS.get());
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_SLAB.get(), DDBlocks.WORMWOOD_PLANKS.get());
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_MOSAIC_SLAB.get(), DDBlocks.WORMWOOD_MOSAIC.get());
        stairBuilder(DDBlocks.WORMWOOD_STAIRS.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_STAIRS.get()), has(DDBlocks.WORMWOOD_STAIRS.get())).save(consumer);
        stairBuilder(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get(), Ingredient.of(DDBlocks.WORMWOOD_MOSAIC.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get()), has(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get())).save(consumer);
        mosaicBuilder(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_MOSAIC.get(), DDBlocks.WORMWOOD_PLANKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDBlocks.WORMWOOD_CABINET.get()).pattern("___")
                .pattern("D D").pattern("___")
                .define('_', DDBlocks.WORMWOOD_SLAB.get()).define('D', DDBlocks.WORMWOOD_TRAPDOOR.get())
                .unlockedBy("has_wormwood_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(DDBlocks.WORMWOOD_TRAPDOOR.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.BUBBLEGUNK.get(), 1)
                .requires(DDItems.GUNK.get()).requires(DDItems.ROTBULB.get()).requires(DDBlocks.WORMROOTS.get())
                .group(DDItems.BUBBLEGUNK.toString())
                .unlockedBy(getItemName(DDBlocks.WORMROOTS.get()), has(DDBlocks.WORMROOTS.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.BUBBLEGUNK.get()) + "_from_shapeless");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.EMBEDDED_EGGS.get(), 1)
                .define('#', DDItems.SCULK_POLYP.get()).define('@', Items.EGG).define('!', Blocks.SCULK)
                .pattern("@#@")
                .pattern("#!#")
                .pattern("@#@").unlockedBy(getHasName(Blocks.SCULK), has(Blocks.SCULK)).save(consumer);

        cleaver(DDItems.FLINT_CLEAVER.get(), Ingredient.of(Items.FLINT)).unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(consumer);
        cleaver(DDItems.IRON_CLEAVER.get(), Ingredient.of(Items.IRON_INGOT)).unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(consumer);
        cleaver(DDItems.GOLDEN_CLEAVER.get(), Ingredient.of(Items.GOLD_INGOT)).unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(consumer);
        cleaver(DDItems.DIAMOND_CLEAVER.get(), Ingredient.of(Items.DIAMOND)).unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND)).save(consumer);
        netheriteSmithing(consumer, DDItems.DIAMOND_CLEAVER.get(), RecipeCategory.COMBAT, DDItems.NETHERITE_CLEAVER.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.SCULK_MAYO_BLOCK.get(), 1)
                .define('#', DDItems.SCULK_MAYO.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDItems.SCULK_MAYO.get()), has(DDItems.SCULK_MAYO.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOTS_BLOCK.get(), 1)
                .define('#', DDBlocks.WORMROOTS.get())
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(DDBlocks.WORMROOTS.get()), has(DDBlocks.WORMROOTS.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOTS.get(), 9)
                .requires(DDBlocks.WORMROOTS_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.WORMROOTS_BLOCK.get()), has(DDBlocks.WORMROOTS_BLOCK.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDBlocks.WORMROOTS.get()) + "_from_wormroots_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_PLANKS.get(), 1)
                .define('#', DDBlocks.WORMROOTS.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDBlocks.WORMROOTS.get()), has(DDBlocks.WORMROOTS.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDBlocks.WORMWOOD_PLANKS.get()) + "_from_wormroots");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_PLANKS.get(), 4)
                .requires(DDBlocks.WORMROOTS_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.WORMROOTS_BLOCK.get()), has(DDBlocks.WORMROOTS_BLOCK.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDBlocks.WORMWOOD_PLANKS.get()) + "_from_wormroots_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.ROTBULB_CRATE.get(), 1)
                .define('#', DDItems.ROTBULB.get())
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(DDItems.ROTBULB.get()), has(DDItems.ROTBULB.get())).save(consumer, "rotbulb_crate");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.ROTBULB.get(), 9)
                .requires(DDBlocks.ROTBULB_CRATE.get())
                .unlockedBy(getItemName(DDBlocks.ROTBULB_CRATE.get()), has(DDBlocks.ROTBULB_CRATE.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.ROTBULB.get()) + "_from_rotbulb_crate");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_BLOCK.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_BARS.get(), 16)
                .define('#', DDItems.STAINED_SCRAP.get())
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP.get(), 4)
                .define('#', DDBlocks.STAINED_SCRAP_BLOCK.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDBlocks.STAINED_SCRAP_BLOCK.get()), has(DDBlocks.STAINED_SCRAP_BLOCK.get())).save(consumer);

        stairBuilder(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), Ingredient.of(DDBlocks.CUT_STAINED_SCRAP.get())).unlockedBy(getHasName(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get()), has(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get())).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDBlocks.CUT_STAINED_SCRAP.get());

        stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP.get(), DDBlocks.STAINED_SCRAP_BLOCK.get(), 4);
        stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 1);
        stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 2);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SLICORICE.get(), 1)
                .requires(DDBlocks.WORMROOTS.get()).requires(Items.SUGAR).requires(Items.SLIME_BALL)
                .unlockedBy(getItemName(DDBlocks.WORMROOTS.get()), has(DDBlocks.WORMROOTS.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDBlocks.SCULK_TART.get(), 1)
                .define('#', Items.SUGAR).define('@', DDItems.SCULK_POLYP.get())
                .define('!', ModItems.PIE_CRUST.get()).define('^', DDItems.ANCIENT_EGG.get())
                .pattern("@@@")
                .pattern("^^^")
                .pattern("#!#").unlockedBy(getHasName(DDItems.SCULK_POLYP.get()), has(DDItems.SCULK_POLYP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDItems.MONSTER_CAKE.get(), 1)
                .define('#', DDItems.ROTBULB.get()).define('@', DDItems.ANCIENT_EGG.get())
                .define('!', DDTags.ItemT.ACIDICS).define('^', Items.SPIDER_EYE)
                .pattern("!!!")
                .pattern("^@^")
                .pattern("###").unlockedBy(getHasName(DDItems.ROTBULB.get()), has(DDItems.ROTBULB.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.MONSTER_CAKE.get(), 1)
                .requires(DDItems.MONSTER_CAKE_SLICE.get()).requires(DDItems.MONSTER_CAKE_SLICE.get()).requires(DDItems.MONSTER_CAKE_SLICE.get())
                .requires(DDItems.MONSTER_CAKE_SLICE.get()).requires(DDItems.MONSTER_CAKE_SLICE.get()).requires(DDItems.MONSTER_CAKE_SLICE.get())
                .requires(DDItems.MONSTER_CAKE_SLICE.get())
                .unlockedBy(getItemName(DDItems.MONSTER_CAKE_SLICE.get()), has(DDItems.MONSTER_CAKE_SLICE.get())).save(consumer, "monster_cake_from_slices");

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDBlocks.SCULK_TART.get(), 1)
                .define('#', DDItems.SCULK_TART_SLICE.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDItems.SCULK_TART_SLICE.get()), has(DDItems.SCULK_TART_SLICE.get())).save(consumer, "sculk_tart_from_slices");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.POI.get(), 1)
                .requires(Items.BOWL).requires(DDItems.ROTBULB.get()).requires(DDItems.ROTBULB.get())
                .requires(DDItems.ROTBULB.get()).requires(DDItems.ROTBULB.get()).requires(DDItems.ROTBULB.get())
                .requires(DDItems.ROTBULB.get())
                .unlockedBy(getItemName(DDItems.ROTBULB.get()), has(DDItems.ROTBULB.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDItems.SPIDER_PIE.get(), 1)
                .define('#', DDItems.SPIDER_PIE_SLICE.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDItems.SPIDER_PIE_SLICE.get()), has(DDItems.SPIDER_PIE_SLICE.get())).save(consumer, "spider_pie_from_slices");

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDItems.SPIDER_PIE.get(), 1)
                .define('#', Items.SUGAR).define('@', DDTags.ItemT.ACIDICS)
                .define('!', ModItems.PIE_CRUST.get()).define('^', Items.FERMENTED_SPIDER_EYE)
                .pattern("@@@")
                .pattern("^^^")
                .pattern("#!#").unlockedBy(getHasName(Items.FERMENTED_SPIDER_EYE), has(DDTags.ItemT.ACIDICS)).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SCULK_APPLE.get(), 1)
                .requires(Items.APPLE).requires(DDItems.SCULK_POLYP.get()).requires(DDItems.SCULK_POLYP.get())
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getItemName(DDItems.SCULK_POLYP.get()), has(DDItems.SCULK_POLYP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.ROTTEN_TOMATO_CRATE.get(), 1)
                .define('#', ModItems.ROTTEN_TOMATO.get())
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(ModItems.ROTTEN_TOMATO.get()), has(ModItems.ROTTEN_TOMATO.get())).save(consumer, "rotten_tomato_crate");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.POISONOUS_POTATO_CRATE.get(), 1)
                .define('#', Items.POISONOUS_POTATO)
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(Items.POISONOUS_POTATO), has(Items.POISONOUS_POTATO)).save(consumer, "poisonous_potato_crate");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.POISONOUS_POTATO, 9)
                .requires(DDBlocks.POISONOUS_POTATO_CRATE.get())
                .unlockedBy(getItemName(DDBlocks.POISONOUS_POTATO_CRATE.get()), has(DDBlocks.POISONOUS_POTATO_CRATE.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(Items.POISONOUS_POTATO) + "_from_poisonous_potato_crate");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ROTTEN_TOMATO.get(), 9)
                .requires(DDBlocks.ROTTEN_TOMATO_CRATE.get())
                .unlockedBy(getItemName(DDBlocks.ROTTEN_TOMATO_CRATE.get()), has(DDBlocks.ROTTEN_TOMATO_CRATE.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(ModItems.ROTTEN_TOMATO.get()) + "_from_rotten_tomato_crate");
    }

    private static void smelting(Consumer<FinishedRecipe> consumer) {
        smokingRecipe(DDItems.SPIDER_MEAT.get(), DDItems.SMOKED_SPIDER_MEAT.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);
        smokingRecipe(DDItems.GHAST_CALAMARI.get(), DDItems.FRIED_GHAST_CALAMARI.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);

        smeltingRecipe(DDItems.SNIFFER_SHANK.get(), DDItems.COOKED_SNIFFER_SHANK.get(), RecipeCategory.FOOD, 200, 0.1F, consumer);
        smokingRecipe(DDItems.SNIFFER_SHANK.get(), DDItems.COOKED_SNIFFER_SHANK.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);
        campfireRecipe(DDItems.SNIFFER_SHANK.get(), DDItems.COOKED_SNIFFER_SHANK.get(), RecipeCategory.FOOD, 600, 0.0F, consumer);

        smokingRecipe(Items.SNIFFER_EGG, DDItems.SOFT_SERVE_SNIFFER_EGG.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);

        smeltingRecipe(DDItems.SNIFFERWURST.get(), DDItems.COOKED_SNIFFERWURST.get(), RecipeCategory.FOOD, 200, 0.1F, consumer);
        smokingRecipe(DDItems.SNIFFERWURST.get(), DDItems.COOKED_SNIFFERWURST.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);
        campfireRecipe(DDItems.SNIFFERWURST.get(), DDItems.COOKED_SNIFFERWURST.get(), RecipeCategory.FOOD, 600, 0.0F, consumer);
    }

    private static void cutting(Consumer<FinishedRecipe> consumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.GHAST_TENTACLE.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.GHAST_CALAMARI.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.SLIME_BAR.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SLIME_NOODLES.get(), 2).addResult(ModItems.CANVAS.get()).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.SCULK, Blocks.SCULK_VEIN),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_POLYP.get(), 1).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.SCULK_MAYO_BLOCK.get(), Blocks.SCULK_SHRIEKER, Blocks.SCULK_CATALYST, Blocks.SCULK_SENSOR),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_POLYP.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.CALIBRATED_SCULK_SENSOR),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_POLYP.get(), 2).addResult(Items.AMETHYST_SHARD).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.ANCIENT_EGG.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.CLEAVED_ANCIENT_EGG.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.ROTTEN_FLESH),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.ROTTEN_TRIPE.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.ROTBULB.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.GUNK.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.SCULK_TART.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_TART_SLICE.get(), 4).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.MONSTER_CAKE.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.MONSTER_CAKE_SLICE.get(), 7).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.GRITTY_FLESH.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.ROTTEN_TRIPE.get(), 2)
                .addResultWithChance(Items.SAND, 0.45F, 3).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.BRINED_FLESH.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.ROTTEN_TRIPE.get(), 2)
                .addResultWithChance(Items.SEAGRASS, 0.45F, 3).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.SPIDER_PIE.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SPIDER_PIE_SLICE.get(), 4).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.WARDENZOLA.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.WARDENZOLA_CRUMBLES.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.GUNK.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.STRAW.get(), 2)
                .addResultWithChance(Items.BONE_MEAL, 0.6F, 2)
                .addResultWithChance(DDBlocks.WORMROOTS.get(), 0.25F, 1)
                .addResultWithChance(Items.SLIME_BALL, 0.4F, 2)
                .build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.ROTBULB_PLANT.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.GUNK.get(), 2)
                .addResultWithChance(Items.PURPLE_DYE, 0.5F, 2)
                .build(consumer);
    }

    protected static void smeltingRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer, "dungeonsdelight:" + output + "_from_smelting");
    }

    protected static void smokingRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer, "dungeonsdelight:" + output + "_from_smoking");
    }

    protected static void campfireRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer, "dungeonsdelight:" + output + "_from_blasting");
    }

    protected static RecipeBuilder cleaver(ItemLike output, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .define('#', ingredient).define('@', Items.STICK)
                .pattern("##")
                .pattern("#@");
    }
}