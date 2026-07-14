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
    public static RecipeBookCategories DD_MONSTERPOT_TIER_1 = RecipeBookCategories.FURNACE_FOOD;
    public static RecipeBookCategories DD_MONSTERPOT_TIER_2 = RecipeBookCategories.FURNACE_BLOCKS;
    public static RecipeBookCategories DD_MONSTERPOT_TIER_3 = RecipeBookCategories.FURNACE_MISC;
    public static RecipeBookCategories DD_MONSTERPOT_MISC = RecipeBookCategories.CRAFTING_MISC;

    public static final String MP_SEARCH_ID = "DD_MONSTERPOT_SEARCH";
    public static final String MP_TIER_1_ID = "DD_MONSTERPOT_TIER_1";
    public static final String MP_TIER_2_ID = "DD_MONSTERPOT_TIER_2";
    public static final String MP_TIER_3_ID = "DD_MONSTERPOT_TIER_3";
    public static final String MP_MISC_ID = "DD_MONSTERPOT_MISC";

    // TODO: Uncomment the belows
    public static final Supplier<ItemStack[]> MP_SEARCH_ITEMS = () -> new ItemStack[] {
            Items.COMPASS.getDefaultInstance() //todo stained compass
    };
    public static final Supplier<ItemStack[]> MP_TIER_1_ITEMS = () -> new ItemStack[] {
            DDItems.GHOULASH.get().getDefaultInstance()
    };
    public static final Supplier<ItemStack[]> MP_TIER_2_ITEMS = () -> new ItemStack[] {
            DDItems.SALMAGUNDI.get().getDefaultInstance()
    };
    public static final Supplier<ItemStack[]> MP_TIER_3_ITEMS = () -> new ItemStack[]{
            DDItems.TELEPOTAGE.get().getDefaultInstance() //todo something else for the icon
    };
    public static final Supplier<ItemStack[]> MP_MISC_ITEMS = () -> new ItemStack[] {
            DDItems.TARO_MILK_TEA.get().getDefaultInstance()
    };

    public static void readyUpCategories() {
        if (CATEGORIES_READIED) throw new IllegalArgumentException("Monster pot categories were already readied up");

        CATEGORIES_READIED = true;
        MONSTER_POT_CAGTEGORIES = ImmutableList.of(
                DD_MONSTERPOT_SEARCH,
                DD_MONSTERPOT_TIER_1,
                DD_MONSTERPOT_TIER_2,
                DD_MONSTERPOT_TIER_3
        );
    }
}
