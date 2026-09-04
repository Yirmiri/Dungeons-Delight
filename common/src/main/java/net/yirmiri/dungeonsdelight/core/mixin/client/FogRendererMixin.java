package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FogType;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Inject(at = @At(value = "HEAD"), method = "setupFog", cancellable = true)
    private static void dungeonsdelight$setupFog(Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean b, float v, CallbackInfo ci) {
        if (camera.getEntity() instanceof Player player) {
            if (camera.getFluidInCamera() == FogType.LAVA && player.hasEffect(DDEffects.DIVER_DOWN.get()) && !player.hasEffect(RLMobEffects.BRIMSTONE_VISION.get())) {
                RenderSystem.setShaderFogStart(-8.0F);
                RenderSystem.setShaderFogEnd(farPlaneDistance * 0.2F);
                RenderSystem.setShaderFogShape(FogShape.SPHERE);

                ci.cancel();
            }
        }
    }
}