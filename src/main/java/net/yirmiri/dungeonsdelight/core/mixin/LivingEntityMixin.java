package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Unique LivingEntity living = (LivingEntity) (Object) this;
    @Shadow private Optional<BlockPos> lastClimbablePos;

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

        if (living.hasEffect(DDEffects.POUNCING.get()) && living.horizontalCollision && !living.isCrouching()) {
            lastClimbablePos = Optional.of(blockpos);
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void dungeonsdelight$tick(CallbackInfo ci) {
        if (living.hasEffect(DDEffects.POUNCING.get()) && living.horizontalCollision && living.isCrouching()) {
            Vec3 movement = living.getDeltaMovement();
            if (movement.y < -0.37) {
                double deltaMovement = -0.29 / movement.y;
                living.setDeltaMovement(new Vec3(movement.x * deltaMovement, -0.29, movement.z * deltaMovement));
            } else {
                living.setDeltaMovement(new Vec3(movement.x, -0.29, movement.z));
            }

            for(int i = 0; i < 5; ++i) {
                living.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, this.living.getFeetBlockState()),
                        living.getX(), living.getY(), living.getZ(), 0.0, 0.0, 0.0);
            }
            living.resetFallDistance();
        }
    }
}