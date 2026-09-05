package net.yirmiri.dungeonsdelight.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.common.util.DDGui;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class FabricGuiMixin {
    @Shadow private ItemStack lastToolHighlight;
    @Shadow @Final private Minecraft minecraft;
    @Shadow private int screenWidth;
    @Shadow private int screenHeight;

    @Shadow protected abstract Player getCameraPlayer();
    @Shadow protected abstract int getVisibleVehicleHeartRows(int vehicleHealth);
    @Shadow protected abstract int getVehicleMaxHearts(LivingEntity vehicle);
    @Shadow protected abstract LivingEntity getPlayerVehicleWithHealth();

    @ModifyVariable(method = "renderSelectedItemName", at = @At("STORE"), ordinal = 0)
    private MutableComponent dundelight$tryReplaceAppendColor(MutableComponent value) {
        return DDRarities.tryToAppendOrReplace(value, this.lastToolHighlight);
    }

    @Inject(method = "renderPlayerHealth", at = @At("TAIL"))
    private void dundelight$minerDownMINERDOWNXD(GuiGraphics guiGraphics, CallbackInfo ci) {
        DDGui.renderDiverDown(
                this.minecraft,
                guiGraphics,
                this.getCameraPlayer(),
                this.screenWidth,
                this.screenHeight,
                this.getVisibleVehicleHeartRows(this.getVehicleMaxHearts(this.getPlayerVehicleWithHealth()))
        );
    }
}