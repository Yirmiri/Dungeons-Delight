package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TrialSpawnerState.ParticleEmission.class)
public interface TrialSpawnerStateMixin {

    @Redirect(method = "lambda$static$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/trialspawner/TrialSpawnerState$ParticleEmission;addParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/Level;)V"))
    private static void dungeonsDelight$lambda$static$1(SimpleParticleType particleType, Vec3 pos, Level level) {
        if (DDConfigCommon.TRIAL_SPAWNERS_EMIT_GREEN_FLAMES.get()) {
            if (particleType == ParticleTypes.FLAME || particleType == ParticleTypes.SMALL_FLAME) {
                level.addParticle(DDParticles.LIVING_FLAME.get(), pos.x(), pos.y(), pos.z(), 0.0, 0.0, 0.0);
            }

            if (particleType == ParticleTypes.SOUL_FIRE_FLAME) {
                level.addParticle(DDParticles.SPIRIT_FLAME.get(), pos.x(), pos.y(), pos.z(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Redirect(method = "lambda$static$2", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/trialspawner/TrialSpawnerState$ParticleEmission;addParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/Level;)V"))
    private static void dungeonsDelight$lambda$static$2(SimpleParticleType particleType, Vec3 pos, Level level) {
        if (DDConfigCommon.TRIAL_SPAWNERS_EMIT_GREEN_FLAMES.get()) {
            if (particleType == ParticleTypes.FLAME || particleType == ParticleTypes.SMALL_FLAME) {
                level.addParticle(DDParticles.LIVING_FLAME.get(), pos.x(), pos.y(), pos.z(), 0.0, 0.0, 0.0);
            }

            if (particleType == ParticleTypes.SOUL_FIRE_FLAME) {
                level.addParticle(DDParticles.SPIRIT_FLAME.get(), pos.x(), pos.y(), pos.z(), 0.0, 0.0, 0.0);
            }
        }
    }
}
