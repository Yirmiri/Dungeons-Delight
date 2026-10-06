package net.yirmiri.dungeonsdelight.common.menu.monster_pot.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.common.menu.monster_pot.MonsterPotMenu;
import net.yirmiri.dungeonsdelight.common.menu.monster_pot.client.recipebook.MonsterPotRecipeBookComponent;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class MonsterPotScreen extends AbstractContainerScreen<MonsterPotMenu> implements RecipeUpdateListener {
    private static final ResourceLocation TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/gui/monster_pot.png");
    private static final ResourceLocation RECIPE_BUTTON_LOCATION = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/gui/monster_recipe_button.png");
    private static final List<ResourceLocation> CONTAINER_ICONS = List.of(
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_bowl"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_bone"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_glass_bottle"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_stick"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "item/icon_monster_slicorice")
    );
    private final CyclingSlotBackground bowlIcon = new CyclingSlotBackground(MonsterPotBlockEntity.BOWL_SLOT);

    private final MonsterPotRecipeBookComponent recipeBookComponent = new MonsterPotRecipeBookComponent(this);
    private boolean widthTooNarrow;
    public boolean renderBowlWidget = false;

    public MonsterPotScreen(MonsterPotMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.inventoryLabelX = 7;
        this.inventoryLabelY = 72;
    }

    @Override
    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.titleLabelX = 28;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);

        this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 49, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, button -> {
            this.recipeBookComponent.toggleVisibility();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
            button.setPosition(this.leftPos + 5, this.height / 2 - 49);
        }));

        this.addWidget(this.recipeBookComponent);
        this.setInitialFocus(this.recipeBookComponent);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.recipeBookComponent.tick();
        this.bowlIcon.tick(CONTAINER_ICONS);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        if (this.menu.getSlot(MonsterPotBlockEntity.BOWL_SLOT).getItem().isEmpty() && this.renderBowlWidget) {
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
        graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, DDUtil.GREEN_UI_TEXT_COLOR, false);
        graphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, DDUtil.GREEN_UI_TEXT_COLOR, false);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        // Recipe Book
        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.renderBg(graphics, partialTick, mouseX, mouseY);
            this.recipeBookComponent.render(graphics, mouseX, mouseY, partialTick);
        } else {
            this.recipeBookComponent.render(graphics, mouseX, mouseY, partialTick);
            super.render(graphics, mouseX, mouseY, partialTick);
            this.recipeBookComponent.renderGhostRecipe(graphics, this.leftPos, this.topPos, true, partialTick);
        }

        int heatedIconX = this.leftPos + 45;
        int heatedIconY = this.topPos + 54;
        int bowlIconX = this.leftPos + 126;
        int bowlIconY = this.topPos + 56;

        if (mouseX >= heatedIconX && mouseX < heatedIconX + 20 && mouseY >= heatedIconY && mouseY < heatedIconY + 15) {
            graphics.renderTooltip(this.font, this.menu.isHeated() ? Component.translatable("tooltip.container.dungeonsdelight.heated")
                    : Component.translatable("tooltip.container.dungeonsdelight.not_heated"), mouseX, mouseY);
        }
        if (this.renderBowlWidget && mouseX >= bowlIconX && mouseX < bowlIconX + 18 && mouseY >= bowlIconY && mouseY < bowlIconY + 18 && this.menu.getSlot(MonsterPotBlockEntity.BOWL_SLOT).getItem().isEmpty()) {
            graphics.renderTooltip(this.font, Component.translatable("tooltip.container.dungeonsdelight.bowl_slot"), mouseX, mouseY);
        }

        this.renderTooltip(graphics, mouseX, mouseY);
        this.recipeBookComponent.renderTooltip(graphics, this.leftPos, this.topPos, mouseX, mouseY);
    }

    protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
        return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(x, y, width, height, mouseX, mouseY);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int buttonId) {
        if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, buttonId)) {
            this.setFocused(this.recipeBookComponent);
            return true;
        } else {
            return this.widthTooNarrow && this.recipeBookComponent.isVisible() || super.mouseClicked(mouseX, mouseY, buttonId);
        }
    }

    protected void slotClicked(Slot slot, int mouseX, int mouseY, ClickType clickType) {
        super.slotClicked(slot, mouseX, mouseY, clickType);
        this.recipeBookComponent.slotClicked(slot);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return this.recipeBookComponent.keyPressed(keyCode, scanCode, modifiers) ? false : super.keyPressed(keyCode, scanCode, modifiers);
    }

    protected boolean hasClickedOutside(double mouseX, double mouseY, int x, int y, int buttonIdx) {
        boolean flag = mouseX < (double)x || mouseY < (double)y || mouseX >= (double)(x + this.imageWidth) || mouseY >= (double)(y + this.imageHeight);
        return flag && this.recipeBookComponent.hasClickedOutside(mouseX, mouseY, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, buttonIdx);
    }

    public boolean charTyped(char codePoint, int modifiers) {
        return this.recipeBookComponent.charTyped(codePoint, modifiers) ? true : super.charTyped(codePoint, modifiers);
    }

    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }
    @Override public RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }
}