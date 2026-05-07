package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDTags {
    public static class BlockT {
        public static final TagKey<Block> CLEAVER_MINEABLE = create("cleaver_mineable");

        private static TagKey<Block> create(String id) {
            return TagKey.create(Registries.BLOCK, RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }
    }

    public static class ItemT {
        public static final TagKey<Item> CLEAVERS = create("cleavers");
        public static final TagKey<Item> FLAMING_CLEAVERS = create("flaming_cleavers");

        private static TagKey<Item> create(String id) {
            return TagKey.create(Registries.ITEM, RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }
    }
}
