package net.yirmiri.dungeons_delight.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class DDProperties {
    public static class BlockP {
        //MISC
        public static final Block.Settings MONSTER_POT = FabricBlockSettings.copyOf(ModBlocks.COOKING_POT.get());
    }

    public static class ItemP {
        //MISC
        public static final Item.Settings GENERIC = new Item.Settings();
        public static final Item.Settings LOGO = new Item.Settings().food(DDFoods.LOGO);
    }
}
