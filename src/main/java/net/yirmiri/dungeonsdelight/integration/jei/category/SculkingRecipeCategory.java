package net.yirmiri.dungeonsdelight.integration.jei.category;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.integration.jei.DDRecipeTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.jei.resource.DecompositionDummy;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
public class SculkingRecipeCategory implements IRecipeCategory<DecompositionDummy> {
    private static final int slotSize = 22;

    private final Component title;
    private final IDrawable background;
    private final IDrawable slotIcon;
    private final IDrawable icon;
    private final ItemStack embeddedEggs;
    private final ItemStack heapOfAncientEggs;

    public SculkingRecipeCategory(IGuiHelper helper) {
        title = TextUtils.getTranslation("jei.sculking");
        ResourceLocation backgroundImage = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sculking_jei.png");
        background = helper.createDrawable(backgroundImage, 0, 0, 118, 80);
        embeddedEggs = new ItemStack(DDBlocks.EMBEDDED_EGGS.get());
        heapOfAncientEggs = new ItemStack(DDBlocks.HEAP_OF_ANCIENT_EGGS.get());
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, heapOfAncientEggs);
        slotIcon = helper.createDrawable(backgroundImage, 119, 0, slotSize, slotSize);
    }

    @Override
    public RecipeType<DecompositionDummy> getRecipeType() {
        return DDRecipeTypes.SCULKING;
    }

    @Override
    public Component getTitle() {
        return this.title;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DecompositionDummy recipe, IFocusGroup focusGroup) {
        List<ItemStack> accelerators = ForgeRegistries.BLOCKS.tags().getTag(DDTags.BlockT.SCULKING_ACTIVATORS).stream().map(ItemStack::new).collect(Collectors.toList());
        List<ItemStack> spawners = ForgeRegistries.BLOCKS.tags().getTag(DDTags.BlockT.MONSTER_HEAT_SOURCES).stream().map(ItemStack::new).collect(Collectors.toList());

        builder.addSlot(RecipeIngredientRole.INPUT, 9, 26).addItemStack(embeddedEggs);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 93, 26).addItemStack(heapOfAncientEggs);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 64, 54).addItemStacks(accelerators);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 48, 54).addItemStacks(spawners);
    }

    @Override
    public void draw(DecompositionDummy recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.slotIcon.draw(guiGraphics, 63, 53);
        this.slotIcon.draw(guiGraphics, 47, 53);
    }

    @Override
    public List<Component> getTooltipStrings(DecompositionDummy recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (ClientRenderUtils.isCursorInsideBounds(40, 38, 11, 11, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".night"));
        }
        if (ClientRenderUtils.isCursorInsideBounds(53, 38, 11, 11, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".spawner"));
        }
        if (ClientRenderUtils.isCursorInsideBounds(67, 38, 11, 11, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".accelerators"));
        }
        return Collections.emptyList();
    }

    private static MutableComponent translateKey(@Nonnull String suffix) {
        return Component.translatable(DungeonsDelight.MOD_ID + ".jei.sculking" + suffix);
    }
}
