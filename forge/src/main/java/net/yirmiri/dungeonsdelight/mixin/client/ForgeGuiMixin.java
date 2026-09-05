package net.yirmiri.dungeonsdelight.mixin.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Gui.class)
public class ForgeGuiMixin {
    @Shadow protected ItemStack lastToolHighlight;

    @ModifyVariable(method = "renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;I)V", at = @At("STORE"), ordinal = 0, remap = false)
    private MutableComponent dundelight$tryReplaceAppendColor(MutableComponent value) {
        return DDRarities.tryToAppendOrReplace(value, this.lastToolHighlight);
    }


}