package net.yirmiri.dungeonsdelight.core.mixin.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Gui.class)
public class FabricGuiMixin {
    @Shadow private ItemStack lastToolHighlight;

    @ModifyVariable(method = "renderSelectedItemName", at = @At("STORE"), ordinal = 0)
    private MutableComponent dundelight$tryReplaceAppendColor(MutableComponent value) {
        return DDRarities.tryToAppendOrReplace(value, this.lastToolHighlight);
    }
}