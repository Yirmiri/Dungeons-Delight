package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.effect.*;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class DDEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DungeonsDelight.MOD_ID);

    //BENEFICIAL
    public static final RegistryObject<MobEffect> FERAL_BITE = MOB_EFFECTS.register("feral_bite", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xc19a9a));

    public static final RegistryObject<MobEffect> RAVENOUS_RUSH = MOB_EFFECTS.register("ravenous_rush", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xa70a39)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, "8cc22e9b-8e4e-41da-8523-a931ac409203", 0.30F, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, "010b97a1-221a-4e49-ab7c-4343c50e6b79", 0.10F, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );

    //HARMFUL
    public static final RegistryObject<MobEffect> SERRATED = MOB_EFFECTS.register("serrated", () -> new SerratedEffect(MobEffectCategory.HARMFUL, 0xe9000d));

    //MONSTER
    public static final RegistryObject<MobEffect> EXUDATION = MOB_EFFECTS.register("exudation", () -> new ExudationEffect(MobEffects.ABSORPTION, MobEffectCategory.BENEFICIAL, 0xbc00fe));
    public static final RegistryObject<MobEffect> DECISIVE = MOB_EFFECTS.register("decisive", () -> new MonsterEffect(MobEffects.DAMAGE_BOOST, MobEffectCategory.BENEFICIAL, 0x4d1978));
    public static final RegistryObject<MobEffect> VORACITY = MOB_EFFECTS.register("voracity", () -> new MonsterHungerDrainEffect(ModEffects.NOURISHMENT.get(), MobEffectCategory.NEUTRAL, 0xbf46ca));
    public static final RegistryObject<MobEffect> TENACITY = MOB_EFFECTS.register("tenacity", () -> new TenacityEffect(ModEffects.COMFORT.get(), MobEffectCategory.NEUTRAL, 0xc5508a));
    public static final RegistryObject<MobEffect> BURROW_GUT = MOB_EFFECTS.register("burrow_gut", () -> new MonsterHungerDrainEffect(MobEffects.DIG_SPEED, MobEffectCategory.NEUTRAL, 0xdba214));

    public static final RegistryObject<MobEffect> POUNCING = MOB_EFFECTS.register("pouncing", () -> new MonsterEffect(MobEffects.JUMP, MobEffectCategory.BENEFICIAL, 0x336151)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, "3d1b3fc4-2786-441a-8b93-082708f2d0ac", 0.05F, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );
}
