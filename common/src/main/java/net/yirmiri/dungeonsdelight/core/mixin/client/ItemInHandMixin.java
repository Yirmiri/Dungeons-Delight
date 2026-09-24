package net.yirmiri.dungeonsdelight.core.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.common.util.data.SquibTickData;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Extremely special thanks to Hecco (@hecco56 on GitHub) for this code!
@Mixin(ItemInHandRenderer.class)
public class ItemInHandMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow private ItemStack mainHandItem;
    @Shadow private ItemStack offHandItem;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getOffhandItem()Lnet/minecraft/world/item/ItemStack;", shift = At.Shift.AFTER))
    private void dungeonsdelight$creeperillaFixer(CallbackInfo ci) {
        LocalPlayer localplayer = minecraft.player;
        ItemStack itemstack = localplayer.getMainHandItem();
        ItemStack itemstack1 = localplayer.getOffhandItem();
        if (itemstack.is(DDItems.CREEPERILLA_SQUIB.get()) || itemstack1.is(DDItems.CREEPERILLA_SQUIB.get())) {
            if (dungeonsdelight$matchCreeperillas(mainHandItem, itemstack)) this.mainHandItem = itemstack;
            if (dungeonsdelight$matchCreeperillas(offHandItem, itemstack1)) this.offHandItem = itemstack1;
        }
    }

    @Unique
    private static boolean dungeonsdelight$matchCreeperillas(ItemStack oldStack, ItemStack newStack) {
        // if (!oldStack.has(DDComponents.SQUIB_DATA.get()) || !newStack.has(DDComponents.SQUIB_DATA.get())) todo 1.21.1
        if (!oldStack.getOrCreateTag().contains(SquibTickData.SQUIB_TAG) || !newStack.getOrCreateTag().contains(SquibTickData.SQUIB_TAG)) {
            return ItemStack.matches(oldStack, newStack);
        }

        ItemStack oldCopy = oldStack.copy();
        ItemStack newCopy = newStack.copy();

        oldCopy.getOrCreateTag().remove(SquibTickData.SQUIB_TAG);
        newCopy.getOrCreateTag().remove(SquibTickData.SQUIB_TAG);
        // todo 1.21.1
        //oldCopy.remove(DDComponents.SQUIB_DATA.get());
        //newCopy.remove(DDComponents.SQUIB_DATA.get());

        return ItemStack.matches(oldCopy, newCopy);
    }
}
