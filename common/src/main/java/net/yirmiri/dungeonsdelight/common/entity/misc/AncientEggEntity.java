package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDCriteriaTriggers;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

public class AncientEggEntity extends ThrowableItemProjectile {
    public AncientEggEntity(EntityType<? extends AncientEggEntity> entityType, Level level) { super(entityType, level); }
    public AncientEggEntity(Level level, LivingEntity shooter) {
        super(DDEntities.ANCIENT_EGG.get(), shooter, level);
    }
    public AncientEggEntity(Level level, double x, double y, double z) { super(DDEntities.ANCIENT_EGG.get(), x, y, z, level); }

    @Override
    protected Item getDefaultItem() {
        return DDItems.ANCIENT_EGG.get();
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - (double)0.5F) * 0.08, ((double)this.random.nextFloat() - (double)0.5F) * 0.08, ((double)this.random.nextFloat() - (double)0.5F) * 0.08);
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(DDDamageTypes.getDamageSource(entity.level(), DDDamageTypes.ECHO_BLAST), 3.0F);

        if (result.getType() == HitResult.Type.ENTITY && result.getEntity() instanceof CleaverEntity cleaverEntity && !this.level().isClientSide && !cleaverEntity.isInGround()) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            cleaverEntity.playSound(DDSounds.CLEAVER_CLEAVE.get(), 1.0F, 1.0F);

            flingDatEgg(DDItems.CLEAVED_ANCIENT_EGG.get().getDefaultInstance(), -0.5F, 0, true);
            flingDatEgg(DDItems.CLEAVED_ANCIENT_EGG.get().getDefaultInstance(), 0.5F, 0, false);

            int expOutput = 3 + this.level().random.nextInt(5) + this.level().random.nextInt(5);
            ExperienceOrb.award((ServerLevel) this.level(), this.position(), expOutput);

            if (cleaverEntity.getOwner() instanceof ServerPlayer player) {
                DDCriteriaTriggers.SICK_THROW_DUDE.trigger(player.connection.getPlayer());
            }
            this.discard();
        }
    }

    @Override public boolean canCollideWith(Entity entity) { return(entity instanceof CleaverEntity cleaverEntity && !cleaverEntity.isInGround()) || super.canCollideWith(entity); }
    @Override protected boolean canHitEntity(Entity target) { return (target instanceof CleaverEntity) || super.canHitEntity(target); }

    public ItemEntity flingDatEgg(ItemStack stack, float offsetX, float offsetY, boolean reverse) {
        if (stack.isEmpty()) {
            return null;
        } else if (this.level().isClientSide) {
            return null;
        } else {
            ItemEntity itementity = new ItemEntity(this.level(), this.getX() + (double) offsetX, this.getY() + (double) offsetY, this.getZ(), stack);
            itementity.setDefaultPickUpDelay();

            double velocity = 0.2D;
            itementity.setDeltaMovement(reverse ? -velocity : velocity, 0.0D, 0.0D);

            this.level().addFreshEntity(itementity);
            return itementity;
        }
    }
}

