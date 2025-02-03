package net.yirmiri.dungeonsdelight.mixin;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Random;

@Mixin(Player.class)
public class PlayerMixin {
    @Unique private static Random random = new Random();

    @Unique Player player = (Player) (Object) this;

    @Inject(at = @At("HEAD"), method = "canEat", cancellable = true)
    private void dungeonsdelight$canConsume(boolean canEat, CallbackInfoReturnable<Boolean> cir) {
        if ((player.hasEffect(DDEffects.BURROW_GUT.get()) || player.hasEffect(DDEffects.VORACITY.get())) && !player.getItemInHand(player.getUsedItemHand()).is(DDTags.ItemT.MONSTER_FOODS)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("TAIL"), method = "attack")
    public void dungeonsdelight$attack(Entity entity, CallbackInfo ci) {
        double luckAmount = player.getAttributeValue(Attributes.LUCK);

        if (entity instanceof LivingEntity && player.hasEffect(DDEffects.VORACITY.get())) {
            int voracityLevel = Objects.requireNonNull(player.getEffect(DDEffects.VORACITY.get())).getAmplifier();

            if (random.nextDouble(100.0) < 32.0 + (luckAmount * 4) && player.isAlive()) {
                player.getFoodData().eat(2 + voracityLevel, 2.0F + voracityLevel);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }

        if (player.hasEffect(DDEffects.DECISIVE.get())) {
            float amount = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
            DamageSource source = player.damageSources().playerAttack(player);
            double criticalStrikeChance = Objects.requireNonNull(player.getEffect(DDEffects.DECISIVE.get())).getAmplifier();

            if (13.3 + criticalStrikeChance != 0 && random.nextDouble(100.0) < (13.3 + criticalStrikeChance) && player.isAlive()) {
                entity.hurt(source, (amount * 1.25F));
                player.playSound(SoundEvents.PLAYER_ATTACK_CRIT, 1.0F, 1.0F);
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
