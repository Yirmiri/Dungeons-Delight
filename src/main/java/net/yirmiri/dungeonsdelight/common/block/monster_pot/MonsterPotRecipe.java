//
//Based on the original version from Farmer's Delight
//

package net.yirmiri.dungeonsdelight.common.block.monster_pot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDRecipeRegistries;

public class MonsterPotRecipe implements Recipe<RecipeWrapper> {
    public static final int INPUT_SLOTS = 6;

    private final String group;
    private final MonsterPotRecipeBookTab tab;
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ItemStack container;
    private final ItemStack containerOverride;
    private final float experience;
    private final int cookTime;

    public MonsterPotRecipe(String group, MonsterPotRecipeBookTab tab, NonNullList<Ingredient> inputItems, ItemStack output, ItemStack container, float experience, int cookTime) {
        this.group = group;
        this.tab = tab != null ? tab : MonsterPotRecipeBookTab.MONSTER_MISC;
        this.inputItems = inputItems;
        this.output = output;

        if (!container.isEmpty()) {
            this.container = container;
        } else if (!output.getCraftingRemainingItem().isEmpty()) {
            this.container = output.getCraftingRemainingItem();
        } else {
            this.container = ItemStack.EMPTY;
        }

        this.containerOverride = container;
        this.experience = experience;
        this.cookTime = cookTime;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    public MonsterPotRecipeBookTab getRecipeBookTab() {
        return this.tab;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputItems;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.output;
    }

    public ItemStack getOutputContainer() {
        return this.container;
    }

    public ItemStack getContainerOverride() {
        return this.containerOverride;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv, HolderLookup.Provider provider) {
        return this.output.copy();
    }

    public float getExperience() {
        return this.experience;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level level) {
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for (int j = 0; j < INPUT_SLOTS; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }
        return i == this.inputItems.size() && RecipeMatcher.findMatches(inputs, this.inputItems) != null;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.inputItems.size();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DDRecipeRegistries.MONSTER_COOKING_SERIALIZERS.get();
    }

    @Override
    public RecipeType<?> getType() {
        return DDRecipeRegistries.MONSTER_COOKING_RECIPE_TYPE.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(DDBlocks.MONSTER_POT.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonsterPotRecipe that)) return false;

        if (Float.compare(that.getExperience(), getExperience()) != 0) return false;
        if (getCookTime() != that.getCookTime()) return false;
        if (!getGroup().equals(that.getGroup())) return false;
        if (tab != that.tab) return false;
        if (!inputItems.equals(that.inputItems)) return false;
        if (!output.equals(that.output)) return false;
        return container.equals(that.container);
    }

    @Override
    public int hashCode() {
        int result = getGroup().hashCode();
        result = 31 * result + (getRecipeBookTab() != null ? getRecipeBookTab().hashCode() : 0);
        result = 31 * result + inputItems.hashCode();
        result = 31 * result + output.hashCode();
        result = 31 * result + container.hashCode();
        result = 31 * result + (getExperience() != 0.0f ? Float.floatToIntBits(getExperience()) : 0);
        result = 31 * result + getCookTime();
        return result;
    }

    public static class Serializer implements RecipeSerializer<MonsterPotRecipe> {
        private static final MapCodec<MonsterPotRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(MonsterPotRecipe::getGroup),
                MonsterPotRecipeBookTab.CODEC.optionalFieldOf("recipe_book_tab", MonsterPotRecipeBookTab.MONSTER_MISC).forGetter(MonsterPotRecipe::getRecipeBookTab),
                Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").xmap(ingredients -> {
                    NonNullList<Ingredient> nonNullList = NonNullList.create();
                    nonNullList.addAll(ingredients);
                    return nonNullList;
                }, ingredients -> ingredients).forGetter(MonsterPotRecipe::getIngredients),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(r -> r.output),
                ItemStack.STRICT_CODEC.optionalFieldOf("container", ItemStack.EMPTY).forGetter(MonsterPotRecipe::getContainerOverride),
                Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(MonsterPotRecipe::getExperience),
                Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(MonsterPotRecipe::getCookTime)
        ).apply(inst, MonsterPotRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MonsterPotRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<MonsterPotRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MonsterPotRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static MonsterPotRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String groupIn = buffer.readUtf();
            MonsterPotRecipeBookTab tabIn = MonsterPotRecipeBookTab.STREAM_CODEC.decode(buffer);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> inputItemsIn = NonNullList.withSize(i, Ingredient.EMPTY);
            inputItemsIn.replaceAll(ignored -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));

            ItemStack outputIn = ItemStack.STREAM_CODEC.decode(buffer);
            ItemStack container = ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer);
            float experienceIn = buffer.readFloat();
            int cookTimeIn = buffer.readVarInt();
            return new MonsterPotRecipe(groupIn, tabIn, inputItemsIn, outputIn, container, experienceIn, cookTimeIn);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, MonsterPotRecipe recipe) {
            buffer.writeUtf(recipe.group);
            MonsterPotRecipeBookTab.STREAM_CODEC.encode(buffer, recipe.tab);
            buffer.writeVarInt(recipe.inputItems.size());

            for (Ingredient ingredient : recipe.inputItems) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, recipe.container);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookTime);
        }
    }
}