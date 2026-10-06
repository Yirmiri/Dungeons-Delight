package net.yirmiri.dungeonsdelight.core.mixin.hollowing;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.yirmiri.dungeonsdelight.common.entity.misc.IHollowable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.UUID;

@Mixin(Horse.class)
public abstract class HorseHollowMixin extends HollowingMixin implements IHollowable {
    @Override
    public Mob dungeonsdelight$convertViaHollowable(ServerLevel level, Player player) {
        Horse horse = (Horse)(Object)this;

        UUID owner = horse.getOwnerUUID();
        boolean tamed = horse.isTamed();
        boolean saddle = horse.isSaddled();
        ItemStack armor = horse.getArmor().copy();
        boolean armorEquipped = horse.isWearingArmor();

        ZombieHorse zombie = horse.convertTo(EntityType.ZOMBIE_HORSE, true);

        if (zombie != null) {
            zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()), MobSpawnType.CONVERSION, null, null);

            zombie.getAttribute(Attributes.MAX_HEALTH).setBaseValue(horse.getAttributeBaseValue(Attributes.MAX_HEALTH));
            zombie.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(horse.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
            zombie.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(horse.getAttributeBaseValue(Attributes.JUMP_STRENGTH));
            zombie.setHealth((float) Math.min(zombie.getHealth(), horse.getAttributeBaseValue(Attributes.MAX_HEALTH)));

            if (tamed) {
                zombie.setTamed(true);
                zombie.setOwnerUUID(owner);
            }
            if (saddle) {
                zombie.getSlot(400).set(Items.SADDLE.getDefaultInstance());
            }
            //zombie.getSlot(401).set(armor); //maybe add armor slot and if so it will prevent burning in daylight
            if (armorEquipped) {
                ItemEntity itemEntity = EntityType.ITEM.create(level);
                if (itemEntity != null) {
                    itemEntity.setDefaultPickUpDelay();
                    itemEntity.setItem(armor);
                    itemEntity.moveTo(zombie.getX(), zombie.getY(), zombie.getZ());
                    level.addFreshEntity(itemEntity);
                }
            }
        }

        return zombie;
    }

    @Override
    public boolean dungeonsdelight$isHollowing() {
        return this.dungeonsdelight$isHollowing;
    }
}
