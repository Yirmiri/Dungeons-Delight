package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class DDMixinUtil {
    /** Thanks, Mojang! */
    public static <T extends Mob> void fixRangedAttack(T me, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && me.getTarget() != null && !me.getTarget().isAlive()) cir.setReturnValue(false);
    }
}
