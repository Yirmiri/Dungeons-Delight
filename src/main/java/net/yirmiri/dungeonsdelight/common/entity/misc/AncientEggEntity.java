package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;

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
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(ModDamageTypes.getSimpleDamageSource(this.level(), DDDamageTypes.ANCIENT_EGG), 4.0F);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }
}
