package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.effect.*;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class DDEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, DungeonsDelight.MOD_ID);

    //BENEFICIAL
    public static final Holder<MobEffect> FERAL_BITE = MOB_EFFECTS.register("feral_bite", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xc19a9a));

    public static final Holder<MobEffect> RAVENOUS_RUSH = MOB_EFFECTS.register("ravenous_rush", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xa70a39)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, RunicLib.customid(DungeonsDelight.MOD_ID, "ravenous_rush.movement_speed"), 0.30F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, RunicLib.customid(DungeonsDelight.MOD_ID, "ravenous_rush.attack_speed"), 0.10F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    //HARMFUL
    public static final Holder<MobEffect> SERRATED = MOB_EFFECTS.register("serrated", () -> new SerratedEffect(MobEffectCategory.HARMFUL, 0xe9000d));

    //MONSTER
    public static final Holder<MobEffect> EXUDATION = MOB_EFFECTS.register("exudation", () -> new ExudationEffect(MobEffects.ABSORPTION, MobEffectCategory.BENEFICIAL, 0xbc00fe));
    public static final Holder<MobEffect> DECISIVE = MOB_EFFECTS.register("decisive", () -> new MonsterEffect(MobEffects.DAMAGE_BOOST, MobEffectCategory.BENEFICIAL, 0x4d1978));
    public static final Holder<MobEffect> VORACITY = MOB_EFFECTS.register("voracity", () -> new MonsterHungerDrainEffect(ModEffects.NOURISHMENT, MobEffectCategory.NEUTRAL, 0xbf46ca));
    public static final Holder<MobEffect> TENACITY = MOB_EFFECTS.register("tenacity", () -> new TenacityEffect(ModEffects.COMFORT, MobEffectCategory.NEUTRAL, 0xc5508a));
    public static final Holder<MobEffect> BURROW_GUT = MOB_EFFECTS.register("burrow_gut", () -> new MonsterHungerDrainEffect(MobEffects.DIG_SPEED, MobEffectCategory.NEUTRAL, 0xdba214));

    public static final Holder<MobEffect> POUNCING = MOB_EFFECTS.register("pouncing", () -> new MonsterEffect(MobEffects.JUMP, MobEffectCategory.BENEFICIAL, 0x336151)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, RunicLib.customid(DungeonsDelight.MOD_ID, "pouncing.movement_speed"), 0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );
}
