package net.yirmiri.dungeonsdelight.common.effect;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class MonsterHungerDrainEffect extends MonsterEffect {
    public MonsterHungerDrainEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color) {
        super(normalVariant, category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        if (living instanceof Player player) {
            player.causeFoodExhaustion(0.075F * (amplifier + 1));
        }
        super.applyEffectTick(living, amplifier);
        return false;
    }
}
