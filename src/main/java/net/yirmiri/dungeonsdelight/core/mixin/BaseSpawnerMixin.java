package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin {
    @Shadow private int spawnDelay;

    @Shadow private double oSpin;

    @Shadow private double spin;

    @Inject(method = "clientTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 1), cancellable = true)
    private void dungeonsdelight$replaceFlameParticle(Level level, BlockPos pos, CallbackInfo ci) {
        ci.cancel();
        RandomSource randomsource = level.getRandom();
        double d0 = (double) pos.getX() + randomsource.nextDouble();
        double d1 = (double) pos.getY() + randomsource.nextDouble();
        double d2 = (double) pos.getZ() + randomsource.nextDouble();
        level.addParticle(DDParticles.DUNGEON_FLAME.get(), d0, d1, d2, 0.0, 0.0, 0.0);
        if (this.spawnDelay > 0) {
            --this.spawnDelay;
        }

        this.oSpin = this.spin;
        this.spin = (this.spin + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0;
    }
}
