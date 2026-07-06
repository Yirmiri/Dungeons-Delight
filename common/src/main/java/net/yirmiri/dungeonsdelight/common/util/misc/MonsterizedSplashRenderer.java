package net.yirmiri.dungeonsdelight.common.util.misc;

import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.util.Mth;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

public class MonsterizedSplashRenderer extends SplashRenderer {
    public static final SplashRenderer ANNIVERSARY_SPLASH = new SplashRenderer("Happy Birthday Dungeon's Delight!");
    public static final SplashRenderer MONSTERIZED_SPLASH = new SplashRenderer("Whoops, I accidentally monsterized this splash!");
    private final String splash;

    public MonsterizedSplashRenderer(String splash) {
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
        guiGraphics.drawCenteredString(font, this.splash, 0, -8, DDUtil.MONSTER_COLOR);
        guiGraphics.pose().popPose();
    }
}