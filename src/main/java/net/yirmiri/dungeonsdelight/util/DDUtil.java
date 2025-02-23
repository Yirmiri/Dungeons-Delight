package net.yirmiri.dungeonsdelight.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.registry.DDDamageTypes;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;

import java.util.function.Predicate;

public class DDUtil {
    //COMPAT IDS
    public static final String RM_ID = "ratmania";
    public static final String FN_ID = "fortnite";
    public static final String TF_ID = "twilightforest";

    public static void skullHeartBlast(Level level, LivingEntity player, Entity attacked) {
        if (player.hasEffect(DDEffects.EXUDATION.get())) {
            level.getEntitiesOfClass(LivingEntity.class, attacked.getBoundingBox().inflate(8.0 + player.getEffect(DDEffects.EXUDATION.get()).getAmplifier()),
                    getKnockbackPredicate(player, attacked, true)).forEach(entity -> {
                entity.hurt(ModDamageTypes.getSimpleDamageSource(level, DDDamageTypes.SKULL_HEART_BLAST), 8.0F);
                Vec3 vec3d = entity.position().subtract(attacked.position());
                Vec3 vec3d2 = vec3d.normalize().multiply(0.75, 0.75, 0.75);
                entity.setDeltaMovement(vec3d2.x, 0.25F, vec3d2.z);
            });
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
