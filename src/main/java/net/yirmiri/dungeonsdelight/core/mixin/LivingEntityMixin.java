package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.entity.misc.CleaverEntity;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Unique LivingEntity living = (LivingEntity) (Object) this;
    @Shadow private Optional<BlockPos> lastClimbablePos;

    @Shadow public abstract ItemStack getMainHandItem();

    @Unique private static Random random = new Random();

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float dungeonsdelight$modifyDamage(float amount) {
        if (living.hasEffect(DDEffects.EXUDATION.get()) && living.getAbsorptionAmount() > 0) {
            return amount * 1.5F;
        }
        return amount;
    }

    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getEntity();
        if (attacker instanceof Player player && player.hasEffect(DDEffects.VORACITY.get())) {
            int voracityLevel = player.getEffect(DDEffects.VORACITY.get()).getAmplifier();

            player.getFoodData().eat(getVoracityRefillAmount(player, amount), 0.3F + ((float) voracityLevel / 10));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 1.0F, 1.0F);
            DDUtil.spreadParticles(DDParticles.DECISIVE_CRITICAL.get(), living, random);
        }

        if (living instanceof MonsterYamEntity && source.is(DamageTypeTags.IS_DROWNING)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "createWitherRose")
    private void dungeonsdelight$createWitherRose(LivingEntity attacker, CallbackInfo ci) {
        if (attacker != null && attacker.hasEffect(DDEffects.VORACITY.get())) {
            attacker.addEffect(new MobEffectInstance(DDEffects.RAVENOUS_RUSH.get(), 100, 0));
            DDUtil.spreadParticles(DDParticles.DECISIVE_CRITICAL.get(), living, random);
        }
    }

    private int getVoracityRefillAmount(LivingEntity living, float amount) {
        if ((amount / 2) < 1) {
            return 1;
        } else if ((living.getEffect(DDEffects.VORACITY.get()).getAmplifier() + 4) > amount) {
            return (int) (amount / 2);
        } else return (living.getEffect(DDEffects.VORACITY.get()).getAmplifier() + 4);
    }

    @Inject(at = @At("HEAD"), method = "isDamageSourceBlocked", cancellable = true)
    private void dungeonsdelight$isDamageSourceBlocked(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (source.getDirectEntity() instanceof CleaverEntity) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "canDisableShield", cancellable = true)
    private void dungeonsdelight$canDisableShield(CallbackInfoReturnable<Boolean> cir) {
        if (this.getMainHandItem().getItem() instanceof CleaverItem) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "onClimbable", cancellable = true)
    private void dungeonsdelight$onClimbable(CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockpos = living.blockPosition();

        if (living.hasEffect(DDEffects.POUNCING.get()) && living.horizontalCollision && !living.isCrouching()) {
            lastClimbablePos = Optional.of(blockpos);
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void dungeonsdelight$tick(CallbackInfo ci) {
        if (living.hasEffect(DDEffects.POUNCING.get()) && living.horizontalCollision && living.isCrouching()) {
            Vec3 movement = living.getDeltaMovement();
            if (movement.y < -0.37) {
                double deltaMovement = -0.29 / movement.y;
                living.setDeltaMovement(new Vec3(movement.x * deltaMovement, -0.29, movement.z * deltaMovement));
            } else {
                living.setDeltaMovement(new Vec3(movement.x, -0.29, movement.z));
            }

            for(int i = 0; i < 5; ++i) {
                living.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, this.living.getFeetBlockState()),
                        living.getX(), living.getY(), living.getZ(), 0.0, 0.0, 0.0);
            }
            living.resetFallDistance();
        }
    }
}