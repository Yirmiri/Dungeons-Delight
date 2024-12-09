package net.yirmiri.dungeonsdelight.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDTags {
    public static class BlockT {
        public static final TagKey<Block> MONSTER_HEAT_SOURCES = create("monster_heat_sources");
        public static final TagKey<Block> MONSTER_TRAY_HEAT_SOURCES = create("monster_tray_heat_sources");
        public static final TagKey<Block> MONSTER_HEAT_CONDUCTORS = create("monster_heat_conductors");

        private static TagKey<Block> create(String id) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(DungeonsDelight.MOD_ID, id));
        }
    }

    public static class ItemT {
        public static final TagKey<Item> MONSTER_FOODS = create("monster_foods");
        public static final TagKey<Item> DUNGEONS_DELIGHT_FOODS = create("dungeons_delight_foods");

        private static TagKey<Item> create(String id) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(DungeonsDelight.MOD_ID, id));
        }
    }
}
