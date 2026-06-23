package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.UseAnim;

public class SpicyFoodItem extends DDFoodItem { //todo, spicy chance and spice + make FrozenFoodItem after
    public SpicyFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, int useTicks, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, useTicks, properties);
    }

    public SpicyFoodItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
    }

    public SpicyFoodItem(boolean hasEffectTooltip, Properties properties, int useTicks) {
        super(hasEffectTooltip, properties, useTicks);
    }

    public SpicyFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, Properties properties) {
        super(hasEffectTooltip, consumeSound, properties);
    }

    public SpicyFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, properties);
    }

    public SpicyFoodItem(boolean hasEffectTooltip, int useTicks, Properties properties) {
        super(hasEffectTooltip, useTicks, properties);
    }
}
