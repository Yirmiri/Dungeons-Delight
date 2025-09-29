package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.item.food_types.SlimeFoodItem;

public class BreezeCreamConeItem extends SlimeFoodItem {
    private int freezeTicks;

    public BreezeCreamConeItem(Properties properties, float chance, int freezeTicks, boolean hasFoodEffectTooltip) {
        super(properties, chance, hasFoodEffectTooltip);
        this.freezeTicks = freezeTicks;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        //if (consumer.getTicksFrozen() < 300) {
            consumer.setTicksFrozen(freezeTicks);
        //}
        consumer.extinguishFire();
        return super.finishUsingItem(stack, level, consumer);
    }
}
