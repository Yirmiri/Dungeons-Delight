package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Unique
    Entity entity = (Entity) (Object) this;

    @Inject(at = @At("HEAD"), method = "dismountsUnderwater", cancellable = true)
    private void dungeonsdelight$dismountsUnderwater(CallbackInfoReturnable<Boolean> cir) {
        if (entity.getType() == EntityType.ZOMBIE_HORSE) {
            cir.setReturnValue(false);
        }
    }
}
