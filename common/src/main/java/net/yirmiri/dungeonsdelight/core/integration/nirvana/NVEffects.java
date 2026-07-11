package net.yirmiri.dungeonsdelight.core.integration.nirvana;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.yirmiri.dungeonsdelight.common.effect.monster.MonsterEffect;
import net.yirmiri.dungeonsdelight.core.integration.DDIntegration;

import java.util.function.Supplier;

public class NVEffects {
    public static final MobEffect NV_PEACE = Services.PLATFORM.isModLoaded(DDIntegration.NV_ID)
            ? BuiltInRegistries.MOB_EFFECT.get(RunicLib.customid(DDIntegration.NV_ID, "peace")) : MobEffects.HEALTH_BOOST;

    //MONSTER
    public static final Supplier<MobEffect> GREENED_OUT = register("greened_out", () -> new MonsterEffect(
            NV_PEACE, MobEffectCategory.BENEFICIAL, 0x9dc62c)); //todo wip

    public static Supplier<MobEffect> register(String id, Supplier<MobEffect> supplier) {
        return Services.REGISTRY.registerEffect(DDIntegration.NV_ID, id, supplier);
    }

    public static void load() {
    }
}
