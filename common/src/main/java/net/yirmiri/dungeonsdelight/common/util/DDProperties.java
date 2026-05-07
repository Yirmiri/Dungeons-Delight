package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;

public class DDProperties {

    public static class BlockP {
        //MISC
        public static final BlockBehaviour.Properties GENERIC = BlockBehaviour.Properties.copy(Blocks.STONE);
    }

    public static class ItemP {
        //MISC
        public static final Item.Properties GENERIC = new Item.Properties();
        public static final Item.Properties GENERIC_MONSTER = new Item.Properties().rarity(DDRarities.MONSTER);
        public static final Item.Properties MONSTER_DISC = new Item.Properties().rarity(DDRarities.MONSTER).stacksTo(1);
        public static final Item.Properties LOGO_ITEM = new Item.Properties().rarity(DDRarities.MONSTER).food(FoodP.LOGO);

        //TOOL
        public static final Item.Properties FLINT = new Item.Properties().durability(131);
        public static final Item.Properties GOLD = new Item.Properties().durability(32);
        public static final Item.Properties IRON = new Item.Properties().durability(250);
        public static final Item.Properties DIAMOND = new Item.Properties().durability(1561);
        public static final Item.Properties NETHERITE = new Item.Properties().durability(2031).fireResistant();
    }

    public static class FoodP {
        //MISC
        public static final FoodProperties LOGO = new FoodProperties.Builder().nutrition(-4).saturationMod(0.0F).alwaysEat().fast().build();
    }
}
