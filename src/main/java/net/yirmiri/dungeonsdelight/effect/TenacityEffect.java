package net.yirmiri.dungeonsdelight.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class TenacityEffect extends StatusEffect {
    int applyInterval = 20;

    public TenacityEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        if (!entity.getEntityWorld().isClient && entity instanceof PlayerEntity player) {
            applyInterval = getInterval(player);
        }
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getEntityWorld().isClient && entity instanceof PlayerEntity player) {
            player.heal(1.0F);
            player.getHungerManager().update(player);
            applyInterval = getInterval(player);
        }

        return true;
    }

    public static int getInterval(PlayerEntity player) {
        if (player.getHungerManager().getFoodLevel() != 0) {
            return (player.getHungerManager().getFoodLevel() * 8);
        } else {
            return 10;
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % applyInterval == 0;
    }
}
