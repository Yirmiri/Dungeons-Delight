package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
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

//    @Override
//    public boolean applyEffectTick(LivingEntity living, int amplifier) {
//        Holder<MobEffect> thisHolder = Holder.direct(this);
//        if (living.hasEffect(normalVariant)) {
//            DDUtil.applyEffectSwap(living, normalVariant, thisHolder);
//            living.removeEffect(normalVariant);
//        }
//        return true;
//    }

//    @Override
//    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
//        return true;
//    }
}
