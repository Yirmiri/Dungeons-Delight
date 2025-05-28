package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Shadow @Final private Abilities abilities;
    @Shadow protected FoodData foodData;
    @Unique private static Random random = new Random();

    @Unique Player player = (Player) (Object) this;

//    @Inject(at = @At("HEAD"), method = "canEat", cancellable = true)
//    private void dungeonsdelight$canConsume(boolean canEat, CallbackInfoReturnable<Boolean> cir) {
//        if (player.hasEffect(DDEffects.BURROW_GUT.get()) || player.hasEffect(DDEffects.VORACITY.get())) {
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
            player.level().addParticle(DDParticles.SKULL_HEART_BLAST.get(),
                    player.getX(), player.getY(), player.getZ(), 0.0, 0.2, 0.0);

            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.WARDEN_SONIC_BOOM, SoundSource.NEUTRAL, 1.0F, 2.0F);

            DDUtil.skullHeartBlast(player.level(), player, player);
            player.hurtTime = 30;
        }

        if (player.hasEffect(DDEffects.EXUDATION) && player.getAbsorptionAmount() == 0) {
            player.removeEffect(DDEffects.EXUDATION);
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
