package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.effect.TenacityEffect;
import net.yirmiri.dungeonsdelight.common.effect.monster.ExudationEffect;
import net.yirmiri.dungeonsdelight.common.effect.monster.MonsterEffect;
import net.yirmiri.dungeonsdelight.common.effect.SerratedEffect;
import net.yirmiri.dungeonsdelight.common.effect.monster.RavenousRushEffect;

import java.util.function.Supplier;

public class DDEffects {
    //MONSTER
    public static final Supplier<MobEffect> BURROW_GUT = register("burrow_gut", () -> new MonsterEffect(
            MobEffects.DIG_SPEED, MobEffectCategory.BENEFICIAL, 0xedb221));

    public static final Supplier<MobEffect> EXUDATION = register("exudation", () -> new ExudationEffect(
            MobEffects.ABSORPTION, MobEffectCategory.BENEFICIAL, 0xf38f26));

    //BENEFICIAL
    public static final Supplier<MobEffect> RAVENOUS_RUSH = register("ravenous_rush", () -> new RavenousRushEffect(MobEffectCategory.BENEFICIAL, 0x85304d));
    public static final Supplier<MobEffect> TENACITY = register("tenacity", () -> new TenacityEffect(MobEffectCategory.BENEFICIAL, 0xfb6666));

    //HARMFUL
    public static final Supplier<MobEffect> SERRATED = register("serrated", () -> new SerratedEffect(MobEffectCategory.HARMFUL, 0xe9000d));

    public static Supplier<MobEffect> register(String id, Supplier<MobEffect> supplier) {
        return Services.REGISTRY.registerEffect(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
