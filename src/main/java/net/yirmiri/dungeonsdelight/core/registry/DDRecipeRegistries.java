package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotRecipe;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterFoodServingRecipe;

import java.util.function.Supplier;

public class DDRecipeRegistries {
    //RECIPE SERIALIZERS
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DungeonsDelight.MOD_ID);

    public static final Supplier<RecipeSerializer<?>> MONSTER_COOKING_SERIALIZERS = RECIPE_SERIALIZERS.register("monster_cooking", MonsterPotRecipe.Serializer::new);
    public static final RegistryObject<SimpleCraftingRecipeSerializer<?>> MONSTER_FOOD_SERVING = RECIPE_SERIALIZERS.register("monster_food_serving", () -> new SimpleCraftingRecipeSerializer<>(MonsterFoodServingRecipe::new));

    //RECIPE TYPES
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, DungeonsDelight.MOD_ID);

    public static final RegistryObject<RecipeType<MonsterPotRecipe>> MONSTER_COOKING_RECIPE_TYPE = RECIPE_TYPES.register("monster_cooking",
            () -> registerRecipeType("monster_cooking"));

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String id) {
        return new RecipeType<>() {
            public String toString() {
                return DungeonsDelight.MOD_ID + ":" + id;
            }
        };
    }
}
