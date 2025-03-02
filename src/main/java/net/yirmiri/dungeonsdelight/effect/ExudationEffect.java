package net.yirmiri.dungeonsdelight.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class ExudationEffect extends MonsterEffect {
    public ExudationEffect(MobEffect normalVariant, MobEffectCategory category, int color) {
        super(normalVariant, category, color);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity living, AttributeMap map, int amp) {
        living.setAbsorptionAmount(living.getAbsorptionAmount() - (float)(4 * (amp + 1)));
        super.removeAttributeModifiers(living, map, amp);
    }

    @Override
    public void addAttributeModifiers(LivingEntity living, AttributeMap map, int amp) {
        living.setAbsorptionAmount(living.getAbsorptionAmount() + (float)(4 * (amp + 1)));
        super.addAttributeModifiers(living, map, amp);
    }
}
