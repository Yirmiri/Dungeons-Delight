package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.effect.HungerDrainingEffect;
import net.yirmiri.dungeonsdelight.effect.NoSpecialEffect;
import net.yirmiri.dungeonsdelight.effect.TenacityEffect;

public class DDEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DungeonsDelight.MOD_ID);

    //BENEFICIAL
    public static final RegistryObject<MobEffect> POUNCING = MOB_EFFECTS.register("pouncing", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x336151).addAttributeModifier(Attributes.MOVEMENT_SPEED, "3d1b3fc4-2786-441a-8b93-082708f2d0ac", 0.05f, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> EXUDATION = MOB_EFFECTS.register("exudation", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xbc00fe));
    public static final RegistryObject<MobEffect> DECISIVE = MOB_EFFECTS.register("decisive", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x4d1978));

    //NEUTRAL
    public static final RegistryObject<MobEffect> ROTGUT = MOB_EFFECTS.register("rotgut", () -> new NoSpecialEffect(MobEffectCategory.NEUTRAL, 0x88325f));
    public static final RegistryObject<MobEffect> BURROW_GUT = MOB_EFFECTS.register("burrow_gut", () -> new HungerDrainingEffect(MobEffectCategory.NEUTRAL, 0xdba214).addAttributeModifier(Attributes.ATTACK_SPEED, "23f7c7f8-9933-4aa0-90d2-f52bd873bc7a", 0.05f, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> VORACITY = MOB_EFFECTS.register("voracity", () -> new HungerDrainingEffect(MobEffectCategory.NEUTRAL, 0xbf46ca));
    public static final RegistryObject<MobEffect> TENACITY = MOB_EFFECTS.register("tenacity", () -> new TenacityEffect(MobEffectCategory.NEUTRAL, 0xc5508a));
}
