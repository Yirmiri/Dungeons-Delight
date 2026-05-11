package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
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
        public static final BlockBehaviour.Properties TERROR_PRETA = BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.ROOTED_DIRT);

        //LIVING/STAINED SCRAP
        public static final BlockBehaviour.Properties STAINED = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(8.0F, 9.0F).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_SCRAP_BARS = BlockBehaviour.Properties.copy(Blocks.IRON_BARS).strength(8.0F, 9.0F).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_GRATE = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(8.0F, 9.0F).noOcclusion().sound(DDSoundTypes.STAINED_SCRAP);
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

        //MISC FOODS
        public static final Item.Properties SPIDER_MEAT = new Item.Properties().food(FoodP.SPIDER_MEAT);
        public static final Item.Properties COOKED_SPIDER_MEAT = new Item.Properties().food(FoodP.COOKED_SPIDER_MEAT);
        public static final Item.Properties ROTTEN_TRIPE = new Item.Properties().food(FoodP.ROTTEN_TRIPE);
        public static final Item.Properties CREEPERILLA = new Item.Properties().food(FoodP.CREEPERILLA);
        public static final Item.Properties SLIME_NOODLES = new Item.Properties().food(FoodP.SLIME_NOODLES);
    }

    public static class FoodP {
        //MISC
        public static final FoodProperties LOGO = new FoodProperties.Builder().nutrition(-4).saturationMod(0.0F).alwaysEat().fast().build();

        //MISC FOODS
        public static final FoodProperties ROTTEN_TRIPE = new FoodProperties.Builder()
                .nutrition(2).saturationMod(0.1F).meat().fast()
                .effect(new MobEffectInstance(MobEffects.HUNGER, 200, 0), 0.2F)
                .build();

        public static final FoodProperties SPIDER_MEAT = new FoodProperties.Builder()
                .nutrition(3).saturationMod(0.3F)
                .effect(new MobEffectInstance(MobEffects.POISON, 240, 0), 0.2F)
                .build();

        public static final FoodProperties COOKED_SPIDER_MEAT = new FoodProperties.Builder()
                .nutrition(8).saturationMod(0.8F)
                .build();

        public static final FoodProperties CREEPERILLA = new FoodProperties.Builder()
                .nutrition(3).saturationMod(0.3F)
                .build();

        public static final FoodProperties SLIME_NOODLES = new FoodProperties.Builder()
                .nutrition(2).saturationMod(0.2F).fast()
                .build();
    }
}
