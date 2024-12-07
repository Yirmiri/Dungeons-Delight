package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.item.BreezeCreamConeItem;
import net.yirmiri.dungeonsdelight.util.DDProperties;

public class DDItems {
    //FOODS
    public static final Item BREEZE_CREAM_CONE = register("breeze_cream_cone", new BreezeCreamConeItem(DDProperties.ItemP.BREEZE_CREAM_CONE));

    //MISC
    public static final Item LOGO_ITEM = register("logo_item", new Item(DDProperties.ItemP.LOGO));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonsDelight.MOD_ID, id), item);
    }

    public static void loadItems() {
    }
}
