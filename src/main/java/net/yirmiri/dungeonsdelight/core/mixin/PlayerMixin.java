package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Unique private static Random random = new Random();

    @Unique Player player = (Player) (Object) this;
    LivingEntity living = (LivingEntity) (Object) this;

    private int dashCount = 0;
    private byte recentlyDashed = 0;

    @Inject(at = @At("HEAD"), method = "tick")
    public void dungeonsdelight$tick(CallbackInfo ci) {
        if (!player.hasEffect(DDEffects.SWIFT_STEP)) {
            dashCount = 0;
            recentlyDashed = 0;
        }

        if (player.hasEffect(DDEffects.SWIFT_STEP)) {
            if (player.onGround()) {
                dashCount = player.getEffect(DDEffects.SWIFT_STEP).getAmplifier() + 1;
            }

            if (player.getDeltaMovement().y < 0) {
                if (player.isShiftKeyDown() && recentlyDashed == 0 && !player.getAbilities().flying && dashCount > 0 && !player.onClimbable() && !player.onGround()) {
                    double d = player.getX();
                    double e = player.getY() + (player.getBbHeight() / 2.0F);
                    double f = player.getZ();

                    player.level().explode(player, null, AbstractWindCharge.EXPLOSION_DAMAGE_CALCULATOR, d, e, f,
                            4.0F + ((float) player.getEffect(DDEffects.SWIFT_STEP).getAmplifier() / 2), false, Level.ExplosionInteraction.TRIGGER,
                            ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.WIND_CHARGE_BURST
                    );

                    DDUtil.knockbackNearbyEntities(player.level(), player, living);

                    DDUtil.dash(player);
                    --dashCount;
                    if (player.hasEffect(DDEffects.RAVENOUS_RUSH)) {
                        recentlyDashed = 10;
                    } else {
                        recentlyDashed = 20;
                        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, true, false, true));
                    }
                }
            }
        }
        if (recentlyDashed > 0) {
            --recentlyDashed;
        }
    }

    @Inject(at = @At("HEAD"), method = "hurt")
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(DDEffects.EXUDATION) && player.getAbsorptionAmount() > 0 && player.hurtTime == 0 && !player.getAbilities().invulnerable) {
            if (player.level() instanceof ServerLevel) {
                ((ServerLevel) player.level()).sendParticles(DDParticles.SKULL_HEART_BLAST.get(), player.getX(), player.getY() + 0.5, player.getZ(),
                        0, 0, 0, 0, 0);
            }

            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.WARDEN_SONIC_BOOM, SoundSource.NEUTRAL, 0.75F, 2.0F);

            DDUtil.skullHeartBlast(player.level(), player, player);
            player.hurtTime = 30;
        }
    }

    @Inject(at = @At("TAIL"), method = "attack")
    public void dungeonsdelight$attack(Entity entity, CallbackInfo ci) {
        if (player.hasEffect(DDEffects.DECISIVE)) {
            float amount = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
            double decisiveLevel = player.getEffect(DDEffects.DECISIVE).getAmplifier();
            DamageSource source = player.damageSources().playerAttack(player);

            if (20.0 + decisiveLevel != 0 && random.nextDouble(100.0) < (20.0 + decisiveLevel) && player.isAlive()) {
                entity.hurt(source, (amount * 1.75F));
                entity.playSound(DDSounds.DECISIVE_CRIT.get(), 1.0F, 1.0F);
                DDUtil.spreadParticles(DDParticles.DECISIVE_CRITICAL.get(), entity, random);

                if (player.hasEffect(DDEffects.VORACITY)) {
                    player.addEffect(new MobEffectInstance(DDEffects.RAVENOUS_RUSH, player.getEffect(DDEffects.VORACITY).getDuration() + 60, 0));
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "isHurt", cancellable = true)
    private void dungeonsdelight$canFoodHeal(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(DDEffects.TENACITY)) {
            cir.setReturnValue(false);
        }
    }
}
