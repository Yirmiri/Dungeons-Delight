package net.yirmiri.dungeonsdelight.common.util.misc;

import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

public class MonsterizedSplashRenderer extends SplashRenderer {
    public static final SplashRenderer ANNIVERSARY_SPLASH = new MonsterizedSplashRenderer("Happy Birthday Dungeon's Delight!", true);
    public static final SplashRenderer MONSTERIZED_SPLASH = new MonsterizedSplashRenderer("Whoops, I accidentally monsterized this splash!", true);

    private final String splash;
    private final boolean weird;

    public MonsterizedSplashRenderer(String splash, boolean weird) {
        super(splash);
        this.splash = splash;
        this.weird = weird;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int screenWidth, Font font, int color) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((float) screenWidth / 2.0F + 123.0F, 69.0F, 0.0F);

        long mls = Util.getMillis();
        float sin1k = Mth.sin((float)(mls % 1000L) / 1000.0F * Mth.TWO_PI);

        float f = 2.0F - Mth.abs(sin1k * 0.1F);
        f = f * 100.0F / (float) (font.width(this.splash) + 32);
        guiGraphics.pose().scale(f, f, f);

        if (this.weird) {
            float sin4k = Mth.sin((float)(mls % 4000L) / 4000.0F * Mth.TWO_PI);
            float sinColor = Mth.sin((float)(mls % 8000L) / 8000.0F * Mth.PI);
            //int colorCool = FastColor.ARGB32.lerp(sinColor, 0x68BC3D, DDUtil.MONSTER_COLOR);
            int colorCool = FastColor.ARGB32.lerp(sinColor, DDUtil.MONSTER_COLOR, DDUtil.MONSTER_COLOR);

            float g = -20.0F - Mth.abs(sin4k * 6F);
            guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(g));

            guiGraphics.drawCenteredString(font, this.splash, 0, -8, colorCool);
        }
        else {
            guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(-20.0F));
            guiGraphics.drawCenteredString(font, this.splash, 0, -8, DDUtil.MONSTER_COLOR);
        }

        guiGraphics.pose().popPose();
    }
}