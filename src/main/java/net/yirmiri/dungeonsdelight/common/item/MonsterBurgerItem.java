package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class MonsterBurgerItem extends ConsumableItem {
    public MonsterBurgerItem(Properties properties) {
        super(properties, false, true);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 64;
    }
}
