package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlockEntity;

import java.util.List;

public class MonsterPotScreen extends AbstractContainerScreen<MonsterPotMenu> {
    private static final ResourceLocation TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/gui/monster_pot.png");
    private static final List<ResourceLocation> CONTAINER_ICONS = List.of(
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_bowl"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_bone"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_glass_bottle"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_stick"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_slicorice")
    );
    private final CyclingSlotBackground bowlIcon = new CyclingSlotBackground(MonsterPotBlockEntity.BOWL_SLOT);

    public MonsterPotScreen(MonsterPotMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.inventoryLabelX = 7;
        this.inventoryLabelY = 72;
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.bowlIcon.tick(CONTAINER_ICONS);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        if (this.menu.getSlot(MonsterPotBlockEntity.BOWL_SLOT).getItem().isEmpty()) {
            this.bowlIcon.render(this.menu, graphics, partialTick, this.leftPos, this.topPos);
        }

        if (this.menu.isHeated()) {
            graphics.blit(TEXTURE, this.leftPos + 45, this.topPos + 54, 176, 0, 20, 15, 256, 256);
        }

        int progress = this.menu.getCookProgress();
        int total = this.menu.getCookTotal();

        if (progress > 0 && total > 0) {
            int width = (int) ((float) progress / (float) total * 24.0F);
            if (width > 0) {
                graphics.blit(TEXTURE, this.leftPos + 88, this.topPos + 25, 176, 15, width, 16, 256, 256);
            }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x92cb43, false);
        graphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0x92cb43, false);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        int heatedIconX = this.leftPos + 45;
        int heatedIconY = this.topPos + 54;
        int bowlIconX = this.leftPos + 126;
        int bowlIconY = this.topPos + 56;

        if (mouseX >= heatedIconX && mouseX < heatedIconX + 20 && mouseY >= heatedIconY && mouseY < heatedIconY + 15) {
            graphics.renderTooltip(this.font, this.menu.isHeated() ? Component.translatable("tooltip.container.dungeonsdelight.heated")
                    : Component.translatable("tooltip.container.dungeonsdelight.not_heated"), mouseX, mouseY);
        }
        if (mouseX >= bowlIconX && mouseX < bowlIconX + 18 && mouseY >= bowlIconY && mouseY < bowlIconY + 18 && this.menu.getSlot(MonsterPotBlockEntity.BOWL_SLOT).getItem().isEmpty()) {
            graphics.renderTooltip(this.font, Component.translatable("tooltip.container.dungeonsdelight.bowl_slot"), mouseX, mouseY);
        }
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}