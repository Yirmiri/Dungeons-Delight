package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.function.Predicate;

public class DDUtil {
    public static final int MONSTER_COLOR = 0xc875c2;

    public static void applyEffectSwap(LivingEntity living, MobEffect oldEffect, MobEffect newEffect) {
        if (living.hasEffect(oldEffect)) {
            int duration = living.getEffect(oldEffect).getDuration();
            living.removeEffect(oldEffect);
            living.addEffect(new MobEffectInstance(newEffect, duration, 0));
        }
    }

    public static void applyMonsterEffectSwap(LivingEntity living, MobEffect oldEffect, MobEffect newEffect) {
        applyEffectSwap(living, oldEffect, newEffect);
        if (living instanceof ServerPlayer serverPlayer) {
            //DDCriteriaTriggers.MONSTERIZE_EFFECT.get().trigger(serverPlayer); //todo
            serverPlayer.level().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(),
                    DDSounds.GENERIC_MONSTERIZE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    public static void exudationBlast(Level level, LivingEntity effectUser, Entity targetEntity) {
        boolean effectPlayers = targetEntity instanceof Player;

        if (effectUser.hasEffect(DDEffects.EXUDATION.get())) {
            level.getEntitiesOfClass(LivingEntity.class,
                    targetEntity.getBoundingBox().inflate(DungeonsDelight.CONFIG.getExudationBaseRange()
                            + effectUser.getEffect(DDEffects.EXUDATION.get()).getAmplifier() * 2),

                    getTargetPredicate(effectUser, targetEntity, effectPlayers)).forEach(entity -> {

                DamageSource source = new DamageSource(entity.level().registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DDDamageTypes.EXUDATION_BLAST));

                entity.hurt(source, DungeonsDelight.CONFIG.getExudationBaseDamage()
                        + (effectUser.getEffect(DDEffects.EXUDATION.get()).getAmplifier() * 3));

                Vec3 vec3d = entity.position().subtract(targetEntity.position());
                Vec3 vec3d2 = vec3d.normalize().multiply(0.75, 0.75, 0.75);
                entity.setDeltaMovement(vec3d2.x, 0.25F, vec3d2.z);
            });
        }
    }

    private static Predicate<LivingEntity> getTargetPredicate(LivingEntity player, Entity attacked, boolean dontEffectPlayers) {
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
