package net.yirmiri.dungeonsdelight.common.effect;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ExudationEffect extends MonsterEffect {
    public ExudationEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color) {
        super(normalVariant, category, color);
    }

    @Override
    public void onEffectStarted(LivingEntity living, int amplifier) {
        super.onEffectStarted(living, amplifier);
        living.setAbsorptionAmount(Math.max(living.getAbsorptionAmount(), (float)(4 * (1 + amplifier))));
    }
}
