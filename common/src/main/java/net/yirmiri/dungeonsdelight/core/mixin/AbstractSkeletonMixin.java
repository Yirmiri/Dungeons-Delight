package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.entity.goal.CleaverAttackGoal;
import net.yirmiri.dungeonsdelight.core.registry.DDAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin extends Monster {
    public AbstractSkeletonMixin(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Inject(method = "createAttributes", at = @At("TAIL"))
    private static void dungeonsdelight$createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(DDAttributes.THROWING_RANGE.get(), 0.84D)
        ;
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void dungeonsdelight$registerGoals(CallbackInfo ci) {
        goalSelector.addGoal(4, new CleaverAttackGoal<>((AbstractSkeleton) (Object) this, 25, 25, 400));
    }
}