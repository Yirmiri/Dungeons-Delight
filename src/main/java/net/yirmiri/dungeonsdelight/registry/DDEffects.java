package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.effect.HungerDrainingEffect;
import net.yirmiri.dungeonsdelight.effect.TenacityEffect;

public class DDEffects {
    //NEUTRAL
    public static final RegistryEntry<StatusEffect> BURROW_GUT = register("burrow_gut", new HungerDrainingEffect(StatusEffectCategory.NEUTRAL, 0xdba214).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, Identifier.of(DungeonsDelight.MOD_ID, "effect.burrow_gut"), 0.05f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistryEntry<StatusEffect> VORACITY = register("voracity", new HungerDrainingEffect(StatusEffectCategory.NEUTRAL, 0xbf46ca));
    public static final RegistryEntry<StatusEffect> TENACITY = register("tenacity", new TenacityEffect(StatusEffectCategory.NEUTRAL, 0xc5508a));

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(DungeonsDelight.MOD_ID, id), statusEffect);
    }

    public static void loadEffects() {
    }
}
