package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.item.Item;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;

import java.util.function.Supplier;

public class DDItems {
    //MISC
    public static final Supplier<Item> LOGO_ITEM = register("logo_item", () -> new Item(DDProperties.ItemP.GENERIC));

    public static Supplier<Item> register(String id, Supplier<Item> supplier) {
        return Services.REGISTRY.registerItem(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
