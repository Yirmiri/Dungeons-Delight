package net.yirmiri.dungeonsdelight.core.event.overlay.effect;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.yirmiri.dungeonsdelight.DDConfigClient;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

@OnlyIn(Dist.CLIENT)
public class RavenousRushEffectOverlay implements LayeredDraw.Layer {
    private static final ResourceLocation RAVENOUS_RUSH_OVERLAY_LOCATION = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/misc/ravenous_rush_overlay.png");
    private static final ResourceLocation VIGNETTE_LOCATION = RunicLib.customid("minecraft", "textures/misc/vignette.png");
    protected int screenWidth;
    protected int screenHeight;

    @Override
    public void render(GuiGraphics graphics, DeltaTracker delta) {
        this.screenWidth = graphics.guiWidth();
        this.screenHeight = graphics.guiHeight();
        Player player = Minecraft.getInstance().player;

        if (DDConfigClient.RAVENOUS_RUSH_OVERLAY.get() && !Minecraft.getInstance().isPaused() && player != null && !player.hasEffect(DDEffects.VORACITY)) {
            renderTextureOverlay(graphics, RAVENOUS_RUSH_OVERLAY_LOCATION, getPercentMonster(player));
        }

        if (player != null && player.hasEffect(DDEffects.RAVENOUS_RUSH) && !Minecraft.getInstance().isPaused()) {
            renderVignette(graphics, getPercentMonsterVignette(player));
        }
    }

    public float getPercentMonster(Player player) {
        if (player.hasEffect(DDEffects.RAVENOUS_RUSH) && player.getEffect(DDEffects.RAVENOUS_RUSH).getDuration() == -1) {
            return 1.0F;
        } else if (player.hasEffect(DDEffects.RAVENOUS_RUSH)) {
            return (float) Math.min(player.getEffect(DDEffects.RAVENOUS_RUSH).getDuration(), 200) / (float) 200;
        } else {
            return 0;
        }
    }

    public float getPercentMonsterVignette(Player player) {
        if (player.getEffect(DDEffects.RAVENOUS_RUSH).getDuration() == -1 || player.getEffect(DDEffects.RAVENOUS_RUSH).getDuration() >= 80) {
            return 0.4F;
        } else return (float) (player.getEffect(DDEffects.RAVENOUS_RUSH).getDuration() / 2) / 100;
    }

    public void renderVignette(GuiGraphics graphics, float alpha) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        graphics.setColor(0.0F, alpha, alpha, 1.0F);

        graphics.blit(VIGNETTE_LOCATION, 0, 0, -90, 0.0F, 0.0F, this.screenWidth, this.screenHeight, this.screenWidth, this.screenHeight);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
    }

    protected void renderTextureOverlay(GuiGraphics graphics, ResourceLocation resourceLocation, float alpha) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        graphics.setColor(1.0F, 1.0F, 1.0F, alpha);
        graphics.blit(resourceLocation, 0, 0, -90, 0.0F, 0.0F, this.screenWidth, this.screenHeight, this.screenWidth, this.screenHeight);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
