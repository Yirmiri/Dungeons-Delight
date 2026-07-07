package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;

import java.util.function.Supplier;

public class DDRecipeTypes {

    public static final Supplier<RecipeType<MonsterCookingRecipe>> MONSTER_COOKING = registerType("monster_cooking");

    public static final Supplier<RecipeSerializer<MonsterCookingRecipe>> MONSTER_COOKING_SERIALIZER = registerSerializer("monster_cooking", new MonsterCookingRecipe.Serializer());

    public static <T extends Recipe<?>> Supplier<RecipeType<T>> registerType(String id) {
        return Services.REGISTRY.registerRecipeType(DungeonsDelight.MOD_ID, id);
    }

    public static <T extends Recipe<?>> Supplier<RecipeSerializer<T>> registerSerializer(String id, RecipeSerializer<?> serializer) {
        return Services.REGISTRY.registerRecipeSerializer(DungeonsDelight.MOD_ID, id, (RecipeSerializer<T>) serializer);
    }

    public static void load() {
    }
}