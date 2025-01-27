package net.yirmiri.dungeonsdelight.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;

public class TenacityEffect extends MobEffect {
    int applyInterval = 20;

    public TenacityEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void addAttributeModifiers(LivingEntity living, AttributeMap map, int amplifier) {
        if (!living.level().isClientSide && living instanceof Player player) {
            applyInterval = getInterval(player);
        }
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (!living.level().isClientSide && living instanceof Player player) {
            player.heal(1.0F);
            player.getFoodData().tick(player);
            applyInterval = getInterval(player);
        }
    }

    public static int getInterval(Player player) {
        if (player.getFoodData().getFoodLevel() != 0) {
            return (player.getFoodData().getFoodLevel() * 8);
        } else {
            return 10;
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % applyInterval == 0;
    }
}
