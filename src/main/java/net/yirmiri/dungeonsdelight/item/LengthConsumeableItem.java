package net.yirmiri.dungeonsdelight.item;

import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class LengthConsumeableItem extends ConsumableItem {
    private int length;

    public LengthConsumeableItem(Properties properties, int length, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
        this.length = length;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return length;
    }
}
