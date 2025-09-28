package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.datagen.recipe.DDCookingPotRecipeBuilder;
import net.yirmiri.dungeonsdelight.datagen.recipe.MonsterCookingPotRecipeBuilder;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class DDRecipeGen extends RecipeProvider implements IConditionBuilder {
    public DDRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        monsterCooking(consumer);
        cooking(consumer);
        crafting(consumer);
        smelting(consumer);
        cutting(consumer);
    }

    private static void monsterCooking(RecipeOutput consumer) {
        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.POLTERGHAST_PIZZA.get(), 1, 200, 2.0F)
                .addIngredient(ModItems.ROTTEN_TOMATO.get())
                .addIngredient(DDItems.WARDENZOLA.get())
                .addIngredient(ModItems.CABBAGE_LEAF.get()) //TODO: Creeper squib
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(DDTags.ItemT.RAW_GHAST)
                .addIngredient(DDItems.ROTBULB.get())
                .unlockedByItems(getHasName(DDItems.ROTBULB.get()), DDItems.ROTBULB.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.BLOODY_MARY.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.GRITTY_FLESH.get())
                .addIngredient(DDItems.GRITTY_FLESH.get())
                .addIngredient(DDItems.SILVERFISH_ABDOMEN.get())
                .addIngredient(CommonTags.CROPS_CABBAGE)
                .unlockedByItems(getHasName(DDItems.SILVERFISH_ABDOMEN.get()), DDItems.SILVERFISH_ABDOMEN.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SPIDER_BUBBLE_TEA.get(), 1, 200, 1.0F, ModItems.MILK_BOTTLE.get())
                .addIngredient(DDItems.SPIDER_EXTRACT.get())
                .addIngredient(DDItems.SPIDER_EXTRACT.get())
                .addIngredient(Items.FERMENTED_SPIDER_EYE)
                .addIngredient(Items.FERMENTED_SPIDER_EYE)
                .addIngredient(Items.GLOWSTONE_DUST)
                .addIngredient(Items.REDSTONE)
                .unlockedByItems(getHasName(DDItems.SPIDER_EXTRACT.get()), DDItems.SPIDER_EXTRACT.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.TARO_MILK_TEA.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(DDItems.GUNK.get())
                .addIngredient(DDItems.RANCID_REDUCTION.get())
                .addIngredient(Items.SUGAR)
                .addIngredient(CommonTags.FOODS_MILK)
                .addIngredient(Items.HONEY_BOTTLE)
                .unlockedByItems(getHasName(DDItems.ROTBULB.get()), DDItems.ROTBULB.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.AU_ROTTEN_POTATOES.get(), 1, 200, 2.0F)
                .addIngredient(Items.POISONOUS_POTATO)
                .addIngredient(Items.POISONOUS_POTATO)
                .addIngredient(CommonTags.FOODS_MILK)
                .addIngredient(DDTags.ItemT.SCULK_CHEESE)
                .addIngredient(DDTags.ItemT.SCULK_CHEESE)
                .unlockedByItems(getHasName(Items.POISONOUS_POTATO), Items.POISONOUS_POTATO)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.CHICKEN_JOCKEY_SANDWICH.get(), 1, 200, 2.0F)
                .addIngredient(DDItems.ANCIENT_EGG.get())
                .addIngredient(Items.CHICKEN)
                .addIngredient(DDTags.ItemT.FLESHES)
                .addIngredient(ModItems.ROTTEN_TOMATO.get())
                .addIngredient(CommonTags.CROPS_CABBAGE)
                .unlockedByItems(getHasName(DDItems.ANCIENT_EGG.get()), DDItems.ANCIENT_EGG.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.GELLED_SALAD.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.SLIME_NOODLES.get())
                .addIngredient(DDItems.SLIME_NOODLES.get())
                .addIngredient(Items.CARROT)
                .addIngredient(Items.BEETROOT)
                .addIngredient(CommonTags.CROPS_CABBAGE)
                .unlockedByItems(getHasName(DDItems.SLIME_NOODLES.get()), DDItems.SLIME_NOODLES.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.GHOULASH.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.SLIME_NOODLES.get())
                .addIngredient(DDTags.ItemT.FLESHES)
                .addIngredient(ModItems.ROTTEN_TOMATO.get())
                .unlockedByItems(getHasName(DDItems.SLIME_NOODLES.get()), DDItems.SLIME_NOODLES.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.GUARDIAN_ANGEL_BLOCK.get(), 1, 200, 2.0F, Items.BOWL)
                .addIngredient(DDItems.SLIME_BAR.get())
                .addIngredient(ModItems.FRIED_EGG.get())
                .addIngredient(ModItems.FRUIT_SALAD.get())
                .addIngredient(Items.GOLDEN_CARROT)
                .addIngredient(Items.COD)
                .addIngredient(Items.SUGAR)
                .unlockedByItems(getHasName(DDItems.SLIME_BAR.get()), DDItems.SLIME_BAR.get())
                .unlockedByItems(getHasName(Items.GOLDEN_CARROT), Items.GOLDEN_CARROT)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.GYUDON.get(), 1, 200, 2.0F)
                .addIngredient(DDItems.GRITTY_FLESH.get())
                .addIngredient(DDTags.ItemT.SCULK_CHEESE)
                .addIngredient(ModItems.COOKED_RICE.get())
                .addIngredient(ModItems.FRIED_EGG.get())
                .addIngredient(ModItems.ONION.get())
                .unlockedByItems(getHasName(DDItems.GRITTY_FLESH.get()), DDItems.GRITTY_FLESH.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.MONSTER_BURGER.get(), 1, 800, 2.0F, ModItems.HAMBURGER.get())
                .addIngredient(DDItems.RANCID_REDUCTION.get())
                .addIngredient(DDItems.GHOULASH.get())
                .addIngredient(DDItems.CANDIED_VEX_SUCKER.get())
                .addIngredient(DDItems.SPIDER_SALMAGUNDI.get())
                .addIngredient(DDItems.MALICIOUS_SANDWICH.get())
                .addIngredient(DDItems.SILVERFISH_FRIED_RICE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.MALICIOUS_SANDWICH.get(), 1, 200, 2.0F, Items.BREAD)
                .addIngredient(DDItems.SCULK_MAYO.get())
                .addIngredient(DDTags.ItemT.SCULK_CHEESE)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(DDTags.ItemT.FLESHES)
                .addIngredient(DDTags.ItemT.FLESHES)
                .unlockedByItems(getHasName(DDItems.SCULK_MAYO.get()), DDItems.SCULK_MAYO.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.OMINOUS_OMELETTE.get(), 1, 200, 2.0F)
                .addIngredient(DDItems.SOFT_SERVE_SNIFFER_EGG.get())
                .addIngredient(DDTags.ItemT.SCULK_CHEESE)
                .addIngredient(CommonTags.FOODS_MILK)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(Items.BROWN_MUSHROOM)
                .unlockedByItems(getHasName(DDItems.SCULK_MAYO.get()), DDItems.SCULK_MAYO.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.OSSOBUCO_BLOCK.get(), 1, 400, 2.0F, Items.SKELETON_SKULL)
                .addIngredient(DDTags.ItemT.FLESHES)
                .addIngredient(DDTags.ItemT.FLESHES)
                .addIngredient(ModItems.ROTTEN_TOMATO.get())
                .addIngredient(ModItems.BONE_BROTH.get())
                .addIngredient(Items.BONE)
                .addIngredient(Items.BEETROOT)
                .unlockedByItems(getHasName(ModItems.ROTTEN_TOMATO.get()), ModItems.ROTTEN_TOMATO.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.POISONOUS_POUTINE.get(), 1, 200, 2.0F)
                .addIngredient(Items.POISONOUS_POTATO)
                .addIngredient(Items.POISONOUS_POTATO)
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(DDTags.ItemT.SCULK_CHEESE)
                .unlockedByItems(getHasName(DDItems.SPIDER_MEAT.get()), DDItems.SPIDER_MEAT.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SALT_SOAKED_STEW.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.BRINED_FLESH.get())
                .addIngredient(DDItems.BRINED_FLESH.get())
                .addIngredient(Items.SALMON)
                .addIngredient(Items.BEETROOT)
                .unlockedByItems(getHasName(DDItems.BRINED_FLESH.get()), DDItems.BRINED_FLESH.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SHIOKARA.get(), 1, 200, 1.0F)
                .addIngredient(DDTags.ItemT.RAW_GHAST)
                .addIngredient(DDItems.ROTTEN_TRIPE.get())
                .addIngredient(DDItems.ROTTEN_TRIPE.get())
                .addIngredient(DDItems.RANCID_REDUCTION.get())
                .addIngredient(Items.GHAST_TEAR)
                .unlockedByItems(getHasName(Items.GHAST_TEAR), Items.GHAST_TEAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SILVERFISH_AND_CHIPS_BLOCK.get(), 1, 200, 2.0F)
                .addIngredient(DDItems.SILVERFISH_ABDOMEN.get())
                .addIngredient(DDItems.SLIME_BAR.get())
                .addIngredient(Items.POISONOUS_POTATO)
                .addIngredient(Items.POISONOUS_POTATO)
                .addIngredient(ModItems.WHEAT_DOUGH.get())
                .addIngredient(DDTags.ItemT.EXTRACTS)
                .unlockedByItems(getHasName(DDItems.SLIME_BAR.get()), DDItems.SLIME_BAR.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SILVERFISH_FRIED_RICE.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.SILVERFISH_ABDOMEN.get())
                .addIngredient(Items.CARROT)
                .addIngredient(CommonTags.CROPS_CABBAGE)
                .addIngredient(ModItems.RICE.get())
                .addIngredient(CommonTags.FOODS_COOKED_EGG)
                .unlockedByItems(getHasName(DDItems.SILVERFISH_ABDOMEN.get()), DDItems.SILVERFISH_ABDOMEN.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SINIGANG.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(DDItems.SPIDER_EXTRACT.get())
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(ModItems.RICE.get())
                .addIngredient(DDTags.ItemT.ANCIENT_FLORA)
                .unlockedByItems(getHasName(DDItems.SPIDER_MEAT.get()), DDItems.SPIDER_MEAT.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SPIDER_SALMAGUNDI.get(), 1, 200, 1.0F)
                .addIngredient(Items.FERMENTED_SPIDER_EYE)
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(ModItems.ONION.get())
                .unlockedByItems(getHasName(DDItems.SPIDER_MEAT.get()), DDItems.SPIDER_MEAT.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.TERRINE_LOAF.get(), 1, 200, 2.0F)
                .addIngredient(DDItems.BRINED_FLESH.get())
                .addIngredient(DDItems.BRINED_FLESH.get())
                .addIngredient(DDItems.SLIME_NOODLES.get())
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(DDItems.ANCIENT_EGG.get())
                .unlockedByItems(getHasName(DDItems.BRINED_FLESH.get()), DDItems.BRINED_FLESH.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.TOKAYAKI.get(), 1, 200, 2.0F)
                .addIngredient(DDTags.ItemT.RAW_GHAST)
                .addIngredient(DDTags.ItemT.RAW_GHAST)
                .addIngredient(DDItems.SCULK_MAYO.get())
                .addIngredient(DDItems.ROTBULB.get())
                .unlockedByItems(getHasName(DDItems.ROTBULB.get()), DDItems.ROTBULB.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.BLOATED_BAKED_POTATO.get(), 1, 200, 2.0F)
                .addIngredient(Items.POISONOUS_POTATO)
                .addIngredient(DDItems.ROTTEN_TRIPE.get())
                .addIngredient(DDItems.ROTTEN_TRIPE.get())
                .addIngredient(DDTags.ItemT.SCULK_CHEESE)
                .addIngredient(DDTags.ItemT.SCULK_CHEESE)
                .unlockedByItems(getHasName(Items.POISONOUS_POTATO), Items.POISONOUS_POTATO)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.COB_N_CANDY.get(), 1, 200, 0.35F)
                .addIngredient(Items.STRING)
                .addIngredient(Items.STRING)
                .addIngredient(Items.STRING)
                .addIngredient(Items.SUGAR)
                .unlockedByItems(getHasName(Items.SUGAR), Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.DEVILISH_EGGS.get(), 2, 200, 0.7F)
                .addIngredient(DDItems.CLEAVED_ANCIENT_EGG.get())
                .addIngredient(DDItems.CLEAVED_ANCIENT_EGG.get())
                .addIngredient(DDItems.SCULK_MAYO.get())
                .unlockedByItems(getHasName(DDItems.CLEAVED_ANCIENT_EGG.get()), DDItems.CLEAVED_ANCIENT_EGG.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.GHAST_ROLL.get(), 1, 200, 1.0F)
                .addIngredient(DDTags.ItemT.RAW_GHAST)
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(ModItems.RICE.get())
                .addIngredient(Items.TWISTING_VINES)
                .addIngredient(Items.GHAST_TEAR)
                .unlockedByItems(getHasName(DDItems.ROTBULB.get()), DDItems.ROTBULB.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.GHASTLY_SPIRITS.get(), 1, 200, 1.0F)
                .addIngredient(DDTags.ItemT.RAW_GHAST)
                .addIngredient(DDTags.ItemT.RAW_GHAST)
                .addIngredient(Items.GHAST_TEAR)
                .addIngredient(Items.GLISTERING_MELON_SLICE)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.SOUL_SAND)
                .unlockedByItems(getHasName(Items.GHAST_TEAR), Items.GHAST_TEAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.MONSTER_MUFFIN.get(), 2, 200, 1.0F)
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(DDTags.ItemT.ACIDICS)
                .addIngredient(Items.SUGAR)
                .unlockedByItems(getHasName(DDItems.ROTBULB.get()), DDItems.ROTBULB.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.POI.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(DDItems.ROTBULB.get())
                .unlockedByItems(getHasName(DDItems.ROTBULB.get()), DDItems.ROTBULB.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.RANCID_REDUCTION.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.GUNK.get())
                .addIngredient(DDItems.GUNK.get())
                .addIngredient(Items.SUGAR)
                .addIngredient(CommonTags.FOODS_MILK)
                .unlockedByItems(getHasName(DDItems.GUNK.get()), DDItems.GUNK.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.RUBABOO.get(), 1, 100, 1.0F)
                .addIngredient(DDItems.GRITTY_FLESH.get())
                .addIngredient(DDItems.GRITTY_FLESH.get())
                .addIngredient(DDTags.ItemT.RUBABOO_INGREDIENTS)
                .unlockedByItems(getHasName(DDItems.GRITTY_FLESH.get()), DDItems.GRITTY_FLESH.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SCULK_MAYO.get(), 1, 200, 2.0F)
                .addIngredient(DDItems.ANCIENT_EGG.get())
                .addIngredient(DDItems.ANCIENT_EGG.get())
                .addIngredient(DDTags.ItemT.EXTRACTS)
                .addIngredient(DDTags.ItemT.EXTRACTS)
                .unlockedByItems(getHasName(DDItems.ANCIENT_EGG.get()), DDItems.ANCIENT_EGG.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SOAKED_SKEWER.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.BRINED_FLESH.get())
                .addIngredient(DDTags.ItemT.SEA_PLANTS)
                .addIngredient(DDTags.ItemT.SEA_PLANTS)
                .unlockedByItems(getHasName(DDItems.BRINED_FLESH.get()), DDItems.BRINED_FLESH.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SPIDER_DONUT.get(), 2, 200, 0.35F)
                .addIngredient(Items.STRING)
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(DDTags.ItemT.ACIDICS)
                .unlockedByItems(getHasName(DDItems.ROTBULB.get()), DDItems.ROTBULB.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SPIDER_EXTRACT.get(), 1, 200, 0.35F)
                .addIngredient(DDItems.SPIDER_MEAT.get())
                .addIngredient(Items.SPIDER_EYE)
                .unlockedByItems(getHasName(DDItems.SPIDER_MEAT.get()), DDItems.SPIDER_MEAT.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.SPIDER_TANGHULU.get(), 1, 200, 1.0F)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.BEETROOT)
                .addIngredient(Items.SUGAR)
                .unlockedByItems(getHasName(Items.SPIDER_EYE), Items.SPIDER_EYE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.WARDENZOLA.get(), 1, 200, 0.7F)
                .addIngredient(DDItems.SCULK_POLYP.get())
                .addIngredient(DDItems.SCULK_POLYP.get())
                .addIngredient(DDItems.ROTBULB.get())
                .addIngredient(CommonTags.FOODS_MILK)
                .unlockedByItems(getHasName(DDItems.SCULK_POLYP.get()), DDItems.SCULK_POLYP.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.JELLY_BEANS.get(), 8, 100, 0.35F)
                .addIngredient(DDTags.ItemT.EXTRACTS)
                .addIngredient(Items.WIND_CHARGE)
                .addIngredient(DDItems.GUNK.get())
                .unlockedByItems(getHasName(DDItems.GUNK.get()), DDItems.GUNK.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.WISPY_RICE_BALL.get(), 1, 200, 1.0F)
                .addIngredient(Items.WIND_CHARGE)
                .addIngredient(DDItems.SLIME_BAR.get())
                .addIngredient(ModItems.RICE.get())
                .unlockedByItems(getHasName(Items.WIND_CHARGE), Items.WIND_CHARGE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.BREEZE_CREAM_CONE.get(), 1, 200, 1.0F, Items.PHANTOM_MEMBRANE)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.WIND_CHARGE)
                .addIngredient(Items.WIND_CHARGE)
                .addIngredient(DDItems.SLIME_BAR.get())
                .unlockedByItems(getHasName(Items.WIND_CHARGE), Items.WIND_CHARGE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.MARSHBELLOW.get(), 2, 200, 0.35F)
                .addIngredient(Items.WIND_CHARGE)
                .addIngredient(Items.WIND_CHARGE)
                .addIngredient(DDItems.SLIME_NOODLES.get())
                .unlockedByItems(getHasName(Items.WIND_CHARGE), Items.WIND_CHARGE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        MonsterCookingPotRecipeBuilder.monsterCookingPotRecipe(DDItems.ECHO_ROCK_CANDY.get(), 1, 200, 2.0F)
                .addIngredient(Items.ECHO_SHARD)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.GLOW_BERRIES)
                .unlockedByItems(getHasName(Items.ECHO_SHARD), Items.ECHO_SHARD)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);
    }

    private static void cooking(RecipeOutput consumer) {
        DDCookingPotRecipeBuilder.cookingPotRecipe(DDItems.CHLOROPASTA.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.SNIFFER_SHANK.get())
                .addIngredient(DDTags.ItemT.ANCIENT_FLORA)
                .addIngredient(DDTags.ItemT.ANCIENT_FLORA)
                .addIngredient(DDItems.SLIME_NOODLES.get())
                .addIngredient(DDItems.SLIME_NOODLES.get())
                .addIngredient(Items.MOSS_BLOCK)
                .unlockedByItems(getHasName(DDItems.SNIFFER_SHANK.get()), DDItems.SNIFFER_SHANK.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        DDCookingPotRecipeBuilder.cookingPotRecipe(DDItems.AMETHYST_ROCK_CANDY.get(), 1, 200, 0.35F)
                .addIngredient(Items.AMETHYST_SHARD)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.SWEET_BERRIES)
                .unlockedByItems(getHasName(Items.AMETHYST_SHARD), Items.AMETHYST_SHARD)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);

        DDCookingPotRecipeBuilder.cookingPotRecipe(DDBlocks.GLOW_BERRY_GELATIN_BLOCK.get().asItem(), 1, 200, 2.0F, Items.BOWL)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(DDItems.SLIME_BAR.get())
                .addIngredient(Items.SUGAR)
                .addIngredient(CommonTags.CROPS_CABBAGE)
                .unlockedByItems(getHasName(DDItems.SLIME_BAR.get()), DDItems.SLIME_BAR.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        DDCookingPotRecipeBuilder.cookingPotRecipe(DDItems.SNUFFLEDOG.get(), 1, 200, 1.0F, Items.BREAD)
                .addIngredient(DDItems.SNIFFERWURST.get())
                .addIngredient(DDTags.ItemT.ANCIENT_FLORA)
                .addIngredient(DDTags.ItemT.ANCIENT_FLORA)
                .addIngredient(ModItems.TOMATO.get())
                .addIngredient(Items.MOSS_BLOCK)
                .unlockedByItems(getHasName(DDItems.SNIFFERWURST.get()), DDItems.SNIFFERWURST.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(DungeonsDelight.MOD_ID, consumer);

        DDCookingPotRecipeBuilder.cookingPotRecipe(DDItems.SNIFFERWURST.get(), 1, 200, 1.0F)
                .addIngredient(DDItems.SNIFFER_SHANK.get())
                .addIngredient(DDItems.SLIME_BAR.get())
                .addIngredient(DDTags.ItemT.ANCIENT_FLORA)
                .addIngredient(Items.MOSS_BLOCK)
                .unlockedByItems(getHasName(DDItems.SNIFFER_SHANK.get()), DDItems.SNIFFER_SHANK.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(DungeonsDelight.MOD_ID, consumer);
    }

    private static void crafting(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.MONSTER_POT.get(), 1)
                .define('#', ModItems.COOKING_POT.get()).define('@', DDItems.STAINED_SCRAP.get())
                .define('$', Items.SPIDER_EYE).define('&', Items.BONE)
                .pattern("$&$")
                .pattern("@#@")
                .pattern("@@@").unlockedBy(getHasName(ModItems.COOKING_POT.get()), has(ModItems.COOKING_POT.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SLIME_BAR.get(), 1)
                .requires(DDTags.ItemT.SLIME_BALLS).requires(DDTags.ItemT.SLIME_BALLS).requires(ModItems.CANVAS.get()).requires(Items.DRIED_KELP)
                .unlockedBy(getItemName(Items.SLIME_BALL), has(Items.SLIME_BALL))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.SLIME_BAR.get()) + "_from_shapeless");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.DUNGEON_STOVE.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', Items.TUFF).define('%', Items.CAMPFIRE)
                .pattern("###")
                .pattern("@ @")
                .pattern("@%@").unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDBlocks.LIVING_CANDLE.get(), 1)
                .pattern("W")
                .pattern("G")
                .pattern("S")
                .define('W', DDBlocks.WORMROOT_TENDRILS.get()).define('G', DDItems.GUNK.get()).define('S', DDItems.STAINED_SCRAP.get())
                .unlockedBy(getHasName(DDItems.GUNK.get()), has(DDItems.GUNK.get()))
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK, 2)
                .define('#', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern("#")
                .pattern("#").unlockedBy(getHasName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get()))
                .save(consumer, "dungeonsdelight:stick_from_wormroots");

        buttonBuilder(DDBlocks.WORMWOOD_BUTTON.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_BUTTON.get()), has(DDBlocks.WORMWOOD_BUTTON.get())).save(consumer);
        doorBuilder(DDBlocks.WORMWOOD_DOOR.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_DOOR.get()), has(DDBlocks.WORMWOOD_DOOR.get())).save(consumer);
        trapdoorBuilder(DDBlocks.WORMWOOD_TRAPDOOR.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_TRAPDOOR.get()), has(DDBlocks.WORMWOOD_TRAPDOOR.get())).save(consumer);
        fenceBuilder(DDBlocks.WORMWOOD_FENCE.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_FENCE.get()), has(DDBlocks.WORMWOOD_FENCE.get())).save(consumer);
        fenceGateBuilder(DDBlocks.WORMWOOD_FENCE_GATE.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_FENCE_GATE.get()), has(DDBlocks.WORMWOOD_FENCE_GATE.get())).save(consumer);
        pressurePlate(consumer, DDBlocks.WORMWOOD_PRESSURE_PLATE.get(), DDBlocks.WORMWOOD_PLANKS.get());
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_SLAB.get(), DDBlocks.WORMWOOD_PLANKS.get());
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_MOSAIC_SLAB.get(), DDBlocks.WORMWOOD_MOSAIC.get());
        stairBuilder(DDBlocks.WORMWOOD_STAIRS.get(), Ingredient.of(DDBlocks.WORMWOOD_PLANKS.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_STAIRS.get()), has(DDBlocks.WORMWOOD_STAIRS.get())).save(consumer);
        stairBuilder(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get(), Ingredient.of(DDBlocks.WORMWOOD_MOSAIC.get())).unlockedBy(getHasName(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get()), has(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get())).save(consumer);
        mosaicBuilder(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_MOSAIC.get(), DDBlocks.WORMWOOD_SLAB.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDBlocks.WORMWOOD_CABINET.get()).pattern("___")
                .pattern("D D").pattern("___")
                .define('_', DDBlocks.WORMWOOD_SLAB.get()).define('D', DDBlocks.WORMWOOD_TRAPDOOR.get())
                .unlockedBy("has_wormwood_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(DDBlocks.WORMWOOD_TRAPDOOR.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.BUBBLEGUNK.get(), 1)
                .requires(DDItems.GUNK.get()).requires(DDItems.GUNK.get()).requires(DDBlocks.WORMROOT_TENDRILS.get())
                .group(DDItems.BUBBLEGUNK.toString())
                .unlockedBy(getItemName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.BUBBLEGUNK.get()) + "_from_shapeless");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.EMBEDDED_EGGS.get(), 1)
                .define('#', DDItems.SCULK_POLYP.get()).define('@', Items.EGG)
                .pattern("@#@")
                .pattern("#@#")
                .pattern("@#@").unlockedBy(getHasName(Blocks.SCULK), has(Blocks.SCULK)).save(consumer);

        cleaver(DDItems.FLINT_CLEAVER.get(), Ingredient.of(Items.FLINT)).unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(consumer);
        cleaver(DDItems.IRON_CLEAVER.get(), Ingredient.of(Items.IRON_INGOT)).unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(consumer);
        cleaver(DDItems.GOLDEN_CLEAVER.get(), Ingredient.of(Items.GOLD_INGOT)).unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(consumer);
        cleaver(DDItems.DIAMOND_CLEAVER.get(), Ingredient.of(Items.DIAMOND)).unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND)).save(consumer);
        netheriteSmithing(consumer, DDItems.DIAMOND_CLEAVER.get(), RecipeCategory.COMBAT, DDItems.NETHERITE_CLEAVER.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.SCULK_MAYO_BLOCK.get(), 1)
                .define('#', DDItems.SCULK_MAYO.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDItems.SCULK_MAYO.get()), has(DDItems.SCULK_MAYO.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOTS_BLOCK.get(), 1)
                .define('#', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOT_TENDRILS.get(), 9)
                .requires(DDBlocks.WORMROOTS_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.WORMROOTS_BLOCK.get()), has(DDBlocks.WORMROOTS_BLOCK.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDBlocks.WORMROOT_TENDRILS.get()) + "_from_wormroots_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMWOOD_PLANKS.get(), 2)
                .define('#', DDBlocks.WORMROOT_TENDRILS.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDBlocks.WORMWOOD_PLANKS.get()) + "_from_wormroots");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DDBlocks.WORMROOT_TENDRILS.get(), 4)
                .requires(DDBlocks.WORMROOT_STALK.get())
                .unlockedBy(getItemName(DDBlocks.WORMROOT_STALK.get()), has(DDBlocks.WORMROOT_STALK.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDBlocks.WORMROOT_TENDRILS.get()) + "_from_wormroot_stalk");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.ROTBULB_CRATE.get(), 1)
                .define('#', DDItems.ROTBULB.get())
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(DDItems.ROTBULB.get()), has(DDItems.ROTBULB.get())).save(consumer, "rotbulb_crate");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.ROTBULB.get(), 9)
                .requires(DDBlocks.ROTBULB_CRATE.get())
                .unlockedBy(getItemName(DDBlocks.ROTBULB_CRATE.get()), has(DDBlocks.ROTBULB_CRATE.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.ROTBULB.get()) + "_from_rotbulb_crate");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_BLOCK.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_BARS.get(), 16)
                .define('#', DDItems.STAINED_SCRAP.get())
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP.get(), 4)
                .define('#', DDBlocks.STAINED_SCRAP_BLOCK.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDBlocks.STAINED_SCRAP_BLOCK.get()), has(DDBlocks.STAINED_SCRAP_BLOCK.get())).save(consumer);

        stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP.get(), DDBlocks.STAINED_SCRAP_BLOCK.get(), 4);

        stairBuilder(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), Ingredient.of(DDBlocks.CUT_STAINED_SCRAP.get())).unlockedBy(getHasName(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get()), has(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get())).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDBlocks.CUT_STAINED_SCRAP.get());

        stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 1);
        stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDBlocks.CUT_STAINED_SCRAP.get(), 2);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SLICORICE.get(), 1)
                .requires(DDBlocks.WORMROOT_TENDRILS.get()).requires(Items.SUGAR).requires(Items.SLIME_BALL)
                .unlockedBy(getItemName(DDBlocks.WORMROOT_TENDRILS.get()), has(DDBlocks.WORMROOT_TENDRILS.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDBlocks.SCULK_TART.get(), 1)
                .define('#', Items.SUGAR).define('@', DDItems.SCULK_POLYP.get())
                .define('!', ModItems.PIE_CRUST.get()).define('^', DDItems.ANCIENT_EGG.get())
                .pattern("@@@")
                .pattern("^^^")
                .pattern("#!#").unlockedBy(getHasName(DDItems.SCULK_POLYP.get()), has(DDItems.SCULK_POLYP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDItems.MONSTER_CAKE.get(), 1)
                .define('#', DDItems.ROTBULB.get()).define('@', DDItems.ANCIENT_EGG.get())
                .define('!', DDTags.ItemT.ACIDICS).define('^', Items.SPIDER_EYE)
                .pattern("!!!")
                .pattern("^@^")
                .pattern("###").unlockedBy(getHasName(DDItems.ROTBULB.get()), has(DDItems.ROTBULB.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.MONSTER_CAKE.get(), 1)
                .requires(DDItems.MONSTER_CAKE_SLICE.get()).requires(DDItems.MONSTER_CAKE_SLICE.get()).requires(DDItems.MONSTER_CAKE_SLICE.get())
                .requires(DDItems.MONSTER_CAKE_SLICE.get()).requires(DDItems.MONSTER_CAKE_SLICE.get()).requires(DDItems.MONSTER_CAKE_SLICE.get())
                .requires(DDItems.MONSTER_CAKE_SLICE.get())
                .unlockedBy(getItemName(DDItems.MONSTER_CAKE_SLICE.get()), has(DDItems.MONSTER_CAKE_SLICE.get())).save(consumer, "monster_cake_from_slices");

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDBlocks.SCULK_TART.get(), 1)
                .define('#', DDItems.SCULK_TART_SLICE.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDItems.SCULK_TART_SLICE.get()), has(DDItems.SCULK_TART_SLICE.get())).save(consumer, "sculk_tart_from_slices");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.POI.get(), 1)
                .requires(Items.BOWL).requires(DDItems.ROTBULB.get()).requires(DDItems.ROTBULB.get())
                .requires(DDItems.ROTBULB.get()).requires(DDItems.ROTBULB.get()).requires(DDItems.ROTBULB.get())
                .requires(DDItems.ROTBULB.get())
                .unlockedBy(getItemName(DDItems.ROTBULB.get()), has(DDItems.ROTBULB.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDItems.SPIDER_PIE.get(), 1)
                .define('#', DDItems.SPIDER_PIE_SLICE.get())
                .pattern("##")
                .pattern("##").unlockedBy(getHasName(DDItems.SPIDER_PIE_SLICE.get()), has(DDItems.SPIDER_PIE_SLICE.get())).save(consumer, "spider_pie_from_slices");

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DDItems.SPIDER_PIE.get(), 1)
                .define('#', Items.SUGAR).define('@', DDTags.ItemT.ACIDICS)
                .define('!', ModItems.PIE_CRUST.get()).define('^', Items.FERMENTED_SPIDER_EYE)
                .pattern("@@@")
                .pattern("^^^")
                .pattern("#!#").unlockedBy(getHasName(Items.FERMENTED_SPIDER_EYE), has(DDTags.ItemT.ACIDICS)).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DDItems.SCULK_APPLE.get(), 1)
                .requires(Items.APPLE).requires(DDItems.SCULK_POLYP.get()).requires(DDItems.SCULK_POLYP.get())
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getItemName(DDItems.SCULK_POLYP.get()), has(DDItems.SCULK_POLYP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.ROTTEN_TOMATO_CRATE.get(), 1)
                .define('#', ModItems.ROTTEN_TOMATO.get())
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(ModItems.ROTTEN_TOMATO.get()), has(ModItems.ROTTEN_TOMATO.get())).save(consumer, "rotten_tomato_crate");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDBlocks.POISONOUS_POTATO_CRATE.get(), 1)
                .define('#', Items.POISONOUS_POTATO)
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(Items.POISONOUS_POTATO), has(Items.POISONOUS_POTATO)).save(consumer, "poisonous_potato_crate");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.POISONOUS_POTATO, 9)
                .requires(DDBlocks.POISONOUS_POTATO_CRATE.get())
                .unlockedBy(getItemName(DDBlocks.POISONOUS_POTATO_CRATE.get()), has(DDBlocks.POISONOUS_POTATO_CRATE.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(Items.POISONOUS_POTATO) + "_from_poisonous_potato_crate");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ROTTEN_TOMATO.get(), 9)
                .requires(DDBlocks.ROTTEN_TOMATO_CRATE.get())
                .unlockedBy(getItemName(DDBlocks.ROTTEN_TOMATO_CRATE.get()), has(DDBlocks.ROTTEN_TOMATO_CRATE.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(ModItems.ROTTEN_TOMATO.get()) + "_from_rotten_tomato_crate");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDBlocks.LIVING_CAMPFIRE.get(), 1)
                .pattern(" C ")
                .pattern("CSC")
                .pattern("WWW")
                .define('W', DDBlocks.WORMROOTS_BLOCK.get()).define('C', DDBlocks.WORMROOT_TENDRILS.get()).define('S', DDItems.STAINED_SCRAP.get())
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDBlocks.LIVING_TORCH.get(), 4)
                .pattern("S")
                .pattern("C")
                .define('C', DDBlocks.WORMROOT_TENDRILS.get()).define('S', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP_FRAGMENT.get()), has(DDItems.STAINED_SCRAP_FRAGMENT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDBlocks.LIVING_LANTERN.get(), 1)
                .pattern("SSS")
                .pattern("SCS")
                .pattern("SSS")
                .define('C', DDBlocks.LIVING_TORCH.get()).define('S', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP_FRAGMENT.get()), has(DDItems.STAINED_SCRAP_FRAGMENT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.STAINED_SCRAP_FRAGMENT.get(), 9)
                .requires(DDItems.STAINED_SCRAP.get())
                .unlockedBy(getItemName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.STAINED_SCRAP.get()) + "_from_stained_scrap");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DDItems.STAINED_SCRAP.get(), 1)
                .define('#', DDItems.STAINED_SCRAP_FRAGMENT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy(getHasName(DDItems.STAINED_SCRAP_FRAGMENT.get()), has(DDItems.STAINED_SCRAP_FRAGMENT.get())).save(consumer, "stained_scrap_from_fragment");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_GRATE.get(), 4)
                .define('#', DDBlocks.STAINED_SCRAP_BLOCK.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ").unlockedBy(getHasName(DDBlocks.STAINED_SCRAP_BLOCK.get()), has(DDBlocks.STAINED_SCRAP_BLOCK.get())).save(consumer);

        stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, DDBlocks.STAINED_SCRAP_GRATE.get(), DDBlocks.STAINED_SCRAP_BLOCK.get(), 4);

        //TODO: OVERHAUL STAINED WEAPON
        //stainedCleaver(DDItems.STAINED_CLEAVER.get()).unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);
        //stainedKnife(DDItems.STAINED_KNIFE.get()).unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DDItems.GUNK_ARROW.get(), 2)
                .define('#', DDItems.GUNK.get()).define('@', Items.ARROW)
                .pattern(" # ")
                .pattern("#@#")
                .pattern(" # ").unlockedBy(getHasName(DDItems.GUNK.get()), has(DDItems.GUNK.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DDItems.STAINED_SCRAP.get(), 4)
                .requires(DDBlocks.STAINED_SCRAP_BLOCK.get())
                .unlockedBy(getItemName(DDBlocks.STAINED_SCRAP_BLOCK.get()), has(DDBlocks.STAINED_SCRAP_BLOCK.get()))
                .save(consumer, "dungeonsdelight:" + getItemName(DDItems.STAINED_SCRAP.get()) + "_from_stained_scrap_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DDItems.STAINED_LANTERN.get(), 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', DDItems.STAINED_SCRAP_FRAGMENT.get()).define('!', DDItems.LIVING_TORCH.get())
                .pattern("@#@")
                .pattern("@!@")
                .pattern("@#@")
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP.get()), has(DDItems.STAINED_SCRAP.get()))
                .unlockedBy(getHasName(DDItems.STAINED_SCRAP_FRAGMENT.get()), has(DDItems.STAINED_SCRAP_FRAGMENT.get()))
                .save(consumer);

        woodenBoat(consumer, DDItems.WORMWOOD_BOAT.get(), DDBlocks.WORMWOOD_PLANKS.get());
        chestBoat(consumer, DDItems.WORMWOOD_CHEST_BOAT.get(), DDItems.WORMWOOD_BOAT.get());
    }

    private static void smelting(RecipeOutput consumer) {
        smokingRecipe(DDItems.SPIDER_MEAT.get(), DDItems.SMOKED_SPIDER_MEAT.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);

        smokingRecipe(DDItems.GHAST_CALAMARI.get(), DDItems.FRIED_GHAST_CALAMARI.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);

        smeltingRecipe(DDItems.SNIFFER_SHANK.get(), DDItems.COOKED_SNIFFER_SHANK.get(), RecipeCategory.FOOD, 200, 0.1F, consumer);
        smokingRecipe(DDItems.SNIFFER_SHANK.get(), DDItems.COOKED_SNIFFER_SHANK.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);
        campfireRecipe(DDItems.SNIFFER_SHANK.get(), DDItems.COOKED_SNIFFER_SHANK.get(), RecipeCategory.FOOD, 600, 0.0F, consumer);

        smokingRecipe(Items.SNIFFER_EGG, DDItems.SOFT_SERVE_SNIFFER_EGG.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);

        smeltingRecipe(DDItems.SNIFFERWURST.get(), DDItems.COOKED_SNIFFERWURST.get(), RecipeCategory.FOOD, 200, 0.1F, consumer);
        smokingRecipe(DDItems.SNIFFERWURST.get(), DDItems.COOKED_SNIFFERWURST.get(), RecipeCategory.FOOD, 100, 0.1F, consumer);
        campfireRecipe(DDItems.SNIFFERWURST.get(), DDItems.COOKED_SNIFFERWURST.get(), RecipeCategory.FOOD, 600, 0.0F, consumer);
    }

    private static void cutting(RecipeOutput consumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.GHAST_TENTACLE.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.GHAST_CALAMARI.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.SLIME_BAR.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.SLIME_NOODLES.get(), 2).addResult(ModItems.CANVAS.get()).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.SCULK, Blocks.SCULK_VEIN),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.SCULK_POLYP.get(), 1).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.SCULK_MAYO_BLOCK.get(), Blocks.SCULK_SHRIEKER, Blocks.SCULK_CATALYST, Blocks.SCULK_SENSOR),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.SCULK_POLYP.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Blocks.CALIBRATED_SCULK_SENSOR),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.SCULK_POLYP.get(), 2).addResult(Items.AMETHYST_SHARD).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.ANCIENT_EGG.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.CLEAVED_ANCIENT_EGG.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.ROTTEN_FLESH),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.ROTTEN_TRIPE.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.ROTBULB.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.GUNK.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.SCULK_TART.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.SCULK_TART_SLICE.get(), 4).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.MONSTER_CAKE.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.MONSTER_CAKE_SLICE.get(), 7).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.GRITTY_FLESH.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.ROTTEN_TRIPE.get(), 2)
                .addResultWithChance(Items.SAND, 0.45F, 3).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.BRINED_FLESH.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.ROTTEN_TRIPE.get(), 2)
                .addResultWithChance(Items.SEAGRASS, 0.45F, 3).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.SPIDER_PIE.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.SPIDER_PIE_SLICE.get(), 4).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.WARDENZOLA.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.WARDENZOLA_CRUMBLES.get(), 2).build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.GUNK.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.STRAW.get(), 2)
                .addResultWithChance(Items.BONE_MEAL, 0.6F, 2)
                .addResultWithChance(DDBlocks.WORMROOT_TENDRILS.get(), 0.25F, 1)
                .addResultWithChance(Items.SLIME_BALL, 0.4F, 2)
                .build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDBlocks.ROTBULB_PLANT.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.GUNK.get(), 2)
                .addResultWithChance(Items.PURPLE_DYE, 0.5F, 2)
                .build(consumer);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DDItems.CREEPERILLA.get()),
                Ingredient.of(CommonTags.TOOLS_KNIFE), DDItems.CREEPERILLA_SQUIB.get(), 2).build(consumer);
    }

    protected static void smeltingRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, RecipeOutput consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer, output + "_from_smelting");
    }

    protected static void smokingRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, RecipeOutput consumer) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer, output + "_from_smoking");
    }

    protected static void campfireRecipe(Item ingredient, Item output, RecipeCategory category, int time, float xp, RecipeOutput consumer) {
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), category, output , xp, time).unlockedBy(getItemName(ingredient), has(ingredient)).save(consumer, output + "_from_blasting");
    }

    protected static RecipeBuilder cleaver(ItemLike output, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .define('#', ingredient).define('@', Items.STICK)
                .pattern("##")
                .pattern("#@");
    }

    protected static RecipeBuilder stainedCleaver(ItemLike output) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', DDBlocks.WORMROOT_TENDRILS.get())
                .define('!', Items.NETHERITE_SCRAP)
                .pattern("##!")
                .pattern("#@ ");
    }

    protected static RecipeBuilder stainedKnife(ItemLike output) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .define('#', DDItems.STAINED_SCRAP.get()).define('@', DDBlocks.WORMROOT_TENDRILS.get())
                .define('!', Items.NETHERITE_SCRAP)
                .pattern("#!")
                .pattern("@ ");
    }
}