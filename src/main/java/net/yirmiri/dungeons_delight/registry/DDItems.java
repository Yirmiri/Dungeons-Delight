package net.yirmiri.dungeons_delight.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeons_delight.DungeonsDelight;
import net.yirmiri.dungeons_delight.util.DDProperties;

public class DDItems {
    //MISC
    public static final Item LOGO_ITEM = register("logo_item", new Item(DDProperties.ItemP.LOGO));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonsDelight.MOD_ID, id), item);
    }

    public static void loadItems() {
    }
}
