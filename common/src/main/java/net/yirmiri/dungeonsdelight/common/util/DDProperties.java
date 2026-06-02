package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDSoundTypes;

public class DDProperties {

    // TODO (FOR ARTYRIAN): replace copy with ofFullCopy in 1.21
    public static class BlockP {
        //METAL DOOR + TRAPDOOR (replaces copper door copying from 1.21)
        private static final BlockBehaviour.Properties METAL_DOOR = BlockBehaviour.Properties.of()
                .mapColor(Blocks.IRON_BLOCK.defaultMapColor()).strength(3.0F, 6.0F).noOcclusion().requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY);
        private static final BlockBehaviour.Properties METAL_TRAPDOOR = BlockBehaviour.Properties.of()
                .mapColor(Blocks.IRON_BLOCK.defaultMapColor()).strength(3.0F, 6.0F).requiresCorrectToolForDrops().noOcclusion().isValidSpawn((state, getter, pos, type) -> false);

        //MISC
        public static final BlockBehaviour.Properties GENERIC = BlockBehaviour.Properties.copy(Blocks.STONE);
        public static final BlockBehaviour.Properties MONSTER_POT = BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(s -> 4).strength(1.0F, 0.0F).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties CLEAVING_BOARD = BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASS).strength(1.0F);
        public static final BlockBehaviour.Properties BAMBOO_CLEAVING_BOARD = CLEAVING_BOARD.mapColor(MapColor.COLOR_YELLOW);

        //CROPS
        public static final BlockBehaviour.Properties WILD_CROP = BlockBehaviour.Properties.copy(Blocks.LILAC).sound(SoundType.AZALEA_LEAVES);
        public static final BlockBehaviour.Properties TERROR_PRETA = BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.ROOTED_DIRT);
        public static final BlockBehaviour.Properties BLEETS = BlockBehaviour.Properties.copy(Blocks.BEETROOTS);
        public static final BlockBehaviour.Properties MANALLIUMS = BlockBehaviour.Properties.copy(Blocks.CARROTS);
        public static final BlockBehaviour.Properties ENDELVES = BlockBehaviour.Properties.copy(Blocks.WHEAT);

        //LIVING/STAINED SCRAP
        public static final BlockBehaviour.Properties STAINED = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0F, 9.0F).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_SCRAP_BARS = BlockBehaviour.Properties.copy(Blocks.IRON_BARS).strength(6.0F, 9.0F).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_GRATE = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0F, 9.0F).noOcclusion().sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties LIVING_LAMP = BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_SCRAP_DOOR = METAL_DOOR.sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_SCRAP_TRAPDOOR = METAL_TRAPDOOR.sound(DDSoundTypes.STAINED_SCRAP);

        //WORMWOOD
        public static final BlockBehaviour.Properties WORMROOT_TENDRILS = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).strength(0.5F).mapColor(MapColor.TERRACOTTA_PURPLE).noOcclusion().noCollission();
        public static final BlockBehaviour.Properties WORMWOOD = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).strength(1.0F).explosionResistance(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMOUTH = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).randomTicks().strength(9.0F).explosionResistance(9.0F).mapColor(MapColor.TERRACOTTA_PURPLE).pushReaction(PushReaction.IGNORE);
        public static final BlockBehaviour.Properties WORMWOOD_DOOR = BlockBehaviour.Properties.copy(Blocks.CRIMSON_DOOR).strength(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_TRAPDOOR = BlockBehaviour.Properties.copy(Blocks.CRIMSON_TRAPDOOR).strength(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_BUTTON = BlockBehaviour.Properties.copy(Blocks.CRIMSON_BUTTON).strength(0.25F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_PRESSURE_PLATE = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PRESSURE_PLATE).strength(0.25F).mapColor(MapColor.TERRACOTTA_PURPLE);
    }

    public static class ItemP {
        //MISC
        public static final Item.Properties GENERIC = new Item.Properties();
        public static final Item.Properties GENERIC_MONSTER = new Item.Properties().rarity(DDRarities.MONSTER);
        public static final Item.Properties GENERIC_UNCOMMON = new Item.Properties().rarity(Rarity.UNCOMMON);
        public static final Item.Properties MONSTER_DISC = new Item.Properties().rarity(DDRarities.MONSTER).stacksTo(1);
        public static final Item.Properties LOGO_ITEM = new Item.Properties().rarity(DDRarities.MONSTER).food(FoodP.LOGO);

        //TOOL
        public static final Item.Properties FLINT = new Item.Properties().durability(131);
        public static final Item.Properties GOLD = new Item.Properties().durability(32);
        public static final Item.Properties IRON = new Item.Properties().durability(250);
        public static final Item.Properties DIAMOND = new Item.Properties().durability(1561);
        public static final Item.Properties NETHERITE = new Item.Properties().durability(2031).fireResistant();

        //INGREDIENT FOODS
        public static final Item.Properties SPIDER_MEAT = new Item.Properties().food(FoodP.SPIDER_MEAT);
        public static final Item.Properties COOKED_SPIDER_MEAT = new Item.Properties().food(FoodP.COOKED_SPIDER_MEAT);
        public static final Item.Properties ROTTEN_TRIPE = new Item.Properties().food(FoodP.ROTTEN_TRIPE);
        public static final Item.Properties CREEPERILLA = new Item.Properties().food(FoodP.CREEPERILLA);
        public static final Item.Properties SLIME_NOODLES = new Item.Properties().food(FoodP.SLIME_NOODLES);
        public static final Item.Properties SILVERFISH_ABDOMEN = new Item.Properties().food(FoodP.SILVERFISH_ABDOMEN);
        public static final Item.Properties SNIFFER_SHANK = new Item.Properties().food(FoodP.SNIFFER_SHANK);
        public static final Item.Properties COOKED_SNIFFER_SHANK = new Item.Properties().food(FoodP.COOKED_SNIFFER_SHANK);
        public static final Item.Properties GHAST_TENTACLE = new Item.Properties().food(FoodP.GHAST_TENTACLE);
        public static final Item.Properties BLEET = new Item.Properties().food(FoodP.BLEET);
        public static final Item.Properties ENDELVE = new Item.Properties().food(FoodP.ENDELVE);
        public static final Item.Properties MANALLIUM = new Item.Properties().food(FoodP.MANALLIUM);

        //NON-TIERED FOODS
        public static final Item.Properties AMETHYST_ROCK_CANDY = new Item.Properties().food(FoodP.AMETHYST_ROCK_CANDY).craftRemainder(Items.STICK).stacksTo(16);

        //TIER I FOODS

        //TIER II FOODS
        public static final Item.Properties CANDIED_SILVERFISH_SUCKER = new Item.Properties().food(FoodP.CANDIED_SILVERFISH_SUCKER).rarity(DDRarities.MONSTER).craftRemainder(Items.STICK).stacksTo(16);
        public static final Item.Properties CANDIED_VEX_SUCKER = new Item.Properties().food(FoodP.CANDIED_VEX_SUCKER).rarity(DDRarities.MONSTER).craftRemainder(Items.STICK).stacksTo(16);

        //TIER III FOODS
    }

    public static class FoodP {
        //MISC
        public static final FoodProperties LOGO = new FoodProperties.Builder().nutrition(-4).saturationMod(0.0F).alwaysEat().fast().build();

        //INGREDIENT FOODS
        public static final FoodProperties ROTTEN_TRIPE = new FoodProperties.Builder()
                .nutrition(2).saturationMod(0.1F).meat().fast()
                .effect(new MobEffectInstance(MobEffects.HUNGER, 200, 0), 0.2F)
                .build();

        public static final FoodProperties SPIDER_MEAT = new FoodProperties.Builder()
                .nutrition(3).saturationMod(0.2F).meat()
                .effect(new MobEffectInstance(MobEffects.POISON, 240, 0), 0.2F)
                .build();

        public static final FoodProperties COOKED_SPIDER_MEAT = new FoodProperties.Builder()
                .nutrition(7).saturationMod(0.6F).meat()
                .build();

        public static final FoodProperties CREEPERILLA = new FoodProperties.Builder()
                .nutrition(3).saturationMod(0.2F)
                .build();

        public static final FoodProperties SLIME_NOODLES = new FoodProperties.Builder()
                .nutrition(2).saturationMod(0.2F)
                .build();

        public static final FoodProperties SILVERFISH_ABDOMEN = new FoodProperties.Builder()
                .nutrition(3).saturationMod(0.4F).fast().meat()
                .build();

        public static final FoodProperties SNIFFER_SHANK = new FoodProperties.Builder()
                .nutrition(4).saturationMod(0.4F).meat()
                .build();

        public static final FoodProperties COOKED_SNIFFER_SHANK = new FoodProperties.Builder()
                .nutrition(8).saturationMod(0.8F).meat()
                .build();

        public static final FoodProperties GHAST_TENTACLE = new FoodProperties.Builder()
                .nutrition(4).saturationMod(0.4F).meat()
                .build();

        public static final FoodProperties BLEET = new FoodProperties.Builder()
                .nutrition(3).saturationMod(0.4F)
                .build();

        public static final FoodProperties ENDELVE = new FoodProperties.Builder()
                .nutrition(2).saturationMod(0.6F)
                .build();

        public static final FoodProperties MANALLIUM = new FoodProperties.Builder()
                .nutrition(4).saturationMod(0.3F)
                .build();

        //NON-TIERED FOODS
        public static final FoodProperties AMETHYST_ROCK_CANDY = new FoodProperties.Builder()
                .nutrition(6).saturationMod(0.4F)
                .build();

        //TIER I FOODS

        //TIER II FOODS

        //todo fuck my life i hate forge
        public static final FoodProperties CANDIED_SILVERFISH_SUCKER = new FoodProperties.Builder()
                .nutrition(8).saturationMod(0.6F).alwaysEat()
                //.effect(new MobEffectInstance(DDEffects.DECISIVE.get(), 4800, 0), 1.0F)
                //.effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 3600, 2), 1.0F)
                .build();

        public static final FoodProperties CANDIED_VEX_SUCKER = new FoodProperties.Builder()
                .nutrition(8).saturationMod(0.6F).alwaysEat()
                //.effect(new MobEffectInstance(DDEffects.DECISIVE.get(), 4800, 2), 1.0F)
                .build();

        //TIER III FOODS
    }
}
