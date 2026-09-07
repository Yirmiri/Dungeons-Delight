package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.util.data.DiverDownData;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
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
    private static final float DIVER_DOWN_LAVA_SWIM_SPEED = 1.15F;

    @Unique
    private int remainingCharge = DiverDownData.MAX_CHARGE;

    @Unique
    private boolean lavaSwimming;

    @Override
    public int getCharge() {
        return remainingCharge;
    }

    @Override
    public void setCharge(int charge) {
        remainingCharge = Math.max(0, Math.min(DiverDownData.MAX_CHARGE, charge));
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

            if (!living.isInWater() && living.isSwimming()) {
                living.setSwimming(false);
            }
            return;
        }
        boolean creative = living instanceof Player player && player.getAbilities().instabuild;

        if (creative) {
            remainingCharge = DiverDownData.MAX_CHARGE;
        } else if (living.isInLava()) {
            if (remainingCharge > 0) {
                remainingCharge--;
            }
        } else if (remainingCharge < DiverDownData.MAX_CHARGE) {
            remainingCharge++;
        }
        lavaSwimming = living.isInLava() && living.isSprinting() && living.getFluidHeight(FluidTags.LAVA) >= 0.4F && (creative || remainingCharge > 0);

        if (lavaSwimming) {
            living.setSwimming(true);
        } else if (!living.isInWater()) {
            living.setSwimming(false);
        }
    }

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$travel(Vec3 travelVector, CallbackInfo ci) {
        if (!dungeonsdelight$isLavaSwimming(living)) {
            return;
        }

        float swimSpeed = living.isSprinting() ? 0.9F : 0.8F;

        living.moveRelative(0.02F * DIVER_DOWN_LAVA_SWIM_SPEED, travelVector);
        living.move(net.minecraft.world.entity.MoverType.SELF, living.getDeltaMovement());

        Vec3 movement = living.getDeltaMovement();

        if (living.horizontalCollision && living.onClimbable()) {
            movement = new Vec3(movement.x, 0.2D, movement.z);
        }

        living.setDeltaMovement(movement.multiply(swimSpeed, 0.8D, swimSpeed));

        Vec3 adjusted = living.getFluidFallingAdjustedMovement(0.08D, living.getDeltaMovement().y <= 0.0D, living.getDeltaMovement());
        living.setDeltaMovement(adjusted);

        if (living.horizontalCollision && living.isFree(adjusted.x, adjusted.y + 0.6D - living.getY() + living.getY(), adjusted.z)) {
            living.setDeltaMovement(adjusted.x, 0.3D, adjusted.z);
        }

        living.setSwimming(true);
        living.calculateEntityAnimation(living instanceof net.minecraft.world.entity.animal.FlyingAnimal);

        ci.cancel();
    }

    @Unique
    private boolean dungeonsdelight$isLavaSwimming(LivingEntity entity) {
        if (entity != living) return false;
        if (!living.hasEffect(DDEffects.DIVER_DOWN.get())) return false;

        boolean creative = living instanceof Player player && player.getAbilities().instabuild;
        if (!creative && remainingCharge <= 0) return false;

        return living.isInLava() && living.isSprinting() && living.getFluidHeight(FluidTags.LAVA) >= 0.4F;
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!living.hasEffect(DDEffects.DIVER_DOWN.get())) return;

        boolean creative = living instanceof Player player && player.getAbilities().instabuild;
        if (!creative && remainingCharge <= 0) return;

        if (source.is(DamageTypeTags.IS_FIRE) || source.is(DamageTypes.LAVA)) {
            cir.setReturnValue(false);
        }
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