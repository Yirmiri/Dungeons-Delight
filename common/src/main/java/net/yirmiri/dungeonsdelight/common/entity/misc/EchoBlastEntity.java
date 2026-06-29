package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

import java.util.HashSet;
import java.util.Set;

public class EchoBlastEntity extends Entity {
    private LivingEntity owner;
    private int amplifier;
    private float damage;
    private double maxRadius;
    private double expansionPerTick;
    private double radius = 0.0;
    private final Set<LivingEntity> hitEntities = new HashSet<>();

    public EchoBlastEntity(EntityType<? extends EchoBlastEntity> type, Level level) {
        super(type, level);
    }

    public EchoBlastEntity(Level level, LivingEntity owner, int amplifier, double maxRadius, float damage, int duration) {
        this(DDEntities.ECHO_BLAST.get(), level);
        this.owner = owner;
        this.amplifier = amplifier;
        this.maxRadius = maxRadius;
        this.damage = damage;
        this.expansionPerTick = maxRadius / duration;
        this.setPos(owner.getX(), owner.getY(), owner.getZ());
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            radius += expansionPerTick;

            level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(radius), entity -> entity != owner && entity.isAlive() && entity instanceof Monster && !entity.getType().is(DDTags.EntityT.IGNORES_ECHO_BLAST) && !hitEntities.contains(entity)).forEach(entity -> {
                hitEntities.add(entity);
                Vec3 normalize = entity.position().subtract(position()).normalize();
                double minStrength = 2.5;
                double maxStrength = 4.0;
                double strength = Math.max(minStrength, maxStrength * (1.0 - entity.position().subtract(position()).length() / radius));

                entity.setDeltaMovement(normalize.x * strength, 0.25F, normalize.z * strength);
                entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(DDDamageTypes.ECHO_BLAST), owner), damage);
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30 * amplifier, 0));
            });

            if (radius >= maxRadius) {
                discard();
            }
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }
}