package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class DDUtil {
    public static final List<Holder<MobEffect>> NORMAL_EFFECTS = List.of(
            MobEffects.DAMAGE_BOOST, MobEffects.JUMP, MobEffects.ABSORPTION,
            ModEffects.NOURISHMENT, ModEffects.COMFORT, MobEffects.DIG_SPEED
    );

    public static final List<Holder<MobEffect>> MONSTER_EFFECTS = List.of(
            DDEffects.DECISIVE, DDEffects.POUNCING, DDEffects.EXUDATION,
            DDEffects.VORACITY, DDEffects.TENACITY, DDEffects.BURROW_GUT
    );

    public static void skullHeartBlast(Level level, LivingEntity player, Entity attacked) {
        if (player.hasEffect(DDEffects.EXUDATION)) {
            level.getEntitiesOfClass(LivingEntity.class, attacked.getBoundingBox().inflate(8.0 + player.getEffect(DDEffects.EXUDATION).getAmplifier()),
                    getKnockbackPredicate(player, attacked, true)).forEach(entity -> {
                entity.hurt(ModDamageTypes.getSimpleDamageSource(level, DDDamageTypes.SKULL_HEART_BLAST), 8.0F);
                Vec3 vec3d = entity.position().subtract(attacked.position());
                Vec3 vec3d2 = vec3d.normalize().multiply(0.75, 0.75, 0.75);
                entity.setDeltaMovement(vec3d2.x, 0.25F, vec3d2.z);
            });
        }
    }

    public static void applyEffectSwap(LivingEntity living, Holder<MobEffect> oldEffect, Holder<MobEffect> newEffect) {
        if (living.hasEffect(oldEffect)) {
            int duration = living.getEffect(oldEffect).getDuration();
            living.removeEffect(oldEffect);
            living.addEffect(new MobEffectInstance(newEffect, duration, 0));
        }
    }

    public static void spreadParticles(ParticleOptions particle, Entity entity, Random random) {
        for (int i = 0; i < 5; ++i) {
            double d0 = random.nextGaussian() * 0.02;
            double d1 = random.nextGaussian() * 0.02;
            double d2 = random.nextGaussian() * 0.02;
            entity.level().addParticle(particle,
                    entity.getRandomX(1.0), entity.getRandomY() + 1.0, entity.getRandomZ(1.0), d0, d1, d2);
        }
    }

    private static Predicate<LivingEntity> getKnockbackPredicate(LivingEntity player, Entity attacked, boolean dontEffectPlayers) {
        return entity -> {
            TamableAnimal tamableAnimal;
            boolean notSpectator = !entity.isSpectator();
            boolean notAttacked = entity != player && entity != attacked;
            boolean notTeammate = !player.isAlliedTo(entity);
            boolean notTamed = !(entity instanceof TamableAnimal && (tamableAnimal = (TamableAnimal)entity).isTame() && player.getUUID().equals(tamableAnimal.getOwnerUUID()));
            boolean distance = attacked.distanceTo(entity) <= Math.pow(3.5, 2.0);
            boolean notPlayer = !(entity instanceof Player);
            if (dontEffectPlayers) {
                return notSpectator && notAttacked && notTeammate && notTamed && distance && notPlayer;
            } else return notSpectator && notAttacked && notTeammate && notTamed && distance;
        };
    }
}
