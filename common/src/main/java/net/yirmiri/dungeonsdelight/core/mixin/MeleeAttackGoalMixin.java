package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MeleeAttackGoal.class)
public abstract class MeleeAttackGoalMixin {
    @Shadow
    protected PathfinderMob mob;

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$canUse(CallbackInfoReturnable<Boolean> cir) {
        if (mob instanceof Zombie zombie
                && zombie.getMainHandItem().getItem() instanceof CleaverItem) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canContinueToUse", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$canContinueToUse(CallbackInfoReturnable<Boolean> cir) {
        if (mob instanceof Zombie zombie
                && zombie.getMainHandItem().getItem() instanceof CleaverItem) {
            cir.setReturnValue(false);
        }
    }
}