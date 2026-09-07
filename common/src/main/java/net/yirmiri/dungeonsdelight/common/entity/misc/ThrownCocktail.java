package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.entity.misc.leftovers.LeftoversEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

import java.util.List;

public class ThrownCocktail extends ThrowableItemProjectile {
    public int particleColor;
    public LeftoversEntity.LeftoversType type;
    public List<MobEffect> mobEffects;
    public List<Integer> effectDurations;
    public List<Integer> effectAmplifiers;
    public List<Integer> effectMaxDurations;
    public int hunger;
    public float saturation;

    public ThrownCocktail(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    public ThrownCocktail(Level level, LivingEntity entity, int particleColor, LeftoversEntity.LeftoversType type, List<MobEffect> mobEffects, List<Integer> effectDurations, List<Integer> effectAmplifiers, List<Integer> effectMaxDurations, int hunger, float saturation) {
        super(DDEntities.THROWN_COCKTAIL.get(), entity, level);
        this.particleColor = particleColor;
        this.type = type;
        this.mobEffects = mobEffects;
        this.effectDurations = effectDurations;
        this.effectAmplifiers = effectAmplifiers;
        this.effectMaxDurations = effectMaxDurations;
        this.hunger = hunger;
        this.saturation = saturation;
    }

    public ThrownCocktail(Level level, double x, double y, double z) {
        super(DDEntities.THROWN_COCKTAIL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return getItemRaw().getItem();
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
    public void tick() {
        super.tick();
        this.getDeltaMovement();
        Vec3 vec3 = this.getDeltaMovement();
        double vecX = vec3.x;
        double vecY = vec3.y;
        double vecZ = vec3.z;
        for (int i = 0; i < 3; ++i) {
            this.level().addParticle(ParticleTypes.SMOKE, this.getX() -vecX, this.getY() -vecY, this.getZ() -vecZ, -vecX, -vecY, -vecZ);
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (this.level() instanceof ServerLevel) {
            this.level().levelEvent(2002, this.blockPosition(), particleColor);
            if (mobEffects != null && !mobEffects.isEmpty()) {
                for (int j = 0; j < 4; ++j) {
                    int effectIndex = this.random.nextInt(mobEffects.size());
                    MobEffect mobEffect = mobEffects.get(effectIndex);
                    int duration = effectDurations.get(effectIndex);
                    int amplifier = effectAmplifiers.get(effectIndex);
                    int maxDuration = effectMaxDurations.get(effectIndex);

                    this.level().addFreshEntity(new LeftoversEntity(this.level(), this.getX(), this.getY(), this.getZ(),
                            200, mobEffect, 1200, duration, amplifier, maxDuration, hunger, saturation, type
                    ));
                }
            }
            this.discard();
        }
    }

    @Override
    protected float getGravity() {
        return 0.07F;
    }
}