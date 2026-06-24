package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.common.util.PouncingData;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(Player.class)
public class PlayerMixin {
    @Unique
    Player player = (Player) (Object) this;

    @Inject(method = "createAttributes", at = @At("TAIL"))
    private static void dungeonsdelight$createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(DDAttributes.THROWING_RANGE.get())
        ;
    }

    @Inject(at = @At("HEAD"), method = "attack")
    private void dungeonsdelight$attack(Entity target, CallbackInfo ci) {
        ItemStack mainhand = player.getMainHandItem();
        if (mainhand.is(DDItems.AMETHYST_ROCK_CANDY.get())) {
            Item tryme = null;

            if (!player.getCooldowns().isOnCooldown(DDItems.AMETHYST_ROCK_CANDY.get())) {
                if (target instanceof Silverfish silverfish) {
                    tryme = DDItems.CANDIED_SILVERFISH_SUCKER.get();
                    player.awardStat(Stats.ITEM_USED.get(mainhand.getItem()));

                    silverfish.handleEntityEvent(EntityEvent.POOF);
                    silverfish.playSound(SoundEvents.SILVERFISH_DEATH, 1.0F, 1.0F);
                    silverfish.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.0F);
                    silverfish.remove(Entity.RemovalReason.DISCARDED);
                }

                if (target instanceof Vex vex) {
                    tryme = DDItems.CANDIED_VEX_SUCKER.get();
                    player.awardStat(Stats.ITEM_USED.get(mainhand.getItem()));

                    vex.handleEntityEvent(EntityEvent.POOF);
                    vex.playSound(SoundEvents.VEX_DEATH, 1.0F, 1.0F);
                    vex.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.0F);
                    vex.remove(Entity.RemovalReason.DISCARDED);
                }

                if (tryme != null) {
                    if (!player.isCreative()) {
                        mainhand.shrink(1);
                        player.getCooldowns().addCooldown(DDItems.AMETHYST_ROCK_CANDY.get(), DungeonsDelight.CONFIG.getRockCandyPickupCooldownTicks());
                    }

                    if (mainhand.isEmpty()) player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(tryme));
                    else if (!player.getInventory().add(new ItemStack(tryme))) player.drop(new ItemStack(tryme), false);
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void dungeonsdelight$tick(CallbackInfo ci) {
        PouncingData data = PouncingData.get(player);
        MobEffectInstance effect = player.getEffect(DDEffects.POUNCING.get());
        int maxCharges = 1;

        if (effect == null) {
            data.charges = 0;
            data.cooldown = 0;
            data.pendingCooldown = 0;
            data.initialized = -1;
            data.touchedGround = false;
            data.leftGround = false;
            data.isPouncing = false;
            return;
        }

        if (data.initialized != maxCharges) {
            data.initialized = maxCharges;
            data.charges = maxCharges;
        }

        if (!player.onGround()) {
            data.leftGround = true;
            data.touchedGround = false;

            if (!player.isCrouching()) {
                data.isPouncing = false;
            }
        } else {
            data.isPouncing = false;

            if (data.leftGround && !data.touchedGround) {
                data.touchedGround = true;
                data.leftGround = false;

                if (data.pendingCooldown > 0) {
                    data.cooldown = data.pendingCooldown;
                    data.pendingCooldown = 0;
                }
            }
        }

        if (data.touchedGround && data.cooldown > 0) {
            data.cooldown--;

            if (data.cooldown <= 0) {
                data.charges = maxCharges;
                data.touchedGround = false;
            }
        }

        if (data.charges > maxCharges) {
            data.charges = maxCharges;
        }
    }

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void dungeonsdelight$aiStep(CallbackInfo ci) {
        PouncingData data = PouncingData.get(player);

        if (!player.hasEffect(DDEffects.POUNCING.get())) return;

        if (data.charges <= 0) return;
        if (!player.isCrouching()) return;
        if (player.onGround()) return;
        if (!data.leftGround) return;
        if (data.cooldown > 0) return;
        if (data.isPouncing) return;
        if (!player.canSprint()) return;

        MobEffectInstance effect = player.getEffect(DDEffects.POUNCING.get());
        MobEffectInstance jumpEffect = player.getEffect(MobEffects.JUMP);
        int level = effect == null ? 0 : effect.getAmplifier();
        int jumpLevel = jumpEffect == null ? 0 : jumpEffect.getAmplifier();

        double distanceMultiplier = 1.0D + (0.16D * level);
        double heightMultiplier = 1.0D + (0.08D * (level + jumpLevel));

        Vec3 look = player.getLookAngle().normalize();
        player.setDeltaMovement(look.x * DungeonsDelight.CONFIG.getPouncingDistance() * distanceMultiplier, DungeonsDelight.CONFIG.getPouncingHeight() * heightMultiplier, look.z * DungeonsDelight.CONFIG.getPouncingDistance() * distanceMultiplier);
        player.hasImpulse = true;
        player.resetFallDistance();
        player.playSound(SoundEvents.POWDER_SNOW_BREAK, 1.0F, 1.0F); //todo arty sound

        data.isPouncing = true;
        data.charges--;

        if (player.hasEffect(DDEffects.RAVENOUS_RUSH.get())) {
            data.pendingCooldown = DungeonsDelight.CONFIG.getPouncingRavenousCooldownTicks();
        } else {
            data.pendingCooldown = DungeonsDelight.CONFIG.getPouncingCooldownTicks();
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"))
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(DDEffects.HOMEWARD.get()) && !source.is(DDTags.DamageT.KEEPS_HOMEWARD)) {
            player.removeEffect(DDEffects.HOMEWARD.get());
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;heal(F)V"), method = "aiStep", cancellable = true)
    private void dungeonsdelight$canFoodHeal(CallbackInfo ci) {
        if (player.hasEffect(DDEffects.TENACITY.get())) {
            ci.cancel();
        }
    }
}
