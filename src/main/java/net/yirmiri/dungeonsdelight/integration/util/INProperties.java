package net.yirmiri.dungeonsdelight.integration.util;

import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class INProperties {
    public static class BlockP {

    }

    public static class ItemP {
        //GENERIC FOODS
        public static final Item.Properties SCULK_DOGAPPLE = new Item.Properties().food(FoodP.SCULK_DOGAPPLE);
        public static final Item.Properties BUG_CHOPS = new Item.Properties().food(FoodP.BUG_CHOPS);
        public static final Item.Properties FRIED_BUG_CHOPS = new Item.Properties().food(FoodP.FRIED_BUG_CHOPS);
        public static final Item.Properties TORCHBERRY_RAISINS = new Item.Properties().food(FoodP.TORCHBERRY_RAISINS);
        public static final Item.Properties WILDERNESS_LUNCHEON = new Item.Properties().food(FoodP.WILDERNESS_LUNCHEON);
        public static final Item.Properties MAZE_ROLL = new Item.Properties().food(FoodP.MAZE_ROLL);
        public static final Item.Properties MEEF_WELLINGTON = new Item.Properties().food(FoodP.MEEF_WELLINGTON);

        //SPECIAL FOODS
        public static final Item.Properties SWEETBREAD = new Item.Properties().food(FoodP.SWEETBREAD).rarity(DDProperties.MONSTER);

        //MEALS
        public static final Item.Properties TOWER_BOREITO = new Item.Properties().food(FoodP.TOWER_BOREITO).stacksTo(16).rarity(DDProperties.MONSTER);
        public static final Item.Properties AURORA_ICE_CREAM = new Item.Properties().food(FoodP.AURORA_ICE_CREAM).stacksTo(16).craftRemainder(Items.BOWL);
        public static final Item.Properties BLAZING_BLOOD_SAUSAGE = new Item.Properties().food(FoodP.BLAZING_BLOOD_SAUSAGE).stacksTo(16).rarity(DDProperties.MONSTER);
        public static final Item.Properties ARCANE_CHILI = new Item.Properties().food(FoodP.ARCANE_CHILI).durability(16).craftRemainder(Items.BUCKET).rarity(DDProperties.MONSTER);
        public static final Item.Properties HYDRA_FRICASSEE = new Item.Properties().food(FoodP.HYDRA_FRICASSEE).craftRemainder(Items.BOWL).stacksTo(16).rarity(DDProperties.MONSTER);
        public static final Item.Properties SCALY_FIDDLEHEAD_RISOTTO = new Item.Properties().food(FoodP.SCALY_FIDDLEHEAD_RISOTTO).craftRemainder(Items.BOWL).stacksTo(16).rarity(DDProperties.MONSTER);

        //DRINKS
        public static final Item.Properties LIVEROOT_BEER = new Item.Properties().food(FoodP.LIVEROOT_BEER).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE);
        public static final Item.Properties TROLLBER_CHUTNEY = new Item.Properties().food(FoodP.TROLLBER_CHUTNEY).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE);
    }

    public static class FoodP {
        //GENERIC FOODS
        public static final FoodProperties SCULK_DOGAPPLE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).alwaysEdible().build();
        public static final FoodProperties BUG_CHOPS = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build();
        public static final FoodProperties FRIED_BUG_CHOPS = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build();
        public static final FoodProperties TORCHBERRY_RAISINS = new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).fast().build();
        public static final FoodProperties WILDERNESS_LUNCHEON = new FoodProperties.Builder().nutrition(7).saturationModifier(0.6F).build();
        public static final FoodProperties MAZE_ROLL = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).fast().build();
        public static final FoodProperties MEEF_WELLINGTON = new FoodProperties.Builder().nutrition(10).saturationModifier(0.7F).build();

        //SPECIAL FOODS
        public static final FoodProperties TOWER_BOREITO = new FoodProperties.Builder().nutrition(12).saturationModifier(1.2F)
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT, 6000, 1), 1.0F).build();

        public static final FoodProperties AURORA_ICE_CREAM = new FoodProperties.Builder().nutrition(9).saturationModifier(0.5F)
                .effect(new MobEffectInstance(ModEffects.COMFORT, 2400, 0), 1.0F)
                .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 2400, 0), 1.0F)
                .effect(new MobEffectInstance(RLMobEffects.PERCEPTION, 600, 0), 1.0F).build();

        public static final FoodProperties BLAZING_BLOOD_SAUSAGE = new FoodProperties.Builder().nutrition(14).saturationModifier(0.9F)
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT, 4800, 0), 1.0F)
                .effect(new MobEffectInstance(RLMobEffects.PYROMANIAC, 2400, 0), 1.0F).build();

        public static final FoodProperties ARCANE_CHILI = new FoodProperties.Builder().nutrition(7).saturationModifier(0.7F)
                .effect(new MobEffectInstance(DDEffects.TENACITY, 12000, 0), 1.0F)
                .effect(new MobEffectInstance(RLMobEffects.PYROMANIAC, 2400, 0), 1.0F).build();

        public static final FoodProperties HYDRA_FRICASSEE = new FoodProperties.Builder().nutrition(10).saturationModifier(1.0F)
                .effect(new MobEffectInstance(DDEffects.VORACITY, 7200, 1), 1.0F)
                .effect(new MobEffectInstance(RLMobEffects.PYROMANIAC, 2400, 0), 1.0F).build();

        public static final FoodProperties SWEETBREAD = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).fast()
                .effect(new MobEffectInstance(DDEffects.BURROW_GUT, 1200, 0), 1.0F).build();

        public static final FoodProperties SCALY_FIDDLEHEAD_RISOTTO = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F)
                .effect(new MobEffectInstance(DDEffects.VORACITY, 9600, 1), 1.0F).build();

        //DRINKS
        public static final FoodProperties LIVEROOT_BEER = new FoodProperties.Builder().nutrition(2).saturationModifier(0.4F).alwaysEdible()
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 1.0F).build();

        public static final FoodProperties TROLLBER_CHUTNEY = new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).alwaysEdible()
                .effect(new MobEffectInstance(RLMobEffects.PERCEPTION, 300, 0), 1.0F).build();
    }
}
