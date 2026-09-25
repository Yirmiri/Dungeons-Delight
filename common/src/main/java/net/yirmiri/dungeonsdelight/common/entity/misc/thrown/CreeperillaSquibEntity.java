package net.yirmiri.dungeonsdelight.common.entity.misc.thrown;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

public class CreeperillaSquibEntity extends CleavableThrowableProjectile {
    public CreeperillaSquibEntity(EntityType<? extends CreeperillaSquibEntity> entityType, Level level) { super(entityType, level); }
    public CreeperillaSquibEntity(Level level, LivingEntity shooter) { super(DDEntities.CREEPERILLA_SQUIB.get(), level, shooter); }
    public CreeperillaSquibEntity(Level level, double x, double y, double z) { super(DDEntities.CREEPERILLA_SQUIB.get(), level, x, y, z); }

    @Override protected Item getDefaultItem() { return DDItems.CREEPERILLA_SQUIB.get(); }

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

        if (result.getType() == HitResult.Type.ENTITY && result instanceof EntityHitResult entH) {
            Entity entity = entH.getEntity();
            if (entity instanceof CleaverEntity cleaverEntity && !this.level().isClientSide && !cleaverEntity.isInGround()) {
                this.level().explode(
                        this,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        5,
                        false,
                        Level.ExplosionInteraction.MOB
                );

                this.discard();
                return;
            }
        }

        boolean playerCheck = !(this.getOwner() instanceof Player player) || (!player.isCreative() && !player.isSpectator());
        if (!this.level().isClientSide && playerCheck) {
            ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), this.getItem());
            this.level().addFreshEntity(itementity);

            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();


    }
}
