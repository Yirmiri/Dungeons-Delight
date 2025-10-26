package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDCriteriaTriggers;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
public class AncientEggEntity extends ThrowableItemProjectile {
    public AncientEggEntity(EntityType<? extends AncientEggEntity> entityType, Level level) {
        super(entityType, level);
    }

    public AncientEggEntity(Level level, LivingEntity entity) {
        super(DDEntities.ANCIENT_EGG.get(), entity, level);
    }

    public AncientEggEntity(Level level, double x, double y, double z) {
        super(DDEntities.ANCIENT_EGG.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return DDItems.ANCIENT_EGG.get();
    }

    @Override
    public void handleEntityEvent(byte id) {
        ItemStack entityStack = new ItemStack(this.getDefaultItem());
        if (id == 3) {
            for (int i = 0; i < 12; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, entityStack), this.getX(), this.getY(), this.getZ(),
                        (this.random.nextFloat() * 2.0 - 1.0) * 0.1, (this.random.nextFloat() * 2.0 - 1.0)
                                * 0.1 + 0.1, (this.random.nextFloat() * 2.0 - 1.0) * 0.1);
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
        entity.hurt(ModDamageTypes.getSimpleDamageSource(this.level(), DDDamageTypes.ANCIENT_EGG), 4.0F);

        if (result.getType() == HitResult.Type.ENTITY && result.getEntity() instanceof CleaverEntity cleaverEntity && !this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            cleaverEntity.playSound(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get(), 1.0F, 1.0F);
            flingDatEgg(DDItems.CLEAVED_ANCIENT_EGG.get().getDefaultInstance(), -0.5F, 0, true);
            flingDatEgg(DDItems.CLEAVED_ANCIENT_EGG.get().getDefaultInstance(), 0.5F, 0, false);
            int expOutput = 3 + this.level().random.nextInt(5) + this.level().random.nextInt(5);
            ExperienceOrb.award((ServerLevel)this.level(), this.position(), expOutput);
            if (cleaverEntity.getOwner() instanceof ServerPlayer player) {
                DDCriteriaTriggers.SICK_THROW_DUDE.get().trigger(player.connection.getPlayer());
            }
            this.discard();
        }
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        if (entity instanceof CleaverEntity cleaverEntity && !cleaverEntity.isInGround()) {
            return true;
        }
        return super.canCollideWith(entity);
    }

    @Override
    protected boolean canHitEntity(Entity target) {
        if (target instanceof CleaverEntity) {
            return true;
        }
        return super.canHitEntity(target);
    }

    @Nullable
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

            if (this.captureDrops() != null) {
                this.captureDrops().add(itementity);
            } else {
                this.level().addFreshEntity(itementity);
            }
            return itementity;
        }
    }
}
