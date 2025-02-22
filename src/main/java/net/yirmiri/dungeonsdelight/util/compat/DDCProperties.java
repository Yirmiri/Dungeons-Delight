package net.yirmiri.dungeonsdelight.util.compat;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class DDCProperties {
    public static class BlockP {
        //MISC
    }

    public static class ItemP {
        //MISC
        public static final Item.Properties FIERY_KNIFE = new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON);

        //GENERIC FOODS
        public static final Item.Properties TORCHBERRY_RAISINS = new Item.Properties().food(FoodP.TORCHBERRY_RAISINS);

        //SPECIAL FOODS
        public static final Item.Properties LIVINGROOTBEER = new Item.Properties().food(FoodP.LIVINGROOTBEER).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);

        //MEALS
        public static final Item.Properties MEEF_WELLINGTON = new Item.Properties().food(FoodP.MEEF_WELLINGTON).rarity(DDProperties.ItemP.DUNGEON).stacksTo(16);
        public static final Item.Properties BRAISED_GLOWWORM_QUEEN = new Item.Properties().food(FoodP.BRAISED_GLOWWORM_QUEEN).craftRemainder(Items.BOWL).durability(16).setNoRepair().rarity(Rarity.RARE);
    }

    public static class FoodP {
        //MISC

        //GENERIC FOODS

        //SPECIAL FOODS
        public static final FoodProperties TORCHBERRY_RAISINS = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).fast().alwaysEat()
                .effect(new MobEffectInstance(MobEffects.GLOWING, 200, 0), 0.7F).build();

        public static final FoodProperties LIVINGROOTBEER = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).alwaysEat()
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 1), 1.0F)
                .effect(new MobEffectInstance(DDEffects.PERCEPTION.get(), 600, 0), 1.0F).build();

        public static final FoodProperties MEEF_WELLINGTON = new FoodProperties.Builder().nutrition(10).saturationMod(0.7F).alwaysEat()
                .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F)
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1), 1.0F).build();

        //MEALS
        public static final FoodProperties BRAISED_GLOWWORM_QUEEN = new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).alwaysEat()
                .effect(new MobEffectInstance(DDEffects.PERCEPTION.get(), 600, 0), 1.0F).build();
    }
}
