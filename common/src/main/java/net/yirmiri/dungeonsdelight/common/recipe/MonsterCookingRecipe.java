package net.yirmiri.dungeonsdelight.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookCategories;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDRecipeTypes;

public class MonsterCookingRecipe implements Recipe<Container> {
    public static final float DEFAULT_SUCCESS = 1.0F;
    public static final int DEFAULT_COOKING_TIME = 200;

    private final String group;
    private final ResourceLocation id;
    private final RecipeBookCategories tab;
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ItemStack container;
    private final ResourceLocation containerIcon;
    private final float experience;
    private final float successChance;
    private final int cookTime;

    public MonsterCookingRecipe(ResourceLocation id, String group, RecipeBookCategories tab, NonNullList<Ingredient> inputItems, ItemStack output, ItemStack container, ResourceLocation contIcon, float experience, float successChance, int cookTime) {
        this.group = group;
        this.id = id;
        this.tab = tab != null ? tab : DDRecipeBookCategories.DD_MONSTERPOT_MISC;
        this.inputItems = inputItems;
        this.output = output;

        ItemStack pre = ItemStack.EMPTY;
        Item base = output.getItem().getCraftingRemainingItem();

        if (!container.isEmpty()) pre = container;
        else if (base != null) container = base.getDefaultInstance();

        this.container = pre;
        this.containerIcon = contIcon;
        this.experience = experience;
        this.successChance = successChance;
        this.cookTime = cookTime;
    }

    public RecipeBookCategories getRecipeTab() { return this.tab; }
    public float getExperience() { return this.experience; }
    public float getSuccessChance() { return this.successChance; }
    public int getCookTime() { return this.cookTime; }
    public ItemStack getContainer() { return this.container; }

    @Override
    public boolean matches(Container container, Level level)
    {
        if (!(container instanceof MonsterPotBlockEntity)) return false;

        boolean ret = true;
        for (int i = 0; i < this.inputItems.size(); i++) {
            ret = this.inputItems.get(i).test(container.getItem(i));
            if (!ret) break;
        }

        if (ret) {
            ret = this.container.isEmpty() || container.getItem(MonsterPotBlockEntity.BOWL_SLOT).is(this.container.getItem());
        }

        return ret;
    }

    @Override public String getGroup() { return this.group; }
    @Override public NonNullList<Ingredient> getIngredients() { return this.inputItems; }
    @Override public ItemStack getResultItem(RegistryAccess/*HolderLookup.Provider 1.21 xd*/ reg) { return this.output; }
    @Override public ItemStack assemble(Container container, RegistryAccess registryAccess) { return this.output.copy(); }
    @Override public boolean canCraftInDimensions(int w, int h) { return true; }
    @Override public ResourceLocation getId() { return this.id; }

    @Override public ItemStack getToastSymbol() { return new ItemStack(DDBlocks.MONSTER_POT.get()); }
    @Override public RecipeSerializer<?> getSerializer() { return DDRecipeTypes.MONSTER_COOKING_SERIALIZER.get(); }
    @Override public RecipeType<?> getType() { return DDRecipeTypes.MONSTER_COOKING.get(); }

    public static class Serializer implements RecipeSerializer<MonsterCookingRecipe> {

        private static final ResourceLocation RESC_BONE = RunicLib.customid(DungeonsDelight.MOD_ID, "icon_mpot_bone");
        private static final ResourceLocation RESC_BOTTLE = RunicLib.customid(DungeonsDelight.MOD_ID, "icon_mpot_bottle");
        private static final ResourceLocation RESC_BOWL = RunicLib.customid(DungeonsDelight.MOD_ID, "icon_mpot_bowl");
        private static final ResourceLocation RESC_DISC = RunicLib.customid(DungeonsDelight.MOD_ID, "icon_mpot_disc");
        private static final ResourceLocation RESC_SCRAP = RunicLib.customid(DungeonsDelight.MOD_ID, "icon_mpot_scrap");
        private static final ResourceLocation RESC_STICK = RunicLib.customid(DungeonsDelight.MOD_ID, "icon_mpot_stick");

        private static final String GROUP = "group";
        private static final String EXP = "experience";
        private static final String SUCCESS = "successchance";
        private static final String COOK_TIME = "cookingtime";
        private static final String INGREDIENTS = "ingredients";
        private static final String CONTAINER = "container";
        private static final String CONTAINER_ICON = "container_icon";
        private static final String RECIPE_BOOK_TAB = "recipe_book_tab";
        private static final String RESULT = "result";

        @Override
        public MonsterCookingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = GsonHelper.getAsString(json, GROUP, "");
            RecipeBookCategories tab = tabDecode(GsonHelper.getAsString(json, RECIPE_BOOK_TAB, "misc"));

            JsonArray ingArr = GsonHelper.getAsJsonArray(json, INGREDIENTS);
            if (ingArr.size() > 6) throw new JsonParseException("Cannot have more than 6 ingredients in a Monster Pot recipe");

            NonNullList<Ingredient> inputItems = NonNullList.create();
            for (int i = 0; i < ingArr.size(); i++) inputItems.add(Ingredient.fromJson(ingArr.get(i), false));

            String jsRes = GsonHelper.getAsString(json, RESULT);
            ResourceLocation resc = new ResourceLocation(jsRes);
            ItemStack output = new ItemStack(BuiltInRegistries.ITEM.getOptional(resc).orElseThrow(() -> new IllegalStateException("Output: Item " + jsRes + " does not exist")));

            ItemStack container = ItemStack.EMPTY;
            String jsCon = GsonHelper.getAsString(json, CONTAINER, null);
            if (jsCon != null) {
                ResourceLocation resc2 = new ResourceLocation(jsCon);
                container = new ItemStack(BuiltInRegistries.ITEM.getOptional(resc2).orElseThrow(() -> new IllegalStateException("Container: Item " + jsCon + " does not exist")));
            }

            ResourceLocation contResc = RESC_BOWL;
            String jsSpr = GsonHelper.getAsString(json, CONTAINER_ICON, null);
            if (jsSpr != null) contResc = new ResourceLocation(jsSpr);
            else if (!container.isEmpty()) contResc = texFallback(container.getItem());

            float exp = GsonHelper.getAsFloat(json, EXP, 0.0F);
            float successChance = GsonHelper.getAsFloat(json, SUCCESS, MonsterCookingRecipe.DEFAULT_SUCCESS);
            int cooktime = GsonHelper.getAsInt(json, COOK_TIME, MonsterCookingRecipe.DEFAULT_COOKING_TIME);

            return new MonsterCookingRecipe(recipeId, group, tab, inputItems, output, container, contResc, exp, successChance, cooktime);
        }

        @Override
        public MonsterCookingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buf) {
            String group = buf.readUtf();
            RecipeBookCategories tab = buf.readEnum(RecipeBookCategories.class);
            int size = buf.readInt();
            NonNullList<Ingredient> inputItems = NonNullList.create();
            for (int i = 0; i < size; i++) inputItems.add(Ingredient.fromNetwork(buf));
            ItemStack output = buf.readItem();
            ItemStack container = (buf.readBoolean()) ? buf.readItem() : ItemStack.EMPTY;
            ResourceLocation containerResc = (buf.readBoolean()) ? new ResourceLocation(buf.readUtf()) : RESC_BOWL;
            float exp = buf.readFloat();
            float successChance = buf.readFloat();
            int cooktime = buf.readInt();
            return new MonsterCookingRecipe(recipeId, group, tab, inputItems, output, container, containerResc, exp, successChance, cooktime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, MonsterCookingRecipe recipe) {
            buf.writeUtf(recipe.group);
            buf.writeEnum(recipe.tab);
            buf.writeInt(recipe.inputItems.size());
            for (int i = 0; i < recipe.inputItems.size(); i++) recipe.inputItems.get(i).toNetwork(buf);
            buf.writeItem(recipe.output);
            buf.writeBoolean(!recipe.container.isEmpty());
            if (!recipe.container.isEmpty()) buf.writeItem(recipe.container);
            buf.writeBoolean(recipe.containerIcon != null);
            if (recipe.containerIcon != null) buf.writeUtf(recipe.containerIcon.toString());
            buf.writeFloat(recipe.experience);
            buf.writeFloat(recipe.successChance);
            buf.writeInt(recipe.cookTime);
        }

        private RecipeBookCategories tabDecode(String id) {
            // TODO: IT IS MOST LIKELY THIS WILL FAIL ON THE SERVER - I NEED TO FIND A WAY AROUND THIS
            return switch (id) {
                case "meals" -> DDRecipeBookCategories.DD_MONSTERPOT_MEALS;
                case "drinks" -> DDRecipeBookCategories.DD_MONSTERPOT_DRINKS;
                default -> DDRecipeBookCategories.DD_MONSTERPOT_MISC;
            };
        }

        // mfw java is flawless and doesnt let me use items in switch
        // i get why but, come on dude we're toby foxing it over here
        private ResourceLocation texFallback(Item item) {
            if (item == null) return RESC_BOWL;

            if (item.equals(Items.BONE)) return RESC_BONE;
            else if (item.equals(Items.GLASS_BOTTLE)) return RESC_BOTTLE;
            else if (item.equals(DDItems.MUSIC_DISC_MALADY.get())) return RESC_DISC;
            else if (item.equals(DDItems.STAINED_SCRAP.get())) return RESC_SCRAP;
            else if (item.equals(Items.STICK)) return RESC_STICK;

            return RESC_BOWL;
        }
    }
}