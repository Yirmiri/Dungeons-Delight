package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    private int dashCount = 0;
    byte recentlyDashed = 0;

    PlayerEntity player = (PlayerEntity) (Object) this;
    LivingEntity living = (LivingEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "tick")
    public void dungeonsdelight_tick(CallbackInfo ci) {
        if (player.hasStatusEffect(DDEffects.BREEZE_BOLT)) {
            if (player.isOnGround()) {
                dashCount = player.getStatusEffect(DDEffects.BREEZE_BOLT).getAmplifier() + 1;
            }

            if (player.getVelocity().y < 0) {
                if (MinecraftClient.getInstance().options.sneakKey.isPressed() && recentlyDashed == 0 && !player.getAbilities().flying && dashCount > 0 && !player.isClimbing() && !player.isOnGround()) {
                    double d = player.getX();
                    double e = player.getY() + (player.getHeight() / 2.0f);
                    double f = player.getZ();

                    player.getWorld().createExplosion(player, null, AbstractWindChargeEntity.EXPLOSION_BEHAVIOR, d, e, f, 1.2f, false, World.ExplosionSourceType.TRIGGER, ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);

                    DDUtil.knockbackNearbyEntities(player.getWorld(), player, living);
                    DDUtil.dash(player);
                    --dashCount;
                    recentlyDashed = 20;
                }
            }
        }

        if (recentlyDashed > 0) {
            --recentlyDashed;
        }
    }
}
