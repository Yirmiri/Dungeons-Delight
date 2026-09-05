package net.yirmiri.dungeonsdelight.common.util;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.data.DiverDownData;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

public class DDGui {
    private static final ResourceLocation DUNDEL_GUI = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/gui/icons.png");

    public static void renderDiverDown(Minecraft minecraft, GuiGraphics guiGraphics, Player player, int screenWidth, int screenHeight, int heartrows) {
        if (player != null) {
            minecraft.getProfiler().push("dundelight_diverdown");

            int xpos = screenWidth / 2 + 91;
            int sH = screenHeight - 39;
            int yoff = sH - 10;

            int maxAir = player.getMaxAirSupply();
            int playerAir = Math.min(player.getAirSupply(), maxAir);

            if (playerAir < maxAir) yoff -= 10;

            int maxflame = DiverDownData.MAX_CHARGE;
            int curfl = Math.min(((DiverDownData)player).getCharge(), maxflame);

            if (player.hasEffect(DDEffects.DIVER_DOWN.get()) && (player.isOnFire() || curfl < maxflame)) {
                yoff -= heartrows * 10;
                int l4 = Mth.ceil((double)(curfl - 2) * 10.0 / (double)maxflame);
                int i5 = Mth.ceil((double)curfl * 10.0 / (double)maxflame) - l4;

                for(int j5 = 0; j5 < l4 + i5; ++j5) {
                    if (j5 < l4) guiGraphics.blit(DUNDEL_GUI, xpos - j5 * 8 - 9, yoff, 0, 9, 9, 9);
                    else guiGraphics.blit(DUNDEL_GUI, xpos - j5 * 8 - 9, yoff, 9, 9, 9, 9);
                }
            }

            minecraft.getProfiler().pop();
        }
    }
}
