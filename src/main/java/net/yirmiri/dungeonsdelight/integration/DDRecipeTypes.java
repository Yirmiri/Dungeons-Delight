package net.yirmiri.dungeonsdelight.integration;

import mezz.jei.api.recipe.RecipeType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.entity.container.MonsterPotRecipe;
import vectorwing.farmersdelight.integration.jei.resource.DecompositionDummy;

public class DDRecipeTypes {
    public static final RecipeType<MonsterPotRecipe> MONSTER_COOKING = RecipeType.create(DungeonsDelight.MOD_ID, "monster_cooking", MonsterPotRecipe.class);
    public static final RecipeType<DecompositionDummy> SCULKING = RecipeType.create(DungeonsDelight.MOD_ID, "sculking", DecompositionDummy.class);

    public DDRecipeTypes() {
    }
}
