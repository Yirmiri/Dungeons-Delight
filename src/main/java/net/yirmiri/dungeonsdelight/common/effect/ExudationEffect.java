package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ExudationEffect extends PublicMobEffect {
    private final Holder<MobEffect> normalVariant;

    public ExudationEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color) {
        super(category, color);
        this.normalVariant = normalVariant;
    }

//    @Override
//    public boolean applyEffectTick(LivingEntity living, int amplifier) {
//        return living.getAbsorptionAmount() > 0.0F || living.level().isClientSide;
//    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEffectStarted(LivingEntity living, int amplifier) {
        //super.onEffectStarted(living, amplifier);
        living.setAbsorptionAmount(Math.max(living.getAbsorptionAmount(), (float)(4 * (1 + amplifier))));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(0xc875c2));
    }
}
