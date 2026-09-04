package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.util.data.DiverDownData;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class DiverDownLivingEntityMixin implements DiverDownData {
    @Unique
    LivingEntity living = (LivingEntity) (Object) this;

    @Unique
    private static final String DIVER_DOWN_CHARGE = "DiverDownCharge";

    @Unique
    private static final String DIVER_DOWN_LAVA_SWIMMING = "DiverDownLavaSwimming";

    @Unique
    private static final int MAX_CHARGE = 140;

    @Unique
    private int remainingCharge = MAX_CHARGE;

    @Unique
    private boolean lavaSwimming;

    @Override
    public int getCharge() {
        return remainingCharge;
    }

    @Override
    public void setCharge(int charge) {
        remainingCharge = Math.max(0, Math.min(MAX_CHARGE, charge));
    }

    @Override
    public boolean isLavaSwimming() {
        return lavaSwimming;
    }

    @Override
    public void setLavaSwimming(boolean swimming) {
        lavaSwimming = swimming;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void dungeonsdelight$tick(CallbackInfo ci) {
        if (!living.hasEffect(DDEffects.DIVER_DOWN.get())) {
            lavaSwimming = false;
            return;
        }

        if (living.isOnFire() || living.isInLava()) {
            if (remainingCharge > 0) {
                remainingCharge--;
            }
        } else if (!living.isOnFire() && remainingCharge < MAX_CHARGE) {
            remainingCharge++;
        }

        if (!lavaSwimming) {
            lavaSwimming = living.isSprinting() && living.isInLava() && remainingCharge > 0 && living.getFluidHeight(FluidTags.LAVA) >= 0.4F;
        } else {
            lavaSwimming = living.isSprinting() && remainingCharge > 0 && (living.isInLava() || living.getFluidHeight(FluidTags.LAVA) > 0.0D);
        }

        if (lavaSwimming) {
            living.setSwimming(true);
        } else if (living.isSwimming()) {
            living.setSwimming(false);
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!living.hasEffect(DDEffects.DIVER_DOWN.get())) return;
        if (remainingCharge <= 0) return;

        if (source.is(DamageTypeTags.IS_FIRE) || source.is(DamageTypes.LAVA)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$travel(Vec3 input, CallbackInfo ci) {
        if (!living.hasEffect(DDEffects.DIVER_DOWN.get())) return;
        if (remainingCharge <= 0) return;
        if (!living.isInLava()) return;
        if (!living.isSprinting()) return;

        living.setSwimming(true);

        float movementSpeed = living.getSpeed();

        living.moveRelative(movementSpeed, input);
        living.move(MoverType.SELF, living.getDeltaMovement());

        Vec3 movement = living.getDeltaMovement();
        float slowdown = 0.96F;
        movement = movement.multiply(slowdown, 0.8D, slowdown);

        if (living.horizontalCollision && living.isFree(movement.x, movement.y + 0.6D, movement.z)) {
            movement = new Vec3(movement.x, 0.3D, movement.z);
        }

        living.setDeltaMovement(movement);
        ci.cancel();
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        tag.putInt(DIVER_DOWN_CHARGE, remainingCharge);
        tag.putBoolean(DIVER_DOWN_LAVA_SWIMMING, lavaSwimming);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains(DIVER_DOWN_CHARGE)) {
            setCharge(tag.getInt(DIVER_DOWN_CHARGE));
        }

        if (tag.contains(DIVER_DOWN_LAVA_SWIMMING)) {
            setLavaSwimming(tag.getBoolean(DIVER_DOWN_LAVA_SWIMMING));
        }
    }
}