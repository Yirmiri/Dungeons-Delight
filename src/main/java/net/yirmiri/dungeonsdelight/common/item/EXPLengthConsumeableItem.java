package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class EXPLengthConsumeableItem extends EXPFoodItem {
    private int length;

    public EXPLengthConsumeableItem(Properties properties, int length, int experience, boolean hasFoodEffectTooltip) {
        super(properties, experience, hasFoodEffectTooltip);
        this.length = length;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return length;
    }
}
