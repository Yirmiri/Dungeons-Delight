package net.yirmiri.dungeons_delight.util;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeons_delight.DungeonsDelight;

public class DDTags {
    public static class Blocks {
        public static final TagKey<Block> MONSTER_HEAT_SOURCES = create("monster_heat_sources");

        private static TagKey<Block> create(String id) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(DungeonsDelight.MOD_ID, id));
        }
    }
}
