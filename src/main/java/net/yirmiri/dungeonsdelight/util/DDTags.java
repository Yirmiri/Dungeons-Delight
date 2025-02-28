package net.yirmiri.dungeonsdelight.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDTags {
    public static class BlockT {
        public static final TagKey<Block> MONSTER_HEAT_SOURCES = create("monster_heat_sources");
        public static final TagKey<Block> MONSTER_TRAY_HEAT_SOURCES = create("monster_tray_heat_sources");
        public static final TagKey<Block> MONSTER_HEAT_CONDUCTORS = create("monster_heat_conductors");
        public static final TagKey<Block> SCULKING_ACTIVATORS = create("sculking_activators");
        public static final TagKey<Block> ROTBULB_GROWABLE_ON = create("rotbulb_growable_on");

        private static TagKey<Block> create(String id) {
            return BlockTags.create(new ResourceLocation(DungeonsDelight.MOD_ID, id));
        }
    }

    public static class ItemT {
        public static final TagKey<Item> MONSTER_FOODS = create("monster_foods");
        public static final TagKey<Item> DUNGEONS_DELIGHT_FOODS = create("dungeons_delight_foods");
        public static final TagKey<Item> ROCK_CANDIES = create("rock_candies");
        public static final TagKey<Item> ALLAY_DUPLICATING_ITEMS = create("allay_duplicating_items");
        public static final TagKey<Item> BITEABLE_FOODS = create("biteable_foods");
        public static final TagKey<Item> CLEAVERS = create("cleavers");

        //BITEABLE REFILLS
        public static final TagKey<Item> REFILLS_BUBBLEGUNK = create("refills_bubblegunk");
        //COMPAT
        public static final TagKey<Item> REFILLS_BRAISED_GLOWWORM_QUEEN = create("refills_braised_glowworm_queen");

        private static TagKey<Item> create(String id) {
            return ItemTags.create(new ResourceLocation(DungeonsDelight.MOD_ID, id));
        }
    }

    public static class EffectT {

        private static TagKey<MobEffect> create(String id) {
            return create(new ResourceLocation(DungeonsDelight.MOD_ID, id));
        }

        public static TagKey<MobEffect> create(ResourceLocation id) {
            return TagKey.create(Registries.MOB_EFFECT, id);
        }
    }

    public static class EntityT {
        public static final TagKey<EntityType<?>> DROPS_SPIDER_MEAT = create("drops_spider_meat");
        public static final TagKey<EntityType<?>> DROPS_ROTTEN_TRIPE = create("drops_rotten_tripe");
        public static final TagKey<EntityType<?>> DROPS_GHAST_TENTACLE = create("drops_ghast_tentacle");
        public static final TagKey<EntityType<?>> DROPS_SILVERFISH_THORAX = create("drops_silverfish_thorax");
        public static final TagKey<EntityType<?>> DROPS_GUNK = create("drops_gunk");

        private static TagKey<EntityType<?>> create(String id) {
            return create(new ResourceLocation(DungeonsDelight.MOD_ID, id));
        }

        public static TagKey<EntityType<?>> create(ResourceLocation id) {
            return TagKey.create(Registries.ENTITY_TYPE, id);
        }
    }
}
