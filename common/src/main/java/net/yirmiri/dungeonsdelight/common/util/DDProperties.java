package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;

public class DDProperties {

    public static class BlockP {
        //MISC
        public static final BlockBehaviour.Properties GENERIC = BlockBehaviour.Properties.copy(Blocks.STONE);
        public static final BlockBehaviour.Properties MORBID_MUSH = BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.ROOTED_DIRT);

        //WORMWOOD
        // 1.21 -> set copy() to ofFullCopy()
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
    }

    public static class FoodP {
        //MISC
        public static final FoodProperties LOGO = new FoodProperties.Builder().nutrition(-4).saturationMod(0.0F).alwaysEat().fast().build();

        //MISC FOODS
        public static final FoodProperties SPIDER_MEAT = new FoodProperties.Builder()
                .nutrition(3).saturationMod(0.3F)
                .effect(new MobEffectInstance(MobEffects.POISON, 240, 0), 0.2F)
                .build();

        public static final FoodProperties COOKED_SPIDER_MEAT = new FoodProperties.Builder()
                .nutrition(8).saturationMod(0.8F)
                .build();
    }
}
