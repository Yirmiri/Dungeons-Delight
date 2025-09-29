package net.yirmiri.dungeonsdelight.integration.jei;

import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotRecipe;
import net.yirmiri.dungeonsdelight.core.registry.DDRecipeRegistries;
import vectorwing.farmersdelight.integration.jei.resource.DecompositionDummy;

public class DDRecipeTypes {
    public static final RecipeType<RecipeHolder<MonsterPotRecipe>> MONSTER_COOKING = RecipeType.createFromVanilla(DDRecipeRegistries.MONSTER_COOKING_RECIPE_TYPE.get());
    public static final RecipeType<DecompositionDummy> SCULKING = RecipeType.create(DungeonsDelight.MOD_ID, "sculking", DecompositionDummy.class);

    public DDRecipeTypes() {
    }
}
