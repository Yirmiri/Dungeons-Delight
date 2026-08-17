package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

public class MonsterPotContainerSlot extends Slot {
    public MonsterPotContainerSlot(Container container, int slot, int x, int y) {
        super(container, slot, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(DDTags.ItemT.MONSTER_POT_CONTAINERS);
    }
}
