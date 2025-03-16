package net.yirmiri.dungeonsdelight.core.init;

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
        public static final TagKey<Item> ROCK_CANDIES = create("rock_candies");
        public static final TagKey<Item> ALLAY_DUPLICATING_ITEMS = create("allay_duplicating_items");
        public static final TagKey<Item> BITEABLE_FOODS = create("biteable_foods");
        public static final TagKey<Item> CLEAVERS = create("cleavers");
        public static final TagKey<Item> RAW_GHAST = create("raw_ghast");
        public static final TagKey<Item> EXTRACTS = create("extracts");
        public static final TagKey<Item> SEA_PLANTS = create("sea_plants");
        public static final TagKey<Item> ACIDICS = create("acidics");
        public static final TagKey<Item> FLESHES = create("fleshes");
        public static final TagKey<Item> SLIME_BALLS = create("slime_balls");
        public static final TagKey<Item> SCULK_FOODS = create("sculk_foods");
        public static final TagKey<Item> SNIFFER_FOODS = create("sniffer_foods");

        //BITEABLE REFILLS
        public static final TagKey<Item> REFILLS_BUBBLEGUNK = create("refills_bubblegunk");
        public static final TagKey<Item> REFILLS_COB_N_CANDY = create("refills_cob_n_candy");
        //COMPAT
        public static final TagKey<Item> REFILLS_BRAISED_GLOWWORM_QUEEN = create("refills_braised_glowworm_queen");

        private static TagKey<Item> create(String id) {
            return ItemTags.create(new ResourceLocation(DungeonsDelight.MOD_ID, id));
        }
    }

    public static class EntityT {
        //SCAVENGING
        public static final TagKey<EntityType<?>> SCAVENGING_SPIDER_MEAT = create("scavenging_spider_meat");
        public static final TagKey<EntityType<?>> SCAVENGING_ROTTEN_TRIPE = create("scavenging_rotten_tripe");
        public static final TagKey<EntityType<?>> SCAVENGING_GHAST_TENTACLE = create("scavenging_ghast_tentacle");
        public static final TagKey<EntityType<?>> SCAVENGING_SILVERFISH_THORAX = create("scavenging_silverfish_thorax");
        public static final TagKey<EntityType<?>> SCAVENGING_GUNK = create("scavenging_gunk");
        public static final TagKey<EntityType<?>> SCAVENGING_SCULK_POLYP = create("scavenging_sculk_polyp");

        //DROPS
        public static final TagKey<EntityType<?>> DROPS_GRITTY_FLESH = create("drops_gritty_flesh");
        public static final TagKey<EntityType<?>> DROPS_BRINED_FLESH = create("drops_brined_flesh");
        public static final TagKey<EntityType<?>> DROPS_SNIFFER_SHANK = create("drops_sniffer_shank");

        private static TagKey<EntityType<?>> create(String id) {
            return create(new ResourceLocation(DungeonsDelight.MOD_ID, id));
        }

        public static TagKey<EntityType<?>> create(ResourceLocation id) {
            return TagKey.create(Registries.ENTITY_TYPE, id);
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
}
