package net.yirmiri.dungeonsdelight.common.item;

import vectorwing.farmersdelight.common.item.ConsumableItem;

public class SlimeFoodItem extends ConsumableItem {
    private final float chance;

    public SlimeFoodItem(Properties properties, float chance, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
        this.chance = chance;
    }
}
