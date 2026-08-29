package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.integration.DDIntegration;

public class DDTags {
    public static class BlockT {
        public static final TagKey<Block> LIVING_HEAT_SOURCES = create("living_heat_sources");
        public static final TagKey<Block> LIVING_FIRE_BASE_BLOCKS = create("living_fire_base_blocks");
        public static final TagKey<Block> CLEAVER_MINEABLE = create("cleaver_mineable");
        public static final TagKey<Block> CANNOT_CLIMB = create("prevents_climbing");
        public static final TagKey<Block> WILD_CROP_GROWABLE_ON = create("wild_crop_growable_on");

        private static TagKey<Block> create(String id) {
            return TagKey.create(Registries.BLOCK, RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        private static TagKey<Block> create(String modid, String id) {
            return TagKey.create(Registries.BLOCK, RunicLib.customid(modid, id));
        }
    }

    public static class ItemT {
        public static final TagKey<Item> REAPS_ITEMS = create("reaps_items");
        public static final TagKey<Item> CLEAVERS = create("cleavers");
        public static final TagKey<Item> USES_DULL_CLEAVER_SOUND = create("uses_dull_cleaver_sound");
        public static final TagKey<Item> REPAIRS_STAINED_TOOLS = create("repairs_stained_tools");
        public static final TagKey<Item> ALLAY_DUPLICATING_ITEMS = create("allay_duplicating_items");
        public static final TagKey<Item> HAS_EFFECT_TOOLTIP = create("has_effect_tooltip");
        public static final TagKey<Item> HAS_MEAL_STACK_SIZE = create("has_meal_stack_size");
        public static final TagKey<Item> MONSTER_POT_CONTAINERS = create("monster_pot_containers");

        //FOOD
        public static final TagKey<Item> MONSTER_FOODS = create("monster_foods");
        public static final TagKey<Item> BITEABLES = create("biteables");
        public static final TagKey<Item> ROCK_CANDIES = create("rock_candies");
        public static final TagKey<Item> FLESHES = create("fleshes");
        public static final TagKey<Item> GHAST_MEATS = create("ghast_meats");
        public static final TagKey<Item> MONSTER_GREENS = create("monster_greens");
        public static final TagKey<Item> RIPE_WARDENZOLAS = create("ripe_wardenzolas");
        public static final TagKey<Item> HOMEWARD_FOODS = create("homeward_foods");
        public static final TagKey<Item> ANY_BUG_ABDOMENS = create("any_bug_abdomens");

        //INTEGRATION
        public static final TagKey<Item> FLAMING_CLEAVERS = create("flaming_cleavers");
        public static final TagKey<Item> NV_SMOKING_ITEMS = create(DDIntegration.NV_ID, "smoking_items");
        public static final TagKey<Item> ST_WOLFRAM_ITEMS = create(DDIntegration.ST_ID, "wolfram_items");

        private static TagKey<Item> create(String id) {
            return TagKey.create(Registries.ITEM, RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        private static TagKey<Item> create(String modid, String id) {
            return TagKey.create(Registries.ITEM, RunicLib.customid(modid, id));
        }
    }

    public static class EntityT {
        public static final TagKey<EntityType<?>> ROTTEN = create("rotten");
        public static final TagKey<EntityType<?>> CAN_HOLLOW = create("can_hollow");
        public static final TagKey<EntityType<?>> PRODUCES_SPIDER_EXTRACT = create("produces_spider_extract");
        public static final TagKey<EntityType<?>> HAS_POTENT_SPIDER_EXTRACT = create("has_potent_spider_extract");
        public static final TagKey<EntityType<?>> IGNORES_ECHO_BLAST = create("ignores_echo_blast");
        public static final TagKey<EntityType<?>> RICOCHET_CANNOT_TARGET = create("ricochet_cannot_target");

        //REAPING
        public static final TagKey<EntityType<?>> REAPS_SPIDER_MEAT = create("reaps_spider_meat");
        public static final TagKey<EntityType<?>> REAPS_CREEPERILLA = create("reaps_creeperilla");
        public static final TagKey<EntityType<?>> REAPS_SLIME_NOODLES = create("reaps_slime_noodles");
        public static final TagKey<EntityType<?>> REAPS_MAGMARONI = create("reaps_magmaroni");
        public static final TagKey<EntityType<?>> REAPS_ROTTEN_TRIPE = create("reaps_rotten_tripe");
        public static final TagKey<EntityType<?>> REAPS_GHAST_TENTACLE = create("reaps_ghast_tentacle");
        public static final TagKey<EntityType<?>> REAPS_SILVERFISH_ABDOMEN = create("reaps_silverfish_abdomen");
        public static final TagKey<EntityType<?>> REAPS_SNIFFER_SHANK = create("reaps_sniffer_shank");
        public static final TagKey<EntityType<?>> REAPS_SCULK_POLYP = create("reaps_sculk_polyp");
        public static final TagKey<EntityType<?>> REAPS_TREASURE_BUG_ABDOMEN = create("reaps_treasure_bug_abdomen");
        public static final TagKey<EntityType<?>> REAPS_GUNK = create("reaps_gunk");

        private static TagKey<EntityType<?>> create(String id) {
            return TagKey.create(Registries.ENTITY_TYPE, RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        private static TagKey<EntityType<?>> create(String modid, String id) {
            return TagKey.create(Registries.ENTITY_TYPE, RunicLib.customid(modid, id));
        }
    }

    public static class EffectT {
        public static final TagKey<MobEffect> MONSTER_EFFECTS = create("monster_effects");
        public static final TagKey<MobEffect> MONSTER_EFFECTS_THAT_PRESERVE_AMPLIFIER = create("monster_effects_that_preserve_amplifier");
        public static final TagKey<MobEffect> UNMODIFIABLE_EFFECTS = create("unmodifiable_effects");

        private static TagKey<MobEffect> create(String id) {
            return TagKey.create(Registries.MOB_EFFECT, RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        private static TagKey<MobEffect> create(String modid, String id) {
            return TagKey.create(Registries.MOB_EFFECT, RunicLib.customid(modid, id));
        }
    }

    public static class DamageT {
        public static final TagKey<DamageType> CLEAVERS = create("cleavers");
        public static final TagKey<DamageType> REAPS_ITEMS = create("reaps_items");
        public static final TagKey<DamageType> KEEPS_HOMEWARD = create("keeps_homeward");

        //INTEGRATION
        public static final TagKey<DamageType> ST_NEGATES_HEALTH = create(DDIntegration.ST_ID, "negates_health");

        private static TagKey<DamageType> create(String id) {
            return TagKey.create(Registries.DAMAGE_TYPE, RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        private static TagKey<DamageType> create(String modid, String id) {
            return TagKey.create(Registries.DAMAGE_TYPE, RunicLib.customid(modid, id));
        }
    }

    public static class FluidT {
        public static final TagKey<Fluid> MAINTAINS_TERROR_PRETA = create("maintains_terror_preta");

        private static TagKey<Fluid> create(String id) {
            return TagKey.create(Registries.FLUID, RunicLib.customid(DungeonsDelight.MOD_ID, id));
        }

        private static TagKey<Fluid> create(String modid, String id) {
            return TagKey.create(Registries.FLUID, RunicLib.customid(modid, id));
        }
    }
}