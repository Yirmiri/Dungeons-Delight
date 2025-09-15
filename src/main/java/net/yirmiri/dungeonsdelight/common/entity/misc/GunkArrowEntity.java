package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import javax.annotation.Nullable;

public class GunkArrowEntity extends AbstractArrow {
    public GunkArrowEntity(EntityType<GunkArrowEntity> type, Level level) {
        super(type, level);
    }

    public GunkArrowEntity(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(DDEntities.GUNK_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    public void setBaseDamage(double baseDamage) {
        super.setBaseDamage(0);
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(DDItems.GUNK_ARROW.get());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(DDItems.GUNK_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        if (entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(DDEffects.PUTRID_SCENT, 200, 0));
        }
//        entity.setRemainingFireTicks(entity.getRemainingFireTicks());
//        this.setDeltaMovement(this.getDeltaMovement().scale(-0.1));
//        this.setYRot(this.getYRot() + 180.0F);
//        this.yRotO += 180.0F;
        super.onHitEntity(hitResult);

        //this.discard(); //prevents flash but wont count as damage
    }
}
