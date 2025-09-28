package net.yirmiri.dungeonsdelight.common.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class MonsterHungerDrainEffect extends MobEffect {
    private final Holder<MobEffect> normalVariant;

    public MonsterHungerDrainEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, particle);
        this.normalVariant = normalVariant;
    }

    public MonsterHungerDrainEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color) {
        super(category, color);
        this.normalVariant = normalVariant;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(0xc875c2));
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        if (living instanceof Player player) {
            player.causeFoodExhaustion(0.05F * (float)(amplifier + 1));
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
