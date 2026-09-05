package net.yirmiri.dungeonsdelight.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.yirmiri.dungeonsdelight.common.util.DDGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeGui.class)
public abstract class ForgeGuiClassMixin {
    @Shadow public abstract Minecraft getMinecraft();

    @Inject(method = "renderHealth", at = @At("TAIL"), remap = false)
    private void dundelight$minerDownMINERDOWNXD(int width, int height, GuiGraphics guiGraphics, CallbackInfo ci) {
        if (this.getMinecraft().getCameraEntity() instanceof Player player) {
            // Reimpl stupid vehicle get healthbar stuff because modding sucks
            LivingEntity entity = null;
            if (player.getVehicle() instanceof LivingEntity l) entity = l;

            int vehicleHealth = 0;
            if (entity != null && entity.showVehicleHealth()) {
                float f = entity.getMaxHealth();
                int i = (int) (f + 0.5F) / 2;
                if (i > 30) {
                    i = 30;
                }

                vehicleHealth = i;
            }

            int heartrows = (int)Math.ceil((double)vehicleHealth / 10.0);

            DDGui.renderDiverDown(
                    this.getMinecraft(),
                    guiGraphics,
                    player,
                    width,
                    height,
                    heartrows
            );
        }
    }
}
