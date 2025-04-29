package net.yirmiri.dungeonsdelight.common.util;

import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.yirmiri.dungeonsdelight.common.block.GlowBerryGelatinBlock;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class DDProperties {
    public static class BlockP {
        //STATIONS
        public static final BlockBehaviour.Properties MONSTER_POT = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(0.5F).explosionResistance(6.0F).sound(SoundType.LANTERN).lightLevel(s -> 4).noOcclusion();
        public static final BlockBehaviour.Properties DUNGEON_STOVE = BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(s -> 10).noOcclusion();

        //FEAST BLOCKS
        public static final BlockBehaviour.Properties GLOW_BERRY_GELATIN_BLOCK = BlockBehaviour.Properties.copy(Blocks.CAKE).lightLevel(GlowBerryGelatinBlock.LIGHT_EMISSION).noOcclusion().sound(SoundType.SLIME_BLOCK);
        public static final BlockBehaviour.Properties OSSOBUSCO_BLOCK = BlockBehaviour.Properties.copy(Blocks.CAKE).noOcclusion().sound(SoundType.BONE_BLOCK);
        public static final BlockBehaviour.Properties GUARDIAN_ANGEL_BLOCK = BlockBehaviour.Properties.copy(Blocks.CAKE).noOcclusion().sound(SoundType.SLIME_BLOCK);

        //WORMWOOD
        public static final BlockBehaviour.Properties WORMROOTS = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).strength(0.5F).mapColor(MapColor.TERRACOTTA_PURPLE).noOcclusion().noCollission();
        public static final BlockBehaviour.Properties WORMWOOD = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).strength(1.0F).explosionResistance(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_DOOR = BlockBehaviour.Properties.copy(Blocks.CRIMSON_DOOR).strength(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_TRAPDOOR = BlockBehaviour.Properties.copy(Blocks.CRIMSON_TRAPDOOR).strength(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_BUTTON = BlockBehaviour.Properties.copy(Blocks.CRIMSON_BUTTON).strength(0.25F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_PRESSURE_PLATE = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PRESSURE_PLATE).strength(0.25F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_CABINET = BlockBehaviour.Properties.copy(Blocks.BARREL).sound(SoundType.NETHER_WOOD).strength(1.0F).explosionResistance(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);

        //MISC
        public static final BlockBehaviour.Properties SCULK_MAYO = BlockBehaviour.Properties.copy(Blocks.SCULK);
        public static final BlockBehaviour.Properties SCULK_EGGS = BlockBehaviour.Properties.copy(Blocks.SCULK).strength(2.0F);
        public static final BlockBehaviour.Properties ROTBULB = BlockBehaviour.Properties.copy(Blocks.PITCHER_CROP);
        public static final BlockBehaviour.Properties SPAWNER = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(8.0F, 9.0F).sound(SoundType.NETHERITE_BLOCK);
        public static final BlockBehaviour.Properties SPAWNER_BARS = BlockBehaviour.Properties.copy(Blocks.IRON_BARS).strength(8.0F, 9.0F).sound(SoundType.NETHERITE_BLOCK);
        public static final BlockBehaviour.Properties ROTTEN_CROP = BlockBehaviour.Properties.copy(Blocks.WHEAT);
    }

    public static class ItemP {
        //RARITY
        public static final Rarity MONSTER = Rarity.create("dungeon", formatStyle -> formatStyle.withColor(0xc875c2));

        //MISC
        public static final Item.Properties LOGO = new Item.Properties().food(FoodP.LOGO).rarity(MONSTER);
        public static final Item.Properties GENERIC = new Item.Properties();
        public static final Item.Properties FIRE_RESISTANT = new Item.Properties().fireResistant();
        public static final Item.Properties GENERIC_UNCOMMON = new Item.Properties().rarity(Rarity.UNCOMMON);
        public static final Item.Properties GENERIC_MONSTER = new Item.Properties().rarity(MONSTER);
        public static final Item.Properties GENERIC_16 = new Item.Properties().stacksTo(16);

        //TOOL
        public static final Item.Properties FLINT = new Item.Properties().durability(131);
        public static final Item.Properties GOLD = new Item.Properties().durability(32);
        public static final Item.Properties IRON = new Item.Properties().durability(250);
        public static final Item.Properties DIAMOND = new Item.Properties().durability(1561);
        public static final Item.Properties NETHERITE = new Item.Properties().durability(2031).fireResistant();

        //GENERIC FOODS
        public static final Item.Properties SLIME_BAR = new Item.Properties().food(FoodP.SLIME_BAR);
        public static final Item.Properties SLIME_NOODLES = new Item.Properties().food(FoodP.SLIME_NOODLES);
        public static final Item.Properties SILVERFISH_THORAX = new Item.Properties().food(FoodP.SILVERFISH_THORAX);
        public static final Item.Properties SPIDER_MEAT = new Item.Properties().food(FoodP.SPIDER_MEAT);
        public static final Item.Properties SMOKED_SPIDER_MEAT = new Item.Properties().food(FoodP.SMOKED_SPIDER_MEAT);
        public static final Item.Properties GHAST_CALAMARI = new Item.Properties().food(FoodP.GHAST_CALAMARI);
        public static final Item.Properties FRIED_GHAST_CALAMARI = new Item.Properties().food(FoodP.FRIED_GHAST_CALAMARI);
        public static final Item.Properties GHAST_TENTACLE = new Item.Properties().food(FoodP.GHAST_TENTACLE);
        public static final Item.Properties BUBBLEGUNK = new Item.Properties().food(FoodP.BUBBLEGUNK).rarity(MONSTER).durability(32).setNoRepair();
        public static final Item.Properties CLEAVED_ANCIENT_EGG = new Item.Properties().food(FoodP.CLEAVED_ANCIENT_EGG);
        public static final Item.Properties SLICORICE = new Item.Properties().food(FoodP.SLICORICE);
        public static final Item.Properties DEVILISH_EGGS = new Item.Properties().food(FoodP.DEVILISH_EGGS);
        public static final Item.Properties GHAST_ROLL = new Item.Properties().food(FoodP.GHAST_ROLL).rarity(MONSTER);
        public static final Item.Properties SCULK_APPLE = new Item.Properties().food(FoodP.SCULK_APPLE);
        public static final Item.Properties WARDENZOLA = new Item.Properties().food(FoodP.WARDENZOLA);
        public static final Item.Properties WARDENZOLA_CRUMBLES = new Item.Properties().food(FoodP.WARDENZOLA_CRUMBLES);
        public static final Item.Properties SNIFFER_SHANK = new Item.Properties().food(FoodP.SNIFFER_SHANK);
        public static final Item.Properties COOKED_SNIFFER_SHANK = new Item.Properties().food(FoodP.COOKED_SNIFFER_SHANK);
        public static final Item.Properties SNIFFERWURST = new Item.Properties().food(FoodP.SNIFFERWURST);
        public static final Item.Properties COOKED_SNIFFERWURST = new Item.Properties().food(FoodP.COOKED_SNIFFERWURST);
        public static final Item.Properties OMINOUS_OMELETTE = new Item.Properties().food(FoodP.OMINOUS_OMELETTE);
        public static final Item.Properties CREEPERILLA = new Item.Properties().food(FoodP.CREEPERILLA);

        //SPECIAL FOODS
        public static final Item.Properties AMETHYST_ROCK_CANDY = new Item.Properties().food(FoodP.AMETHYST_ROCK_CANDY).craftRemainder(Items.STICK).stacksTo(16);
        public static final Item.Properties CANDIED_VEX_SUCKER = new Item.Properties().food(FoodP.CANDIED_VEX_SUCKER).craftRemainder(Items.STICK).stacksTo(16);
        public static final Item.Properties CANDIED_SILVERFISH_SUCKER = new Item.Properties().food(FoodP.CANDIED_SILVERFISH_SUCKER).craftRemainder(Items.STICK).stacksTo(16);
        public static final Item.Properties SPIDER_EXTRACT = new Item.Properties().food(FoodP.SPIDER_EXTRACT).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1);
        public static final Item.Properties SPIDER_TANGHULU = new Item.Properties().food(FoodP.SPIDER_TANGHULU).rarity(MONSTER).craftRemainder(Items.BONE).stacksTo(16);
        public static final Item.Properties SPIDER_EYE_SALMAGUNDI = new Item.Properties().food(FoodP.SPIDER_EYE_SALMAGUNDI).rarity(MONSTER).craftRemainder(Items.BOWL).stacksTo(16);
        public static final Item.Properties SCULK_MAYO = new Item.Properties().food(FoodP.SCULK_MAYO).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE);
        public static final Item.Properties ROTTEN_TRIPE = new Item.Properties().food(FoodP.ROTTEN_TRIPE);
        public static final Item.Properties COB_N_CANDY = new Item.Properties().food(FoodP.COB_N_CANDY).rarity(MONSTER).durability(8);
        public static final Item.Properties FLESH = new Item.Properties().food(Foods.ROTTEN_FLESH);
        public static final Item.Properties SOAKED_SKEWER = new Item.Properties().food(FoodP.SOAKED_SKEWER).rarity(MONSTER).craftRemainder(Items.BONE).stacksTo(16);
        public static final Item.Properties POI = new Item.Properties().food(FoodP.POI).craftRemainder(Items.BOWL).stacksTo(16).rarity(MONSTER);
        public static final Item.Properties MONSTER_MUFFIN = new Item.Properties().food(FoodP.MONSTER_MUFFIN).rarity(MONSTER);
        public static final Item.Properties RANCID_REDUCTION = new Item.Properties().rarity(MONSTER).stacksTo(16).food(FoodP.RANCID_REDUCTION).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1);
        public static final Item.Properties SCULK_TART_SLICE = new Item.Properties().food(FoodValues.PIE_SLICE);
        public static final Item.Properties SPIDER_PIE_SLICE = new Item.Properties().food(FoodValues.PIE_SLICE).rarity(MONSTER);
        public static final Item.Properties MONSTER_CAKE_SLICE = new Item.Properties().food(FoodP.MONSTER_CAKE_SLICE).rarity(MONSTER);
        public static final Item.Properties SOFT_SERVE_SNIFFER_EGG = new Item.Properties().food(FoodP.SOFT_SERVE_SNIFFER_EGG).stacksTo(16);
        public static final Item.Properties GHASTLY_SPIRITS = new Item.Properties().food(FoodP.GHASTLY_SPIRITS).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1);

        //MEALS
        public static final Item.Properties GHOULASH = new Item.Properties().food(FoodP.GHOULASH).rarity(MONSTER).craftRemainder(Items.BOWL).stacksTo(16);
        public static final Item.Properties SILVERFISH_FRIED_RICE = new Item.Properties().food(FoodP.SILVERFISH_FRIED_RICE).rarity(MONSTER).craftRemainder(Items.BOWL).stacksTo(16);
        public static final Item.Properties MONSTER_BURGER = new Item.Properties().food(FoodP.MONSTER_BURGER).rarity(MONSTER).stacksTo(1);
        public static final Item.Properties GLOW_BERRY_GELATIN = new Item.Properties().food(FoodP.GLOWBERRY_GELATIN).craftRemainder(Items.BOWL).stacksTo(16);
        public static final Item.Properties GELLED_SALAD = new Item.Properties().food(FoodP.GELLED_SALAD).rarity(MONSTER).craftRemainder(Items.BOWL).stacksTo(16);
        public static final Item.Properties TOKAYAKI = new Item.Properties().food(FoodP.TOKAYAKI).rarity(MONSTER).stacksTo(16).craftRemainder(Items.BOWL);
        public static final Item.Properties SALT_SOAKED_STEW = new Item.Properties().food(FoodP.SALT_SOAKED_STEW).rarity(MONSTER).stacksTo(16).craftRemainder(Items.BOWL);
        public static final Item.Properties OSSOBUSCO = new Item.Properties().food(FoodP.OSSOBUSCO).rarity(MONSTER).stacksTo(16).craftRemainder(Items.BOWL);
        public static final Item.Properties SHIOKARA = new Item.Properties().food(FoodP.SHIOKARA).rarity(MONSTER).stacksTo(16).craftRemainder(Items.BOWL);
        public static final Item.Properties MALICIOUS_SANDWICH = new Item.Properties().food(FoodP.MALICIOUS_SANDWICH).rarity(MONSTER).stacksTo(16);
        public static final Item.Properties TERRINE_LOAF = new Item.Properties().food(FoodP.TERRINE_LOAF).rarity(MONSTER).stacksTo(16);
        public static final Item.Properties GYUDON = new Item.Properties().food(FoodP.GYUDON).rarity(MONSTER).stacksTo(16).craftRemainder(Items.BOWL);
        public static final Item.Properties SINIGANG = new Item.Properties().food(FoodP.SINIGANG).rarity(MONSTER).craftRemainder(Items.BOWL).stacksTo(16);
        public static final Item.Properties SNUFFLEDOG = new Item.Properties().stacksTo(16).food(FoodP.SNUFFLEDOG);
        public static final Item.Properties CHLOROPASTA = new Item.Properties().stacksTo(16).food(FoodP.CHLOROPASTA).craftRemainder(Items.BOWL);
        public static final Item.Properties GUARDIAN_ANGEL = new Item.Properties().stacksTo(16).rarity(MONSTER).food(FoodP.GUARDIAN_ANGEL).craftRemainder(Items.BOWL);
        public static final Item.Properties CHICKEN_JOCKEY_SANDWICH = new Item.Properties().food(FoodP.CHICKEN_JOCKEY_SANDWICH).rarity(MONSTER).stacksTo(16);

        //PLACEABLE FOODS
        public static final Item.Properties MONSTER_CAKE = new Item.Properties().rarity(MONSTER).stacksTo(1);
        public static final Item.Properties SPIDER_PIE = new Item.Properties().rarity(MONSTER).stacksTo(1);
        public static final Item.Properties OSSOBUSCO_BLOCK = new Item.Properties().stacksTo(1).rarity(MONSTER);
        public static final Item.Properties MONSTER_FEAST = new Item.Properties().stacksTo(1).rarity(MONSTER);

        //DRINKS
        public static final Item.Properties BLOODY_MARY = new Item.Properties().food(FoodP.BLOODY_MARY).rarity(MONSTER).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE);
        public static final Item.Properties TARO_MILK_TEA = new Item.Properties().food(FoodP.TARO_MILK_TEA).rarity(MONSTER).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE);
    }

    public static class FoodP {
        //MISC
        public static final FoodProperties LOGO = new FoodProperties.Builder().nutrition(-3).saturationMod(0.0F).alwaysEat().fast().build();

        //GENERIC FOODS
        public static final FoodProperties SLIME_BAR = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build();
        public static final FoodProperties SLIME_NOODLES = new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).build();
        public static final FoodProperties SILVERFISH_THORAX = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).meat().build();
        public static final FoodProperties GHAST_CALAMARI = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).meat().fast().build();
        public static final FoodProperties FRIED_GHAST_CALAMARI = new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).meat().fast().build();
        public static final FoodProperties GHAST_TENTACLE = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).meat().build();
        public static final FoodProperties SMOKED_SPIDER_MEAT = new FoodProperties.Builder().nutrition(5).saturationMod(0.7F).meat().build();
        public static final FoodProperties BUBBLEGUNK = new FoodProperties.Builder().nutrition(-2).saturationMod(0.0F).alwaysEat().build();
        public static final FoodProperties CLEAVED_ANCIENT_EGG = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).build();
        public static final FoodProperties SLICORICE = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).fast().build();
        public static final FoodProperties DEVILISH_EGGS = new FoodProperties.Builder().nutrition(4).saturationMod(0.7F).build();
        public static final FoodProperties GHAST_ROLL = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).fast().build();
        public static final FoodProperties SCULK_APPLE = new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).alwaysEat().build();
        public static final FoodProperties SHIOKARA = new FoodProperties.Builder().nutrition(7).saturationMod(0.5F).build();
        public static final FoodProperties WARDENZOLA = new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).build();
        public static final FoodProperties WARDENZOLA_CRUMBLES = new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.3F).build();
        public static final FoodProperties MALICIOUS_SANDWICH = new FoodProperties.Builder().nutrition(9).saturationMod(0.9F).build();
        public static final FoodProperties SNIFFER_SHANK = new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).build();
        public static final FoodProperties COOKED_SNIFFER_SHANK = new FoodProperties.Builder().nutrition(8).saturationMod(0.9F).build();
        public static final FoodProperties CREEPERILLA = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).fast().build();
        public static final FoodProperties CHICKEN_JOCKEY_SANDWICH = new FoodProperties.Builder().nutrition(7).saturationMod(0.7F).build();

        //SPECIAL FOODS
        public static final FoodProperties AMETHYST_ROCK_CANDY = new FoodProperties.Builder().nutrition(4).saturationMod(0.5F)
                .effect(new MobEffectInstance(DDEffects.DECISIVE.get(), 3600, 0), 1.0F).build();

        public static final FoodProperties CANDIED_VEX_SUCKER = new FoodProperties.Builder().nutrition(4).saturationMod(0.5F)
                .effect(new MobEffectInstance(DDEffects.DECISIVE.get(), 2400, 0), 1.0F)
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 0), 1.0F).build();

        public static final FoodProperties CANDIED_SILVERFISH_SUCKER = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F)
                .effect(new MobEffectInstance(DDEffects.DECISIVE.get(), 2400, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 2400, 0), 1.0F).build();

        public static final FoodProperties SPIDER_EXTRACT = new FoodProperties.Builder().nutrition(0).saturationMod(0.0F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.POISON, 600, 0), 1.0F).build();

        public static final FoodProperties SPIDER_MEAT = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).meat()
                .effect(new MobEffectInstance(MobEffects.POISON, 300, 0), 0.5F).build();

        public static final FoodProperties SPIDER_TANGHULU = new FoodProperties.Builder().nutrition(5).saturationMod(0.7F)
                .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 6000, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.DECISIVE.get(), 2400, 0), 1.0F).build();

        public static final FoodProperties SCULK_MAYO = new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.WEAKNESS, 2400, 0), 0.2F).build();

        public static final FoodProperties ROTTEN_TRIPE = new FoodProperties.Builder().nutrition(2).saturationMod(0.05F).meat()
                .effect(new MobEffectInstance(MobEffects.HUNGER, 200, 0), 0.2F).build();

        public static final FoodProperties COB_N_CANDY = new FoodProperties.Builder().nutrition(4).saturationMod(0.4F)
                .effect(new MobEffectInstance(DDEffects.DECISIVE.get(), 400, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 400, 0), 1.0F).build();

        public static final FoodProperties SOAKED_SKEWER = new FoodProperties.Builder().nutrition(7).saturationMod(0.6F)
                .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 2400, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.DECISIVE.get(), 2400, 0), 1.0F).build();

        public static final FoodProperties POI = new FoodProperties.Builder().nutrition(6).saturationMod(0.5F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 3600, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 2400, 0), 1.0F).build();

        public static final FoodProperties MONSTER_MUFFIN = new FoodProperties.Builder().nutrition(5).saturationMod(0.5F)
                .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 2400, 0), 1.0F).build();

        public static final FoodProperties RANCID_REDUCTION = new FoodProperties.Builder().nutrition(0).saturationMod(0.0F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.WEAKNESS, 1200, 1), 1.0F)
                .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 1200, 2), 1.0F).build();

        public static final FoodProperties MONSTER_CAKE_SLICE = new FoodProperties.Builder().nutrition(3).saturationMod(0.5F)
                .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 2400, 1), 1.0F)
                .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 1200, 1), 1.0F).build();

        public static final FoodProperties SOFT_SERVE_SNIFFER_EGG = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0), 0.75F).build();

        public static final FoodProperties SNIFFERWURST = new FoodProperties.Builder().nutrition(6).saturationMod(0.5F)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 0.5F).build();

        public static final FoodProperties COOKED_SNIFFERWURST = new FoodProperties.Builder().nutrition(10).saturationMod(0.9F)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 160, 0), 1.0F).build();

        public static final FoodProperties OMINOUS_OMELETTE = new FoodProperties.Builder().nutrition(7).saturationMod(0.8F)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0), 1.0F).build();

        //MEALS
        public static final FoodProperties MONSTER_BURGER = new FoodProperties.Builder().nutrition(20).saturationMod(1.0F)
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 1, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.DECISIVE.get(), 1, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 1, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 1, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 1, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 1, 0), 1.0F)
                .build();

        public static final FoodProperties GHOULASH = new FoodProperties.Builder().nutrition(8).saturationMod(0.6F)
                .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 4800, 0), 1.0F).build();

        public static final FoodProperties SILVERFISH_FRIED_RICE = new FoodProperties.Builder().nutrition(12).saturationMod(0.9F)
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 3600, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 4800, 0), 1.0F).build();

        public static final FoodProperties SPIDER_EYE_SALMAGUNDI = new FoodProperties.Builder().nutrition(7).saturationMod(0.9F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 600, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 600, 0), 1.0F).build();

        public static final FoodProperties GLOWBERRY_GELATIN = new FoodProperties.Builder().nutrition(7).saturationMod(0.5F)
                .effect(new MobEffectInstance(RLMobEffects.PERCEPTION.get(), 3600, 0), 1.0F)
                .effect(new MobEffectInstance(ModEffects.COMFORT.get(), 3600, 0), 1.0F).build();

        public static final FoodProperties GELLED_SALAD = new FoodProperties.Builder().nutrition(10).saturationMod(0.6F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 2400, 0), 1.0F).build();

        public static final FoodProperties TOKAYAKI = new FoodProperties.Builder().nutrition(9).saturationMod(0.7F)
                .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 2400, 0), 1.0F).build();

        public static final FoodProperties SALT_SOAKED_STEW = new FoodProperties.Builder().nutrition(10).saturationMod(0.8F)
                .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 3600, 0), 1.0F).build();

        public static final FoodProperties OSSOBUSCO = new FoodProperties.Builder().nutrition(12).saturationMod(0.9F)
                .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 6000, 1), 1.0F).build();

        public static final FoodProperties TERRINE_LOAF = new FoodProperties.Builder().nutrition(7).saturationMod(0.9F)
                .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 3600, 0), 1.0F).build();

        public static final FoodProperties GYUDON = new FoodProperties.Builder().nutrition(9).saturationMod(0.7F)
                .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 3600, 1), 1.0F).build();

        public static final FoodProperties GHASTLY_SPIRITS = new FoodProperties.Builder().nutrition(0).saturationMod(0.0F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.LEVITATION, 300, 0), 1.0F)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 240, 2), 1.0F)
                .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 0), 1.0F).build();

        public static final FoodProperties SINIGANG = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 2400, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 3600, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 3600, 1), 1.0F).build();

        public static final FoodProperties SNUFFLEDOG = new FoodProperties.Builder().nutrition(14).saturationMod(1.1F)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 80, 1), 1.0F).build();

        public static final FoodProperties CHLOROPASTA = new FoodProperties.Builder().nutrition(12).saturationMod(0.8F)
                .effect(new MobEffectInstance(ModEffects.COMFORT.get(), 2400, 0), 1.0F)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 120, 1), 1.0F).build();

        public static final FoodProperties GUARDIAN_ANGEL = new FoodProperties.Builder().nutrition(8).saturationMod(1.0F)
                .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 3600, 0), 1.0F).build();

        //DRINKS
        public static final FoodProperties BLOODY_MARY = new FoodProperties.Builder().alwaysEat()
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 3600, 1), 1.0F).build();

        public static final FoodProperties TARO_MILK_TEA = new FoodProperties.Builder().alwaysEat()
                .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 6000, 2), 1.0F).build();
    }
}
