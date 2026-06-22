package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.registry.DDAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    @Unique
    LocalPlayer player = (LocalPlayer) (Object) this;

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void dungeonsdelight$airControl(CallbackInfo ci) {
        if (player.getAttribute(DDAttributes.AIR_CONTROL.get()) == null) return;
        if (player.onGround()) return;

        float forward = player.input.forwardImpulse;
        float strafe = player.input.leftImpulse;

        if (forward != 0.0F && strafe != 0.0F) {
            player.setDeltaMovement(player.getDeltaMovement().add(new Vec3(strafe, 0.0D, forward).yRot((float) -Math.toRadians(player.getYRot()))
                    .normalize().scale(player.getAttributeValue(DDAttributes.AIR_CONTROL.get()) / 10)));
        }
    }
}