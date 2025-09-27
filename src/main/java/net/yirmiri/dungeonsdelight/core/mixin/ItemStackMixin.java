package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.common.item.BiteableItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "split", at = @At("RETURN"), cancellable = true)
    private void dungeonsdelight$split(int amount, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack original = (ItemStack) (Object) this;
        ItemStack splitStack = cir.getReturnValue();

        if (original.getItem() instanceof BiteableItem) {
            splitStack.setDamageValue(original.getDamageValue());
            cir.setReturnValue(splitStack);
        }
    }
}
