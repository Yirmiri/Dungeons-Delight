package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Unique private static Random random = new Random();

    @Unique Player player = (Player) (Object) this;

//    @Inject(at = @At("HEAD"), method = "canEat", cancellable = true)
//    private void dungeonsdelight$canConsume(boolean canEat, CallbackInfoReturnable<Boolean> cir) {
//        if (player.hasEffect(DDEffects.BURROW_GUT) || player.hasEffect(DDEffects.VORACITY)) {
//            if (!player.getItemInHand(player.getUsedItemHand()).is(DDTags.ItemT.MONSTER_FOODS)) {
//                cir.setReturnValue(false);
//            }
//
//            if (player.getItemInHand(player.getUsedItemHand()).is(DDTags.ItemT.MONSTER_FOODS)) {
//                cir.setReturnValue(this.abilities.invulnerable || canEat || this.foodData.needsFood());
//            }
//        }
//    }

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

//        if (player.hasEffect(DDEffects.EXUDATION) && player.getAbsorptionAmount() == 0) {
//            player.removeEffect(DDEffects.EXUDATION);
//        }
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

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float dungeonsdelight$pouncingHurt(float amount, DamageSource source) {
        if (player.hasEffect(DDEffects.POUNCING) && source.is(DamageTypeTags.IS_FALL)) {
            int amplifier = player.getEffect(DDEffects.POUNCING).getAmplifier();
            float reduced = amount * (1.0F - 0.20F + 0.05F * amplifier);
            if (reduced < 1.0F) {
                return 0.0F;
            }
            return reduced;
        }
        return amount;
    }

    @Inject(at = @At("HEAD"), method = "isHurt", cancellable = true)
    private void dungeonsdelight$canFoodHeal(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(DDEffects.TENACITY)) {
            cir.setReturnValue(false);
        }
    }
}
