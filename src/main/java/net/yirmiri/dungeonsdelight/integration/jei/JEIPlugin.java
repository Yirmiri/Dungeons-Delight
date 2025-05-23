package net.yirmiri.dungeonsdelight.integration.jei;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.*;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotMenu;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.integration.jei.category.MonsterPotRecipeCategory;
import net.yirmiri.dungeonsdelight.integration.jei.category.SculkingRecipeCategory;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDMenuTypes;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.jei.resource.DecompositionDummy;

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

        //RECIPES
        registration.addRecipes(DDRecipeTypes.MONSTER_COOKING, modRecipes.getMonsterPotRecipes());
        registration.addRecipes(DDRecipeTypes.SCULKING, ImmutableList.of(new DecompositionDummy()));

        //INFO
        registration.addIngredientInfo(new ItemStack(DDItems.STAINED_SCRAP.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.stained_scrap"));

        registration.addIngredientInfo(new ItemStack(DDItems.AMETHYST_ROCK_CANDY.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.rock_candy"));
        registration.addIngredientInfo(new ItemStack(DDItems.CANDIED_VEX_SUCKER.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.candied_vex"));
        registration.addIngredientInfo(new ItemStack(DDItems.CANDIED_SILVERFISH_SUCKER.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.candied_silverfish"));

        registration.addIngredientInfo(new ItemStack(DDItems.FLINT_CLEAVER.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.cleaver"));
        registration.addIngredientInfo(new ItemStack(DDItems.GOLDEN_CLEAVER.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.cleaver"));
        registration.addIngredientInfo(new ItemStack(DDItems.IRON_CLEAVER.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.cleaver"));
        registration.addIngredientInfo(new ItemStack(DDItems.DIAMOND_CLEAVER.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.cleaver"));
        registration.addIngredientInfo(new ItemStack(DDItems.NETHERITE_CLEAVER.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.cleaver"));
        registration.addIngredientInfo(new ItemStack(DDItems.STAINED_CLEAVER.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.cleaver"));
        registration.addIngredientInfo(new ItemStack(DDItems.STAINED_KNIFE.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"));

        registration.addIngredientInfo(new ItemStack(DDItems.RANCID_REDUCTION.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.rancid_reduction"));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new MonsterPotRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new SculkingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(DDBlocks.MONSTER_POT.get().asItem()), DDRecipeTypes.MONSTER_COOKING);
        registration.addRecipeCatalyst(new ItemStack(DDBlocks.EMBEDDED_EGGS.get()), DDRecipeTypes.SCULKING);
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
