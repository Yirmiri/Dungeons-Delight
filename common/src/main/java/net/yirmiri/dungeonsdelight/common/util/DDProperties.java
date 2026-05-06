package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DDProperties {

    public static class BlockP {
        //MISC
        public static final BlockBehaviour.Properties GENERIC = BlockBehaviour.Properties.copy(Blocks.STONE);
    }

    public static class ItemP {
        static {Rarity.values();}
        public static Rarity MONSTER = Rarity.UNCOMMON;

        //MISC
        public static final Item.Properties GENERIC = new Item.Properties();
        public static final Item.Properties MONSTER_DISC = new Item.Properties().rarity(MONSTER).stacksTo(1);
    }
}
