package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectUtil.class)
public class MobEffectUtilMixin {

    @Inject(at = @At("HEAD"), method = "getDigSpeedAmplification", cancellable = true)
    private static void dungeonsdelight$getDigSpeedAmplification(LivingEntity living, CallbackInfoReturnable<Integer> cir) {
        if (living.hasEffect(DDEffects.BURROW_GUT) && living.hasEffect(DDEffects.RAVENOUS_RUSH)) {
            cir.setReturnValue((living.getEffect(DDEffects.RAVENOUS_RUSH).getDuration() / 10) + living.getEffect(DDEffects.RAVENOUS_RUSH).getAmplifier());
        }
    }

    @Inject(at = @At("HEAD"), method = "hasDigSpeed", cancellable = true)
    private static void dungeonsdelight$hasDigSpeed(LivingEntity living, CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(DDEffects.BURROW_GUT) && living.hasEffect(DDEffects.RAVENOUS_RUSH)) {
            cir.setReturnValue(true);
        }
    }
}
