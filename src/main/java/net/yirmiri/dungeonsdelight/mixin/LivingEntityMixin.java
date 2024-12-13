package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.Difficulty;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.registry.DDParticles;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    private static Random random = new Random();

    LivingEntity living = (LivingEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "damage")
    private void dungeonsdelight_damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (living.hasStatusEffect(DDEffects.EXUDATION) && living.getAbsorptionAmount() > 0) {
            DDUtil.skullHeartBlast(living.getWorld(), living, living);
            living.timeUntilRegen = 30;

            if (living instanceof ServerPlayerEntity serverPlayerEntity) {
                serverPlayerEntity.networkHandler.sendPacket(new ParticleS2CPacket(DDParticles.SKULL_HEART_BLAST, true,
                        serverPlayerEntity.getX(), serverPlayerEntity.getEyeY(), serverPlayerEntity.getZ(),
                        0, 0, 0, 0.5F, 1));

                serverPlayerEntity.networkHandler.sendPacket(new PlaySoundFromEntityS2CPacket(RegistryEntry.of(
                        SoundEvents.ENTITY_WARDEN_SONIC_BOOM), SoundCategory.PLAYERS, serverPlayerEntity, 0.3F, 1.0F, random.nextInt(24)));
            }
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "damage", argsOnly = true)
    public float dungeonsdelight_exudationDamage(float amount) {
        var difficulty = living.getWorld().getDifficulty();

        if (living.hasStatusEffect(DDEffects.EXUDATION) && living.getAbsorptionAmount() > 0) {
            if (difficulty.equals(Difficulty.HARD)) {
                return amount * 1.75F;
            } else if (difficulty.equals(Difficulty.NORMAL)) {
                return amount * 1.5F;
            } else return amount * 1.25F;
        }
        return amount;
    }
}
