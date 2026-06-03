package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class TenacityEffect extends PublicMobEffect {
    public TenacityEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public static int getInterval(Player player, int amplifier) {
        int hunger = player.getFoodData().getFoodLevel();
        float healthPercent = player.getHealth() / player.getMaxHealth();
        int interval = Math.max(1, hunger * DungeonsDelight.CONFIG.getTenacityInterval());

        interval = (int) (interval * healthPercent);
        interval -= amplifier * 2;

        return Math.max(1, interval);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (!living.level().isClientSide && living instanceof Player player) {
            if (player.tickCount % getInterval(player, amplifier) == 0) {
                player.heal(1.0F);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
