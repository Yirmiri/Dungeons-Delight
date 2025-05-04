package net.yirmiri.dungeonsdelight.integration.util;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class INProperties {
    public static class BlockP {

    }

    public static class ItemP {
        //APPLEDOG
        public static final Item.Properties SCULK_DOGAPPLE = new Item.Properties().food(FoodP.SCULK_DOGAPPLE);

        //TWILIGHT FOREST
        public static final Item.Properties BUG_CHOPS = new Item.Properties().food(FoodP.BUG_CHOPS);
        public static final Item.Properties FRIED_BUG_CHOPS = new Item.Properties().food(FoodP.FRIED_BUG_CHOPS);
    }

    public static class FoodP {
        //APPLEDOG
        public static final FoodProperties SCULK_DOGAPPLE = new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).alwaysEat().build();

        //TWILIGHT FOREST
        public static final FoodProperties BUG_CHOPS = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).meat().build();
        public static final FoodProperties FRIED_BUG_CHOPS = new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).meat().build();
    }
}
