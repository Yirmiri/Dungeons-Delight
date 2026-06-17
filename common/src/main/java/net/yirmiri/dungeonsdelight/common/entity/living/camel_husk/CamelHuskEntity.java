package net.yirmiri.dungeonsdelight.common.entity.living.camel_husk;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class CamelHuskEntity extends Camel {
    public CamelHuskEntity(EntityType<? extends Camel> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isPersistenceRequired() {
        return isTamed() || isSaddled() || hasCustomName();
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.RABBIT_FOOT);
    }

    @Override
    public boolean canMate(Animal animal) {
        return false;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }
}
