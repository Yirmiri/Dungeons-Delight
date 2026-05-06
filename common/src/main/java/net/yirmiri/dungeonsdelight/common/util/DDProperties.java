package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DDProperties {

    public static class BlockP {
        //MISC
        public static final BlockBehaviour.Properties GENERIC = BlockBehaviour.Properties.copy(Blocks.STONE);
    }

    public static class ItemP {
        //MISC
        public static final Item.Properties GENERIC = new Item.Properties();
    }
}
