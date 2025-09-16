package net.yirmiri.dungeonsdelight.datagen.recipe;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotRecipe;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.LinkedHashMap;
import java.util.Map;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MonsterCookingPotRecipeBuilder implements RecipeBuilder {
    private CookingPotRecipeBookTab tab;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Item result;
    private final ItemStack resultStack;
    private final int cookingTime;
    private final float experience;
    private final ItemStack container;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public MonsterCookingPotRecipeBuilder(ItemLike result, int count, int cookingTime, float experience, @Nullable ItemLike container) {
        this(new ItemStack(result, count), cookingTime, experience, container);
    }

    public MonsterCookingPotRecipeBuilder(ItemStack resultIn, int cookingTime, float experience, @Nullable ItemLike container) {
        this.result = resultIn.getItem();
        this.resultStack = resultIn;
        this.cookingTime = cookingTime;
        this.experience = experience;
        this.container = container != null ? new ItemStack(container) : ItemStack.EMPTY;
        this.tab = null;
    }

    public static MonsterCookingPotRecipeBuilder monsterCookingPotRecipe(ItemLike mainResult, int count, int cookingTime, float experience) {
        return new MonsterCookingPotRecipeBuilder(mainResult, count, cookingTime, experience, null);
    }

    public static MonsterCookingPotRecipeBuilder cookingPotRecipe(ItemLike mainResult, int count, int cookingTime, float experience, ItemLike container) {
        return new MonsterCookingPotRecipeBuilder(mainResult, count, cookingTime, experience, container);
    }

    public MonsterCookingPotRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return addIngredient(Ingredient.of(tagIn));
    }

    public MonsterCookingPotRecipeBuilder addIngredient(ItemLike itemIn) {
        return addIngredient(itemIn, 1);
    }

    public MonsterCookingPotRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            addIngredient(Ingredient.of(itemIn));
        }
        return this;
    }

    public MonsterCookingPotRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return addIngredient(ingredientIn, 1);
    }

    public MonsterCookingPotRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            ingredients.add(ingredientIn);
        }
        return this;
    }

    @Override
    public RecipeBuilder group(@org.jetbrains.annotations.Nullable String p_176495_) {
        return this;
    }

    public MonsterCookingPotRecipeBuilder setRecipeBookTab(CookingPotRecipeBookTab tab) {
        this.tab = tab;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public MonsterCookingPotRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger) {
        this.criteria.put(criterionName, criterionTrigger);
        return this;
    }

    public MonsterCookingPotRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
        return unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
    }

    public MonsterCookingPotRecipeBuilder unlockedByAnyIngredient(ItemLike... items) {
        this.criteria.put("has_any_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items).build()));
        return this;
    }

    public void build(RecipeOutput output) {
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(result);
        save(output, ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, location.getPath()));
    }

    public void build(RecipeOutput outputIn, String save) {
        ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(result);
        if ((ResourceLocation.parse(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Monster Cooking Recipe " + save + " should remove its 'save' argument");
        } else {
            save(outputIn, ResourceLocation.parse(save));
        }
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation id) {
        ResourceLocation recipeId = id.withPrefix("monster_cooking/");
        Advancement.Builder advancementBuilder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancementBuilder::addCriterion);
        MonsterPotRecipe recipe = new MonsterPotRecipe(
                "",
                this.tab,
                this.ingredients,
                this.resultStack,
                this.container,
                this.experience,
                this.cookingTime
        );
        output.accept(recipeId, recipe, advancementBuilder.build(id.withPrefix("recipes/monster_cooking/")));
    }
}
