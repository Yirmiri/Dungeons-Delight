package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.util.data.DiverDownData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class DiverDownEntityMixin implements DiverDownData {
    @Shadow public abstract boolean isInLava();
    @Shadow public abstract boolean isInWater();
    @Shadow public abstract boolean isSwimming();
    @Shadow public abstract void setSwimming(boolean swimming);
    @Shadow public abstract boolean isSprinting();
    @Shadow public abstract boolean isEyeInFluid(TagKey<Fluid> fluidTag);
    @Shadow public abstract double getY();
    @Shadow public abstract void moveRelative(float amount, Vec3 relative);
    @Shadow public abstract Vec3 getDeltaMovement();
    @Shadow public abstract void move(MoverType type, Vec3 pos);
    @Shadow public abstract double getFluidHeight(TagKey<Fluid> fluidTag);
    @Shadow public abstract double getFluidJumpThreshold();
    @Shadow public abstract void setDeltaMovement(Vec3 deltaMovement);
    @Shadow public abstract void setDeltaMovement(double x, double y, double z);
    @Shadow public abstract boolean isNoGravity();
    @Shadow public boolean horizontalCollision;
    @Shadow public abstract boolean isFree(double x, double y, double z);
    @Shadow public abstract boolean onGround();

    @Shadow public abstract Level level();

    @Shadow public abstract BlockPos blockPosition();

    @Unique protected int dundel$remainingCharge = DiverDownData.MAX_CHARGE;
    @Unique protected boolean dundel$lavaSwimming;

    @Override public int getCharge() { return this.dundel$remainingCharge; }
    @Override public void setCharge(int charge) { this.dundel$remainingCharge = Math.max(0, Math.min(DiverDownData.MAX_CHARGE, charge)); }
    @Override public boolean isLavaSwimming() { return this.dundel$lavaSwimming; }
    @Override public void setLavaSwimming(boolean swimming) { this.dundel$lavaSwimming = swimming; }

    @Inject(method = "saveWithoutId", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V",
            ordinal = 0,
            shift = At.Shift.AFTER
    ))
    private void dungeonsdelight$addAdditionalSaveData(CompoundTag compound, CallbackInfoReturnable<CompoundTag> cir) {
        compound.putInt(DiverDownData.DIVER_DOWN_CHARGE, this.dundel$remainingCharge);
        compound.putBoolean(DiverDownData.DIVER_DOWN_LAVA_SWIMMING, this.dundel$lavaSwimming);
    }

    @Inject(method = "load", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V",
            ordinal = 0,
            shift = At.Shift.AFTER
    ))
    private void dungeonsdelight$readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains(DiverDownData.DIVER_DOWN_CHARGE)) this.setCharge(tag.getInt(DiverDownData.DIVER_DOWN_CHARGE));
        if (tag.contains(DiverDownData.DIVER_DOWN_LAVA_SWIMMING)) this.setLavaSwimming(tag.getBoolean(DiverDownData.DIVER_DOWN_LAVA_SWIMMING));
    }
}
