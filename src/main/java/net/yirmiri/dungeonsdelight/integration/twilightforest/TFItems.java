package net.yirmiri.dungeonsdelight.integration.twilightforest;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDMaterials;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.integration.common.*;
import net.yirmiri.dungeonsdelight.integration.util.INProperties;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class TFItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DungeonsDelight.MOD_ID);

    //TOOLS
    public static final RegistryObject<Item> IRONWOOD_KNIFE = ITEMS.register("ironwood_knife", () -> new INKnifeItem(IntegrationIds.TWILIGHTFOREST, DDMaterials.DDCMaterials.IRONWOOD, 0.5F, -2.0F, DDProperties.ItemP.GENERIC));
    public static final RegistryObject<Item> IRONWOOD_CLEAVER = ITEMS.register("ironwood_cleaver", () -> new INCleaverItem(IntegrationIds.TWILIGHTFOREST, 1.5F, DDMaterials.DDCMaterials.IRONWOOD, 2.0F, -3.0F, DDProperties.ItemP.GENERIC));

    public static final RegistryObject<Item> STEELEAF_KNIFE = ITEMS.register("steeleaf_knife", () -> new INKnifeItem(IntegrationIds.TWILIGHTFOREST, DDMaterials.DDCMaterials.STEELEAF, 0.5F, -2.0F, DDProperties.ItemP.GENERIC));
    public static final RegistryObject<Item> STEELEAF_CLEAVER = ITEMS.register("steeleaf_cleaver", () -> new INCleaverItem(IntegrationIds.TWILIGHTFOREST, 1.75F, DDMaterials.DDCMaterials.STEELEAF, 2.0F, -3.0F, DDProperties.ItemP.GENERIC));

    public static final RegistryObject<Item> KNIGHTMETAL_KNIFE = ITEMS.register("knightmetal_knife", () -> new INKnifeItem(IntegrationIds.TWILIGHTFOREST, DDMaterials.DDCMaterials.KNIGHTMETAL, 0.5F, -2.0F, DDProperties.ItemP.GENERIC));
    public static final RegistryObject<Item> KNIGHTMETAL_CLEAVER = ITEMS.register("knightmetal_cleaver", () -> new INCleaverItem(IntegrationIds.TWILIGHTFOREST, 1.75F, DDMaterials.DDCMaterials.KNIGHTMETAL, 2.0F, -3.0F, DDProperties.ItemP.GENERIC));

    public static final RegistryObject<Item> FIERY_KNIFE = ITEMS.register("fiery_knife", () -> new INKnifeItem(IntegrationIds.TWILIGHTFOREST, DDMaterials.DDCMaterials.FIERY, 0.5F, -2.0F, DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final RegistryObject<Item> FIERY_CLEAVER = ITEMS.register("fiery_cleaver", () -> new INCleaverItem(IntegrationIds.TWILIGHTFOREST, 1.75F, DDMaterials.DDCMaterials.FIERY, 2.0F, -3.0F, DDProperties.ItemP.GENERIC_UNCOMMON));

    //INGREDIENT FOODS
    public static final RegistryObject<Item> BUG_CHOPS = ITEMS.register("bug_chops", () -> new INItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.BUG_CHOPS));
    public static final RegistryObject<Item> FRIED_BUG_CHOPS = ITEMS.register("fried_bug_chops", () -> new INItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.FRIED_BUG_CHOPS));
    public static final RegistryObject<Item> TORCHBERRY_RAISINS = ITEMS.register("torchberry_raisins", () -> new INItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.TORCHBERRY_RAISINS));

    //GENERIC FOODS
    public static final RegistryObject<Item> WILDERNESS_LUNCHEON = ITEMS.register("wilderness_luncheon", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.WILDERNESS_LUNCHEON, false, false));
    public static final RegistryObject<Item> MAZE_ROLL = ITEMS.register("maze_roll", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.MAZE_ROLL, false, false));
    public static final RegistryObject<Item> MEEF_WELLINGTON = ITEMS.register("meef_wellington", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.MEEF_WELLINGTON, false, false));
    public static final RegistryObject<Item> SWEETBREAD = ITEMS.register("sweetbread", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.SWEETBREAD, true, false));

    //MEALS
    public static final RegistryObject<Item> TOWER_BOREITO = ITEMS.register("tower_boreito", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.TOWER_BOREITO, true, false));
    public static final RegistryObject<Item> AURORA_ICE_CREAM = ITEMS.register("aurora_ice_cream", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.AURORA_ICE_CREAM, true, false));
    public static final RegistryObject<Item> BLAZING_BLOOD_SAUSAGE = ITEMS.register("blazing_blood_sausage", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.BLAZING_BLOOD_SAUSAGE, true, false));
    public static final RegistryObject<Item> ARCANE_CHILI = ITEMS.register("arcane_chili", () -> new INArcaneChiliItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.ARCANE_CHILI, true));
    public static final RegistryObject<Item> HYDRA_FRICASSEE = ITEMS.register("hydra_fricassee", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.HYDRA_FRICASSEE, true, false));
    public static final RegistryObject<Item> SCALY_FIDDLEHEAD_RISOTTO = ITEMS.register("scaly_fiddlehead_risotto", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.SCALY_FIDDLEHEAD_RISOTTO, true, false));

    //DRINKS
    public static final RegistryObject<Item> LIVEROOT_BEER = ITEMS.register("liveroot_beer", () -> new INDrinkableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.LIVEROOT_BEER, true, false));
    public static final RegistryObject<Item> TROLLBER_CHUTNEY = ITEMS.register("trollber_chutney", () -> new INConsumableItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.TROLLBER_CHUTNEY, true, false));
}
