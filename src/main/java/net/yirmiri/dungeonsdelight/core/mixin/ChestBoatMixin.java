package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBoat.class)
public abstract class ChestBoatMixin extends Boat {
    public ChestBoatMixin(EntityType<? extends Boat> type, Level level) {
        super(type, level);
    }

    @Inject(at = @At(value = "RETURN"), method = "getDropItem", cancellable = true)
    public void dungeonsdelight$getDropItem(CallbackInfoReturnable<Item> cir) {
        if (this.getVariant() == DDUtil.WORMWOOD_BOAT) {
            cir.setReturnValue(DDItems.WORMWOOD_CHEST_BOAT.get());
        }
    }
}
