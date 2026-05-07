package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.common.item.PublicRecordItem;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDTiers;

import java.util.function.Supplier;

public class DDItems {
    //MISC
    public static final Supplier<Item> LOGO_ITEM = register("logo_item", () -> new Item(DDProperties.ItemP.GENERIC_MONSTER));
    //TODO: Music Discs will need datadrive in 1.21
    public static final Supplier<Item> MUSIC_DISC_MALADY = register("music_disc_malady", () -> new PublicRecordItem(6, DDSounds.DISC_MALADY.get(), DDProperties.ItemP.MONSTER_DISC, 382));
    public static final Supplier<Item> MUSIC_DISC_MALADY_B_SIDE = register("music_disc_malady_b_side", () -> new PublicRecordItem(13, DDSounds.DISC_MALADY_B.get(), DDProperties.ItemP.MONSTER_DISC, 382));

    //TOOLS
    public static final Supplier<Item> FLINT_CLEAVER = register("flint_cleaver", () -> new CleaverItem(0.75F, 2.0F, -3.1F, DDTiers.FLINT, DDProperties.ItemP.FLINT));
    public static final Supplier<Item> IRON_CLEAVER = register("iron_cleaver", () -> new CleaverItem(1.0F, 2.0F, -3.1F, Tiers.IRON, DDProperties.ItemP.IRON));
    public static final Supplier<Item> GOLDEN_CLEAVER = register("golden_cleaver", () -> new CleaverItem(1.75F, 2.0F, -3.1F, Tiers.GOLD, DDProperties.ItemP.GOLD));
    public static final Supplier<Item> DIAMOND_CLEAVER = register("diamond_cleaver", () -> new CleaverItem(1.25F, 2.0F, -3.1F, Tiers.DIAMOND, DDProperties.ItemP.DIAMOND));
    public static final Supplier<Item> NETHERITE_CLEAVER = register("netherite_cleaver", () -> new CleaverItem(1.5F, 2.0F, -3.1F, Tiers.NETHERITE, DDProperties.ItemP.NETHERITE));

    public static Supplier<Item> register(String id, Supplier<Item> supplier) {
        return Services.REGISTRY.registerItem(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
