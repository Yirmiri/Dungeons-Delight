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

public class DDProperties {
    public static class BlockP {
        //MISC
        public static final BlockBehaviour.Properties MONSTER_POT = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(0.5F).explosionResistance(6.0F).sound(SoundType.LANTERN).lightLevel(s -> 4).noOcclusion();
        public static final BlockBehaviour.Properties DUNGEON_STOVE = BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(litBlockEmission -> 10).noOcclusion();
    }

    public static class ItemP {
        //MISC
        public static final Item.Properties GENERIC = new Item.Properties();
        public static final Item.Properties LOGO = new Item.Properties().food(FoodP.LOGO).rarity(Rarity.EPIC);

        //GENERIC FOODS
        public static final Item.Properties SLIME_SLAB = new Item.Properties().food(FoodP.SLIME_SLAB);
        public static final Item.Properties SLIME_NOODLES = new Item.Properties().food(FoodP.SLIME_NOODLES);
        public static final Item.Properties SILVERFISH_THORAX = new Item.Properties().food(FoodP.SILVERFISH_THORAX);

        //MEALS
        public static final Item.Properties GHOULASH = new Item.Properties().food(FoodP.GHOULASH).craftRemainder(Items.BOWL).stacksTo(16);

        //SPECIAL FOODS
        public static final Item.Properties AMETHYST_ROCK_CANDY = new Item.Properties().food(FoodP.AMETHYST_ROCK_CANDY).craftRemainder(Items.STICK);
        public static final Item.Properties CANDIED_VEX_SUCKER = new Item.Properties().food(FoodP.CANDIED_VEX_SUCKER).craftRemainder(Items.STICK);
        public static final Item.Properties CANDIED_SILVERFISH_SUCKER = new Item.Properties().food(FoodP.CANDIED_SILVERFISH_SUCKER).craftRemainder(Items.STICK);
    }

    public static class FoodP {
        //MISC
        public static final FoodProperties LOGO = new FoodProperties.Builder().nutrition(-3).saturationMod(0.0F).alwaysEat().fast().build();

        //GENERIC FOODS
        public static final FoodProperties SLIME_SLAB = new FoodProperties.Builder().nutrition(2).saturationMod(1.2F).build();
        public static final FoodProperties SLIME_NOODLES = new FoodProperties.Builder().nutrition(1).saturationMod(0.6F).build();
        public static final FoodProperties SILVERFISH_THORAX = new FoodProperties.Builder().nutrition(2).saturationMod(1.2F).build();

        //MEALS
        public static final FoodProperties GHOULASH = new FoodProperties.Builder().nutrition(10).saturationMod(12.2F).alwaysEat()
                .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 6000, 0), 1.0F)
                .effect(new MobEffectInstance(DDEffects.TENACITY.get(), 6000, 0), 1.0F).build();

        //SPECIAL FOODS
        public static final FoodProperties AMETHYST_ROCK_CANDY = new FoodProperties.Builder().nutrition(3).saturationMod(2.2F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0F).build();

        public static final FoodProperties CANDIED_VEX_SUCKER = new FoodProperties.Builder().nutrition(4).saturationMod(7.7F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0F).build(); //TODO: ADD DECISIVE

        public static final FoodProperties CANDIED_SILVERFISH_SUCKER = new FoodProperties.Builder().nutrition(5).saturationMod(4.3F).alwaysEat()
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 3600, 0), 1.0F)
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0F).build();
    }
}
