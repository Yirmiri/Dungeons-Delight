package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.PublicRecordItem;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;

import java.util.function.Supplier;

public class DDItems {
    //MISC
    public static final Supplier<Item> LOGO_ITEM = register("logo_item", () -> new Item(DDProperties.ItemP.GENERIC));
    // TODO: Music Discs will need datadrive in 1.21
    public static final Supplier<Item> MUSIC_DISC_MALADY = register("music_disc_malady", () -> new PublicRecordItem(6, DDSounds.DISC_MALADY.get(), DDProperties.ItemP.MUSIC_DISC_MALADY, 382));

    //INGREDIENTS

    //STAINED SCRAP

    //LIVING FIRE

    //BLOCK ITEMS

    //TOOLS

    //INGREDIENT FOODS

    //GENERIC FOODS

    //MEALS

    //DRINKS

    public static Supplier<Item> register(String id, Supplier<Item> supplier) {
        return Services.REGISTRY.registerItem(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
