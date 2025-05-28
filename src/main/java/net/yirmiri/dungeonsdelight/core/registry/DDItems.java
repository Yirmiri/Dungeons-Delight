package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.*;
import net.yirmiri.dungeonsdelight.common.util.DDMaterials;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModMaterials;

import java.util.function.Supplier;

public class DDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, DungeonsDelight.MOD_ID);

    //MISC
    public static final Supplier<Item> LOGO_ITEM = ITEMS.register("logo_item", () -> new Item(DDProperties.ItemP.LOGO));
    public static final Supplier<Item> MONSTER_YAM_SPAWN_EGG = ITEMS.register("monster_yam_spawn_egg", () -> new DeferredSpawnEggItem(DDEntities.MONSTER_YAM, 0x713587, 0xdba214, new Item.Properties()));
    public static final Supplier<Item> GUNK_ARROW = ITEMS.register("gunk_arrow", () -> new WIPItem(DDProperties.ItemP.GENERIC_MONSTER));

    //INGREDIENTS
    public static final Supplier<Item> STAINED_SCRAP = ITEMS.register("stained_scrap", () -> new Item(DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> STAINED_SCRAP_FRAGMENT = ITEMS.register("stained_scrap_fragment", () -> new Item(DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> SCULK_POLYP = ITEMS.register("sculk_polyp", () -> new Item(DDProperties.ItemP.GENERIC));
    public static final Supplier<Item> ANCIENT_EGG = ITEMS.register("ancient_egg", () -> new AncientEggItem(DDProperties.ItemP.GENERIC_16));
    public static final Supplier<Item> ROTBULB = ITEMS.register("rotbulb", () -> new Item(DDProperties.ItemP.GENERIC_MONSTER));
    public static final Supplier<Item> GUNK = ITEMS.register("gunk", () -> new BlockItem(DDBlocks.GUNK.get(), DDProperties.ItemP.GENERIC_MONSTER));

    //BLOCK ITEMS
    public static final Supplier<Item> MONSTER_POT = ITEMS.register("monster_pot", () -> new BlockItem(DDBlocks.MONSTER_POT.get(), DDProperties.ItemP.MONSTER_POT));
    public static final Supplier<Item> DUNGEON_STOVE = ITEMS.register("dungeon_stove", () -> new BlockItem(DDBlocks.DUNGEON_STOVE.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> STAINED_SCRAP_BLOCK = ITEMS.register("stained_scrap_block", () -> new BlockItem(DDBlocks.STAINED_SCRAP_BLOCK.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> STAINED_SCRAP_BARS = ITEMS.register("stained_scrap_bars", () -> new BlockItem(DDBlocks.STAINED_SCRAP_BARS.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> CUT_STAINED_SCRAP = ITEMS.register("cut_stained_scrap", () -> new BlockItem(DDBlocks.CUT_STAINED_SCRAP.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> CUT_STAINED_SCRAP_STAIRS = ITEMS.register("cut_stained_scrap_stairs", () -> new BlockItem(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> CUT_STAINED_SCRAP_SLAB = ITEMS.register("cut_stained_scrap_slab", () -> new BlockItem(DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> LIVING_CANDLE = ITEMS.register("living_candle", () -> new BlockItem(DDBlocks.LIVING_CANDLE.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> MONSTER_CAKE = ITEMS.register("monster_cake", () -> new BlockItem(DDBlocks.MONSTER_CAKE.get(), DDProperties.ItemP.MONSTER_CAKE));
    public static final Supplier<Item> SPIDER_PIE = ITEMS.register("spider_pie", () -> new BlockItem(DDBlocks.SPIDER_PIE.get(), DDProperties.ItemP.SPIDER_PIE));
    public static final Supplier<Item> OSSOBUCO_BLOCK = ITEMS.register("ossobuco_block", () -> new BlockItem(DDBlocks.OSSOBUCO_BLOCK.get(), DDProperties.ItemP.OSSOBUCO_BLOCK));
    public static final Supplier<Item> GUARDIAN_ANGEL_BLOCK = ITEMS.register("guardian_angel_block", () -> new BlockItem(DDBlocks.GUARDIAN_ANGEL_BLOCK.get(), DDProperties.ItemP.MONSTER_FEAST));
    public static final Supplier<Item> ROTBULB_CRATE = ITEMS.register("rotbulb_crate", () -> new BlockItem(DDBlocks.ROTBULB_CRATE.get(), DDProperties.ItemP.GENERIC_MONSTER));
    public static final Supplier<Item> ROTBULB_CROP = ITEMS.register("rotbulb_crop", () -> new BlockItem(DDBlocks.ROTBULB_CROP.get(), DDProperties.ItemP.GENERIC_MONSTER));
    public static final Supplier<Item> ROTBULB_PLANT = ITEMS.register("rotbulb_plant", () -> new BlockItem(DDBlocks.ROTBULB_PLANT.get(), DDProperties.ItemP.GENERIC_MONSTER));
    public static final Supplier<Item> ROTTEN_SPAWNER = ITEMS.register("rotten_spawner", () -> new BlockItem(DDBlocks.ROTTEN_SPAWNER.get(), DDProperties.ItemP.GENERIC_MONSTER));
    public static final Supplier<Item> LIVING_CAMPFIRE = ITEMS.register("living_campfire", () -> new BlockItem(DDBlocks.LIVING_CAMPFIRE.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> LIVING_TORCH = ITEMS.register("living_torch", () -> new StandingAndWallBlockItem(DDBlocks.LIVING_TORCH.get(), DDBlocks.WALL_LIVING_TORCH.get(), DDProperties.ItemP.GENERIC_UNCOMMON, Direction.DOWN));
    public static final Supplier<Item> LIVING_LANTERN = ITEMS.register("living_lantern", () -> new BlockItem(DDBlocks.LIVING_LANTERN.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> STAINED_SCRAP_CHAIN = ITEMS.register("stained_scrap_chain", () -> new BlockItem(DDBlocks.STAINED_SCRAP_CHAIN.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> SPIDER_DONUT = ITEMS.register("spider_donut", () -> new BlockItem(DDBlocks.SPIDER_DONUT.get(), DDProperties.ItemP.SPIDER_DONUT));
    public static final Supplier<Item> STAINED_SCRAP_GRATE = ITEMS.register("stained_scrap_grate", () -> new BlockItem(DDBlocks.STAINED_SCRAP_GRATE.get(), DDProperties.ItemP.GENERIC_UNCOMMON));

    //CLEAVERS & KNIVES
    public static final Supplier<Item> FLINT_CLEAVER = ITEMS.register("flint_cleaver", () -> new CleaverItem(1.25F, ModMaterials.FLINT, 2.0F, -3.0F, DDProperties.ItemP.FLINT));
    public static final Supplier<Item> IRON_CLEAVER = ITEMS.register("iron_cleaver", () -> new CleaverItem(1.5F, Tiers.IRON, 2.0F, -3.0F, DDProperties.ItemP.IRON));
    public static final Supplier<Item> GOLDEN_CLEAVER = ITEMS.register("golden_cleaver", () -> new CleaverItem(1.75F, Tiers.GOLD, 2.0F, -3.0F, DDProperties.ItemP.GOLD));
    public static final Supplier<Item> DIAMOND_CLEAVER = ITEMS.register("diamond_cleaver", () -> new CleaverItem(1.75F, Tiers.DIAMOND, 2.0F, -3.0F, DDProperties.ItemP.DIAMOND));
    public static final Supplier<Item> NETHERITE_CLEAVER = ITEMS.register("netherite_cleaver", () -> new CleaverItem(1.75F, Tiers.NETHERITE, 2.0F, -3.0F, DDProperties.ItemP.NETHERITE));
    public static final Supplier<Item> STAINED_CLEAVER = ITEMS.register("stained_cleaver", () -> new StainedCleaverItem(2.0F, DDMaterials.STAINED, 2.0F, -3.0F, DDProperties.ItemP.STAINED));
    public static final Supplier<Item> STAINED_KNIFE = ITEMS.register("stained_knife", () -> new StainedKnifeItem(DDMaterials.STAINED, 2.0F, -2.0F, DDProperties.ItemP.STAINED));

    //INGREDIENT FOODS
    public static final Supplier<Item> SLIME_NOODLES = ITEMS.register("slime_noodles", () -> new Item(DDProperties.ItemP.SLIME_NOODLES));
    public static final Supplier<Item> SLIME_BAR = ITEMS.register("slime_bar", () -> new Item(DDProperties.ItemP.SLIME_BAR));
    public static final Supplier<Item> SILVERFISH_ABDOMEN = ITEMS.register("silverfish_abdomen", () -> new Item(DDProperties.ItemP.SILVERFISH_ABDOMEN));
    public static final Supplier<Item> GHAST_CALAMARI = ITEMS.register("ghast_calamari", () -> new Item(DDProperties.ItemP.GHAST_CALAMARI));
    public static final Supplier<Item> FRIED_GHAST_CALAMARI = ITEMS.register("fried_ghast_calamari", () -> new Item(DDProperties.ItemP.FRIED_GHAST_CALAMARI));
    public static final Supplier<Item> GHAST_TENTACLE = ITEMS.register("ghast_tentacle", () -> new Item(DDProperties.ItemP.GHAST_TENTACLE));
    public static final Supplier<Item> SPIDER_EXTRACT = ITEMS.register("spider_extract", () -> new DrinkableItem(DDProperties.ItemP.SPIDER_EXTRACT, false, false));
    public static final Supplier<Item> SPIDER_MEAT = ITEMS.register("spider_meat", () -> new Item(DDProperties.ItemP.SPIDER_MEAT));
    public static final Supplier<Item> SMOKED_SPIDER_MEAT = ITEMS.register("smoked_spider_meat", () -> new Item(DDProperties.ItemP.SMOKED_SPIDER_MEAT));
    public static final Supplier<Item> CLEAVED_ANCIENT_EGG = ITEMS.register("cleaved_ancient_egg", () -> new EXPFoodItem(DDProperties.ItemP.CLEAVED_ANCIENT_EGG, 5, false));
    public static final Supplier<Item> SCULK_MAYO = ITEMS.register("sculk_mayo", () -> new EXPFoodItem(DDProperties.ItemP.SCULK_MAYO, 5, false));
    public static final Supplier<Item> ROTTEN_TRIPE = ITEMS.register("rotten_tripe", () -> new Item(DDProperties.ItemP.ROTTEN_TRIPE));
    public static final Supplier<Item> SLICORICE = ITEMS.register("slicorice", () -> new Item(DDProperties.ItemP.SLICORICE));
    public static final Supplier<Item> BRINED_FLESH = ITEMS.register("brined_flesh", () -> new Item(DDProperties.ItemP.FLESH));
    public static final Supplier<Item> GRITTY_FLESH = ITEMS.register("gritty_flesh", () -> new Item(DDProperties.ItemP.FLESH));
    public static final Supplier<Item> RANCID_REDUCTION = ITEMS.register("rancid_reduction", () -> new RancidReductionItem(DDProperties.ItemP.RANCID_REDUCTION));
    public static final Supplier<Item> WARDENZOLA = ITEMS.register("wardenzola", () -> new EXPFoodItem(DDProperties.ItemP.WARDENZOLA, 5, false));
    public static final Supplier<Item> WARDENZOLA_CRUMBLES = ITEMS.register("wardenzola_crumbles", () -> new EXPFoodItem(DDProperties.ItemP.WARDENZOLA_CRUMBLES, 2, false));
    public static final Supplier<Item> GHASTLY_SPIRITS = ITEMS.register("ghastly_spirits", () -> new GhastlySpiritsItem(DDProperties.ItemP.GHASTLY_SPIRITS, true, false));
    public static final Supplier<Item> CREEPERILLA = ITEMS.register("creeperilla", () -> new WIPItem(DDProperties.ItemP.CREEPERILLA));

    //GENERIC FOODS
    public static final Supplier<Item> AMETHYST_ROCK_CANDY = ITEMS.register("amethyst_rock_candy", () -> new RockCandyItem(DDProperties.ItemP.AMETHYST_ROCK_CANDY));
    public static final Supplier<Item> CANDIED_SILVERFISH_SUCKER = ITEMS.register("candied_silverfish_sucker", () -> new RockCandyItem(DDProperties.ItemP.CANDIED_SILVERFISH_SUCKER));
    public static final Supplier<Item> CANDIED_VEX_SUCKER = ITEMS.register("candied_vex_sucker", () -> new RockCandyItem(DDProperties.ItemP.CANDIED_VEX_SUCKER));
    public static final Supplier<Item> SPIDER_TANGHULU = ITEMS.register("spider_tanghulu", () -> new ConsumableItem(DDProperties.ItemP.SPIDER_TANGHULU, true, false));
    public static final Supplier<Item> BUBBLEGUNK = ITEMS.register("bubblegunk", () -> new BubblegunkItem(DDProperties.ItemP.BUBBLEGUNK, false));
    public static final Supplier<Item> COB_N_CANDY = ITEMS.register("cob_n_candy", () -> new BiteableItem(DDProperties.ItemP.COB_N_CANDY.craftRemainder(DDItems.SLICORICE.get()), true));
    public static final Supplier<Item> DEVILISH_EGGS = ITEMS.register("devilish_eggs", () -> new EXPFoodItem(DDProperties.ItemP.DEVILISH_EGGS, 10, false));
    public static final Supplier<Item> GHAST_ROLL = ITEMS.register("ghast_roll", () -> new Item(DDProperties.ItemP.GHAST_ROLL));
    public static final Supplier<Item> SOAKED_SKEWER = ITEMS.register("soaked_skewer", () -> new ConsumableItem(DDProperties.ItemP.SOAKED_SKEWER, true, false));
    public static final Supplier<Item> POI = ITEMS.register("poi", () -> new ConsumableItem(DDProperties.ItemP.POI, true, false));
    public static final Supplier<Item> MONSTER_MUFFIN = ITEMS.register("monster_muffin", () -> new ConsumableItem(DDProperties.ItemP.MONSTER_MUFFIN, true, false));
    public static final Supplier<Item> SCULK_TART_SLICE = ITEMS.register("sculk_tart_slice", () -> new EXPFoodItem(DDProperties.ItemP.SCULK_TART_SLICE, 15, false));
    public static final Supplier<Item> SPIDER_PIE_SLICE = ITEMS.register("spider_pie_slice", () -> new ConsumableItem(DDProperties.ItemP.SPIDER_PIE_SLICE, false));
    public static final Supplier<Item> MONSTER_CAKE_SLICE = ITEMS.register("monster_cake_slice", () -> new EXPFoodItem(DDProperties.ItemP.MONSTER_CAKE_SLICE, 5, false));
    public static final Supplier<Item> SCULK_APPLE = ITEMS.register("sculk_apple", () -> new EXPCandiedFoodItem(DDProperties.ItemP.SCULK_APPLE, 5, false, true, false));
    public static final Supplier<Item> SNIFFER_SHANK = ITEMS.register("sniffer_shank", () -> new LengthConsumeableItem(DDProperties.ItemP.SNIFFER_SHANK, 48, false, false));
    public static final Supplier<Item> COOKED_SNIFFER_SHANK = ITEMS.register("cooked_sniffer_shank", () -> new LengthConsumeableItem(DDProperties.ItemP.COOKED_SNIFFER_SHANK, 48, false, false));
    public static final Supplier<Item> SOFT_SERVE_SNIFFER_EGG = ITEMS.register("soft_serve_sniffer_egg", () -> new LengthConsumeableItem(DDProperties.ItemP.SOFT_SERVE_SNIFFER_EGG, 48, true, false));
    public static final Supplier<Item> SNIFFERWURST = ITEMS.register("snifferwurst", () -> new LengthConsumeableItem(DDProperties.ItemP.SNIFFERWURST, 48, true, false));
    public static final Supplier<Item> COOKED_SNIFFERWURST = ITEMS.register("cooked_snifferwurst", () -> new LengthConsumeableItem(DDProperties.ItemP.COOKED_SNIFFERWURST, 48, true, false));
    public static final Supplier<Item> OMINOUS_OMELETTE = ITEMS.register("ominous_omelette", () -> new EXPLengthConsumeableItem(DDProperties.ItemP.OMINOUS_OMELETTE, 48, 2, true));
    public static final Supplier<Item> BLOATED_BAKED_POTATO = ITEMS.register("bloated_baked_potato", () -> new PoisonPotatoesItem(DDProperties.ItemP.BLOATED_BAKED_POTATO, 4, false));

    //MEALS
    public static final Supplier<Item> GHOULASH = ITEMS.register("ghoulash", () -> new SlimeFoodItem(DDProperties.ItemP.GHOULASH, 0.25F, true));
    public static final Supplier<Item> SILVERFISH_FRIED_RICE = ITEMS.register("silverfish_fried_rice", () -> new ConsumableItem(DDProperties.ItemP.SILVERFISH_FRIED_RICE, true, false));
    public static final Supplier<Item> SPIDER_SALMAGUNDI = ITEMS.register("spider_salmagundi", () -> new ConsumableItem(DDProperties.ItemP.SPIDER_SALMAGUNDI, true, false));
    public static final Supplier<Item> MONSTER_BURGER = ITEMS.register("monster_burger", () -> new MonsterBurgerItem(DDProperties.ItemP.MONSTER_BURGER));
    public static final Supplier<Item> GLOW_BERRY_GELATIN = ITEMS.register("glow_berry_gelatin", () -> new ConsumableItem(DDProperties.ItemP.GLOW_BERRY_GELATIN, true, false));
    public static final Supplier<Item> GELLED_SALAD = ITEMS.register("gelled_salad", () -> new SlimeFoodItem(DDProperties.ItemP.GELLED_SALAD, 0.33F, true));
    public static final Supplier<Item> TOKAYAKI = ITEMS.register("tokayaki", () -> new EXPFoodItem(DDProperties.ItemP.TOKAYAKI, 10, true));
    public static final Supplier<Item> SALT_SOAKED_STEW = ITEMS.register("salt_soaked_stew", () -> new ConsumableItem(DDProperties.ItemP.SALT_SOAKED_STEW, true, false));
    public static final Supplier<Item> OSSOBUCO = ITEMS.register("ossobuco", () -> new OssobucoItem(DDProperties.ItemP.OSSOBUCO, true, true));
    public static final Supplier<Item> SHIOKARA = ITEMS.register("shiokara", () -> new UndeadFoodItem(DDProperties.ItemP.SHIOKARA, false));
    public static final Supplier<Item> MALICIOUS_SANDWICH = ITEMS.register("malicious_sandwich", () -> new EXPUndeadFoodItem(DDProperties.ItemP.MALICIOUS_SANDWICH, 7, false));
    public static final Supplier<Item> TERRINE_LOAF = ITEMS.register("terrine_loaf", () -> new EXPUndeadFoodItem(DDProperties.ItemP.TERRINE_LOAF, 5, true));
    public static final Supplier<Item> GYUDON = ITEMS.register("gyudon", () -> new EXPFoodItem(DDProperties.ItemP.GYUDON, 2, true));
    public static final Supplier<Item> SINIGANG = ITEMS.register("sinigang", () -> new ConsumableItem(DDProperties.ItemP.SINIGANG, true, false));
    public static final Supplier<Item> SNUFFLEDOG = ITEMS.register("snuffledog", () -> new LengthConsumeableItem(DDProperties.ItemP.SNUFFLEDOG, 48, true, false));
    public static final Supplier<Item> CHLOROPASTA = ITEMS.register("chloropasta", () -> new LengthConsumeableItem(DDProperties.ItemP.CHLOROPASTA, 48, true, false));
    public static final Supplier<Item> GUARDIAN_ANGEL = ITEMS.register("guardian_angel", () -> new ConsumableItem(DDProperties.ItemP.GUARDIAN_ANGEL, true, false));
    public static final Supplier<Item> CHICKEN_JOCKEY_SANDWICH = ITEMS.register("chicken_jockey_sandwich", () -> new EXPUndeadFoodItem(DDProperties.ItemP.CHICKEN_JOCKEY_SANDWICH, 10, false));
    public static final Supplier<Item> AU_ROTTEN_POTATOES = ITEMS.register("au_rotten_potatoes", () -> new PoisonPotatoesItem(DDProperties.ItemP.AU_ROTTEN_POTATOES, 4, true));
    public static final Supplier<Item> POISONOUS_POUTINE = ITEMS.register("poisonous_poutine", () -> new PoisonPotatoesItem(DDProperties.ItemP.POISONOUS_POUTINE, 2, true));

    //DRINKS
    public static final Supplier<Item> TARO_MILK_TEA = ITEMS.register("taro_milk_tea", () -> new DrinkableItem(DDProperties.ItemP.TARO_MILK_TEA, true, false));
    public static final Supplier<Item> BLOODY_MARY = ITEMS.register("bloody_mary", () -> new BloodyMaryItem(DDProperties.ItemP.BLOODY_MARY, true));
    public static final Supplier<Item> SPIDER_BUBBLE_TEA = ITEMS.register("spider_bubble_tea", () -> new SpiderBubbleTeaItem(DDProperties.ItemP.SPIDER_BUBBLE_TEA, false));
}
