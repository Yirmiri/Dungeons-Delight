package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.block.spawner.MobSpawnerLogic;
import net.yirmiri.dungeonsdelight.registry.DDParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(MobSpawnerLogic.class)
public abstract class SpawnerBlockMixin {

    @ModifyArgs(method = "clientTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", ordinal = 1))
    private void dungeonsdelight_replaceFlameParticle(Args args) {
        args.set(0, DDParticles.DUNGEON_FLAME);
    }
}
