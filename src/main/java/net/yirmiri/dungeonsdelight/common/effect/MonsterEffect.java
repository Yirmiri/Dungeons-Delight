package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

public class MonsterEffect extends PublicMobEffect {
    private final Holder<MobEffect> normalVariant;

    public MonsterEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color) {
        super(category, color);
        this.normalVariant = normalVariant;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(0xc875c2));
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        for (MobEffectInstance effectInstance : living.getActiveEffects()) {
            if (effectInstance.getEffect().equals(normalVariant)) {
                applyEffectSwap(living, normalVariant, living.registryAccess().registryOrThrow(Registries.MOB_EFFECT).getResourceKey(this)
                        .flatMap(key -> living.registryAccess().registryOrThrow(Registries.MOB_EFFECT).getHolder(key)).orElseThrow());
            }
        }
        return true;
    }

    public static void applyEffectSwap(LivingEntity living, Holder<MobEffect> oldEffect, Holder<MobEffect> newEffect) {
        if (living.hasEffect(oldEffect)) {
            MobEffectInstance old = living.getEffect(oldEffect);
            int duration = old.getDuration();

            living.removeEffect(oldEffect);
            living.addEffect(new MobEffectInstance(newEffect, duration, 0));
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
