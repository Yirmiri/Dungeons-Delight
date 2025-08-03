package net.yirmiri.dungeonsdelight.common.entity.rotten_zombie;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import javax.annotation.Nullable;

public class RottenZombieEntity extends Zombie {
    public RottenZombieEntity(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ARMOR, 6.0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            if (entity instanceof LivingEntity living) {
                int duration = 4;
                if (this.level().getDifficulty() == Difficulty.NORMAL) {
                    duration = 6;
                } else if (this.level().getDifficulty() == Difficulty.HARD) {
                    duration = 8;
                }
                living.addEffect(new MobEffectInstance(DDEffects.PUTRID_SCENT.get(), duration * 20, 0), this);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        data = super.finalizeSpawn(accessor, difficultyInstance, spawnType, data, tag);
        if (this.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty() && accessor.getRandom().nextFloat() < 0.03F) {
            this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(DDItems.ROTBULB.get()));
            this.setGuaranteedDrop(EquipmentSlot.OFFHAND);
        }
        return data;
    }

    @Override
    protected boolean canReplaceCurrentItem(ItemStack stack, ItemStack stack1) {
        if (stack1.is(DDItems.ROTBULB.get())) {
            return false;
        } else {
            return super.canReplaceCurrentItem(stack, stack1);
        }
    }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }
}
