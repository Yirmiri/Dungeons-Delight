package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.effect.MonsterEffect;
import net.yirmiri.dungeonsdelight.common.effect.SerratedEffect;

import java.util.function.Supplier;

public class DDEffects {
    //MONSTER
    public static final Supplier<MobEffect> CLAMBERING = register("clambering", () -> new MonsterEffect(MobEffects.JUMP, MobEffectCategory.BENEFICIAL, 0x336151));

    //HARMFUL
    public static final Supplier<MobEffect> SERRATED = register("serrated", () -> new SerratedEffect(MobEffectCategory.HARMFUL, 0xe9000d));

    public static Supplier<MobEffect> register(String id, Supplier<MobEffect> supplier) {
        return Services.REGISTRY.registerEffect(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
