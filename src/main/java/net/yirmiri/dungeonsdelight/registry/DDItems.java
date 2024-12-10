package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.item.BreezeCreamConeItem;
import net.yirmiri.dungeonsdelight.item.TrialFreakshakeItem;
import net.yirmiri.dungeonsdelight.util.DDProperties;

public class DDItems {
    //SPECIAL FOODS
    public static final Item BREEZE_CREAM_CONE = register("breeze_cream_cone", new BreezeCreamConeItem(DDProperties.ItemP.BREEZE_CREAM_CONE));
    public static final Item TRIAL_FREAKSHAKE = register("trial_freakshake", new TrialFreakshakeItem(DDProperties.ItemP.TRIAL_FREAKSHAKE));

    //FOOD INGREDIENTS
    public static final Item SLIME_SLAB = register("slime_slab", new Item(DDProperties.ItemP.SLIME_SLAB));
    public static final Item SLIME_NOODLES = register("slime_noodles", new Item(DDProperties.ItemP.SLIME_NOODLES));

    //MISC
    public static final Item LOGO_ITEM = register("logo_item", new Item(DDProperties.ItemP.LOGO));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonsDelight.MOD_ID, id), item);
    }

    public static void loadItems() {
    }
}
