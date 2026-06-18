package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.UseAnim;

public class CreeperFoodItem extends DDFoodItem {
    public CreeperFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, int useTicks, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, useTicks, properties);
    }

    public CreeperFoodItem(boolean hasEffectTooltip, Properties properties) {
        this(hasEffectTooltip, SoundEvents.GENERIC_EAT, UseAnim.EAT, 32, properties);
    }

    public CreeperFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, Properties properties) {
        this(hasEffectTooltip, consumeSound, UseAnim.EAT, 32, properties);
    }

    public CreeperFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, Properties properties) {
        this(hasEffectTooltip, consumeSound, useAnimation, 32, properties);
    }

    public CreeperFoodItem(boolean hasEffectTooltip, int useTicks, Properties properties) {
        this(hasEffectTooltip, SoundEvents.GENERIC_EAT, UseAnim.EAT, useTicks, properties);
    }
}
