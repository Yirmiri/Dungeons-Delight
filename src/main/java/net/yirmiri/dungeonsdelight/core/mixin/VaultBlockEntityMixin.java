package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VaultBlockEntity.Client.class)
public class VaultBlockEntityMixin {

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/core/particles/ParticleTypes;SMALL_FLAME:Lnet/minecraft/core/particles/SimpleParticleType;"))
    private static SimpleParticleType dungeonsDelight$smallFlameTick() {
        if (DDConfigCommon.VAULTS_EMIT_GREEN_FLAMES.get()) {
            return DDParticles.LIVING_FLAME.get();
        }
        else return ParticleTypes.SMALL_FLAME;
    }

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/core/particles/ParticleTypes;SOUL_FIRE_FLAME:Lnet/minecraft/core/particles/SimpleParticleType;"))
    private static SimpleParticleType dungeonsDelight$soulFlameTick() {
        if (DDConfigCommon.VAULTS_EMIT_GREEN_FLAMES.get()) {
            return DDParticles.SPIRIT_FLAME.get();
        }
        else return ParticleTypes.SOUL_FIRE_FLAME;
    }
}
