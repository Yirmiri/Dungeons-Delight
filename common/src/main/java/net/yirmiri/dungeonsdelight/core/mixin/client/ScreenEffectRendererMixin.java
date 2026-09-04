package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {

	@Inject(at = @At("HEAD"), method = "renderFire")
	private static void dungeonsdelight$renderFire(Minecraft minecraft, PoseStack poseStack, CallbackInfo ci) {
		LocalPlayer player = Minecraft.getInstance().player;

		if (player != null && player.hasEffect(DDEffects.DIVER_DOWN.get()) && !player.hasEffect(RLMobEffects.BRIMSTONE_VISION.get()))
			poseStack.translate(0, -0.25, 0);
	}
}