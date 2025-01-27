package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.item.RockCandyItem;
import net.yirmiri.dungeonsdelight.util.DDProperties;

public class DDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DungeonsDelight.MOD_ID);

    //MISC
    public static final RegistryObject<Item> LOGO_ITEM = ITEMS.register("logo_item", () -> new Item(DDProperties.ItemP.LOGO));

    //INGREDIENTS
    public static final RegistryObject<Item> SLIME_NOODLES = ITEMS.register("slime_noodles", () -> new Item(DDProperties.ItemP.SLIME_NOODLES));
    public static final RegistryObject<Item> SLIME_SLAB = ITEMS.register("slime_slab", () -> new Item(DDProperties.ItemP.SLIME_SLAB));
    public static final RegistryObject<Item> SILVERFISH_THORAX = ITEMS.register("silverfish_thorax", () -> new Item(DDProperties.ItemP.SILVERFISH_THORAX));

    //GENERIC FOODS
    public static final RegistryObject<Item> AMETHYST_ROCK_CANDY = ITEMS.register("amethyst_rock_candy", () -> new RockCandyItem(DDProperties.ItemP.AMETHYST_ROCK_CANDY));
    public static final RegistryObject<Item> CANDIED_SILVERFISH_SUCKER = ITEMS.register("candied_silverfish_sucker", () -> new RockCandyItem(DDProperties.ItemP.CANDIED_SILVERFISH_SUCKER));
    public static final RegistryObject<Item> CANDIED_VEX_SUCKER = ITEMS.register("candied_vex_sucker", () -> new RockCandyItem(DDProperties.ItemP.CANDIED_VEX_SUCKER));

    //MEALS
    public static final RegistryObject<Item> GHOULASH = ITEMS.register("ghoulash", () -> new Item(DDProperties.ItemP.GHOULASH));
}
