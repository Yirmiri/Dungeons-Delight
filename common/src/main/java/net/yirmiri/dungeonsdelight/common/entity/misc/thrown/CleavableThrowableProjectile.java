package net.yirmiri.dungeonsdelight.common.entity.misc.thrown;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;

public abstract class CleavableThrowableProjectile extends ThrowableItemProjectile
{
    protected CleavableThrowableProjectile(EntityType<? extends CleavableThrowableProjectile> entityType, Level level) { super(entityType, level); }
    protected CleavableThrowableProjectile(EntityType<? extends CleavableThrowableProjectile> entityType, Level level, LivingEntity shooter) { super(entityType, shooter, level); }
    protected CleavableThrowableProjectile(EntityType<? extends CleavableThrowableProjectile> entityType, Level level, double x, double y, double z) { super(entityType, x, y, z, level); }

    @Override
    public boolean canCollideWith(Entity entity)
    {
        return (entity instanceof CleaverEntity cleaverEntity && !cleaverEntity.isInGround()) || super.canCollideWith(entity);
    }

    @Override
    protected boolean canHitEntity(Entity target)
    {
        return (target instanceof CleaverEntity) || super.canHitEntity(target);
    }
}