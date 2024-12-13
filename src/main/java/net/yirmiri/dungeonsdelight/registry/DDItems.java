package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.item.AmethystRockCandyItem;
import net.yirmiri.dungeonsdelight.item.BreezeCreamConeItem;
import net.yirmiri.dungeonsdelight.item.TrialFreakshakeItem;
import net.yirmiri.dungeonsdelight.util.DDProperties;

public class DDItems {
    //GENERIC FOODS
    public static final Item SLIME_SLAB = register("slime_slab", new Item(DDProperties.ItemP.SLIME_SLAB));
    public static final Item SLIME_NOODLES = register("slime_noodles", new Item(DDProperties.ItemP.SLIME_NOODLES));
    public static final Item SILVERFISH_THORAX = register("silverfish_thorax", new Item(DDProperties.ItemP.SILVERFISH_THORAX));

    //MEAL FOODS

    //SPECIAL FOODS
    public static final Item BREEZE_CREAM_CONE = register("breeze_cream_cone", new BreezeCreamConeItem(DDProperties.ItemP.BREEZE_CREAM_CONE));
    public static final Item TRIAL_FREAKSHAKE = register("trial_freakshake", new TrialFreakshakeItem(DDProperties.ItemP.TRIAL_FREAKSHAKE));
    public static final Item AMETHYST_ROCK_CANDY = register("amethyst_rock_candy", new AmethystRockCandyItem(DDProperties.ItemP.AMETHYST_ROCK_CANDY));
    public static final Item CANDIED_VEX_SUCKER = register("candied_vex_sucker", new Item(DDProperties.ItemP.CANDIED_VEX_SUCKER));
    public static final Item CANDIED_SILVERFISH_SUCKER = register("candied_silverfish_sucker", new Item(DDProperties.ItemP.CANDIED_SILVERFISH_SUCKER));

    //MISC
    public static final Item LOGO_ITEM = register("logo_item", new Item(DDProperties.ItemP.LOGO));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonsDelight.MOD_ID, id), item);
    }

    public static void loadItems() {
    }
}
