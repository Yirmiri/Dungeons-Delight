package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

public class DDProperties {

    public static class BlockP {
        //MISC
        public static final BlockBehaviour.Properties GENERIC = BlockBehaviour.Properties.copy(Blocks.STONE);
    }

    public static class ItemP {
        //MISC
        public static final Item.Properties GENERIC = new Item.Properties();
        public static final Item.Properties MUSIC_DISC_MALADY = new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1);
    }
}
