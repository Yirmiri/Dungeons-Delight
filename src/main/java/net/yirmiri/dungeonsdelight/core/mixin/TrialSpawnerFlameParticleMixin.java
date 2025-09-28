package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import net.yirmiri.dungeonsdelight.common.util.misc.TrialSpawnerFlameParticleAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TrialSpawner.FlameParticle.class)
public class TrialSpawnerFlameParticleMixin implements TrialSpawnerFlameParticleAccessor {
    @Mutable
    @Shadow @Final public SimpleParticleType particleType;

    @Override
    public void setParticleType(SimpleParticleType newType) {
        this.particleType = newType;
    }
}
