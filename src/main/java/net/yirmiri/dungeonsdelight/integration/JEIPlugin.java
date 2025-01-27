package net.yirmiri.dungeonsdelight.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.entity.container.MonsterPotMenu;
import net.yirmiri.dungeonsdelight.block.entity.container.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.integration.category.MonsterPotRecipeCategory;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDMenuTypes;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation ID = new ResourceLocation(DungeonsDelight.MOD_ID, "jei_plugin");

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        DDRecipes modRecipes = new DDRecipes();
        registration.addRecipes(DDRecipeTypes.MONSTER_COOKING, modRecipes.getMonsterPotRecipes());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new MonsterPotRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(DDBlocks.MONSTER_POT.get().asItem()), DDRecipeTypes.MONSTER_COOKING);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(MonsterPotScreen.class, 89, 25, 24, 17, DDRecipeTypes.MONSTER_COOKING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(MonsterPotMenu.class, DDMenuTypes.MONSTER_POT.get(), DDRecipeTypes.MONSTER_COOKING, 0, 6, 9, 36);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}
