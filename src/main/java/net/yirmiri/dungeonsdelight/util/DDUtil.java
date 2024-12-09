package net.yirmiri.dungeonsdelight.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.yirmiri.dungeonsdelight.registry.DDStats;

import java.util.List;
import java.util.function.Predicate;

public class DDUtil {
    public static void dash(PlayerEntity player) {
        Vec3d getVelocity = player.getRotationVector().normalize();
        player.setVelocity(getVelocity.getX() * 1.5, getVelocity.getY() * 1, getVelocity.getZ() * 1.5);
        player.fallDistance = 0;

        player.incrementStat(DDStats.DASHES);
        if (player.isSprinting()) {
            player.addExhaustion(0.2F);
        } else {
            player.addExhaustion(0.05F);
        }
    }

    public static void knockbackNearbyEntities(World world, PlayerEntity player, Entity attacked) {
        world.syncWorldEvent(WorldEvents.SMASH_ATTACK, attacked.getSteppingPos(), 750);
        world.getEntitiesByClass(LivingEntity.class, attacked.getBoundingBox().expand(4.0), getKnockbackPredicate(player, attacked)).forEach(entity -> {
            Vec3d vec3d = entity.getPos().subtract(attacked.getPos());
            Vec3d vec3d2 = vec3d.normalize().multiply(2.25F);
            entity.addVelocity(vec3d2.x, 1.25f, vec3d2.z);
            if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
            }
        });
    }

    private static Predicate<LivingEntity> getKnockbackPredicate(PlayerEntity player, Entity attacked) {
        return entity -> {
            TameableEntity tameableEntity;
            boolean notSpectator = !entity.isSpectator();
            boolean notAttacked = entity != player && entity != attacked;
            boolean notTeammate = !player.isTeammate(entity);
            boolean notTamed = !(entity instanceof TameableEntity && (tameableEntity = (TameableEntity)entity).isTamed() && player.getUuid().equals(tameableEntity.getOwnerUuid()));
            boolean bl5 = attacked.squaredDistanceTo(entity) <= Math.pow(3.5, 2.0);
            return notSpectator && notAttacked && notTeammate && notTamed && bl5;
        };
    }
}