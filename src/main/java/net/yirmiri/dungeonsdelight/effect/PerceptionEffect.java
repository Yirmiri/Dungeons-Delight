package net.yirmiri.dungeonsdelight.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

public class PerceptionEffect extends NoSpecialEffect {
    public PerceptionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        Level level = living.level();
        List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, living.getBoundingBox().inflate(8.0D + amplifier), Entity::isAlive);
        for (LivingEntity livingEntity : list) {
            if (livingEntity.isAlive() && living != livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 30, 0));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
