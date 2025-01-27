package net.yirmiri.dungeonsdelight.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HungerDrainingEffect extends MobEffect {
    public HungerDrainingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living instanceof Player player) {
            player.causeFoodExhaustion(0.005F * (amplifier + 1));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
