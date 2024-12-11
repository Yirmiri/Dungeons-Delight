package net.yirmiri.dungeonsdelight.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDRecipeRegistries;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.RecipeWrapper;

import java.util.Optional;

public class DungeonPotRecipe implements Recipe<RecipeWrapper> {
    public static final int INPUT_SLOTS = 6;

    private final String group;
    private final CookingPotRecipeBookTab tab;
    private final DefaultedList<Ingredient> inputItems;
    private final ItemStack output;
    private final ItemStack container;
    private final ItemStack containerOverride;
    private final float experience;
    private final int cookTime;

    public DungeonPotRecipe(String group, @Nullable CookingPotRecipeBookTab tab, DefaultedList<Ingredient> inputItems, ItemStack output, ItemStack container, float experience, int cookTime) {
        this.group = group;
        this.tab = tab;
        this.inputItems = inputItems;
        this.output = output;

        if (!container.isEmpty()) {
            this.container = container;
        } else if (output.getItem().hasRecipeRemainder()) {
            this.container = new ItemStack(output.getItem().getRecipeRemainder());
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

    @Nullable
    public CookingPotRecipeBookTab getRecipeBookTab() {
        return this.tab;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.inputItems;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup provider) {
        return this.output;
    }

    public ItemStack getOutputContainer() {
        return this.container;
    }

    public ItemStack getContainerOverride() {
        return this.containerOverride;
    }

    @Override
    public ItemStack craft(RecipeWrapper input, RegistryWrapper.WrapperLookup lookup) {
        return this.output.copy();
    }

    public float getExperience() {
        return this.experience;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World level) {
        RecipeMatcher stackedContents = new RecipeMatcher();
        int i = 0;

        for (int j = 0; j < INPUT_SLOTS; ++j) {
            ItemStack itemstack = inv.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                ++i;
                stackedContents.addInput(itemstack);
            }
        }
        return i == this.inputItems.size() && stackedContents.match(this, null);
    }

    @Override
    public boolean fits(int width, int height) {
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
    public ItemStack createIcon() {
        return new ItemStack(DDBlocks.DUNGEON_POT.asItem());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DungeonPotRecipe that = (DungeonPotRecipe) o;

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

    public static class Serializer implements RecipeSerializer<DungeonPotRecipe>
    {
        private static final MapCodec<DungeonPotRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(DungeonPotRecipe::getGroup),
                CookingPotRecipeBookTab.CODEC.optionalFieldOf("recipe_book_tab").xmap(optional -> optional.orElse(null), Optional::of).forGetter(DungeonPotRecipe::getRecipeBookTab),
                Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").xmap(ingredients -> {
                    DefaultedList<Ingredient> nonNullList = DefaultedList.of();
                    nonNullList.addAll(ingredients);
                    return nonNullList;
                }, ingredients -> ingredients).forGetter(DungeonPotRecipe::getIngredients),
                ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(r -> r.output),
                ItemStack.VALIDATED_CODEC.optionalFieldOf("container", ItemStack.EMPTY).forGetter(DungeonPotRecipe::getContainerOverride),
                Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(DungeonPotRecipe::getExperience),
                Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(DungeonPotRecipe::getCookTime)
        ).apply(inst, DungeonPotRecipe::new));

        public static final PacketCodec<RegistryByteBuf, DungeonPotRecipe> STREAM_CODEC = PacketCodec.ofStatic(DungeonPotRecipe.Serializer::toNetwork, DungeonPotRecipe.Serializer::fromNetwork);

        public Serializer() {
        }

        @Override
        public MapCodec<DungeonPotRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, DungeonPotRecipe> packetCodec() {
            return STREAM_CODEC;
        }

        private static DungeonPotRecipe fromNetwork(RegistryByteBuf buffer) {
            String groupIn = buffer.readString();
            CookingPotRecipeBookTab tabIn = CookingPotRecipeBookTab.findByName(buffer.readString());
            int i = buffer.readVarInt();
            DefaultedList<Ingredient> inputItemsIn = DefaultedList.ofSize(i, Ingredient.EMPTY);

            inputItemsIn.replaceAll(ignored -> Ingredient.PACKET_CODEC.decode(buffer));

            ItemStack outputIn = ItemStack.PACKET_CODEC.decode(buffer);
            ItemStack container = ItemStack.OPTIONAL_PACKET_CODEC.decode(buffer);
            float experienceIn = buffer.readFloat();
            int cookTimeIn = buffer.readVarInt();
            return new DungeonPotRecipe(groupIn, tabIn, inputItemsIn, outputIn, container, experienceIn, cookTimeIn);
        }

        private static void toNetwork(RegistryByteBuf buffer, DungeonPotRecipe recipe) {
            buffer.writeString(recipe.group);
            buffer.writeString(recipe.tab != null ? recipe.tab.toString() : "");
            buffer.writeVarInt(recipe.inputItems.size());

            for (Ingredient ingredient : recipe.inputItems) {
                Ingredient.PACKET_CODEC.encode(buffer, ingredient);
            }

            ItemStack.PACKET_CODEC.encode(buffer, recipe.output);
            ItemStack.OPTIONAL_PACKET_CODEC.encode(buffer, recipe.container);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookTime);
        }
    }
}
