package net.yirmiri.dungeonsdelight.integration.jei;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeManager;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotRecipe;
import net.yirmiri.dungeonsdelight.core.registry.DDRecipeRegistries;

import java.util.List;

public class DDRecipes {
    private final RecipeManager recipeManager;

    public DDRecipes() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        if (level != null) {
            this.recipeManager = level.getRecipeManager();
        } else {
            throw new NullPointerException("minecraft world must not be null.");
        }
    }

    public List<MonsterPotRecipe> getMonsterPotRecipes() {
        return this.recipeManager.getAllRecipesFor(DDRecipeRegistries.MONSTER_COOKING_RECIPE_TYPE.get()).stream().toList();
    }
}
