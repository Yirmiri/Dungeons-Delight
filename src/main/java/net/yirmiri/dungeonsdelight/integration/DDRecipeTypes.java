package net.yirmiri.dungeonsdelight.integration;

import mezz.jei.api.recipe.RecipeType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.entity.container.MonsterPotRecipe;

public class DDRecipeTypes {
    public static final RecipeType<MonsterPotRecipe> MONSTER_COOKING = RecipeType.create(DungeonsDelight.MOD_ID, "monster_cooking", MonsterPotRecipe.class);

    public DDRecipeTypes() {
    }
}
