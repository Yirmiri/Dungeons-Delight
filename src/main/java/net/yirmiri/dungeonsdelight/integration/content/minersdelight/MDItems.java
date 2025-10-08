package net.yirmiri.dungeonsdelight.integration.content.minersdelight;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.integration.common.INBiteableItem;
import net.yirmiri.dungeonsdelight.integration.common.INCopperCupFoodItem;
import net.yirmiri.dungeonsdelight.integration.util.INProperties;
import net.yirmiri.dungeonsdelight.integration.util.INUtil;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

import java.util.function.Supplier;

public class MDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, DungeonsDelight.MOD_ID);

    //COPPER CUP FOODS
    public static final Supplier<Item> RUBABOO_CUP = ITEMS.register("rubaboo_cup", () -> new INBiteableItem(IntegrationIds.MINERSDELIGHT, INProperties.ItemP.RUBABOO_CUP.craftRemainder(INUtil.dynamicCraftRemainder(IntegrationIds.MINERSDELIGHT, "copper_cup", Items.BOWL)), 16, true));
    public static final Supplier<Item> SPIDER_SALMAGUNDI_CUP = ITEMS.register("spider_salmagundi_cup", () -> new INCopperCupFoodItem(IntegrationIds.MINERSDELIGHT, INProperties.ItemP.SPIDER_SALMAGUNDI_CUP.craftRemainder(INUtil.dynamicCraftRemainder(IntegrationIds.MINERSDELIGHT, "copper_cup", Items.BOWL))));
    public static final Supplier<Item> SALT_SOAKED_STEW_CUP = ITEMS.register("salt_soaked_stew_cup", () -> new INCopperCupFoodItem(IntegrationIds.MINERSDELIGHT, INProperties.ItemP.SALT_SOAKED_STEW_CUP.craftRemainder(INUtil.dynamicCraftRemainder(IntegrationIds.MINERSDELIGHT, "copper_cup", Items.BOWL))));
    public static final Supplier<Item> POI_CUP = ITEMS.register("poi_cup", () -> new INCopperCupFoodItem(IntegrationIds.MINERSDELIGHT, INProperties.ItemP.POI_CUP.craftRemainder(INUtil.dynamicCraftRemainder(IntegrationIds.MINERSDELIGHT, "copper_cup", Items.BOWL))));
}
