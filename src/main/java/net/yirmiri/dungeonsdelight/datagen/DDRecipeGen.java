package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

public class DDRecipeGen extends RecipeProvider implements IConditionBuilder {
    public DDRecipeGen(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EBBlocks.BIRCH_DECORATIVE_SHELF.get(), 1)
//                .define('#', Blocks.BIRCH_PLANKS)
//                .pattern("###")
//                .pattern("   ")
//                .pattern("###").unlockedBy(getHasName(Blocks.BIRCH_PLANKS), has(Blocks.BIRCH_PLANKS)).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SLIME_SLAB.get(), 1)
                .requires(Items.SLIME_BALL).requires(ModItems.CANVAS.get())
                .group(DDItems.SLIME_SLAB.toString())
                .unlockedBy(getItemName(Items.SLIME_BALL), has(Items.SLIME_BALL));
                //.save(consumer, "dungeonsdelight:" + getItemName(DDItems.SLIME_SLAB.get()));
    }
}