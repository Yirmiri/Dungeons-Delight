package net.yirmiri.dungeonsdelight.common.effect.monster;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

public class MonsterEffect extends PublicMobEffect {
    private final MobEffect normalVariant;

    public MonsterEffect(MobEffect normalVariant, MobEffectCategory category, int color) {
        super(category, color);
        this.normalVariant = normalVariant;
    }

    public MobEffect getNormalVariant() {
        return normalVariant;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(DDUtil.MONSTER_COLOR));
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        for (MobEffectInstance effectInstance : living.getActiveEffects()) {
            if (effectInstance.getEffect().equals(getNormalVariant())) {
                DDUtil.applyMonsterEffectSwap(living, getNormalVariant(), this,
                        !DDUtil.MONSTER_EFFECTS_THAT_PRESERVE_AMPLIFIER.contains(effectInstance.getEffect()));
                living.removeEffect(effectInstance.getEffect());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}