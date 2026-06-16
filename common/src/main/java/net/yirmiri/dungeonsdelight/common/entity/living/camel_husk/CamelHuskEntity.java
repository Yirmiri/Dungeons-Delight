package net.yirmiri.dungeonsdelight.common.entity.living.camel_husk;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class CamelHuskEntity extends Camel {
    public CamelHuskEntity(EntityType<? extends Camel> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.5F);
        this.moveControl = new CamelMoveControl();
        GroundPathNavigation groundpathnavigation = (GroundPathNavigation) this.getNavigation();
        groundpathnavigation.setCanFloat(true);
        groundpathnavigation.setCanWalkOverFences(true);
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
    public boolean canFallInLove() {
        return false;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    class CamelMoveControl extends MoveControl {
        public CamelMoveControl() {
            super(CamelHuskEntity.this);
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO && !CamelHuskEntity.this.isLeashed() && CamelHuskEntity.this.isCamelSitting() && !CamelHuskEntity.this.isInPoseTransition()) {
                CamelHuskEntity.this.standUp();
            }

            super.tick();
        }
    }
}
