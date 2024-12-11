package net.yirmiri.dungeonsdelight.registry;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryKeys;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.recipe.DungeonPotRecipe;

import java.util.function.Supplier;

public class DDRecipeRegistries {
    //RECIPE SERIALIZERS
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(RegistryKeys.RECIPE_SERIALIZER, DungeonsDelight.MOD_ID);

    public static final Supplier<RecipeSerializer<?>> MONSTER_COOKING_SERIALIZERS = RECIPE_SERIALIZERS.register("monster_cooking", DungeonPotRecipe.Serializer::new);

    //RECIPE TYPES
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(RegistryKeys.RECIPE_TYPE, DungeonsDelight.MOD_ID);

    public static final Supplier<RecipeType<DungeonPotRecipe>> MONSTER_COOKING_RECIPE_TYPE = RECIPE_TYPES.register("monster_cooking", () -> registerRecipeType("monster_cooking"));

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String id) {
        return new RecipeType<>() {
            public String toString() {
                return DungeonsDelight.MOD_ID + ":" + id;
            }
        };
    }

    public static void loadRecipeRegistries() {
    }
}
