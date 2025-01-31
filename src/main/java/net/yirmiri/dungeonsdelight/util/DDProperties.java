package net.yirmiri.dungeonsdelight.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class DDProperties {
    public static class BlockP {
        //STATIONS
        public static final BlockBehaviour.Properties MONSTER_POT = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(0.5F).explosionResistance(6.0F).sound(SoundType.LANTERN).lightLevel(s -> 4).noOcclusion();
        public static final BlockBehaviour.Properties DUNGEON_STOVE = BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(litBlockEmission -> 10).noOcclusion();

        //WORMWOOD
        public static final BlockBehaviour.Properties WORMROOTS = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).strength(0.5F).mapColor(MapColor.TERRACOTTA_PURPLE).noOcclusion().noCollission();
        public static final BlockBehaviour.Properties WORMWOOD = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).strength(1.0F).explosionResistance(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_DOOR = BlockBehaviour.Properties.copy(Blocks.CRIMSON_DOOR).strength(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_TRAPDOOR = BlockBehaviour.Properties.copy(Blocks.CRIMSON_TRAPDOOR).strength(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_BUTTON = BlockBehaviour.Properties.copy(Blocks.CRIMSON_BUTTON).strength(0.25F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_PRESSURE_PLATE = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PRESSURE_PLATE).strength(0.25F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_CABINET = BlockBehaviour.Properties.copy(ModBlocks.CRIMSON_CABINET.get()).strength(1.0F).explosionResistance(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
    }

    public static class ItemP {
        //RARITY
        public static final Rarity DUNGEON = Rarity.create("dungeon", formatStyle -> formatStyle.withColor(0xc875c2));

        //MISC
        public static final Item.Properties GENERIC_UNCOMMON = new Item.Properties().rarity(Rarity.UNCOMMON);
        public static final Item.Properties GENERIC_DUNGEON = new Item.Properties().rarity(DUNGEON);
        public static final Item.Properties LOGO = new Item.Properties().food(FoodP.LOGO).rarity(DUNGEON);

        //GENERIC FOODS
        public static final Item.Properties SLIME_SLAB = new Item.Properties().food(FoodP.SLIME_SLAB);
        public static final Item.Properties SLIME_NOODLES = new Item.Properties().food(FoodP.SLIME_NOODLES);
        public static final Item.Properties SILVERFISH_THORAX = new Item.Properties().food(FoodP.SILVERFISH_THORAX);
        public static final Item.Properties SPIDER_MEAT = new Item.Properties().food(FoodP.SPIDER_MEAT);
        public static final Item.Properties SMOKED_SPIDER_MEAT = new Item.Properties().food(FoodP.SMOKED_SPIDER_MEAT);
        public static final Item.Properties GHAST_CALAMARI = new Item.Properties().food(FoodP.GHAST_CALAMARI);
        public static final Item.Properties FRIED_GHAST_CALAMARI = new Item.Properties().food(FoodP.FRIED_GHAST_CALAMARI);
        public static final Item.Properties GHAST_TENTACLE = new Item.Properties().food(FoodP.GHAST_TENTACLE);

        //SPECIAL FOODS
        public static final Item.Properties AMETHYST_ROCK_CANDY = new Item.Properties().food(FoodP.AMETHYST_ROCK_CANDY).craftRemainder(Items.STICK);
        public static final Item.Properties CANDIED_VEX_SUCKER = new Item.Properties().food(FoodP.CANDIED_VEX_SUCKER).craftRemainder(Items.STICK);
        public static final Item.Properties CANDIED_SILVERFISH_SUCKER = new Item.Properties().food(FoodP.CANDIED_SILVERFISH_SUCKER).craftRemainder(Items.STICK);
        public static final Item.Properties SPIDER_EXTRACT = new Item.Properties().food(FoodP.SPIDER_EXTRACT).craftRemainder(Items.GLASS_BOTTLE);
        public static final Item.Properties SPIDER_TANGHULU = new Item.Properties().food(FoodP.SPIDER_TANGHULU).rarity(DUNGEON).craftRemainder(Items.BONE);
        public static final Item.Properties SPIDER_EYE_SALMAGUNDI = new Item.Properties().food(FoodP.SPIDER_EYE_SALMAGUNDI).rarity(DUNGEON).craftRemainder(Items.BOWL);

        //MEALS
        public static final Item.Properties GHOULASH = new Item.Properties().food(FoodP.GHOULASH).rarity(DUNGEON).craftRemainder(Items.BOWL).stacksTo(16);
        public static final Item.Properties SILVERFISH_FRIED_RICE = new Item.Properties().food(FoodP.SILVERFISH_FRIED_RICE).rarity(DUNGEON).craftRemainder(Items.BOWL).stacksTo(16);
        public static final Item.Properties MONSTER_BURGER = new Item.Properties().food(FoodP.MONSTER_BURGER).rarity(DUNGEON).stacksTo(1);
    }

    public static class FoodP {
        //MISC
        public static final FoodProperties LOGO = new FoodProperties.Builder().nutrition(-3).saturationMod(0.0F).alwaysEat().fast().build();

        //GENERIC FOODS
        public static final FoodProperties SLIME_SLAB = new FoodProperties.Builder().nutrition(2).saturationMod(1.2F).build();
        public static final FoodProperties SLIME_NOODLES = new FoodProperties.Builder().nutrition(1).saturationMod(0.6F).build();
        public static final FoodProperties SILVERFISH_THORAX = new FoodProperties.Builder().nutrition(2).saturationMod(1.2F).meat().build();
        public static final FoodProperties GHAST_CALAMARI = new FoodProperties.Builder().nutrition(2).saturationMod(0.5F).meat().fast().build();
        public static final FoodProperties FRIED_GHAST_CALAMARI = new FoodProperties.Builder().nutrition(4).saturationMod(4.5F).meat().fast().build();
        public static final FoodProperties GHAST_TENTACLE = new FoodProperties.Builder().nutrition(3).saturationMod(1.2F).meat().build();
        public static final FoodProperties SMOKED_SPIDER_MEAT = new FoodProperties.Builder().nutrition(5).saturationMod(6.7F).meat().build();

        //SPECIAL FOODS
        public static final FoodProperties AMETHYST_ROCK_CANDY = new FoodProperties.Builder().nutrition(3).saturationMod(2.2F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0F).build();

        public static final FoodProperties CANDIED_VEX_SUCKER = new FoodProperties.Builder().nutrition(4).saturationMod(7.7F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0F).build(); //TODO: ADD DECISIVE

        public static final FoodProperties CANDIED_SILVERFISH_SUCKER = new FoodProperties.Builder().nutrition(5).saturationMod(4.3F).alwaysEat()
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 3600, 0), 1.0F)
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0F).build();

        public static final FoodProperties SPIDER_EXTRACT = new FoodProperties.Builder().nutrition(0).saturationMod(0.0F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.POISON, 1200, 0), 1.0F).build();

        public static final FoodProperties SPIDER_MEAT = new FoodProperties.Builder().nutrition(2).saturationMod(3.6F).alwaysEat().meat()
                .effect(new MobEffectInstance(MobEffects.POISON, 300, 0), 0.5F).build();

        public static final FoodProperties SPIDER_TANGHULU = new FoodProperties.Builder().nutrition(5).saturationMod(6.8F).alwaysEat()
                .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 6000, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 4800, 0), 1.0F).build(); //TODO: ADD DECISIVE

        //MEALS
        public static final FoodProperties GHOULASH = new FoodProperties.Builder().nutrition(10).saturationMod(12.2F).alwaysEat()
                .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 6000, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 6000, 0), 1.0F).build();

        public static final FoodProperties SILVERFISH_FRIED_RICE = new FoodProperties.Builder().nutrition(12).saturationMod(7.2F).alwaysEat()
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 3600, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 4800, 0), 1.0F).build();

        public static final FoodProperties SPIDER_EYE_SALMAGUNDI = new FoodProperties.Builder().nutrition(7).saturationMod(8.6F).alwaysEat()
                .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 4800, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 9600, 0), 1.0F).build(); //TODO: ADD DECISIVE

        public static final FoodProperties MONSTER_BURGER = new FoodProperties.Builder().nutrition(20).saturationMod(20.0F).alwaysEat()
                .build(); //TODO: ADD EFFECTS TO THE MONSTER BURGER
    }
}
