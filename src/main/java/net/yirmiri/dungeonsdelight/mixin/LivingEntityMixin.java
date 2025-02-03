package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Unique private static Random random = new Random();

    @Unique LivingEntity living = (LivingEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "hurt")
    private void dungeonsdelight$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(DDEffects.EXUDATION.get()) && living.getAbsorptionAmount() > 0) {
            DDUtil.skullHeartBlast(living.level(), living, living);
            living.hurtTime = 30;
// TODO: ADD COOL EFFECTS

//            if (living instanceof ServerPlayer serverPlayer) {
//                serverPlayer.sendParticles.sendPacket(new ParticleS2CPacket(DDParticles.SKULL_HEART_BLAST, true,
//                        serverPlayer.getX(), serverPlayer.getEyeY(), serverPlayer.getZ(),
//                        0, 0, 0, 0.5F, 1));
//
//                serverPlayer.networkHandler.sendPacket(new PlaySoundFromEntityS2CPacket(RegistryEntry.of(
//                        SoundEvents.ENTITY_WARDEN_SONIC_BOOM), SoundCategory.PLAYERS, serverPlayer, 0.3F, 1.0F, random.nextInt(24)));
//            }
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float dungeonsdelight$exudationDamage(float amount) {
        if (living.hasEffect(DDEffects.EXUDATION.get()) && living.getAbsorptionAmount() > 0) {
            return amount * 1.5F;
        }
        return amount;
    }
}
