package net.yirmiri.dungeonsdelight.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class AmethystRockCandyItem extends ConsumableItem {
    public AmethystRockCandyItem(Settings settings) {
        super(settings, true, false);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity entity) {
        return 48;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        super.useOnEntity(stack, user, entity, hand);
        VexEntity vexEntity;
        SilverfishEntity silverfishEntity;
        if (entity instanceof VexEntity && (vexEntity = (VexEntity) entity).isAlive()) {
            vexEntity.getWorld().playSoundFromEntity(user, vexEntity, SoundEvents.ENTITY_VEX_DEATH, SoundCategory.HOSTILE, 1.0F, 1.0F);
            vexEntity.remove(Entity.RemovalReason.DISCARDED);

            if (!user.getWorld().isClient) {
                ItemUsage.exchangeStack(stack, user, new ItemStack(DDItems.CANDIED_VEX_SUCKER));
                user.getItemCooldownManager().set(this, 40);
            }
            return ActionResult.success(user.getWorld().isClient);

        } else if (entity instanceof SilverfishEntity && (silverfishEntity = (SilverfishEntity) entity).isAlive()) {
            silverfishEntity.getWorld().playSoundFromEntity(user, silverfishEntity, SoundEvents.ENTITY_SILVERFISH_DEATH, SoundCategory.HOSTILE, 1.0F, 1.0F);
            silverfishEntity.remove(Entity.RemovalReason.DISCARDED);

            if (!user.getWorld().isClient) {
                ItemUsage.exchangeStack(stack, user, new ItemStack(DDItems.CANDIED_SILVERFISH_SUCKER));
                user.getItemCooldownManager().set(this, 40);
            }
            return ActionResult.success(user.getWorld().isClient);
        }
        return ActionResult.PASS;
    }
}
