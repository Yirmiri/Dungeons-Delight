package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDTags;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    private static Random random = new Random();

    PlayerEntity player = (PlayerEntity) (Object) this;
//TODO: Breezy Blast full air mobility
    @Inject(at = @At("HEAD"), method = "canConsume", cancellable = true)
    private void dungeonsdelight_canConsume(boolean ignoreHunger, CallbackInfoReturnable<Boolean> cir) {
        if (player.hasStatusEffect(DDEffects.BURROW_GUT) && !player.getStackInHand(player.getActiveHand()).isIn(DDTags.ItemT.MONSTER_FOODS)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("TAIL"), method = "attack")
    public void dungeonsdelight_attack(Entity entity, CallbackInfo ci) {
        int voracityLevel = player.getStatusEffect(DDEffects.VORACITY).getAmplifier();
        double luckAmount = player.getAttributeValue(EntityAttributes.GENERIC_LUCK);

        if (entity instanceof LivingEntity && player.hasStatusEffect(DDEffects.VORACITY)) {
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