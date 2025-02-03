package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.item.BiteableItem;
import net.yirmiri.dungeonsdelight.item.BubblegunkItem;
import net.yirmiri.dungeonsdelight.item.MonsterBurgerItem;
import net.yirmiri.dungeonsdelight.item.RockCandyItem;
import net.yirmiri.dungeonsdelight.util.DDProperties;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class DDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DungeonsDelight.MOD_ID);

    //MISC
    public static final RegistryObject<Item> LOGO_ITEM = ITEMS.register("logo_item", () -> new Item(DDProperties.ItemP.LOGO));
    public static final RegistryObject<Item> STAINED_SCRAP = ITEMS.register("stained_scrap", () -> new Item(DDProperties.ItemP.GENERIC_UNCOMMON));

    //INGREDIENT FOODS
    public static final RegistryObject<Item> SLIME_NOODLES = ITEMS.register("slime_noodles", () -> new Item(DDProperties.ItemP.SLIME_NOODLES));
    public static final RegistryObject<Item> SLIME_SLAB = ITEMS.register("slime_slab", () -> new Item(DDProperties.ItemP.SLIME_SLAB));
    public static final RegistryObject<Item> SILVERFISH_THORAX = ITEMS.register("silverfish_thorax", () -> new Item(DDProperties.ItemP.SILVERFISH_THORAX));
    public static final RegistryObject<Item> GHAST_CALAMARI = ITEMS.register("ghast_calamari", () -> new Item(DDProperties.ItemP.GHAST_CALAMARI));
    public static final RegistryObject<Item> FRIED_GHAST_CALAMARI = ITEMS.register("fried_ghast_calamari", () -> new Item(DDProperties.ItemP.FRIED_GHAST_CALAMARI));
    public static final RegistryObject<Item> GHAST_TENTACLE = ITEMS.register("ghast_tentacle", () -> new Item(DDProperties.ItemP.GHAST_TENTACLE));
    public static final RegistryObject<Item> SPIDER_EXTRACT = ITEMS.register("spider_extract", () -> new DrinkableItem(DDProperties.ItemP.SPIDER_EXTRACT, true, false));
    public static final RegistryObject<Item> SPIDER_MEAT = ITEMS.register("spider_meat", () -> new Item(DDProperties.ItemP.SPIDER_MEAT));
    public static final RegistryObject<Item> SMOKED_SPIDER_MEAT = ITEMS.register("smoked_spider_meat", () -> new Item(DDProperties.ItemP.SMOKED_SPIDER_MEAT));

    //GENERIC FOODS
    public static final RegistryObject<Item> AMETHYST_ROCK_CANDY = ITEMS.register("amethyst_rock_candy", () -> new RockCandyItem(DDProperties.ItemP.AMETHYST_ROCK_CANDY));
    public static final RegistryObject<Item> CANDIED_SILVERFISH_SUCKER = ITEMS.register("candied_silverfish_sucker", () -> new RockCandyItem(DDProperties.ItemP.CANDIED_SILVERFISH_SUCKER));
    public static final RegistryObject<Item> CANDIED_VEX_SUCKER = ITEMS.register("candied_vex_sucker", () -> new RockCandyItem(DDProperties.ItemP.CANDIED_VEX_SUCKER));
    public static final RegistryObject<Item> SPIDER_TANGHULU = ITEMS.register("spider_tanghulu", () -> new ConsumableItem(DDProperties.ItemP.SPIDER_TANGHULU, true, false));
    public static final RegistryObject<Item> BUBBLEGUNK = ITEMS.register("bubblegunk", () -> new BubblegunkItem(DDProperties.ItemP.BUBBLEGUNK, false));

    //MEALS
    public static final RegistryObject<Item> GHOULASH = ITEMS.register("ghoulash", () -> new ConsumableItem(DDProperties.ItemP.GHOULASH, true, false));
    public static final RegistryObject<Item> SILVERFISH_FRIED_RICE = ITEMS.register("silverfish_fried_rice", () -> new ConsumableItem(DDProperties.ItemP.SILVERFISH_FRIED_RICE, true, false));
    public static final RegistryObject<Item> SPIDER_EYE_SALMAGUNDI = ITEMS.register("spider_eye_salmagundi", () -> new ConsumableItem(DDProperties.ItemP.SPIDER_EYE_SALMAGUNDI, true, false));
    public static final RegistryObject<Item> MONSTER_BURGER = ITEMS.register("monster_burger", () -> new MonsterBurgerItem(DDProperties.ItemP.MONSTER_BURGER));
}
