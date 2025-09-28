package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class DDUtil {
    public static final Boat.Type WORMWOOD_BOAT = Boat.Type.byName("wormwood");

    public static final List<Holder<MobEffect>> NORMAL_EFFECTS = List.of(
            MobEffects.DAMAGE_BOOST, MobEffects.JUMP, MobEffects.ABSORPTION,
            ModEffects.NOURISHMENT, ModEffects.COMFORT, MobEffects.DIG_SPEED,
            MobEffects.MOVEMENT_SPEED
    );

    public static final List<Holder<MobEffect>> MONSTER_EFFECTS = List.of(
            DDEffects.DECISIVE, DDEffects.POUNCING, DDEffects.EXUDATION,
            DDEffects.VORACITY, DDEffects.TENACITY, DDEffects.BURROW_GUT,
            DDEffects.SWIFT_STEP
    );

    public static void applyEffectSwap(LivingEntity living, Holder<MobEffect> oldEffect, Holder<MobEffect> newEffect) {
        if (living.hasEffect(oldEffect)) {
            int duration = living.getEffect(oldEffect).getDuration();
            living.removeEffect(oldEffect);
            living.addEffect(new MobEffectInstance(newEffect, duration, 0));
        }
    }

    public static Holder<Enchantment> getEnchantmentHolder(RegistryAccess registryAccess, ResourceKey<Enchantment> resourceKey) {
        return registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(resourceKey);
    }

    public static Holder<Enchantment> getEnchantmentHolder(Entity entity, ResourceKey<Enchantment> resourceKey) {
        return getEnchantmentHolder(entity.registryAccess(), resourceKey);
    }

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

    public static void spreadParticles(ParticleOptions particle, Entity entity, Random random) {
        for (int i = 0; i < 5; ++i) {
            double d0 = random.nextGaussian() * 0.02;
            double d1 = random.nextGaussian() * 0.02;
            double d2 = random.nextGaussian() * 0.02;
            entity.level().addParticle(particle,
                    entity.getRandomX(1.0), entity.getRandomY() + 1.0, entity.getRandomZ(1.0), d0, d1, d2);
        }
    }

    public static void dash(Player player) {
        Vec3 velocity = player.getLookAngle().normalize();
        player.setDeltaMovement(velocity.x * 1.5, velocity.y * 1.0, velocity.z * 1.5);
        player.fallDistance = 0;

        //player.awardStat(DDStats.DASHES);
        if (player.isSprinting()) {
            player.causeFoodExhaustion(0.2F);
        } else {
            player.causeFoodExhaustion(0.05F);
        }
    }

    public static void knockbackNearbyEntities(Level level, LivingEntity player, Entity attacked) {
        level.levelEvent(LevelEvent.PARTICLES_SMASH_ATTACK, attacked.blockPosition(), 750);
        level.getEntitiesOfClass(LivingEntity.class, attacked.getBoundingBox().inflate(4.0), getKnockbackPredicate(player, attacked, false))
                .forEach(entity -> {
                    Vec3 vec = entity.position().subtract(attacked.position());
                    Vec3 vecNorm = vec.normalize().scale(2.25F);
                    entity.push(vecNorm.x, 1.25F, vecNorm.z);

                    if (entity instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                    }
                });
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
