package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Boat.class)
public abstract class BoatMixin {
    @Shadow public abstract Boat.Type getVariant();

    @Inject(at = @At(value = "RETURN"), method = "getDropItem", cancellable = true)
    public void excessiveBuilding$getDropItem(CallbackInfoReturnable<Item> cir) {
        if (this.getVariant() == DDUtil.WORMWOOD_BOAT) {
            cir.setReturnValue(DDItems.WORMWOOD_BOAT.get());
        }
    }
}