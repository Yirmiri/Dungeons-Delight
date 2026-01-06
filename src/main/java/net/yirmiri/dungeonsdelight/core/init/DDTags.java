package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

public class DDTags {
    public static class BlockT {
        public static final TagKey<Block> MONSTER_HEAT_SOURCES = create("monster_heat_sources");
        public static final TagKey<Block> MONSTER_TRAY_HEAT_SOURCES = create("monster_tray_heat_sources");
        public static final TagKey<Block> SCULKING_ACTIVATORS = create("sculking_activators");
        public static final TagKey<Block> ROTBULB_GROWABLE_ON = create("rotbulb_growable_on");
        public static final TagKey<Block> ROTBULB_CROP_GROWS_FASTER = create("rotbulb_crop_grows_faster");
        public static final TagKey<Block> LIVING_FIRE_BASE_BLOCKS = create("living_fire_base_blocks");

        private static TagKey<Block> create(String id) {
            return BlockTags.create(RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }
    }

    public static class ItemT {
        //CONVENTIAL
        public static final TagKey<Item> C_CLEAVERS = create(IntegrationIds.C, "tools/cleaver");
        public static final TagKey<Item> C_EGGS = create(IntegrationIds.C, "item/eggs");

        //MISC
        public static final TagKey<Item> CLEAVERS = create("cleavers");
        public static final TagKey<Item> CLEAVER_ENCHANTABLE = create("cleaver_enchantable");
        public static final TagKey<Item> STAINED_ENCHANTABLE = create("stained_enchantable");
        public static final TagKey<Item> FLAMING_KNIVES = create("flaming_knives");
        public static final TagKey<Item> REPAIRS_STAINED_TOOLS = create("repairs_stained_tools");

        //FOODS
        public static final TagKey<Item> MONSTER_FOODS = create("monster_foods");
        public static final TagKey<Item> WORMOUTH_BLACKLIST = create("wormouth_blacklist");
        public static final TagKey<Item> WORMOUTH_FAVORITES = create("wormouth_favorites");
        public static final TagKey<Item> BITEABLE_FOODS = create("biteable_foods");
        public static final TagKey<Item> SCULK_FOODS = create("sculk_foods");
        public static final TagKey<Item> SNIFFER_FOODS = create("sniffer_foods");
        public static final TagKey<Item> ROCK_CANDIES = create("rock_candies");
        public static final TagKey<Item> ALLAY_DUPLICATING_ITEMS = create("allay_duplicating_items");
        public static final TagKey<Item> EVAPORATES_IN_WATER = create("evaporates_in_water");

        //INGREDIENTS
        public static final TagKey<Item> RUBABOO_INGREDIENTS = create("rubaboo_ingredients");
        public static final TagKey<Item> RAW_GHAST = create("raw_ghast");
        public static final TagKey<Item> SCULK_CHEESE = create("sculk_cheese");
        public static final TagKey<Item> EXTRACTS = create("extracts");
        public static final TagKey<Item> ACIDICS = create("acidics");
        public static final TagKey<Item> FLESHES = create("fleshes");
        public static final TagKey<Item> SEA_PLANTS = create("sea_plants");
        public static final TagKey<Item> ANCIENT_FLORA = create("ancient_flora");
        public static final TagKey<Item> SLIME_BALLS = create("slime_balls");

        //INTEGRATION (just used for referencing)
        public static final TagKey<Item> REPAIRS_IRONWOOD_TOOLS = create(IntegrationIds.TWILIGHTFOREST, "repairs_ironwood_tools");
        public static final TagKey<Item> REPAIRS_FIERY_TOOLS = create(IntegrationIds.TWILIGHTFOREST, "repairs_fiery_tools");
        public static final TagKey<Item> REPAIRS_STEELEAF_TOOLS = create(IntegrationIds.TWILIGHTFOREST, "repairs_steeleaf_tools");
        public static final TagKey<Item> REPAIRS_KNIGHTMETAL_TOOLS = create(IntegrationIds.TWILIGHTFOREST, "repairs_knightmetal_tools");
        public static final TagKey<Item> REPAIRS_ZANITE_TOOLS = create(IntegrationIds.AETHER, "repairs_zanite_tools");
        public static final TagKey<Item> REPAIRS_GRAVITITE_TOOLS = create(IntegrationIds.AETHER, "repairs_gravitite_tools");
        public static final TagKey<Item> REPAIRS_STEEL_TOOLS = create(IntegrationIds.ALLOYED, "repairs_steel_tools");
        public static final TagKey<Item> GROSS_FOODS = create(IntegrationIds.MALUM, "gross_foods");

        private static TagKey<Item> create(String id) {
            return ItemTags.create(RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        private static TagKey<Item> create(String modid, String id) {
            return ItemTags.create(RunicLib.customid(modid, id));
        }
    }

    public static class EntityT {
        //MISC
        public static final TagKey<EntityType<?>> IGNORES_ECHO_BLAST = create("ignores_echo_blast");
        public static final TagKey<EntityType<?>> VORACITY_DEATH_FX_BLACKLIST = create("voracity_death_fx_blacklist");

        //SCAVENGING
        public static final TagKey<EntityType<?>> SCAVENGING_SPIDER_MEAT = create("scavenging_spider_meat");
        public static final TagKey<EntityType<?>> SCAVENGING_ROTTEN_TRIPE = create("scavenging_rotten_tripe");
        public static final TagKey<EntityType<?>> SCAVENGING_GHAST_TENTACLE = create("scavenging_ghast_tentacle");
        public static final TagKey<EntityType<?>> SCAVENGING_SILVERFISH_ABDOMEN = create("scavenging_silverfish_abdomen");
        public static final TagKey<EntityType<?>> SCAVENGING_GUNK = create("scavenging_gunk");
        public static final TagKey<EntityType<?>> SCAVENGING_SCULK_POLYP = create("scavenging_sculk_polyp");
        public static final TagKey<EntityType<?>> SCAVENGING_CREEPERILLA = create("scavenging_creeperilla");
        public static final TagKey<EntityType<?>> SCAVENGING_SLIME_NOODLES = create("scavenging_slime_noodles");
        //INTEGRATION
        public static final TagKey<EntityType<?>> INTEGRATION_SCAVENGING_BUG_CHOPS = create("integration_scavenging_bug_chops");

        //DROPS
        public static final TagKey<EntityType<?>> DROPS_GRITTY_FLESH = create("drops_gritty_flesh");
        public static final TagKey<EntityType<?>> DROPS_BRINED_FLESH = create("drops_brined_flesh");
        public static final TagKey<EntityType<?>> DROPS_SNIFFER_SHANK = create("drops_sniffer_shank");

        private static TagKey<EntityType<?>> create(String id) {
            return create(RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        public static TagKey<EntityType<?>> create(ResourceLocation id) {
            return TagKey.create(Registries.ENTITY_TYPE, id);
        }
    }

    public static class EffectT {
        //MISC
        public static final TagKey<MobEffect> MONSTER_EFFECT = create("monster_effect");
        public static final TagKey<MobEffect> UNMODIFIABLE_EFFECTS = create("unmodifiable_effects");

        private static TagKey<MobEffect> create(String id) {
            return create(RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        public static TagKey<MobEffect> create(ResourceLocation id) {
            return TagKey.create(Registries.MOB_EFFECT, id);
        }
    }

    public static class BiomeT {
        //FEATURES
        public static final TagKey<Biome> WILD_ROTBULB_GENERATES_IN = create("wild_rotbulb_generates_in");

        private static TagKey<Biome> create(String id) {
            return create(RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        public static TagKey<Biome> create(ResourceLocation id) {
            return TagKey.create(Registries.BIOME, id);
        }
    }
}
