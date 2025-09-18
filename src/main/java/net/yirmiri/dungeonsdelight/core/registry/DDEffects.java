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

    //HARMFUL
    public static final Holder<MobEffect> SERRATED = MOB_EFFECTS.register("serrated", () -> new SerratedEffect(MobEffectCategory.HARMFUL, 0xe9000d));
    public static final Holder<MobEffect> PUTRID_SCENT = MOB_EFFECTS.register("putrid_scent", () -> new PutridScentEffect(MobEffectCategory.HARMFUL, 0xa70a39));

    //MONSTER
    public static final Holder<MobEffect> RAVENOUS_RUSH = MOB_EFFECTS.register("ravenous_rush", () -> new PureMonsterEffect(MobEffectCategory.BENEFICIAL, 0xa70a39)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, RunicLib.customid(DungeonsDelight.MOD_ID, "effect.ravenous_rush"), 0.30F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, RunicLib.customid(DungeonsDelight.MOD_ID, "effect.ravenous_rush"), 0.10F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final Holder<MobEffect> POUNCING = MOB_EFFECTS.register("pouncing", () -> new MonsterEffect(MobEffects.JUMP, MobEffectCategory.NEUTRAL, 0x336151)
            .addAttributeModifier(Attributes.SNEAKING_SPEED, RunicLib.customid(DungeonsDelight.MOD_ID, "effect.pouncing"), 0.15F, AttributeModifier.Operation.ADD_VALUE)
    );


    public static final Holder<MobEffect> EXUDATION = MOB_EFFECTS.register("exudation", () -> new ExudationEffect(MobEffects.ABSORPTION, MobEffectCategory.NEUTRAL, 0xbc00fe)
            .addAttributeModifier(Attributes.MAX_ABSORPTION, RunicLib.customid(DungeonsDelight.MOD_ID, "effect.exudation"), 4.0, AttributeModifier.Operation.ADD_VALUE)
    );

    public static final Holder<MobEffect> SWIFT_STEP = MOB_EFFECTS.register("swift_step", () -> new MonsterEffect(MobEffects.MOVEMENT_SPEED, MobEffectCategory.NEUTRAL, 0x4d1978));

    public static final Holder<MobEffect> DECISIVE = MOB_EFFECTS.register("decisive", () -> new MonsterEffect(MobEffects.DAMAGE_BOOST, MobEffectCategory.NEUTRAL, 0x4d1978));
    public static final Holder<MobEffect> VORACITY = MOB_EFFECTS.register("voracity", () -> new MonsterHungerDrainEffect(ModEffects.NOURISHMENT, MobEffectCategory.NEUTRAL, 0xbf46ca));
    public static final Holder<MobEffect> TENACITY = MOB_EFFECTS.register("tenacity", () -> new TenacityEffect(ModEffects.COMFORT, MobEffectCategory.NEUTRAL, 0xc5508a));
    public static final Holder<MobEffect> BURROW_GUT = MOB_EFFECTS.register("burrow_gut", () -> new MonsterHungerDrainEffect(MobEffects.DIG_SPEED, MobEffectCategory.NEUTRAL, 0xdba214));
}
