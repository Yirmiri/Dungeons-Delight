package net.yirmiri.dungeonsdelight.integration.content.aether;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDMaterials;
import net.yirmiri.dungeonsdelight.integration.common.*;
import net.yirmiri.dungeonsdelight.integration.util.INProperties;
import net.yirmiri.dungeonsdelight.integration.util.INUtil;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.function.Supplier;

public class AEItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, DungeonsDelight.MOD_ID);

    //TOOLS
    public static final Supplier<Item> ZANITE_KNIFE = ITEMS.register("zanite_knife", () -> new INKnifeItem(IntegrationIds.AETHER, DDMaterials.DDCMaterials.ZANITE, DDProperties.ItemP.GENERIC_1.attributes(KnifeItem.createAttributes(DDMaterials.DDCMaterials.ZANITE, 0.5F, -2.0F))));
    public static final Supplier<Item> ZANITE_CLEAVER = ITEMS.register("zanite_cleaver", () -> new INCleaverItem(IntegrationIds.AETHER, 1.75F, DDMaterials.DDCMaterials.ZANITE, 2.0F, -3.0F, DDProperties.ItemP.GENERIC_1));

    public static final Supplier<Item> GRAVITITE_KNIFE = ITEMS.register("gravitite_knife", () -> new INKnifeItem(IntegrationIds.AETHER, DDMaterials.DDCMaterials.GRAVITITE, DDProperties.ItemP.GENERIC_1.attributes(KnifeItem.createAttributes(DDMaterials.DDCMaterials.ZANITE, 0.5F, -2.0F))));
    public static final Supplier<Item> GRAVITITE_CLEAVER = ITEMS.register("gravitite_cleaver", () -> new INCleaverItem(IntegrationIds.AETHER, 1.75F, DDMaterials.DDCMaterials.GRAVITITE, 2.0F, -3.0F, DDProperties.ItemP.GENERIC_1));

    //INGREDIENT FOODS
    public static final Supplier<Item> MARBLED_MEAT = ITEMS.register("marbled_meat", () -> new INItem(IntegrationIds.AETHER, INProperties.ItemP.MARBLED_MEAT));
    public static final Supplier<Item> COOKED_MARBLED_MEAT = ITEMS.register("cooked_marbled_meat", () -> new INItem(IntegrationIds.AETHER, INProperties.ItemP.COOKED_MARBLED_MEAT));
    public static final Supplier<Item> VOLAILLE = ITEMS.register("volaille", () -> new INItem(IntegrationIds.AETHER, INProperties.ItemP.VOLAILLE));

    //GENERIC FOODS
    public static final Supplier<Item> VENOMOUS_ONIGIRI = ITEMS.register("venomous_onigiri", () -> new INConsumableItem(IntegrationIds.AETHER, INProperties.ItemP.VENOMOUS_ONIGIRI, true, false));
    public static final Supplier<Item> FLUFFY_FLOSS = ITEMS.register("fluffy_floss", () -> new INBiteableItem(IntegrationIds.AETHER, INProperties.ItemP.FLUFFY_FLOSS.craftRemainder(INUtil.dynamicCraftRemainder(IntegrationIds.AETHER, "skyroot_stick", Items.STICK)), 8, true));

    //MEALS
    public static final Supplier<Item> AMBER_E_OLIO = ITEMS.register("amber_e_olio", () -> new INSlimeFoodItem(IntegrationIds.AETHER, INProperties.ItemP.AMBER_E_OLIO, 0.12F, true));
    public static final Supplier<Item> AMBROSIA_RING = ITEMS.register("ambrosia_ring", () -> new INSlimeFoodItem(IntegrationIds.AETHER, INProperties.ItemP.AMBROSIA_RING, 0.2F, true));

    //DRINKS
    public static final Supplier<Item> SKYBERRY_BREW = ITEMS.register("skyberry_brew", () -> new INDrinkableItem(IntegrationIds.AETHER, INProperties.ItemP.SKYBERRY_BREW, true, false));
}
