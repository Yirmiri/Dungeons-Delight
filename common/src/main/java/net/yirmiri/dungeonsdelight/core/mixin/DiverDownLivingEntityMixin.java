package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.util.data.DiverDownData;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class DiverDownLivingEntityMixin extends DiverDownEntityMixin {
    @Shadow protected abstract boolean isAffectedByFluids();
    @Shadow public abstract boolean canStandOnFluid(FluidState fluidState);
    @Shadow public abstract boolean hasEffect(MobEffect effect);
    @Shadow public abstract Vec3 getFluidFallingAdjustedMovement(double gravity, boolean isFalling, Vec3 deltaMovement);
    @Shadow public abstract float getSpeed();
    @Shadow public abstract boolean onClimbable();

    @Inject(method = "tick", at = @At("TAIL"))
    private void dungeonsdelight$tick(CallbackInfo ci) {
        LivingEntity me = (LivingEntity)(Object)this;

        if (!this.hasEffect(DDEffects.DIVER_DOWN.get())) {
            this.dundel$lavaSwimming = false;
            if (!this.isInWater() && this.isSwimming()) this.setSwimming(false);
            return;
        }

        boolean creative = (me instanceof Player player && player.getAbilities().instabuild);
        if (creative) this.dundel$remainingCharge = DiverDownData.MAX_CHARGE;
        else if (this.isInLava()) {
            if (this.dundel$remainingCharge > 0) this.dundel$remainingCharge--;
        } else if (this.dundel$remainingCharge < DiverDownData.MAX_CHARGE) this.dundel$remainingCharge++;

        this.dundel$lavaSwimming = (this.isInLava() && this.isSprinting() && this.isEyeInFluid(FluidTags.LAVA) && (creative || this.dundel$remainingCharge > 0));

        if (this.dundel$lavaSwimming) this.setSwimming(true);
        else if (!this.isInWater()) this.setSwimming(false);
    }

    @Inject(
            method = "travel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;getFluidState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    // LivingEntity instance, Operation<Boolean> original, @Local(ordinal = 0) FluidState fluidstate, @Local(argsOnly = true, ordinal = 0) Vec3 travelVector
    private void dungeonsdelight$lavaTravel(Vec3 travelVector, CallbackInfo ci)
    {
        FluidState fluidstate = this.level().getFluidState(this.blockPosition());
        LivingEntity me = (LivingEntity)(Object)this;

        if (this.isInLava() && DiverDownData.isLavaSwimming(me) && this.isAffectedByFluids() && !this.canStandOnFluid(fluidstate)) {
            double d0 = 0.08;
            boolean flag = this.getDeltaMovement().y <= 0.0;
            if (flag && this.hasEffect(MobEffects.SLOW_FALLING)) d0 = 0.01;

            double d9 = this.getY();
            float f4 = this.isSprinting() ? 0.9F : 0.8F;
            float f5 = 0.02F;

            float f6 = (float) EnchantmentHelper.getDepthStrider(me);
            if (f6 > 1.0F) f6 = 1.0F;

            if (!this.onGround()) f6 *= 0.5F;

            if (f6 > 0.0F) {
                f4 += (0.54600006F - f4) * f6 / 3.0F;
                f5 += (this.getSpeed() - f5) * f6 / 3.0F;
            }

            this.moveRelative(f5, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            Vec3 vec36 = this.getDeltaMovement();
            if (this.horizontalCollision && this.onClimbable()) {
                vec36 = new Vec3(vec36.x, 0.2, vec36.z);
            }

            this.setDeltaMovement(vec36.multiply(f4, 0.800000011920929, f4));
            Vec3 vec32 = this.getFluidFallingAdjustedMovement(d0, flag, this.getDeltaMovement());
            this.setDeltaMovement(vec32);
            if (this.horizontalCollision && this.isFree(vec32.x, vec32.y + 0.6000000238418579 - this.getY() + d9, vec32.z)) {
                this.setDeltaMovement(vec32.x, 0.30000001192092896, vec32.z);
            }

            me.calculateEntityAnimation(me instanceof FlyingAnimal);

            ci.cancel();
        }
    }

    //this.moveRelative(0.02F, travelVector);
    //            this.move(MoverType.SELF, this.getDeltaMovement());
    //
    //Vec3 vec34;
    //            if (this.getFluidHeight(FluidTags.LAVA) <= this.getFluidJumpThreshold()) {
    //    this.setDeltaMovement(this.getDeltaMovement().multiply(0.5, 0.800000011920929, 0.5));
    //    vec34 = this.getFluidFallingAdjustedMovement(d0, flag, this.getDeltaMovement());
    //    this.setDeltaMovement(vec34);
    //} else {
    //    this.setDeltaMovement(this.getDeltaMovement().scale(0.5));
    //}
    //
    //            if (!this.isNoGravity()) this.setDeltaMovement(this.getDeltaMovement().add(0.0, -d0 / 4.0, 0.0));
    //
    //vec34 = this.getDeltaMovement();
    //            if (this.horizontalCollision && this.isFree(vec34.x, vec34.y + 0.6000000238418579 - this.getY() + d9, vec34.z)) {
    //    this.setDeltaMovement(vec34.x, 0.30000001192092896, vec34.z);
    //}

    //@Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    //private void dungeonsdelight$travel(Vec3 travelVector, CallbackInfo ci) {
    //    LivingEntity me = (LivingEntity)(Object)this;
    //
    //    //if (!dungeonsdelight$isLavaSwimming(me)) return;
    //
    //    float swimSpeed = me.isSprinting() ? 0.9F : 0.8F;
    //
    //    me.moveRelative(0.02F * DiverDownData.DIVER_DOWN_LAVA_SWIM_SPEED, travelVector);
    //    me.move(MoverType.SELF, me.getDeltaMovement());
    //
    //    Vec3 movement = me.getDeltaMovement();
    //
    //    if (me.horizontalCollision && me.onClimbable()) {
    //        movement = new Vec3(movement.x, 0.2D, movement.z);
    //    }
    //
    //    me.setDeltaMovement(movement.multiply(swimSpeed, 0.8D, swimSpeed));
    //
    //    Vec3 adjusted = me.getFluidFallingAdjustedMovement(0.08D, me.getDeltaMovement().y <= 0.0D, me.getDeltaMovement());
    //    me.setDeltaMovement(adjusted);
    //
    //    if (me.horizontalCollision && me.isFree(adjusted.x, adjusted.y + 0.6D - me.getY() + me.getY(), adjusted.z)) {
    //        me.setDeltaMovement(adjusted.x, 0.3D, adjusted.z);
    //    }
    //
    //    me.setSwimming(true);
    //    me.calculateEntityAnimation(me instanceof FlyingAnimal);
    //
    //    ci.cancel();
    //}

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity me = (LivingEntity)(Object)this;
        if (!me.hasEffect(DDEffects.DIVER_DOWN.get())) return;

        boolean creative = (me instanceof Player player && player.getAbilities().instabuild);
        if (!creative && this.dundel$remainingCharge <= 0) return;

        if (source.is(DamageTypeTags.IS_FIRE) || source.is(DamageTypes.LAVA)) cir.setReturnValue(false);
    }
}