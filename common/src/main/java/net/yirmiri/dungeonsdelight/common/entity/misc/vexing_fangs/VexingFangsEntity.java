package net.yirmiri.dungeonsdelight.common.entity.misc.vexing_fangs;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

import java.util.UUID;

public class VexingFangsEntity extends Entity implements TraceableEntity {
    private int warmupDelayTicks;
    private boolean sentSpikeEvent;
    private int lifeTicks;
    private boolean clientSideAttackStarted;
    private LivingEntity owner;
    private UUID ownerUUID;

    public VexingFangsEntity(EntityType<? extends VexingFangsEntity> entityType, Level level) {
        super(entityType, level);
        this.lifeTicks = DungeonsDelight.CONFIG.getVexingFangsLifetimeTicks();
    }

    public VexingFangsEntity(Level level, double x, double y, double z, float yRot, int warmupDelay, LivingEntity owner) {
        this(DDEntities.VEXING_FANGS.get(), level);
        this.warmupDelayTicks = warmupDelay;
        this.setOwner(owner);
        this.setYRot(yRot * (180F / (float) Math.PI));
        this.setPos(x, y, z);
    }

    @Override
    protected void defineSynchedData() {
    }

    public void setOwner(LivingEntity owner) {
        this.owner = owner;
        this.ownerUUID = owner == null ? null : owner.getUUID();
    }

    @Override
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }
        return this.owner;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.warmupDelayTicks = compound.getInt("Warmup");
        if (compound.hasUUID("Owner")) {
            this.ownerUUID = compound.getUUID("Owner");
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("Warmup", this.warmupDelayTicks);
        if (this.ownerUUID != null) {
            compound.putUUID("Owner", this.ownerUUID);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (this.clientSideAttackStarted) {
                --this.lifeTicks;
                if (this.lifeTicks == 14) {
                    for(int i = 0; i < 12; ++i) {
                        double d0 = this.getX() + (this.random.nextDouble() * (double) 2.0F - (double)1.0F) * (double) this.getBbWidth() * (double) 0.5F;
                        double d1 = this.getY() + 0.05 + this.random.nextDouble();
                        double d2 = this.getZ() + (this.random.nextDouble() * (double) 2.0F - (double)1.0F) * (double) this.getBbWidth() * (double) 0.5F;
                        double d3 = (this.random.nextDouble() * (double) 2.0F - (double) 1.0F) * 0.3;
                        double d4 = 0.3 + this.random.nextDouble() * 0.3;
                        double d5 = (this.random.nextDouble() * (double) 2.0F - (double) 1.0F) * 0.3;
                        this.level().addParticle(ParticleTypes.CRIT, d0, d1 + (double) 1.0F, d2, d3, d4, d5);
                    }
                }
            }
        } else if (--this.warmupDelayTicks < 0) {
            if (this.warmupDelayTicks == -8) {
                for(LivingEntity livingentity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.2, 0.0F, 0.2))) {
                    this.dealDamageTo(livingentity);
                }
            }

            if (!this.sentSpikeEvent) {
                this.level().broadcastEntityEvent(this, (byte)4);
                this.sentSpikeEvent = true;
            }

            if (--this.lifeTicks < 0) {
                this.discard();
            }
        }
    }

    private void dealDamageTo(LivingEntity target) {
        LivingEntity livingentity = this.getOwner();
        if (target.isAlive() && !target.isInvulnerable() && target != livingentity) {
            if (livingentity == null) {
                target.hurt(new DamageSource(this.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(DDDamageTypes.VEXING_FANGS), this, owner == null ? this : owner), DungeonsDelight.CONFIG.getVexingFangsDamage());
            } else {
                if (livingentity.isAlliedTo(target)) {
                    return;
                }
                target.hurt(new DamageSource(this.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(DDDamageTypes.VEXING_FANGS), this, owner == null ? this : owner), DungeonsDelight.CONFIG.getVexingFangsDamage());
            }
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
        if (id == 4) {
            this.clientSideAttackStarted = true;
            if (!this.isSilent()) {
                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.EVOKER_FANGS_ATTACK, this.getSoundSource(), 0.75F, this.random.nextFloat() * 0.2F + 0.85F, false);
            }
        }
    }

    public float getAnimationProgress(float partialTicks) {
        if (!this.clientSideAttackStarted) {
            return 0.0F;
        } else {
            int i = this.lifeTicks - 2;
            return i <= 0 ? 1.0F : 1.0F - ((float)i - partialTicks) / 20.0F;
        }
    }
}
