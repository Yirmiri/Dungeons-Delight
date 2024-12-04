package net.yirmiri.dungeonsdelight.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class HungerDrainingEffect extends StatusEffect {
    public HungerDrainingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public boolean applyUpdateEffect(LivingEntity living, int amplifier) {
        if (living instanceof PlayerEntity player) {
            player.addExhaustion(0.005F * (amplifier + 1));
        }

        return true;
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
