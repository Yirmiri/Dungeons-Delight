package net.yirmiri.dungeonsdelight.core.event.overlay.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

public class VoracityGUIOverlay implements IGuiOverlay {
    private static final ResourceLocation FULL = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/hud/hunger/voracity_full.png");
    private static final ResourceLocation HALF = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/hud/hunger/voracity_half.png");
    private static final ResourceLocation EMPTY = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/hud/hunger/voracity_empty.png");

    private static final ResourceLocation HUNGER_FULL = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/hud/hunger/voracity_hunger_full.png");
    private static final ResourceLocation HUNGER_HALF = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/hud/hunger/voracity_hunger_half.png");

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int width, int height) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.hasEffect(DDEffects.VORACITY.get())) {
                if (player.hasEffect(MobEffects.HUNGER)) {
                    for (int i = 0; i < 10; i++) {
                        int iconX = 10 + i * 8;
                        int iconY = height - 39;

                        if (i < player.getFoodData().getFoodLevel() / 2) {
                            guiGraphics.blit(HUNGER_FULL, iconX, iconY, 0, 0, 9, 9);
                        } else if (i == player.getFoodData().getFoodLevel() / 2 && player.getFoodData().getFoodLevel() % 2 == 1) {
                            guiGraphics.blit(HUNGER_HALF, iconX, iconY, 9, 0, 9, 9);
                        } else {
                            guiGraphics.blit(EMPTY, iconX, iconY, 18, 0, 9, 9);
                        }
                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        int iconX = 10 + i * 8;
                        int iconY = height - 39;

                        if (i < player.getFoodData().getFoodLevel() / 2) {
                            guiGraphics.blit(FULL, iconX, iconY, 0, 0, 9, 9);
                        } else if (i == player.getFoodData().getFoodLevel() / 2 && player.getFoodData().getFoodLevel() % 2 == 1) {
                            guiGraphics.blit(HALF, iconX, iconY, 9, 0, 9, 9);
                        } else {
                            guiGraphics.blit(EMPTY, iconX, iconY, 18, 0, 9, 9);
                        }
                    }
                }
            }
        }
    }
}
