package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.yirmiri.dungeonsdelight.common.util.DDMixinUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RangedBowAttackGoal.class)
public class ForgeRangedBowGoalMixin {
    @Shadow @Final private Mob mob;

    @Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
    private void dundelight$canUse(CallbackInfoReturnable<Boolean> cir) {
        DDMixinUtil.fixRangedAttack(this.mob, cir);
    }
}
