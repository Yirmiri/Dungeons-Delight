package net.yirmiri.dungeonsdelight.common.event;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.yirmiri.dungeonsdelight.DDConfigClient;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

public class RavenousRushVignette implements IGuiOverlay {
    private static final ResourceLocation RAVENOUS_RUSH_OVERLAY_LOCATION = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/misc/ravenous_rush_overlay.png");
    private static final ResourceLocation VIGNETTE_LOCATION = new ResourceLocation("textures/misc/vignette.png");
    protected int screenWidth;
    protected int screenHeight;

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics graphics, float v, int i, int i1) {
        this.screenWidth = graphics.guiWidth();
        this.screenHeight = graphics.guiHeight();
        Player player = Minecraft.getInstance().player;

        if (DDConfigClient.RAVENOUS_RUSH_OVERLAY.get() && player != null && !player.hasEffect(DDEffects.VORACITY.get())) {
            renderTextureOverlay(graphics, RAVENOUS_RUSH_OVERLAY_LOCATION, getPercentMonster(player));
        }

        if (player != null && player.hasEffect(DDEffects.RAVENOUS_RUSH.get())) {
            renderVignette(graphics, getPercentMonsterVignette(player));
        }
    }

    public float getPercentMonster(Player player) {
        if (player.getEffect(DDEffects.RAVENOUS_RUSH.get()).getDuration() == -1) {
            return 1.0F;
        } else return (float) Math.min(player.getEffect(DDEffects.RAVENOUS_RUSH.get()).getDuration(), 200) / (float) 200;
    }

    public float getPercentMonsterVignette(Player player) {
        if (player.getEffect(DDEffects.RAVENOUS_RUSH.get()).getDuration() == -1 || player.getEffect(DDEffects.RAVENOUS_RUSH.get()).getDuration() >= 80) {
            return 0.4F;
        } else return (float) (player.getEffect(DDEffects.RAVENOUS_RUSH.get()).getDuration() / 2) / 100;
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
