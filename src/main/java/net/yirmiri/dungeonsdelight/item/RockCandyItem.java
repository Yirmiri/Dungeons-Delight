package net.yirmiri.dungeonsdelight.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class RockCandyItem extends ConsumableItem {
    public RockCandyItem(Item.Properties properties) {
        super(properties, true, false);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 48;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);
        Vex vexEntity;
        Silverfish silverfishEntity;
        if (target instanceof Vex && (vexEntity = (Vex) target).isAlive() && !((Player) attacker).getCooldowns().isOnCooldown(this)) {
            vexEntity.level().playSound((Player) attacker, vexEntity, SoundEvents.VEX_DEATH, SoundSource.HOSTILE, 1.0F, 1.0F);
            vexEntity.remove(Entity.RemovalReason.DISCARDED);

            if (!attacker.level().isClientSide) {
                if (stack.isEmpty()) {
                    new ItemStack(DDItems.CANDIED_VEX_SUCKER.get());
                } else {
                    if (attacker instanceof Player && !((Player) attacker).getAbilities().instabuild) {
                        ItemStack itemStack = new ItemStack(DDItems.CANDIED_VEX_SUCKER.get());
                        Player inventory = (Player) attacker;
                        if (!inventory.getInventory().add(itemStack)) {
                            inventory.drop(itemStack, false);
                        }
                    }
                    ((Player) attacker).getCooldowns().addCooldown(this, 40);
                    stack.shrink(1);
                }
            }

        } else if (target instanceof Silverfish && (silverfishEntity = (Silverfish) target).isAlive() && !((Player) attacker).getCooldowns().isOnCooldown(this)) {
            silverfishEntity.level().playSound((Player) attacker, silverfishEntity, SoundEvents.SILVERFISH_DEATH, SoundSource.HOSTILE, 1.0F, 1.0F);
            silverfishEntity.remove(Entity.RemovalReason.DISCARDED);

            if (!attacker.level().isClientSide) {
                if (stack.isEmpty()) {
                    new ItemStack(DDItems.CANDIED_VEX_SUCKER.get());
                } else {
                    if (attacker instanceof Player && !((Player) attacker).getAbilities().instabuild) {
                        ItemStack itemStack = new ItemStack(DDItems.CANDIED_SILVERFISH_SUCKER.get());
                        Player inventory = (Player) attacker;
                        if (!inventory.getInventory().add(itemStack)) {
                            inventory.drop(itemStack, false);
                        }
                    }
                    ((Player) attacker).getCooldowns().addCooldown(this, 40);
                    stack.shrink(1);
                }
            }
        }
        return true;
    }
}
