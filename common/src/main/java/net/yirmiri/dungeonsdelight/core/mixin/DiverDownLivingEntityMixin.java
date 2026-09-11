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

    @Override
    protected boolean dundelight$canLavaSwim() {
        if (this.hasEffect(DDEffects.DIVER_DOWN.get())) {
            LivingEntity me = (LivingEntity)(Object)this;
            boolean inLava = this.level().getFluidState(this.blockPosition()).is(FluidTags.LAVA);
            boolean wasInlava = (this.isInLava() || this.dungeonsdelight$wasTouchingLava);

            boolean creative = (me instanceof Player player && player.isCreative());

            if (creative) this.dundel$remainingCharge = DiverDownData.MAX_CHARGE;
            else if (inLava) {
                if (this.dundel$remainingCharge > 0) this.dundel$remainingCharge--;
            }
            else if (this.dundel$remainingCharge < DiverDownData.MAX_CHARGE) this.dundel$remainingCharge++;

            if (this.dundel$lavaSwimming) {
                this.dundel$lavaSwimming = (this.isSprinting() && wasInlava && !this.isPassenger() && this.dundel$remainingCharge > 0);
                // TODO: check miri dms involving fix via localplayer mixin xdxdxdxdxd
            } else {
                this.dundel$lavaSwimming = (this.isSprinting() && inLava && this.isEyeInFluid(FluidTags.LAVA) && !this.isPassenger() && this.dundel$remainingCharge > 0);
            }

            return (this.dundel$lavaSwimming);
        }
        else return super.dundelight$canLavaSwim();
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

            // This has to be calculated here since the rest of the loop is getting returned early
            me.calculateEntityAnimation(me instanceof FlyingAnimal);

            ci.cancel();
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity me = (LivingEntity)(Object)this;

        if ((source.is(DamageTypeTags.IS_FIRE) || source.is(DamageTypes.LAVA)) && me.hasEffect(DDEffects.DIVER_DOWN.get())) {
            boolean creative = (me instanceof Player player && player.getAbilities().instabuild);
            if (!creative && this.dundel$remainingCharge <= 0) return;

            cir.setReturnValue(false);
        }
    }
}