package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
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
public abstract class DiverDownLivingEntityMixin extends DiverDownEntityMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void dungeonsdelight$tick(CallbackInfo ci) {
        LivingEntity me = (LivingEntity)(Object)this;

        if (!me.hasEffect(DDEffects.DIVER_DOWN.get())) {
            this.dundel$lavaSwimming = false;
            if (!me.isInWater() && me.isSwimming()) me.setSwimming(false);
            return;
        }
        boolean creative = (me instanceof Player player && player.getAbilities().instabuild);

        if (creative) this.dundel$remainingCharge = DiverDownData.MAX_CHARGE;
        else if (me.isInLava()) {
            if (this.dundel$remainingCharge > 0) this.dundel$remainingCharge--;
        } else if (this.dundel$remainingCharge < DiverDownData.MAX_CHARGE) this.dundel$remainingCharge++;

        this.dundel$lavaSwimming = (me.isInLava() && me.isSprinting() && me.isEyeInFluid(FluidTags.LAVA) && (creative || this.dundel$remainingCharge > 0));

        if (this.dundel$lavaSwimming) me.setSwimming(true);
        else if (!me.isInWater()) me.setSwimming(false);
    }

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$travel(Vec3 travelVector, CallbackInfo ci) {
        LivingEntity me = (LivingEntity)(Object)this;

        if (!dungeonsdelight$isLavaSwimming(me)) return;

        float swimSpeed = me.isSprinting() ? 0.9F : 0.8F;

        me.moveRelative(0.02F * DiverDownData.DIVER_DOWN_LAVA_SWIM_SPEED, travelVector);
        me.move(net.minecraft.world.entity.MoverType.SELF, me.getDeltaMovement());

        Vec3 movement = me.getDeltaMovement();

        if (me.horizontalCollision && me.onClimbable()) {
            movement = new Vec3(movement.x, 0.2D, movement.z);
        }

        me.setDeltaMovement(movement.multiply(swimSpeed, 0.8D, swimSpeed));

        Vec3 adjusted = me.getFluidFallingAdjustedMovement(0.08D, me.getDeltaMovement().y <= 0.0D, me.getDeltaMovement());
        me.setDeltaMovement(adjusted);

        if (me.horizontalCollision && me.isFree(adjusted.x, adjusted.y + 0.6D - me.getY() + me.getY(), adjusted.z)) {
            me.setDeltaMovement(adjusted.x, 0.3D, adjusted.z);
        }

        me.setSwimming(true);
        me.calculateEntityAnimation(me instanceof FlyingAnimal);

        ci.cancel();
    }

    @Unique
    private boolean dungeonsdelight$isLavaSwimming(LivingEntity entity) {
        LivingEntity me = (LivingEntity)(Object)this;

        if (entity != me) return false;
        if (!me.hasEffect(DDEffects.DIVER_DOWN.get())) return false;

        boolean creative = me instanceof Player player && player.getAbilities().instabuild;
        if (!creative && this.dundel$remainingCharge <= 0) return false;

        return me.isInLava() && me.isSprinting() && me.getFluidHeight(FluidTags.LAVA) >= 0.4F;
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity me = (LivingEntity)(Object)this;

        if (!me.hasEffect(DDEffects.DIVER_DOWN.get())) return;

        boolean creative = (me instanceof Player player && player.getAbilities().instabuild);

        if (!creative && this.dundel$remainingCharge <= 0) return;
        if (source.is(DamageTypeTags.IS_FIRE) || source.is(DamageTypes.LAVA)) cir.setReturnValue(false);
    }
}