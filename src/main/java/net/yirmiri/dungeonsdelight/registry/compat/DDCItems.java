package net.yirmiri.dungeonsdelight.registry.compat;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.item.compat.CompatConsumableItem;
import net.yirmiri.dungeonsdelight.item.compat.CompatDrinkableItem;
import net.yirmiri.dungeonsdelight.item.compat.CompatBiteableItem;
import net.yirmiri.dungeonsdelight.item.compat.CompatItem;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import net.yirmiri.dungeonsdelight.init.DDTags;
import net.yirmiri.dungeonsdelight.util.compat.DDCProperties;
import net.yirmiri.dungeonsdelight.util.DDUtil;

public class DDCItems {
    //INGREDIENT FOODS
    public static final RegistryObject<Item> FRIED_RAT = DDItems.ITEMS.register("fried_rat", () -> new CompatItem(DDUtil.FN_ID, DDCProperties.ItemP.FRIED_RAT));

    //GENERIC FOODS
    public static final RegistryObject<Item> TORCHBERRY_RAISINS = DDItems.ITEMS.register("torchberry_raisins", () -> new CompatItem(DDUtil.TF_ID, DDCProperties.ItemP.TORCHBERRY_RAISINS));
    public static final RegistryObject<Item> LIVEROOT_BEER = DDItems.ITEMS.register("liveroot_beer", () -> new CompatDrinkableItem(DDUtil.TF_ID, DDCProperties.ItemP.LIVINGROOTBEER, true, false));

    //MEALS
    public static final RegistryObject<Item> MEEF_WELLINGTON = DDItems.ITEMS.register("meef_wellington", () -> new CompatConsumableItem(DDUtil.TF_ID, DDCProperties.ItemP.MEEF_WELLINGTON, true, false));
    public static final RegistryObject<Item> BRAISED_GLOWWORM_QUEEN = DDItems.ITEMS.register("braised_glowworm_queen", () -> new CompatBiteableItem(DDUtil.TF_ID, DDTags.ItemT.REFILLS_BRAISED_GLOWWORM_QUEEN, DDCProperties.ItemP.BRAISED_GLOWWORM_QUEEN, true));

    public static void register() {
    }
}
