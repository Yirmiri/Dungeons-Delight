package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

public class GunkArrowEntity extends AbstractArrow {
    public GunkArrowEntity(EntityType<GunkArrowEntity> type, Level level) {
        super(type, level);
    }

    public GunkArrowEntity(Level level, LivingEntity owner, ItemStack pickupItemStack, ItemStack firedFromWeapon) {
        super(DDEntities.GUNK_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
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
    protected void doPostHurtEffects(LivingEntity living) {
        super.doPostHurtEffects(living);
        playSound(SoundEvents.SLIME_ATTACK, 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));
        living.addEffect(new MobEffectInstance(DDEffects.PUTRID_SCENT, 300, 0), getEffectSource());
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide && !inGround) {
            level().addParticle(DDParticles.DECISIVE_CRITICAL.get(), getX(), getY(), getZ(), 0.0F, 0.0F, 0.0F);
        }
    }
}
