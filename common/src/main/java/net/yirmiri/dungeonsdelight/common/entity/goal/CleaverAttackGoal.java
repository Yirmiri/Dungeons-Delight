package net.yirmiri.dungeonsdelight.common.entity.goal;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.pathfinder.Path;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;

import java.util.EnumSet;

public class CleaverAttackGoal<T extends Monster> extends Goal {
    private final T mob;
    private int seeTime;
    private final int attackCooldownTicks;
    private int attackCooldown;
    private final int chargeTicks;
    private final boolean canStrafe;
    private int strafingTime = -1;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private final int dashCooldownTicks;
    private int dashCooldown = 140;
    private int dashTicks;
    private boolean dashing;
    private double dashDirX;
    private double dashDirZ;
    private int gallopTicks;
    private boolean galloping;

    public CleaverAttackGoal(T mob, int chargeTicks, int attackCooldownTicks, int dashCooldownTicks) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.chargeTicks = chargeTicks;
        this.attackCooldownTicks = attackCooldownTicks;
        this.dashCooldownTicks = dashCooldownTicks;
        this.canStrafe = true;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = mob != null ? mob.getTarget() : null;
        return target != null && target.isAlive() && getCleaver() != null;
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = mob != null ? mob.getTarget() : null;
        return target != null && target.isAlive() && getCleaver() != null;
    }

    @Override
    public void stop() {
        if (mob == null) return;

        mob.stopUsingItem();
        mob.getNavigation().stop();
        seeTime = 0;
        strafingTime = -1;
        dashing = false;
        galloping = false;
        gallopTicks = 0;
        mob.getMoveControl().strafe(0.0F, 0.0F);
    }

    @Override
    public void tick() {
        if (mob == null) return;
        LivingEntity target = mob.getTarget();
        if (target == null || !target.isAlive()) return;

        if (dashCooldown > 0) {
            --dashCooldown;
        }

        double distanceSqr = mob.distanceToSqr(target);
        double rangeSqr = 16.0D * 16.0D;
        boolean canSee = mob.getSensing().hasLineOfSight(target);

        if (canSee != seeTime > 0) {
            seeTime = 0;
        }

        if (canSee) {
            ++seeTime;
        } else {
            --seeTime;
        }

        if (mob.isPassenger() && dashCooldown <= 0 && distanceSqr <= 144.0D) {
            double dx = target.getX() - mob.getX();
            double dz = target.getZ() - mob.getZ();
            double sqrt = Math.sqrt(dx * dx + dz * dz);

            if (sqrt > 0.001D) {
                dashDirX = dx / sqrt;
                dashDirZ = dz / sqrt;

                if (mob.getControlledVehicle() != null) {
                    mob.getControlledVehicle().setSprinting(true);
                }

                mob.getNavigation().stop();
                mob.getMoveControl().strafe(0.0F, 0.0F);

                dashing = true;
                dashTicks = 0;
                dashCooldown = dashCooldownTicks;
            }
        }

        if (galloping) {
            --gallopTicks;

            if (gallopTicks <= 0) {
                Path path = mob.getNavigation().createPath(target, 0);

                if (path != null) {
                    mob.getNavigation().moveTo(path, 2.2D);
                    mob.getMoveControl().strafe(0.0F, 0.0F);
                    dashing = true;
                    dashTicks = 0;
                    dashCooldown = dashCooldownTicks;
                }
                if (mob.getControlledVehicle() != null) {
                    mob.getControlledVehicle().setSprinting(true);
                }
                galloping = false;
            }
        }

        if (dashing) {
            tickDash();
            return;
        }

        if (!(distanceSqr > rangeSqr)) {
            mob.getNavigation().stop();

            if (canStrafe) {
                ++strafingTime;
            }
        } else {
            double moveSpeed = mob.isPassenger() ? 1.6D : 1.25D;

            mob.getMoveControl().strafe(0.0F, 0.0F);
            mob.getNavigation().moveTo(target, moveSpeed);
            strafingTime = -1;
        }

        if (canStrafe && strafingTime >= 20) {
            if ((double) mob.getRandom().nextFloat() < 0.3D) {
                strafingClockwise = !strafingClockwise;
            }

            if ((double) mob.getRandom().nextFloat() < 0.3D) {
                strafingBackwards = !strafingBackwards;
            }
            strafingTime = 0;
        }

        if (canStrafe && strafingTime > -1) {
            if (distanceSqr > (rangeSqr * 0.75F)) {
                strafingBackwards = false;
            } else if (distanceSqr < (rangeSqr * 0.25F)) {
                strafingBackwards = true;
            }

            float strafeSpeed = mob.isPassenger() ? 1.25F : 0.5F;
            mob.getMoveControl().strafe(strafingBackwards ? -strafeSpeed : strafeSpeed, strafingClockwise ? strafeSpeed : -strafeSpeed);

            if (mob.getControlledVehicle() instanceof Mob vehicle) {
                vehicle.lookAt(target, 30.0F, 30.0F);
            }
            mob.lookAt(target, 30.0F, 30.0F);
        } else {
            mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
        }

        if (mob.isUsingItem()) {
            if (!canSee && seeTime < -60) {
                mob.stopUsingItem();
            } else if (canSee) {
                if (mob.getTicksUsingItem() >= chargeTicks) {
                    mob.stopUsingItem();

                    ItemStack stack = getCleaver();

                    if (stack != null) {
                        faceTarget(target);
                        CleaverItem.throwCleaver(mob, stack, 1.25F, false);
                    }
                    attackCooldown = attackCooldownTicks;
                }
            }
        } else {
            if (distanceSqr <= rangeSqr) {
                if (attackCooldown > 0) {
                    --attackCooldown;
                }

                if (attackCooldown <= 0 && canSee) {
                    mob.startUsingItem(getCleaverHand());
                }
            }
        }
    }

    private void tickDash() {
        mob.getMoveControl().strafe(0.0F, 0.0F);

        if (!(mob.getControlledVehicle() instanceof Mob vehicle)) {
            dashing = false;
            return;
        }
        ++dashTicks;

        double speed = 1.66D;

        vehicle.setDeltaMovement(dashDirX * speed, vehicle.getDeltaMovement().y, dashDirZ * speed);
        vehicle.hasImpulse = true;
        LivingEntity target = mob.getTarget();

        if (target != null && target.isAlive() && mob.distanceToSqr(target) <= 4.0D) {
            vehicle.setSprinting(false);
            dashing = false;
            return;
        }

        if (dashTicks > 50) {
            vehicle.setSprinting(false);
            dashing = false;
            return;
        }

        for (Player player : mob.level().getEntitiesOfClass(Player.class, vehicle.getBoundingBox().inflate(0.4D))) {
            ResourceKey<DamageType> trampleDamageType = DDDamageTypes.TRAMPLED;

            if (mob.getControlledVehicle() instanceof Horse || mob.getControlledVehicle() instanceof ZombieHorse || mob.getControlledVehicle() instanceof SkeletonHorse) {
                trampleDamageType = DDDamageTypes.HORSE_TRAMPLED;
            }
            if (mob.getControlledVehicle() instanceof Donkey) {
                trampleDamageType = DDDamageTypes.DONKEY_TRAMPLED;
            }

            player.hurt(new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(trampleDamageType), mob), 8.0F);

            double dx = player.getX() - mob.getX();
            double dz = player.getZ() - mob.getZ();

            double len = Math.sqrt(dx * dx + dz * dz);

            if (len > 0.001D) {
                dx /= len;
                dz /= len;

                player.push(dx * 1.2D, 0.35D, dz * 1.2D);
                player.hurtMarked = true;
            }

            if (player.isBlocking()) {
                player.disableShield(true);

                vehicle.setSprinting(false);
                dashing = false;

                vehicle.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));

                for (Entity passengers : vehicle.getPassengers()) {
                    if (passengers instanceof LivingEntity living) {
                        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                    }
                }
                return;
            }
        }
    }

    private void faceTarget(LivingEntity target) {
        double dx = target.getX() - mob.getX();
        double dz = target.getZ() - mob.getZ();
        double dy = target.getEyeY() - mob.getEyeY();

        mob.setYRot((float) (Mth.atan2(dz, dx) * (180F / Math.PI)) - 90F);
        mob.setXRot((float) -(Mth.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180F / Math.PI)));
    }

    private ItemStack getCleaver() {
        if (mob == null) return null;

        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = mob.getItemInHand(hand);

            if (stack.getItem() instanceof CleaverItem) {
                return stack;
            }
        }
        return null;
    }

    private InteractionHand getCleaverHand() {
        if (mob == null) return InteractionHand.MAIN_HAND;

        for (InteractionHand hand : InteractionHand.values()) {
            if (mob.getItemInHand(hand).getItem() instanceof CleaverItem) {
                return hand;
            }
        }
        return InteractionHand.MAIN_HAND;
    }
}