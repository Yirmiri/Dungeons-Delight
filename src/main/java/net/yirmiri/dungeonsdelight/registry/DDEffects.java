package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.effect.BurrowGutEffect;

public class DDEffects {
    public static final RegistryEntry<StatusEffect> BURROW_GUT = register("burrow_gut", new BurrowGutEffect(StatusEffectCategory.NEUTRAL, 0xdba214));

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(DungeonsDelight.MOD_ID, id), statusEffect);
    }

    public static void loadEffects() {
    }
}
