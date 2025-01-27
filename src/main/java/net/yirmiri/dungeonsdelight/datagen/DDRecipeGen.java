package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

public class DDRecipeGen extends RecipeProvider implements IConditionBuilder {
    public DDRecipeGen(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.MONSTER_POT.get(), 1)
                .define('#', ModItems.COOKING_POT.get()).define('@', Items.EMERALD)
                .define('!', Items.ROTTEN_FLESH).define('$', Items.FERMENTED_SPIDER_EYE)
                .pattern("$#$")
                .pattern("!@!").unlockedBy(getHasName(ModItems.COOKING_POT.get()), has(ModItems.COOKING_POT.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SLIME_SLAB.get(), 1)
                .requires(Items.SLIME_BALL).requires(Items.SLIME_BALL).requires(ModItems.CANVAS.get())
                .group(DDItems.SLIME_SLAB.toString())
                .unlockedBy(getItemName(Items.SLIME_BALL), has(Items.SLIME_BALL));
                //.save(output, "dungeonsdelight:" + getItemName(DDItems.SLIME_SLAB.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.DUNGEON_STOVE.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', Items.TUFF)
                .define('!', Items.NETHERITE_SCRAP).define('%', Items.CAMPFIRE)
                .pattern("#!#")
                .pattern("@ @")
                .pattern("@%@").unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);

        smokingRecipe(DDItems.SPIDER_MEAT.get(), DDItems.SMOKED_SPIDER_MEAT.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);
        smokingRecipe(DDItems.GHAST_CALAMARI.get(), DDItems.FRIED_GHAST_CALAMARI.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_PLANKS.get(), 1)
                .requires(DDBlocks.WORMROOTS.get()).requires(DDBlocks.WORMROOTS.get()).requires(DDBlocks.WORMROOTS.get()).requires(DDBlocks.WORMROOTS.get())
                .group(DDBlocks.WORMWOOD_PLANKS.toString())
                .unlockedBy(getItemName(DDBlocks.WORMROOTS.get()), has(DDBlocks.WORMROOTS.get()));

        //TODO - WORMWOOD RECIPES
    }

    protected static void smeltingRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer);
    }

    protected static void smokingRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer);
    }
}