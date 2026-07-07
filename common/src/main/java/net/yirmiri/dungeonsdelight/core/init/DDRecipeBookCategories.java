package net.yirmiri.dungeonsdelight.core.init;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.List;
import java.util.function.Supplier;

public class DDRecipeBookCategories {
    private static boolean CATEGORIES_READIED = false;
    public static List<RecipeBookCategories> MONSTER_POT_CAGTEGORIES = ImmutableList.of();

    public static RecipeBookCategories DD_MONSTERPOT_SEARCH = RecipeBookCategories.FURNACE_SEARCH;
    public static RecipeBookCategories DD_MONSTERPOT_MEALS = RecipeBookCategories.FURNACE_FOOD;
    public static RecipeBookCategories DD_MONSTERPOT_DRINKS = RecipeBookCategories.FURNACE_BLOCKS;
    public static RecipeBookCategories DD_MONSTERPOT_MISC = RecipeBookCategories.FURNACE_MISC;

    public static final String MP_SEARCH_ID = "DD_MONSTERPOT_SEARCH";
    public static final String MP_MEALS_ID = "DD_MONSTERPOT_MEALS";
    public static final String MP_DRINKS_ID = "DD_MONSTERPOT_DRINKS";
    public static final String MP_MISC_ID = "DD_MONSTERPOT_MISC";

    // TODO: Uncomment the belows
    public static final Supplier<ItemStack[]> MP_SEARCH_ITEMS = () -> new ItemStack[]
            {
                    Items.COMPASS.getDefaultInstance()
            };
    public static final Supplier<ItemStack[]> MP_MEALS_ITEMS = () -> new ItemStack[]
            {
                    DDItems.GHOULASH.get().getDefaultInstance()
            };
    public static final Supplier<ItemStack[]> MP_DRINKS_ITEMS = () -> new ItemStack[]
            {
                    DDItems.TARO_MILK_TEA.get().getDefaultInstance()
            };
    public static final Supplier<ItemStack[]> MP_MISC_ITEMS = () -> new ItemStack[]
            {
                    DDItems.GUNPOWDER_BAKED_ARACHNID.get().getDefaultInstance()
                    //DDItems.MONSTER_MUFFIN.get().getDefaultInstance()
            };

    public static void readyUpCategories() {
        if (CATEGORIES_READIED) throw new IllegalArgumentException("Monster pot categories were already readied up");

        CATEGORIES_READIED = true;
        MONSTER_POT_CAGTEGORIES = ImmutableList.of(
                DD_MONSTERPOT_SEARCH,
                DD_MONSTERPOT_MEALS,
                DD_MONSTERPOT_DRINKS,
                DD_MONSTERPOT_MISC
        );
    }
}
