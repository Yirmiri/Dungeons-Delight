package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDTags;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    private static Random random = new Random();

    PlayerEntity player = (PlayerEntity) (Object) this;
    LivingEntity living = (LivingEntity) (Object) this;

    private int dashCount = 0;
    byte recentlyDashed = 0;

    @Inject(at = @At("HEAD"), method = "canConsume", cancellable = true)
    private void dungeonsdelight_canConsume(boolean ignoreHunger, CallbackInfoReturnable<Boolean> cir) {
        if (player.hasStatusEffect(DDEffects.BURROW_GUT) && !player.getStackInHand(player.getActiveHand()).isIn(DDTags.ItemT.MONSTER_FOODS)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void dungeonsdelight_tick(CallbackInfo ci) {
        if (player.hasStatusEffect(DDEffects.BREEZE_BLAST)) {
            if (player.isOnGround()) {
                dashCount = player.getStatusEffect(DDEffects.BREEZE_BLAST).getAmplifier() + 1;
            }

            if (player.getVelocity().y < 0) {
                if (player.isSneaking() && recentlyDashed == 0 && !player.getAbilities().flying && dashCount > 0 && !player.isClimbing() && !player.isOnGround()) {
                    double d = player.getX();
                    double e = player.getY() + (player.getHeight() / 2.0f);
                    double f = player.getZ();

                    player.getWorld().createExplosion(player, null, AbstractWindChargeEntity.EXPLOSION_BEHAVIOR, d, e, f, 4.0F, false, World.ExplosionSourceType.TRIGGER, ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
                    DDUtil.knockbackNearbyEntities(player.getWorld(), player, living);

                    DDUtil.dash(player);
                    --dashCount;
                    recentlyDashed = 20;
                }
            }
        }

        if (recentlyDashed > 0) {
            --recentlyDashed;
        }
    }

    @Inject(at = @At("TAIL"), method = "attack")
    public void dungeonsdelight_attack(Entity entity, CallbackInfo ci) {
        double luckAmount = player.getAttributeValue(EntityAttributes.GENERIC_LUCK);

        if (entity instanceof LivingEntity && player.hasStatusEffect(DDEffects.VORACITY)) {
            int voracityLevel = player.getStatusEffect(DDEffects.VORACITY).getAmplifier();

            if (random.nextDouble(100.0) < 32.0 + (luckAmount * 4) && player.isAlive()) {
                player.getHungerManager().add(2 + voracityLevel, 2.0F + voracityLevel);
                player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canFoodHeal", cancellable = true)
    private void dungeonsdelight_canFoodHeal(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasStatusEffect(DDEffects.TENACITY)) {
            cir.setReturnValue(false);
        }
    }
}