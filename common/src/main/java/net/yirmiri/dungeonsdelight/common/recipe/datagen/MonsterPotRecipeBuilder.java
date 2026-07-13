package net.yirmiri.dungeonsdelight.common.recipe.datagen;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.azurune.runiclib.RunicLib;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;
import net.yirmiri.dungeonsdelight.core.registry.DDRecipeTypes;

import java.util.List;
import java.util.function.Consumer;

public class MonsterPotRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final MonsterBookCategory bookCategory;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Item result;
    private final Item container;
    private final float experience;
    private final float successChance;
    private final int cookingTime;
    private final RecipeSerializer<? extends MonsterCookingRecipe> serializer;
    private String group;

    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    private MonsterPotRecipeBuilder(
            RecipeCategory category,
            MonsterBookCategory bookCategory,
            ItemLike container,
            ItemLike result,
            int cookingTime,
            float successChance,
            float experience,
            RecipeSerializer<? extends MonsterCookingRecipe> serializer
    ) {
        this.category = category;
        this.bookCategory = bookCategory;

        this.container = (container != null) ? container.asItem() : null;
        this.result = result.asItem();

        this.cookingTime = cookingTime;
        this.successChance = successChance;
        this.experience = experience;
        this.serializer = serializer;
    }

    public static MonsterPotRecipeBuilder create(
            RecipeCategory category,
            MonsterBookCategory bookCategory,
            ItemLike container,
            ItemLike result,
            int cookingTime,
            float successChance,
            float exp
    ) {
        return new MonsterPotRecipeBuilder(
                category,
                bookCategory,
                container,
                result,
                cookingTime,
                successChance,
                exp,
                DDRecipeTypes.MONSTER_COOKING_SERIALIZER.get()
        );
    }

    public MonsterPotRecipeBuilder addIngredient(Item item) { return addIngredient(Ingredient.of(item)); }

    public MonsterPotRecipeBuilder addIngredient(Ingredient ingredient) {
        if (this.ingredients.size() > 6) throw new IndexOutOfBoundsException("Cannot add more than 6 ingredients to a Monster Pot recipe");

        this.ingredients.add(ingredient);
        return this;
    }

    @Override
    public MonsterPotRecipeBuilder group(String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, CriterionTriggerInstance trig) {
        this.advancement.addCriterion(name, trig);
        return this;
    }

    @Override public Item getResult() { return this.result; }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation recipe) {
        ResourceLocation finalRecipeLoc = RunicLib.customid(recipe.getNamespace(), MonsterCookingRecipe.RESC_PREFIX + recipe.getPath());

        DungeonsDelight.LOGGER.info(recipe.toString());
        this.ensureValid(finalRecipeLoc);
        this.advancement
                .parent(ROOT_RECIPE_ADVANCEMENT)
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(finalRecipeLoc))
                .rewards(AdvancementRewards.Builder.recipe(finalRecipeLoc))
                .requirements(RequirementsStrategy.OR)
        ;
        consumer.accept(
                new Result(
                        finalRecipeLoc,
                        (this.group == null) ? "" : this.group,
                        this.bookCategory,
                        this.ingredients,
                        this.container,
                        this.result,
                        this.successChance,
                        this.experience,
                        this.cookingTime,
                        this.advancement,
                        recipe.withPrefix("recipes/" + this.category.getFolderName() + "/"),
                        this.serializer
                )
        );
    }

    private void ensureValid(ResourceLocation id) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining monster pot recipe " + id);
        }
    }

    static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final MonsterBookCategory category;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final Item container;
        private final float experience;
        private final float successChance;
        private final int cookingTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final RecipeSerializer<? extends MonsterCookingRecipe> serializer;

        public Result(
                ResourceLocation id,
                String group,
                MonsterBookCategory category,
                List<Ingredient> ingredients,
                Item container,
                Item result,
                float successChance,
                float experience,
                int cookingTime,
                Advancement.Builder advancement,
                ResourceLocation advancementId,
                RecipeSerializer<? extends MonsterCookingRecipe> serializer
        ) {
            this.id = id;
            this.group = group;
            this.category = category;
            this.ingredients = ingredients;
            this.container = container;
            this.successChance = successChance;
            this.result = result;
            this.experience = experience;
            this.cookingTime = cookingTime;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.serializer = serializer;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) json.addProperty(MonsterCookingRecipe.Serializer.GROUP, this.group);
            json.addProperty(MonsterCookingRecipe.Serializer.RECIPE_BOOK_TAB, this.category.getSerializedName());

            // Ingredients
            JsonArray ingArr = new JsonArray();
            for (int i = 0; i < this.ingredients.size(); i++) ingArr.add(this.ingredients.get(i).toJson());
            json.add(MonsterCookingRecipe.Serializer.INGREDIENTS, ingArr);

            json.addProperty(MonsterCookingRecipe.Serializer.RESULT, BuiltInRegistries.ITEM.getKey(this.result).toString());
            if (this.container != null) json.addProperty(MonsterCookingRecipe.Serializer.CONTAINER, BuiltInRegistries.ITEM.getKey(this.container).toString());

            json.addProperty(MonsterCookingRecipe.Serializer.EXP, this.experience);
            json.addProperty(MonsterCookingRecipe.Serializer.SUCCESS, this.successChance);
            json.addProperty(MonsterCookingRecipe.Serializer.COOK_TIME, this.cookingTime);
        }

        public RecipeSerializer<?> getType() {
            return this.serializer;
        }
        public ResourceLocation getId() {
            return this.id;
        }
        public JsonObject serializeAdvancement() { return this.advancement.serializeToJson(); }
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}