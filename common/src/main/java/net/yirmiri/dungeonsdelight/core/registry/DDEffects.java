package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.effect.monster.ExudationEffect;
import net.yirmiri.dungeonsdelight.common.effect.monster.MonsterEffect;
import net.yirmiri.dungeonsdelight.common.effect.SerratedEffect;

import java.util.function.Supplier;

public class DDEffects {
    //MONSTER
    public static final Supplier<MobEffect> DECISIVE = register("decisive", () -> new MonsterEffect(MobEffects.DAMAGE_BOOST, MobEffectCategory.BENEFICIAL, 0x33124c));
    public static final Supplier<MobEffect> EXUDATION = register("exudation", () -> new ExudationEffect(MobEffects.ABSORPTION, MobEffectCategory.BENEFICIAL, 0xf38f26));

    //HARMFUL
    public static final Supplier<MobEffect> SERRATED = register("serrated", () -> new SerratedEffect(MobEffectCategory.HARMFUL, 0xe9000d));

    public static Supplier<MobEffect> register(String id, Supplier<MobEffect> supplier) {
        return Services.REGISTRY.registerEffect(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
