package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.IHollowable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity> {
    @ModifyReturnValue(method = "isShaking", at = @At("RETURN"))
    private boolean dungeonsdelight$isConvert(boolean original, @Local(argsOnly = true) T entity) {
        return original || (entity instanceof IHollowable h && h.dungeonsdelight$isHollowing());
    }
}
