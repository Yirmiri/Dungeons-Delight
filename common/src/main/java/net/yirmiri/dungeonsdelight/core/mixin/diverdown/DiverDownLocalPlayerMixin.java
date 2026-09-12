package net.yirmiri.dungeonsdelight.core.mixin.diverdown;

import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.tags.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class DiverDownLocalPlayerMixin extends DiverDownLivingEntityMixin {
    @Shadow public Input input;

    @Shadow protected abstract boolean hasEnoughFoodToStartSprinting();
    @Shadow public abstract boolean isUnderWater();

    @Unique private boolean dungeonsdelight$lavaSwimmingLastFrame = false;

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void dungeonsdelight$preLavaSwim(CallbackInfo ci) {
        this.dungeonsdelight$lavaSwimmingLastFrame = (this.isLavaSwimming() && this.isSwimming() && this.isSprinting());
    }

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void dungeonsdelight$postLavaSwim(CallbackInfo ci) {
        if (this.dungeonsdelight$lavaSwimmingLastFrame) {
            boolean hasenergy = this.input.hasForwardImpulse() && this.hasEnoughFoodToStartSprinting();

            if (hasenergy && this.isInLava() && this.isEyeInFluid(FluidTags.LAVA) && this.dundel$remainingCharge > 0) {
                this.setSprinting(true);
            }
        }
    }
}
