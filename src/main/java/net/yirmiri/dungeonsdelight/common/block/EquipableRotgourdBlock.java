package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;

public class EquipableRotgourdBlock extends CarvedRotgourdBlock implements Equipable {
    public EquipableRotgourdBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
