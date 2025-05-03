package net.yirmiri.dungeonsdelight.common.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.DDConfigClient;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

public class VoracityOverlay implements IGuiOverlay {
    private static final ResourceLocation VORACITY_OVERLAY_LOCATION = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/misc/voracity_overlay.png");
    protected int screenWidth;
    protected int screenHeight;

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics graphics, float partialTicks, int screenWidth, int screenHeight) {
        this.screenWidth = graphics.guiWidth();
        this.screenHeight = graphics.guiHeight();
        Player player = Minecraft.getInstance().player;

        if (DDConfigClient.VORACITY_OVERLAY.get() && player != null && player.hasEffect(DDEffects.VORACITY.get())) {
            renderTextureOverlay(graphics, VORACITY_OVERLAY_LOCATION, getPercentMonster(player));
        }
    }

    public float getPercentMonster(Player player) {
        if (player.getEffect(DDEffects.VORACITY.get()).getDuration() == -1) {
            return 1.0F;
        } else return (float) Math.min(player.getEffect(DDEffects.VORACITY.get()).getDuration(), 200) / (float) 200;
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
