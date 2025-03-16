package net.yirmiri.dungeonsdelight.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.dungeonsdelight.util.DDUtil;
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
    public void applyEffectTick(LivingEntity living, int amplifier) {
        for (MobEffectInstance effectInstance : living.getActiveEffects()) {
            if (effectInstance.getEffect().equals(normalVariant)) {
                DDUtil.applyEffectSwap(living, normalVariant, this);
                living.removeEffect(effectInstance.getEffect());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
