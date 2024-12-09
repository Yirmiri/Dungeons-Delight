package net.yirmiri.dungeonsdelight.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class BreezeCreamConeItem extends ConsumableItem {
    public BreezeCreamConeItem(Settings settings) {
        super(settings, true, false);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (user.getFrozenTicks() <= 600) {
            user.setFrozenTicks(user.getFrozenTicks() + 100);
        }
        return stack;
    }
}
