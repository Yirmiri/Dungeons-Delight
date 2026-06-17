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
    private boolean returningDash;
    private final int dashCooldownTicks;
    private int dashCooldown = 100;
    private int dashTicks;
    private boolean dashing;
    private double dashX;
    private double dashY;
    private double dashZ;
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

        if (mob.isPassenger() && dashCooldown <= 0 && distanceSqr <= 49.0D && mob.getRandom().nextInt(160) == 0) {
            if (mob.getControlledVehicle() instanceof AbstractHorse horse && (horse instanceof ZombieHorse || horse instanceof SkeletonHorse)) {
                horse.setSprinting(true);
                horse.playAmbientSound();
                galloping = true;
                gallopTicks = 20;
            } else {
                dashX = mob.getX();
                dashY = mob.getY();
                dashZ = mob.getZ();

                Path path = mob.getNavigation().createPath(target, 0);

                if (path != null) {
                    mob.getNavigation().moveTo(path, 2.2D);
                    dashing = true;
                    dashTicks = 0;
                    dashCooldown = dashCooldownTicks;
                }
            }
        }

        if (galloping) {
            --gallopTicks;

            if (gallopTicks <= 0) {
                dashX = mob.getX();
                dashY = mob.getY();
                dashZ = mob.getZ();

                Path path = mob.getNavigation().createPath(target, 0);

                if (path != null) {
                    mob.getNavigation().moveTo(path, 2.2D);
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
            mob.getNavigation().moveTo(target, 1.0D);
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

            mob.getMoveControl().strafe(
                    strafingBackwards ? -strafeSpeed : strafeSpeed,
                    strafingClockwise ? strafeSpeed : -strafeSpeed
            );

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
        if (!(mob.getControlledVehicle() instanceof Mob vehicle)) {
            dashing = false;
            returningDash = false;
            galloping = false;
            gallopTicks = 0;
            return;
        }

        ++dashTicks;

        if (!returningDash) {
            LivingEntity target = mob.getTarget();

            if (target == null || !target.isAlive()) {
                dashing = false;
                galloping = false;
                gallopTicks = 0;
                return;
            }

            if (mob.distanceToSqr(target) <= 4.0D) {
                Path returnPath = mob.getNavigation().createPath(dashX, dashY, dashZ, 0);

                if (returnPath != null) {
                    mob.getNavigation().moveTo(returnPath, 2.2D);
                    returningDash = true;
                } else {
                    dashing = false;
                    galloping = false;
                    gallopTicks = 0;
                }
            }
        } else {
            if (mob.getNavigation().isDone()) {
                dashing = false;
                returningDash = false;
                galloping = false;
                gallopTicks = 0;
                return;
            }
        }

        if (dashTicks > 120) {
            dashing = false;
            returningDash = false;
            if (mob.getControlledVehicle() != null) {
                mob.getControlledVehicle().setSprinting(true);
            }
            galloping = false;
            gallopTicks = 0;
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

                dashing = false;
                returningDash = false;
                galloping = false;
                gallopTicks = 0;

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