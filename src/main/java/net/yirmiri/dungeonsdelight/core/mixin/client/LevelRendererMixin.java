package net.yirmiri.dungeonsdelight.core.mixin.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.particles.ParticleOptions;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Redirect(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 4))
    private void dungeonsdelight$replaceFireParticle(ClientLevel instance, ParticleOptions p_104706_, double x, double y, double z, double dx, double dy, double dz) {
        instance.addParticle(DDParticles.LIVING_FLAME.get(), x, y, z, dx, dy, dz);
    }
}
