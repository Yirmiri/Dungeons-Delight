package net.yirmiri.dungeonsdelight.common.effect;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

public class MonsterHungerDrainEffect extends MonsterEffect {
    private final Holder<MobEffect> normalVariant;

    public MonsterHungerDrainEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color) {
        super(normalVariant, category, color);
        this.normalVariant = normalVariant;
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        if (living instanceof Player player) {
            player.causeFoodExhaustion(0.075F * (amplifier + 1));
        }
        Holder<MobEffect> thisHolder = Holder.direct(this);
        if (living.hasEffect(normalVariant)) {
            DDUtil.applyEffectSwap(living, normalVariant, thisHolder);
            living.removeEffect(normalVariant);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
