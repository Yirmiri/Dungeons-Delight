package net.yirmiri.dungeonsdelight.common.effect.monster;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class VoracityEffect extends MonsterEffect {
    public VoracityEffect(MobEffect normalVariant, MobEffectCategory category, int color) {
        super(normalVariant, category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living instanceof Player player && player.level().canSeeSky(player.blockPosition()) && !player.hasItemInSlot(EquipmentSlot.HEAD) &&
                player.level().isDay() && player.level().dimensionType().hasSkyLight()) {
            player.causeFoodExhaustion(0.2F);
        }
        super.applyEffectTick(living, amplifier);
    }
}
