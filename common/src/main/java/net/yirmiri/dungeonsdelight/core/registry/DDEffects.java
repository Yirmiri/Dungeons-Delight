package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.effect.FoulScentEffect;
import net.yirmiri.dungeonsdelight.common.effect.TenacityEffect;
import net.yirmiri.dungeonsdelight.common.effect.monster.ExudationEffect;
import net.yirmiri.dungeonsdelight.common.effect.monster.BurrowGutEffect;
import net.yirmiri.dungeonsdelight.common.effect.monster.MonsterEffect;
import net.yirmiri.dungeonsdelight.common.effect.SerratedEffect;
import net.yirmiri.dungeonsdelight.common.effect.RavenousRushEffect;
import net.yirmiri.dungeonsdelight.common.effect.monster.VoracityEffect;

import java.util.function.Supplier;

public class DDEffects {
    //BENEFICIAL
    public static final Supplier<MobEffect> RAVENOUS_RUSH = register("ravenous_rush", () -> new RavenousRushEffect(MobEffectCategory.BENEFICIAL, 0x85304d));
    public static final Supplier<MobEffect> TENACITY = register("tenacity", () -> new TenacityEffect(MobEffectCategory.BENEFICIAL, 0xbd3d4b));

    //HARMFUL
    public static final Supplier<MobEffect> SERRATED = register("serrated", () -> new SerratedEffect(MobEffectCategory.HARMFUL, 0xe9000d));
    public static final Supplier<MobEffect> FOUL_SCENT = register("foul_scent", () -> new FoulScentEffect(MobEffectCategory.HARMFUL, 0x872452));

    //MONSTER
    public static final Supplier<MobEffect> BURROW_GUT = register("burrow_gut", () -> new BurrowGutEffect(
            MobEffects.DIG_SPEED, MobEffectCategory.BENEFICIAL, 0xedb221)); //todo wip

    public static final Supplier<MobEffect> DEBRIDEMENT = register("debridement", () -> new MonsterEffect(
            MobEffects.REGENERATION, MobEffectCategory.BENEFICIAL, 0xab2c6e)); //todo wip

    public static final Supplier<MobEffect> EXUDATION = register("exudation", () -> new ExudationEffect(
            MobEffects.ABSORPTION, MobEffectCategory.BENEFICIAL, 0xf38f26));

    public static final Supplier<MobEffect> POUNCING = register("pouncing", () -> new MonsterEffect(
            MobEffects.MOVEMENT_SPEED, MobEffectCategory.BENEFICIAL, 0x0b625e)); //todo wip

    public static final Supplier<MobEffect> VORACITY = register("voracity", () -> new VoracityEffect(
            TENACITY.get(), MobEffectCategory.BENEFICIAL, 0xbb29b7)); //todo wip

    public static Supplier<MobEffect> register(String id, Supplier<MobEffect> supplier) {
        return Services.REGISTRY.registerEffect(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
