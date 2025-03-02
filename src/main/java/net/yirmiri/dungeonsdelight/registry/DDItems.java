package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.item.*;
import net.yirmiri.dungeonsdelight.util.DDProperties;
import net.yirmiri.dungeonsdelight.util.DDTags;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModMaterials;

public class DDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DungeonsDelight.MOD_ID);

    //MISC
    public static final RegistryObject<Item> LOGO_ITEM = ITEMS.register("logo_item", () -> new Item(DDProperties.ItemP.LOGO));
    public static final RegistryObject<Item> MONSTER_YAM_SPAWN_EGG = ITEMS.register("monster_yam_spawn_egg", () -> new ForgeSpawnEggItem(DDEntities.MONSTER_YAM, 0x713587, 0xdba214, new Item.Properties()));

    //INGREDIENTS
    public static final RegistryObject<Item> STAINED_SCRAP = ITEMS.register("stained_scrap", () -> new Item(DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final RegistryObject<Item> SCULK_POLYP = ITEMS.register("sculk_polyp", () -> new Item(DDProperties.ItemP.GENERIC));
    public static final RegistryObject<Item> ANCIENT_EGG = ITEMS.register("ancient_egg", () -> new AncientEggItem(DDProperties.ItemP.GENERIC_16));
    public static final RegistryObject<Item> ROTBULB = ITEMS.register("rotbulb", () -> new Item(DDProperties.ItemP.GENERIC));
    public static final RegistryObject<Item> GUNK = ITEMS.register("gunk", () -> new Item(DDProperties.ItemP.GENERIC));

    //BLOCK ITEMS
    public static final RegistryObject<Item> DUNGEON_STOVE = ITEMS.register("dungeon_stove", () -> new BlockItem(DDBlocks.DUNGEON_STOVE.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final RegistryObject<Item> STAINED_SCRAP_BLOCK = ITEMS.register("stained_scrap_block", () -> new BlockItem(DDBlocks.STAINED_SCRAP_BLOCK.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final RegistryObject<Item> STAINED_SCRAP_BARS = ITEMS.register("stained_scrap_bars", () -> new BlockItem(DDBlocks.STAINED_SCRAP_BARS.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final RegistryObject<Item> CUT_STAINED_SCRAP = ITEMS.register("cut_stained_scrap", () -> new BlockItem(DDBlocks.CUT_STAINED_SCRAP.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final RegistryObject<Item> CUT_STAINED_SCRAP_STAIRS = ITEMS.register("cut_stained_scrap_stairs", () -> new BlockItem(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final RegistryObject<Item> CUT_STAINED_SCRAP_SLAB = ITEMS.register("cut_stained_scrap_slab", () -> new BlockItem(DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final RegistryObject<Item> MONSTER_CAKE = ITEMS.register("monster_cake", () -> new BlockItem(DDBlocks.MONSTER_CAKE.get(), DDProperties.ItemP.MONSTER_CAKE));
    public static final RegistryObject<Item> OSSOBUSCO_BLOCK = ITEMS.register("ossobusco_block", () -> new BlockItem(DDBlocks.OSSOBUSCO_BLOCK.get(), DDProperties.ItemP.OSSOBUSCO_BLOCK));

    //CLEAVERS
    public static final RegistryObject<Item> FLINT_CLEAVER = ITEMS.register("flint_cleaver", () -> new CleaverItem(1.0F, ModMaterials.FLINT, 1.5F, -3.0F, DDProperties.ItemP.FLINT));
    public static final RegistryObject<Item> IRON_CLEAVER = ITEMS.register("iron_cleaver", () -> new CleaverItem(1.25F, Tiers.IRON, 1.5F, -3.0F, DDProperties.ItemP.IRON));
    public static final RegistryObject<Item> GOLDEN_CLEAVER = ITEMS.register("golden_cleaver", () -> new CleaverItem(1.0F, Tiers.GOLD, 1.5F, -3.0F, DDProperties.ItemP.GOLD));
    public static final RegistryObject<Item> DIAMOND_CLEAVER = ITEMS.register("diamond_cleaver", () -> new CleaverItem(1.5F, Tiers.DIAMOND, 1.5F, -3.0F, DDProperties.ItemP.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_CLEAVER = ITEMS.register("netherite_cleaver", () -> new CleaverItem(1.5F, Tiers.NETHERITE, 1.5F, -3.0F, DDProperties.ItemP.NETHERITE));

    //INGREDIENT FOODS
    public static final RegistryObject<Item> SLIME_NOODLES = ITEMS.register("slime_noodles", () -> new Item(DDProperties.ItemP.SLIME_NOODLES));
    public static final RegistryObject<Item> SLIME_BAR = ITEMS.register("slime_bar", () -> new Item(DDProperties.ItemP.SLIME_BAR));
    public static final RegistryObject<Item> SILVERFISH_THORAX = ITEMS.register("silverfish_thorax", () -> new Item(DDProperties.ItemP.SILVERFISH_THORAX));
    public static final RegistryObject<Item> GHAST_CALAMARI = ITEMS.register("ghast_calamari", () -> new Item(DDProperties.ItemP.GHAST_CALAMARI));
    public static final RegistryObject<Item> FRIED_GHAST_CALAMARI = ITEMS.register("fried_ghast_calamari", () -> new Item(DDProperties.ItemP.FRIED_GHAST_CALAMARI));
    public static final RegistryObject<Item> GHAST_TENTACLE = ITEMS.register("ghast_tentacle", () -> new Item(DDProperties.ItemP.GHAST_TENTACLE));
    public static final RegistryObject<Item> SPIDER_EXTRACT = ITEMS.register("spider_extract", () -> new DrinkableItem(DDProperties.ItemP.SPIDER_EXTRACT, false, false));
    public static final RegistryObject<Item> SPIDER_MEAT = ITEMS.register("spider_meat", () -> new Item(DDProperties.ItemP.SPIDER_MEAT));
    public static final RegistryObject<Item> SMOKED_SPIDER_MEAT = ITEMS.register("smoked_spider_meat", () -> new Item(DDProperties.ItemP.SMOKED_SPIDER_MEAT));
    public static final RegistryObject<Item> CLEAVED_ANCIENT_EGG = ITEMS.register("cleaved_ancient_egg", () -> new ExperienceFood(DDProperties.ItemP.CLEAVED_ANCIENT_EGG, 5, false));
    public static final RegistryObject<Item> SCULK_MAYO = ITEMS.register("sculk_mayo", () -> new ExperienceFood(DDProperties.ItemP.SCULK_MAYO, 5, false));
    public static final RegistryObject<Item> ROTTEN_TRIPE = ITEMS.register("rotten_tripe", () -> new Item(DDProperties.ItemP.ROTTEN_TRIPE));
    public static final RegistryObject<Item> SLICORICE = ITEMS.register("slicorice", () -> new Item(DDProperties.ItemP.SLICORICE));
    public static final RegistryObject<Item> BRINED_FLESH = ITEMS.register("brined_flesh", () -> new Item(DDProperties.ItemP.FLESH));
    public static final RegistryObject<Item> GRITTY_FLESH = ITEMS.register("gritty_flesh", () -> new Item(DDProperties.ItemP.FLESH));
    public static final RegistryObject<Item> RANCID_REDUCTION = ITEMS.register("rancid_reduction", () -> new DrinkableItem(DDProperties.ItemP.RANCID_REDUCTION, false, false));

    //GENERIC FOODS
    public static final RegistryObject<Item> AMETHYST_ROCK_CANDY = ITEMS.register("amethyst_rock_candy", () -> new RockCandyItem(DDProperties.ItemP.AMETHYST_ROCK_CANDY));
    public static final RegistryObject<Item> CANDIED_SILVERFISH_SUCKER = ITEMS.register("candied_silverfish_sucker", () -> new RockCandyItem(DDProperties.ItemP.CANDIED_SILVERFISH_SUCKER));
    public static final RegistryObject<Item> CANDIED_VEX_SUCKER = ITEMS.register("candied_vex_sucker", () -> new RockCandyItem(DDProperties.ItemP.CANDIED_VEX_SUCKER));
    public static final RegistryObject<Item> SPIDER_TANGHULU = ITEMS.register("spider_tanghulu", () -> new ConsumableItem(DDProperties.ItemP.SPIDER_TANGHULU, true, false));
    public static final RegistryObject<Item> BUBBLEGUNK = ITEMS.register("bubblegunk", () -> new BubblegunkItem(DDProperties.ItemP.BUBBLEGUNK, DDTags.ItemT.REFILLS_BUBBLEGUNK, false));
    public static final RegistryObject<Item> COB_N_CANDY = ITEMS.register("cob_n_candy", () -> new BiteableItem(DDProperties.ItemP.COB_N_CANDY.craftRemainder(DDItems.SLICORICE.get()), DDTags.ItemT.REFILLS_COB_N_CANDY, true));
    public static final RegistryObject<Item> DEVILISH_EGGS = ITEMS.register("devilish_eggs", () -> new ExperienceFood(DDProperties.ItemP.DEVILISH_EGGS, 10, false));
    public static final RegistryObject<Item> GHAST_ROLL = ITEMS.register("ghast_roll", () -> new Item(DDProperties.ItemP.GHAST_ROLL));
    public static final RegistryObject<Item> SOAKED_SKEWER = ITEMS.register("soaked_skewer", () -> new ConsumableItem(DDProperties.ItemP.SOAKED_SKEWER, true, false));
    public static final RegistryObject<Item> POI = ITEMS.register("poi", () -> new ConsumableItem(DDProperties.ItemP.POI, true, false));
    public static final RegistryObject<Item> MONSTER_MUFFIN = ITEMS.register("monster_muffin", () -> new ConsumableItem(DDProperties.ItemP.MONSTER_MUFFIN, true, false));
    public static final RegistryObject<Item> SCULK_TART_SLICE = ITEMS.register("sculk_tart_slice", () -> new ExperienceFood(DDProperties.ItemP.SCULK_TART_SLICE, 15, false));
    public static final RegistryObject<Item> MONSTER_CAKE_SLICE = ITEMS.register("monster_cake_slice", () -> new ExperienceFood(DDProperties.ItemP.MONSTER_CAKE_SLICE, 5, false));

    //MEALS
    public static final RegistryObject<Item> GHOULASH = ITEMS.register("ghoulash", () -> new ConsumableItem(DDProperties.ItemP.GHOULASH, true, false));
    public static final RegistryObject<Item> SILVERFISH_FRIED_RICE = ITEMS.register("silverfish_fried_rice", () -> new ConsumableItem(DDProperties.ItemP.SILVERFISH_FRIED_RICE, true, false));
    public static final RegistryObject<Item> SPIDER_EYE_SALMAGUNDI = ITEMS.register("spider_eye_salmagundi", () -> new ConsumableItem(DDProperties.ItemP.SPIDER_EYE_SALMAGUNDI, true, false));
    public static final RegistryObject<Item> MONSTER_BURGER = ITEMS.register("monster_burger", () -> new MonsterBurgerItem(DDProperties.ItemP.MONSTER_BURGER));
    public static final RegistryObject<Item> GLOW_BERRY_GELATIN = ITEMS.register("glow_berry_gelatin", () -> new ConsumableItem(DDProperties.ItemP.GLOW_BERRY_GELATIN, true, false));
    public static final RegistryObject<Item> GELLED_SALAD = ITEMS.register("gelled_salad", () -> new ConsumableItem(DDProperties.ItemP.GELLED_SALAD, true, false));
    public static final RegistryObject<Item> TOKAYAKI = ITEMS.register("tokayaki", () -> new ExperienceFood(DDProperties.ItemP.TOKAYAKI, 10, true));
    public static final RegistryObject<Item> SALT_SOAKED_STEW = ITEMS.register("salt_soaked_stew", () -> new ConsumableItem(DDProperties.ItemP.SALT_SOAKED_STEW, true, false));
    public static final RegistryObject<Item> OSSOBUSCO = ITEMS.register("ossobusco", () -> new OssobuscoItem(DDProperties.ItemP.OSSOBUSCO, true, true));
}
