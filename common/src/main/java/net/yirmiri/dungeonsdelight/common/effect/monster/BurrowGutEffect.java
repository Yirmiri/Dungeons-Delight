package net.yirmiri.dungeonsdelight.common.effect.monster;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class BurrowGutEffect extends MonsterEffect {
    public BurrowGutEffect(MobEffect normalVariant, MobEffectCategory category, int color) {
        super(normalVariant, category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living instanceof Player player) {
            player.causeFoodExhaustion(0.05F * (float) (amplifier + 1));
        }
        super.applyEffectTick(living, amplifier);
    }
}
