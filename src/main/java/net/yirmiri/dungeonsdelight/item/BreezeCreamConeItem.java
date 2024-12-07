package net.yirmiri.dungeonsdelight.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BreezeCreamConeItem extends Item {
    public BreezeCreamConeItem(Settings settings) {
        super(settings);
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
