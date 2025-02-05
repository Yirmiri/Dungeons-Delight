package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.Random;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Unique private static Random random = new Random();

    @Unique LivingEntity living = (LivingEntity) (Object) this;
    @Shadow private Optional<BlockPos> lastClimbablePos;

    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
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

    @Inject(at = @At("HEAD"), method = "onClimbable", cancellable = true)
    private void dungeonsdelight$onClimbable(CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockpos = living.blockPosition();

        if (living.hasEffect(DDEffects.POUNCING.get()) && living.horizontalCollision && living.isCrouching()) {
            lastClimbablePos = Optional.of(blockpos);
            cir.setReturnValue(true);
        }
    }
//SLIDE DOWN BLOCK CODE (future to do thing)
//    @Inject(at = @At("HEAD"), method = "tick")
//    private void dungeonsdelight$tick(CallbackInfo ci) {
//        if (living.hasEffect(DDEffects.POUNCING.get()) && living.horizontalCollision && living.isCrouching() && !living.onGround()) {
//            Vec3 movement = living.getDeltaMovement();
//            if (movement.y < -0.37) {
//                double deltaMovement = -0.29 / movement.y;
//                living.setDeltaMovement(new Vec3(movement.x * deltaMovement, -0.29, movement.z * deltaMovement));
//            } else {
//                living.setDeltaMovement(new Vec3(movement.x, -0.29, movement.z));
//            }
//
//            for(int i = 0; i < 5; ++i) {
//                living.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, this.living.getFeetBlockState()),
//                        living.getX(), living.getY(), living.getZ(), 0.0, 0.0, 0.0);
//            }
//        }
//    }
}