package net.yirmiri.dungeonsdelight.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class MonsterEffect extends NoSpecialEffect {
    private final MobEffect normalVariant;

    public MonsterEffect(MobEffect normalVariant, MobEffectCategory category, int color) {
        super(category, color);
        this.normalVariant = normalVariant;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(0xc875c2));
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) { //TODO: have it grant the duration of the normal effect variant
        for (MobEffectInstance effectInstance : living.getActiveEffects()) {
            if (effectInstance.getEffect().equals(normalVariant)) {
                living.removeEffect(effectInstance.getEffect());
                living.level().playSound(null, living.getX(), living.getY(), living.getZ(),
                        SoundEvents.ZOMBIE_VILLAGER_CONVERTED, SoundSource.NEUTRAL, 1.0F, 0.5F);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
