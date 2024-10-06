package net.yirmiri.dungeons_delight.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class BurrowGutEffect extends StatusEffect {
    public BurrowGutEffect(StatusEffectCategory category, int color) {
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
