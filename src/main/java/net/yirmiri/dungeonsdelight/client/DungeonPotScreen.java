package net.yirmiri.dungeonsdelight.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.entity.container.DungeonPotMenu;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.client.gui.CookingPotRecipeBookComponent;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DungeonPotScreen extends HandledScreen<DungeonPotMenu> implements RecipeBookProvider {
    private static final ButtonTextures RECIPE_BUTTON = new ButtonTextures(Identifier.of(DungeonsDelight.MOD_ID, "recipe_book/dungeon_button"), Identifier.of(DungeonsDelight.MOD_ID, "recipe_book/dungeon_button_highlighted"));
    private static final Identifier BACKGROUND_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID, "textures/gui/dungeon_pot.png");
    private static final Rectangle HEAT_ICON = new Rectangle(47, 55, 20, 15);
    private static final Rectangle PROGRESS_ARROW = new Rectangle(89, 25, 0, 17);

    private final CookingPotRecipeBookComponent recipeBookComponent = new CookingPotRecipeBookComponent();
    private boolean widthTooNarrow;

    public DungeonPotScreen(DungeonPotMenu screenContainer, PlayerInventory inv, Text titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.titleX = 28;
        this.recipeBookComponent.initialize(this.width, this.height, this.client, this.widthTooNarrow, this.handler);
        this.x = this.recipeBookComponent.findLeftEdge(this.width, this.backgroundWidth);
        if (Configuration.ENABLE_RECIPE_BOOK_COOKING_POT.get()) {
            this.addDrawableChild(new TexturedButtonWidget(this.x + 5, this.height / 2 - 49, 20, 18, RECIPE_BUTTON, (button) ->
            {
                this.recipeBookComponent.toggleOpen();
                this.x = this.recipeBookComponent.findLeftEdge(this.width, this.backgroundWidth);
                button.setPosition(this.x + 5, this.height / 2 - 49);
            }));
        } else {
            this.recipeBookComponent.hide();
            this.x = this.recipeBookComponent.findLeftEdge(this.width, this.backgroundWidth);
        }
        this.addSelectableChild(this.recipeBookComponent);
        this.setInitialFocus(this.recipeBookComponent);
    }

    @Override
    protected void handledScreenTick() {
        super.handledScreenTick();
        this.recipeBookComponent.update();
    }

    @Override
    public void render(DrawContext gui, final int mouseX, final int mouseY, float partialTicks) {
        if (this.recipeBookComponent.isOpen() && this.widthTooNarrow) {
            this.renderBackground(gui, mouseX, mouseY, partialTicks);
            this.recipeBookComponent.render(gui, mouseX, mouseY, partialTicks);
        } else {
            super.render(gui, mouseX, mouseY, partialTicks);
            this.recipeBookComponent.render(gui, mouseX, mouseY, partialTicks);
            this.recipeBookComponent.drawGhostSlots(gui, this.x, this.y, false, partialTicks);
        }

        this.renderMealDisplayTooltip(gui, mouseX, mouseY);
        this.renderHeatIndicatorTooltip(gui, mouseX, mouseY);
        this.recipeBookComponent.drawTooltip(gui, this.x, this.y, mouseX, mouseY);
    }

    private void renderHeatIndicatorTooltip(DrawContext gui, int mouseX, int mouseY) {
        if (this.isPointWithinBounds(HEAT_ICON.x, HEAT_ICON.y, HEAT_ICON.width, HEAT_ICON.height, mouseX, mouseY)) {
            String key = "container.cooking_pot." + (this.handler.isHeated() ? "heated" : "not_heated");
            gui.drawTooltip(this.textRenderer, TextUtils.getTranslation(key), mouseX, mouseY);
        }
    }

    protected void renderMealDisplayTooltip(DrawContext gui, int mouseX, int mouseY) {
        if (this.client != null && this.client.player != null && this.handler.getCursorStack().isEmpty() && this.focusedSlot != null && this.focusedSlot.hasStack()) {
            if (this.focusedSlot.id == 6) {
                List<Text> tooltip = new ArrayList<>();

                ItemStack mealStack = this.focusedSlot.getStack();
                tooltip.add(((MutableText) mealStack.getItem().getName()).formatted(mealStack.getRarity().getFormatting()));

                ItemStack containerStack = this.handler.blockEntity.getContainer();
                String container = !containerStack.isEmpty() ? containerStack.getItem().getName().getString() : "";

                tooltip.add(TextUtils.getTranslation("container.cooking_pot.served_on", container).formatted(Formatting.GRAY));

                gui.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
            } else {
                gui.drawItemTooltip(textRenderer, this.focusedSlot.getStack(), mouseX, mouseY);
            }
        }
    }

    @Override
    protected void drawForeground(DrawContext gui, int mouseX, int mouseY) {
        super.drawForeground(gui, mouseX, mouseY);
        gui.drawText(this.textRenderer, this.playerInventoryTitle, 8, (this.backgroundHeight - 96 + 2), 4210752, false);
    }

    @Override
    protected void drawBackground(DrawContext gui, float partialTicks, int mouseX, int mouseY) {
        // Render UI background
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.client == null)
            return;

        gui.drawTexture(BACKGROUND_TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        // Render heat icon
        if (this.handler.isHeated()) {
            gui.drawTexture(BACKGROUND_TEXTURE, this.x + HEAT_ICON.x, this.y + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height);
        }

        // Render progress arrow
        int l = this.handler.getCookProgressionScaled();
        gui.drawTexture(BACKGROUND_TEXTURE, this.x + PROGRESS_ARROW.x, this.y + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height);
    }

    @Override
    protected boolean isPointWithinBounds(int x, int y, int width, int height, double mouseX, double mouseY) {
        return (!this.widthTooNarrow || !this.recipeBookComponent.isOpen()) && super.isPointWithinBounds(x, y, width, height, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonId) {
        if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, buttonId)) {
            this.setFocused(this.recipeBookComponent);
            return true;
        }
        return this.widthTooNarrow && this.recipeBookComponent.isOpen() || super.mouseClicked(mouseX, mouseY, buttonId);
    }

    @Override
    protected boolean isClickOutsideBounds(double mouseX, double mouseY, int x, int y, int buttonIdx) {
        boolean flag = mouseX < (double) x || mouseY < (double) y || mouseX >= (double) (x + this.backgroundWidth) || mouseY >= (double) (y + this.backgroundHeight);
        return flag && this.recipeBookComponent.isClickOutsideBounds(mouseX, mouseY, this.x, this.y, this.backgroundWidth, this.backgroundHeight, buttonIdx);
    }

    @Override
    protected void onMouseClick(Slot slot, int mouseX, int mouseY, SlotActionType clickType) {
        super.onMouseClick(slot, mouseX, mouseY, clickType);
        this.recipeBookComponent.slotClicked(slot);
    }

    @Override
    public void refreshRecipeBook() {
        this.recipeBookComponent.refresh();
    }

//	@Override
//	public void removed() {
//		this.recipeBookComponent.removed();
//		super.removed();
//	}

    @Override
    @NotNull
    public RecipeBookWidget getRecipeBookWidget() {
        return this.recipeBookComponent;
    }
}
