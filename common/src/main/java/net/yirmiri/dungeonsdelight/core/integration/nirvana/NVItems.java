package net.yirmiri.dungeonsdelight.core.integration.nirvana;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import net.yirmiri.dungeonsdelight.core.integration.DDIntegration;
import net.yirmiri.dungeonsdelight.core.integration.common.item.foods.NVCreeperillaBluntItem;

import java.util.function.Supplier;

public class NVItems {
    public static final Supplier<Item> CREEPERS_LETTUCE = register("creepers_lettuce", () -> new NVCreeperillaBluntItem(
            true, new Item.Properties()
            .rarity(DDRarities.MONSTER).durability(8)
            .food(new FoodProperties.Builder()
                    .alwaysEat()
                    .effect(new MobEffectInstance(NVEffects.GREENED_OUT.get(), 2400, 0), 1.0F)
                    .build()))
    );

    public static Supplier<Item> register(String id, Supplier<Item> supplier) {
        return Services.REGISTRY.registerItem(DDIntegration.NV_ID, id, supplier);
    }

    public static void load() {
    }
}
