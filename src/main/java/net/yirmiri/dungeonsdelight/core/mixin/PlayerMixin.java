package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
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

import java.util.Objects;
import java.util.Random;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Shadow @Final private Abilities abilities;
    @Shadow protected FoodData foodData;
    @Unique private static Random random = new Random();

    @Unique Player player = (Player) (Object) this;

    @Inject(at = @At("HEAD"), method = "canEat", cancellable = true)
    private void dungeonsdelight$canConsume(boolean canEat, CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(DDEffects.BURROW_GUT.get()) || player.hasEffect(DDEffects.VORACITY.get())) {
            if (!player.getItemInHand(player.getUsedItemHand()).is(DDTags.ItemT.MONSTER_FOODS)) {
                cir.setReturnValue(false);
            }

            if (player.getItemInHand(player.getUsedItemHand()).is(DDTags.ItemT.MONSTER_FOODS)) {
                cir.setReturnValue(this.abilities.invulnerable || canEat || this.foodData.needsFood());
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "attack")
    public void dungeonsdelight$attack(Entity entity, CallbackInfo ci) {
        double luckAmount = player.getAttributeValue(Attributes.LUCK);

        if (entity instanceof LivingEntity && player.hasEffect(DDEffects.VORACITY.get())) {
            int voracityLevel = Objects.requireNonNull(player.getEffect(DDEffects.VORACITY.get())).getAmplifier();

            if (random.nextDouble(100.0) < 32.0 + (luckAmount * 4) && player.isAlive()) {
                player.getFoodData().eat(5 + voracityLevel, 0.8F + ((float) voracityLevel / 10));
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }

        if (player.hasEffect(DDEffects.DECISIVE.get())) {
            float amount = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
            double decisiveAmp = player.getEffect(DDEffects.DECISIVE.get()).getAmplifier();
            DamageSource source = player.damageSources().playerAttack(player);

            entity.hurt(source, (amount * 1.75F));
            entity.playSound(DDSounds.DECISIVE_CRIT.get(), 1.0F, 1.0F);

            if (20.0 + decisiveAmp != 0 && random.nextDouble(100.0) < (20.0 + decisiveAmp) && player.isAlive()) {
                if (entity.level().isClientSide) {
                    for (int i = 0; i < 8; i++) {
                        entity.level().addParticle(DDParticles.DECISIVE_CRITICAL.get(),
                                entity.getX() + 0.25,
                                entity.getY() + 1.25,
                                entity.getZ() + 0.25,
                                0.0, 0.0, 0.0);
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "isHurt", cancellable = true)
    private void dungeonsdelight$canFoodHeal(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(DDEffects.TENACITY.get())) {
            cir.setReturnValue(false);
        }
    }
}
