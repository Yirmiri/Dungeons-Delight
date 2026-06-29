package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.UseAnim;

public class CreeperFoodItem extends DDFoodItem {
    public CreeperFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, int useTicks, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, useTicks, properties);
    }

    public CreeperFoodItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
    }

    public CreeperFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, Properties properties) {
        super(hasEffectTooltip, consumeSound, properties);
    }

    public CreeperFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, properties);
    }

    public CreeperFoodItem(boolean hasEffectTooltip, int useTicks, Properties properties) {
        super(hasEffectTooltip, useTicks, properties);
    }
}
