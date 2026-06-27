package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDPotions {
    //HOLLOWED
    public static final Supplier<Potion> HOLLOWED = registerPotion("dungeonsdelight.hollowed",
            () -> new Potion(new MobEffectInstance(DDEffects.HOLLOWED.get(), 36000, 0)));

    public static final Supplier<Potion> LONG_HOLLOWED = registerPotion("dungeonsdelight.long_hollowed",
            () -> new Potion(new MobEffectInstance(DDEffects.HOLLOWED.get(), 72000, 0)));

    private static Supplier<Potion> registerPotion(String id, Supplier<Potion> type) {
        return Services.REGISTRY.registerPotion(DungeonsDelight.MOD_ID, id, type);
    }

    public static void load() {
    }
}