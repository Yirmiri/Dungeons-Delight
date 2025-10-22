package net.yirmiri.dungeonsdelight.common.util.misc;

import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.util.Mth;

public class SpecialSplashRenderer extends SplashRenderer {
    public static final SplashRenderer ANNIVERSARY_SPLASH = new SplashRenderer("Happy Birthday Dungeon's Delight!");
    private static final int WIDTH_OFFSET = 123;
    private static final int HEIGH_OFFSET = 69;
    private final String splash;

    public SpecialSplashRenderer(String splash) {
        super(splash);
        this.splash = splash;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int screenWidth, Font font, int color) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((float) screenWidth / 2.0F + 123.0F, 69.0F, 0.0F);
        guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(-20.0F));
        float f = 1.8F - Mth.abs(Mth.sin((float) (Util.getMillis() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
        f = f * 100.0F / (float) (font.width(this.splash) + 32);
        guiGraphics.pose().scale(f, f, f);
        guiGraphics.drawCenteredString(font, this.splash, 0, -8, 0xC875C2);
        guiGraphics.pose().popPose();
    }
}
