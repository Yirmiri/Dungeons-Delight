package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.entity.goal.CleaverAttackGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {
    public ZombieMixin(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void dungeonsdelight$registerGoals(CallbackInfo ci) {
        goalSelector.addGoal(4, new CleaverAttackGoal<>((Zombie) (Object) this, 30, 30, 200));
    }
}