package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDItems;
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
    }

    private static void crafting(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.MONSTER_POT.get(), 1)
                .define('#', ModItems.COOKING_POT.get()).define('@', Items.EMERALD)
                .define('!', Items.ROTTEN_FLESH).define('$', Items.FERMENTED_SPIDER_EYE).define('&', Items.BONE)
                .pattern("&  ")
                .pattern("$#$")
                .pattern("!@!").unlockedBy(getHasName(ModItems.COOKING_POT.get()), has(ModItems.COOKING_POT.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SLIME_SLAB.get(), 1)
                .requires(Items.SLIME_BALL).requires(Items.SLIME_BALL).requires(ModItems.CANVAS.get())
                .group(DDItems.SLIME_SLAB.toString())
                .unlockedBy(getItemName(Items.SLIME_BALL), has(Items.SLIME_BALL))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.SLIME_SLAB.get()) + "_from_shapeless");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.DUNGEON_STOVE.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', Items.TUFF)
                .define('!', Items.NETHERITE_SCRAP).define('%', Items.CAMPFIRE)
                .pattern("#!#")
                .pattern("@ @")
                .pattern("@%@").unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 2)
                .define('#', DDBlocks.WORMROOTS.get())
                .pattern("#")
                .pattern("#").unlockedBy(getHasName(DDBlocks.WORMROOTS.get()), has(DDBlocks.WORMROOTS.get()))
                .save(consumer, "dungeonsdelight:_stick_from_wormroots");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_PLANKS.get(), 1)
                .define('#', DDBlocks.WORMROOTS.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDBlocks.WORMROOTS.get()), has(DDBlocks.WORMROOTS.get())).save(consumer);

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
                .requires(Items.SLIME_BALL).requires(Items.SUGAR).requires(DDBlocks.WORMROOTS.get())
                .group(DDItems.BUBBLEGUNK.toString()) //TODO: CHANGE RECIPE WHEN ROTBULBS ARE ADDED
                .unlockedBy(getItemName(DDBlocks.WORMROOTS.get()), has(DDBlocks.WORMROOTS.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.BUBBLEGUNK.get()) + "_from_shapeless");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.EMBEDDED_EGGS.get(), 1)
                .define('#', DDItems.SCULK_POLYP.get()).define('@', Items.EGG).define('!', Blocks.SCULK)
                .pattern("@#@")
                .pattern("#!#")
                .pattern("@#@").unlockedBy(getHasName(Blocks.SCULK), has(Blocks.SCULK)).save(consumer);
    }

    private static void smelting(Consumer<FinishedRecipe> consumer) {
        smokingRecipe(DDItems.SPIDER_MEAT.get(), DDItems.SMOKED_SPIDER_MEAT.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);
        smokingRecipe(DDItems.GHAST_CALAMARI.get(), DDItems.FRIED_GHAST_CALAMARI.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);
    }

    private static void cutting(Consumer<FinishedRecipe> consumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.GHAST_TENTACLE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.GHAST_CALAMARI.get(), 2).build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.SLIME_SLAB.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SLIME_NOODLES.get(), 2).addResult(ModItems.CANVAS.get()).build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.SCULK_VEIN), Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_POLYP.get(), 1).build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.SCULK), Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_POLYP.get(), 1).build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.SCULK_CATALYST), Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_POLYP.get(), 2).build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.SCULK_SHRIEKER), Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_POLYP.get(), 2).build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.SCULK_SENSOR), Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_POLYP.get(), 2).build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.CALIBRATED_SCULK_SENSOR), Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.SCULK_POLYP.get(), 2).addResult(Items.AMETHYST_SHARD).build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.ANCIENT_EGG.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), DDItems.CLEAVED_ANCIENT_EGG.get(), 2).build(consumer);
    }

    protected static void smeltingRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer);
    }

    protected static void smokingRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer);
    }
}