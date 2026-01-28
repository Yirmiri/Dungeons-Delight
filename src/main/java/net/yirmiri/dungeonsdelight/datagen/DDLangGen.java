package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.*;
import net.yirmiri.dungeonsdelight.integration.content.aether.AEItems;
import net.yirmiri.dungeonsdelight.integration.content.alloyed.ALItems;
import net.yirmiri.dungeonsdelight.integration.content.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.content.fishy_fiesta.FFItems;
import net.yirmiri.dungeonsdelight.integration.content.minersdelight.MDItems;
import net.yirmiri.dungeonsdelight.integration.content.twilightforest.TFItems;

public class DDLangGen extends LanguageProvider {
    public DDLangGen(PackOutput output) {
        super(output, DungeonsDelight.MOD_ID, "en_us");
    }

    public static final String DD_ID = "dungeonsdelight";
    public static final String YT_ID = "yapping_tooltips";

    public static final String NA_DESC = "Yirmiri & Betwixer seem to have forgotten this tooltip D:";

    @Override
    protected void addTranslations() {
        //MISC
        add("dungeonsdelight_tab", "Dungeon's Delight");
        add("dungeonsdelight_compat_tab", "Dungeon's Delight Integration");
        add("farmersdelight.container.monster_pot", "Monster Pot");
        add("farmersdelight.tooltip.wip", "Warning! This item is currently unfinished and is subject to change...");
        add("trim_material.dungeonsdelight.stained_scrap", "Stained Scrap");
        add("item.dungeonsdelight.music_disc_malady.desc", "Artyrian - Malady");

        //JEI
        add("farmersdelight.jei.monster_cooking", "Monster Cooking");
        add("farmersdelight.jei.sculking", "Sculking");
        add("dungeonsdelight.jei.sculking.night", "Sped up and can only sculk by moonlight");
        add("dungeonsdelight.jei.sculking.spawner", "Sped up by adjacent spawner heat conductors (see below)");
        add("dungeonsdelight.jei.sculking.accelerators", "Sped up by adjacent activators (see below)");
        add("farmersdelight.jei.info.rock_candy", "Rock candy can be used to capture mobs inside of them by attacking them.");
        add("farmersdelight.jei.info.candied_vex", "Obtained by attacking a Vex with rock candy.");
        add("farmersdelight.jei.info.candied_silverfish", "Obtained by attacking a Silverfish with rock candy.");
        add("farmersdelight.jei.info.cleaver", "Cleavers are lightweight ranged and melee weapons. They can harvest Straw from grasses, and guarantee secondary drops from entities.");
        add("farmersdelight.jei.info.rancid_reduction", "Can be thrown to inflict entities with weakness and exudation, rots fully grown crops.");
        add("farmersdelight.jei.info.stained_scrap", "Can be obtained by destroying spawner blocks");

        //TOOLTIPS
        add("farmersdelight.tooltip.monster_burger", "Every sin, disease, and unhealthy treat, merged together between two slices of bread...");
        add("farmersdelight.tooltip.bubblegunk", "Can be chewed multiple times, makes the consumer hungry");
        add("farmersdelight.tooltip.biteable", "Can be chewed multiple times");
        add("farmersdelight.tooltip.small_xp", "Grants a small amount of experience");
        add("farmersdelight.tooltip.average_xp", "Grants a sizeable amount of experience");
        add("farmersdelight.tooltip.large_xp", "Grants a large amount of experience");
        add("farmersdelight.tooltip.ossobuco", "Refills a random active monster effect");
        add("farmersdelight.tooltip.chance_to_not_consume", "chance to not consume when eaten");
        add("farmersdelight.tooltip.dungeonsdelight:sculk_apple", "Instant Health");
        add("farmersdelight.tooltip.undead", "Monsterizes one effect if no monster effects are active");
        add("farmersdelight.tooltip.bloody_mary", "Monsterizes one effect... at a cost");
        add("farmersdelight.tooltip.dungeonsdelight:rancid_reduction", "Can be thrown to rot fully grown crops");
        add("farmersdelight.tooltip.small_xp_poison_potato", "Grants a small amount of experience and cures poison");
        add("farmersdelight.tooltip.average_xp_poison_potato", "Grants a sizeable amount of experience and cures poison");
        add("farmersdelight.tooltip.large_xp_poison_potato", "Grants a large amount of experience and cures poison");
        add("farmersdelight.tooltip.poison_potato_food", "Cures poison and converts it into a short regeneration");
        add("farmersdelight.tooltip.necronog", "Doubles the duration of a random effect but reduces it's amplifier by 1");
        add("farmersdelight.tooltip.spider_bubble_tea", "Amplifies a random level I effect but halves it's duration");
        add("farmersdelight.tooltip.spider_bubble_tea_pouncing", "Pouncing II (02:00) if consumer has no level I effects");
        add("farmersdelight.tooltip.monster_burger_food", "Monsterizes Comfort, Haste, Strength, Absorption, Jump Boost, and Nourishment");
        add("dungeonsdelight.tooltip.attribute.range", "Throw Range");
        add("dungeonsdelight.tooltip.heat_charge", "Heat Charge:");
        add("dungeonsdelight.tooltip.gunk_arrow", "Causes no damage but attracts undead to the struck target");
        add("farmersdelight.tooltip.raw_creeper_food", "Causes an explosion and leaks active effects into a cloud");
        add("farmersdelight.tooltip.jelly_beans", "Grants a random effect upon consumption, can be positive or negative");
        add("farmersdelight.tooltip.average_xp_poison_potato_bite", "Grants a sizeable amount of experience, cures poison, and can be chewed multiple times");
        add("farmersdelight.tooltip.average_xp_bite", "Grants a sizeable amount of experience and can be chewed multiple times");
        add("farmersdelight.tooltip.sippable", "Can be sipped multiple times");
        add("block.dungeonsdelight.grate.desc1", "Interact with Item:");
        add("block.dungeonsdelight.grate.desc2", "Sets Item Displayed");
        add("farmersdelight.container.monster_pot.heated", "Heated");
        add("farmersdelight.container.monster_pot.not_heated", "Needs living heat from below");
        add("farmersdelight.tooltip.chance_to_cure", "chance to harmonize one monster effect");
        add("farmersdelight.tooltip.chance_to_cure_not_consume", "chance to harmonize one monster effect and not be consumed");
        add("farmersdelight.tooltip.sculk_level_1", "Upon consumption release a small sonic blast that knocks entities away");
        add("farmersdelight.tooltip.sculk_level_2", "Upon consumption release a sizeable sonic blast that knocks entities away");
        add("farmersdelight.tooltip.sculk_level_3", "Upon consumption release a large sonic blast that knocks entities away");
        add("farmersdelight.tooltip.undead.sculk_level_1", "Monsterizes one effect if none are active and releases a small sonic blast");
        add("farmersdelight.tooltip.undead.sculk_level_2", "Monsterizes one effect if none are active and releases a sizeable sonic blast");
        add("farmersdelight.tooltip.undead.sculk_level_3", "Monsterizes one effect if none are active and releases a large sonic blast");
        //INTEGRATION
        add("farmersdelight.tooltip.dungeonsdelight:sculk_dogapple", "Instant Health");
        add("farmersdelight.tooltip.arcane_chili", "Can be chewed multiple times, grants a random effect upon consumption");
        add("farmersdelight.tooltip.dungeonsdelight:sculk_catblueberry", "Minor Instant Health");

        //BLOCKS
        add(DDBlocks.DUNGEON_STOVE.get(), "Dungeon Stove");
        add(DDBlocks.MONSTER_POT.get(), "Monster Pot");
        add(DDBlocks.WORMWOOD_PLANKS.get(), "Wormwood Planks");
        add(DDBlocks.WORMWOOD_MOSAIC.get(), "Wormwood Mosaic");
        add(DDBlocks.WORMWOOD_STAIRS.get(), "Wormwood Stairs");
        add(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get(), "Wormwood Mosaic Stairs");
        add(DDBlocks.WORMWOOD_SLAB.get(), "Wormwood Slab");
        add(DDBlocks.WORMWOOD_MOSAIC_SLAB.get(), "Wormwood Mosaic Slab");
        add(DDBlocks.WORMWOOD_FENCE.get(), "Wormwood Fence");
        add(DDBlocks.WORMWOOD_FENCE_GATE.get(), "Wormwood Fence Gate");
        add(DDBlocks.WORMWOOD_DOOR.get(), "Wormwood Door");
        add(DDBlocks.WORMWOOD_TRAPDOOR.get(), "Wormwood Trapdoor");
        add(DDBlocks.WORMWOOD_BUTTON.get(), "Wormwood Button");
        add(DDBlocks.WORMWOOD_PRESSURE_PLATE.get(), "Wormwood Pressure Plate");
        add(DDBlocks.WORMROOT_TENDRILS.get(), "Wormroot Tendrils");
        add(DDBlocks.WORMWOOD_CABINET.get(), "Wormwood Cabinet");
        add(DDBlocks.EMBEDDED_EGGS.get(), "Embedded Eggs");
        add(DDBlocks.HEAP_OF_ANCIENT_EGGS.get(), "Heap of Ancient Eggs");
        add(DDBlocks.SCULK_MAYO_BLOCK.get(), "Block of Sculk Mayo");
        add(DDBlocks.WORMROOTS_BLOCK.get(), "Block of Wormroots");
        add(DDBlocks.ROTBULB_CROP.get(), "Rotbulbling");
        add(DDBlocks.ROTBULB_PLANT.get(), "Wild Rotbulb");
        add(DDBlocks.ROTBULB_CRATE.get(), "Rotbulb Crate");
        add(DDBlocks.STAINED_SCRAP_BLOCK.get(), "Block of Stained Scrap");
        add(DDBlocks.CHISELED_STAINED_SCRAP.get(), "Chiseled Stained Scrap");
        add(DDBlocks.STAINED_SCRAP_PILLAR.get(), "Stained Scrap Pillar");
        add(DDBlocks.STAINED_SCRAP_DOOR.get(), "Stained Scrap Door");
        add(DDBlocks.STAINED_SCRAP_TRAPDOOR.get(), "Stained Scrap Trapdoor");
        add(DDBlocks.STAINED_SCRAP_BARS.get(), "Stained Scrap Bars");
        add(DDBlocks.CUT_STAINED_SCRAP.get(), "Cut Stained Scrap");
        add(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), "Cut Stained Scrap Stairs");
        add(DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), "Cut Stained Scrap Slab");
        add(DDBlocks.SCULK_TART.get(), "Sculk Tart");
        add(DDBlocks.MONSTER_CAKE.get(), "Monster Cake");
        add(DDBlocks.SPIDER_PIE.get(), "Spider Pie");
        add(DDBlocks.ROTTEN_CROP.get(), "Rotten Crop");
        add(DDBlocks.ROTTEN_POTATOES.get(), "Rotten Potatoes");
        add(DDBlocks.ROTTEN_TOMATOES.get(), "Rotten Tomatoes");
        add(DDBlocks.GUARDIAN_ANGEL_BLOCK.get(), "Guardian Angel");
        add(DDBlocks.OSSOBUCO_BLOCK.get(), "Ossobuco");
        add(DDBlocks.MONSTER_MOUSSE_BLOCK.get(), "Monster Mousse");
        add(DDBlocks.POISONOUS_POTATO_CRATE.get(), "Poisonous Potato Crate");
        add(DDBlocks.ROTTEN_TOMATO_CRATE.get(), "Rotten Tomato Crate");
        add(DDBlocks.LIVING_FIRE.get(), "Living Fire");
        add(DDBlocks.LIVING_CANDLE.get(), "Living Candle");
        add(DDBlocks.ROTTEN_SPAWNER.get(), "Rotten Spawner");
        add(DDBlocks.LIVING_CAMPFIRE.get(), "Living Campfire");
        add(DDBlocks.LIVING_TORCH.get(), "Living Torch");
        add(DDBlocks.LIVING_LANTERN.get(), "Living Lantern");
        add(DDBlocks.STAINED_SCRAP_GRATE.get(), "Stained Scrap Grate");
        add(DDBlocks.WORMROOT_STALK.get(), "Wormroot Stalk");
        add(DDBlocks.WORMOUTH.get(), "Wormouth");
        add(DDBlocks.STAINED_LANTERN.get(), "Stained Lantern");
        add(DDBlocks.ROTGOURD.get(), "Rotgourd");
        add(DDBlocks.CARVED_ROTGOURD.get(), "Carved Rotgourd");
        add(DDBlocks.LIVING_JACK_O_LANTERN.get(), "Living Jack o'Lantern");
        add(DDBlocks.SPIRIT_FIRE.get(), "Spirit Fire");

        //ITEMS
        add(DDItems.LOGO_ITEM.get(), "Logo Item");
        add(DDItems.STAINED_SCRAP.get(), "Stained Scrap");
        add(DDItems.SCULK_POLYP.get(), "Sculk Polyp");
        add(DDItems.ANCIENT_EGG.get(), "Ancient Egg");
        add(DDItems.FLINT_CLEAVER.get(), "Flint Cleaver");
        add(DDItems.IRON_CLEAVER.get(), "Iron Cleaver");
        add(DDItems.GOLDEN_CLEAVER.get(), "Golden Cleaver");
        add(DDItems.DIAMOND_CLEAVER.get(), "Diamond Cleaver");
        add(DDItems.NETHERITE_CLEAVER.get(), "Netherite Cleaver");
        add(DDItems.ROTBULB.get(), "Rotbulb");
        add(DDItems.GUNK.get(), "Gunk");
        add(DDItems.MONSTER_YAM_SPAWN_EGG.get(), "Monster Yam Spawn Egg");
        add(DDItems.ZOMBIFIED_DRYAD_SPAWN_EGG.get(), "Zombified Dryad Spawn Egg");
        add(DDItems.STAINED_SCRAP_FRAGMENT.get(), "Stained Scrap Fragment");
        add(DDItems.STAINED_CLEAVER.get(), "Stained Cleaver");
        add(DDItems.STAINED_KNIFE.get(), "Stained Knife");
        add(DDItems.WORMWOOD_BOAT.get(), "Wormwood Boat");
        add(DDItems.WORMWOOD_CHEST_BOAT.get(), "Wormwood Boat with Chest");
        add(DDItems.GLUTTONY_POTTERY_SHERD.get(), "Gluttony Pottery Sherd");
        add(DDItems.MUSIC_DISC_MALADY.get(), "Music Disc");
        //INTEGRATION
        add(TFItems.IRONWOOD_KNIFE.get(), "Ironwood Bolene");
        add(TFItems.IRONWOOD_CLEAVER.get(), "Ironwood Dagger");
        add(TFItems.KNIGHTMETAL_KNIFE.get(), "Knightmetal Knife");
        add(TFItems.KNIGHTMETAL_CLEAVER.get(), "Knightmetal Cleaver");
        add(TFItems.FIERY_KNIFE.get(), "Fiery Knife");
        add(TFItems.FIERY_CLEAVER.get(), "Fiery Cleaver");
        add(TFItems.STEELEAF_KNIFE.get(), "Steeleaf Knife");
        add(TFItems.STEELEAF_CLEAVER.get(), "Steeleaf Cleaver");
        add(ALItems.STEEL_CLEAVER.get(), "Steel Cleaver");

        //CONSUMABLES
        add(DDItems.SLIME_NOODLES.get(), "Slime Noodles");
        add(DDItems.SLIME_BAR.get(), "Slime Slab");
        add(DDItems.GHOULASH.get(), "Ghoulash");
        add(DDItems.AMETHYST_ROCK_CANDY.get(), "Amethyst Rock Candy");
        add(DDItems.CANDIED_SILVERFISH_SUCKER.get(), "Candied Silverfish Sucker");
        add(DDItems.CANDIED_VEX_SUCKER.get(), "Candied Vex Sucker");
        add(DDItems.SILVERFISH_ABDOMEN.get(), "Silverfish Abdomen");
        add(DDItems.GHAST_CALAMARI.get(), "Ghast Calamari");
        add(DDItems.FRIED_GHAST_CALAMARI.get(), "Fried Ghast Calamari");
        add(DDItems.GHAST_TENTACLE.get(), "Ghast Tentacle");
        add(DDItems.SILVERFISH_FRIED_RICE.get(), "Silverfish Fried Rice");
        add(DDItems.SPIDER_MEAT.get(), "Spider Meat");
        add(DDItems.SPIDER_EXTRACT.get(), "Spider Extract");
        add(DDItems.SMOKED_SPIDER_MEAT.get(), "Smoked Spider Meat");
        add(DDItems.SPIDER_TANGHULU.get(), "Spider Tanghulu");
        add(DDItems.SPIDER_SALMAGUNDI.get(), "Spider Salmagundi");
        add(DDItems.MONSTER_BURGER.get(), "The Monster Burger");
        add(DDItems.BUBBLEGUNK.get(), "Bubblegunk");
        add(DDItems.CLEAVED_ANCIENT_EGG.get(), "Cleaved Ancient Egg");
        add(DDItems.SCULK_MAYO.get(), "Sculk Mayo");
        add(DDItems.MONSTER_MOUSSE.get(), "Bowl of Monster Mousse");
        add(DDItems.ROTTEN_TRIPE.get(), "Rotten Tripe");
        add(DDItems.GELLED_SALAD.get(), "Gelled Salad");
        add(DDItems.SLICORICE.get(), "Slicorice");
        add(DDItems.COB_N_CANDY.get(), "Cob n' Candy");
        add(DDItems.BRINED_FLESH.get(), "Brined Flesh");
        add(DDItems.GRITTY_FLESH.get(), "Gritty Flesh");
        add(DDItems.DEVILISH_EGGS.get(), "Devilish Eggs");
        add(DDItems.GHAST_ROLL.get(), "Ghast Roll");
        add(DDItems.TOKAYAKI.get(), "Tokayaki");
        add(DDItems.SALT_SOAKED_STEW.get(), "Salt Soaked Stew");
        add(DDItems.SOAKED_SKEWER.get(), "Soaked Skewer");
        add(DDItems.POI.get(), "Poi");
        add(DDItems.MONSTER_MUFFIN.get(), "Monster Muffin");
        add(DDItems.RANCID_REDUCTION.get(), "Rancid Reduction");
        add(DDItems.SCULK_TART_SLICE.get(), "Slice of Sculk Tart");
        add(DDItems.MONSTER_CAKE_SLICE.get(), "Slice of Monster Cake");
        add(DDItems.OSSOBUCO.get(), "Bowl of Ossobuco");
        add(DDItems.SPIDER_PIE_SLICE.get(), "Slice of Spider Pie");
        add(DDItems.SCULK_APPLE.get(), "Sculk Apple");
        add(DDItems.SHIOKARA.get(), "Shiokara");
        add(DDItems.BLOODY_MARY.get(), "Bloody Mary");
        add(DDItems.WARDENZOLA.get(), "Wardenzola");
        add(DDItems.WARDENZOLA_CRUMBLES.get(), "Wardenzola Crumbles");
        add(DDItems.MALICIOUS_SANDWICH.get(), "Malicious Sandwich");
        add(DDItems.TARO_MILK_TEA.get(), "Taro Milk Tea");
        add(DDItems.SNIFFER_SHANK.get(), "Raw Sniffer Shank");
        add(DDItems.COOKED_SNIFFER_SHANK.get(), "Cooked Sniffer Shank");
        add(DDItems.SOFT_SERVE_SNIFFER_EGG.get(), "Soft Serve Sniffer Egg");
        add(DDItems.SNIFFERWURST.get(), "Raw Snifferwurst");
        add(DDItems.COOKED_SNIFFERWURST.get(), "Cooked Snifferwurst");
        add(DDItems.TERRINE_LOAF.get(), "Terrine Loaf");
        add(DDItems.GYUDON.get(), "Gyudon");
        add(DDItems.GHASTLY_SPIRITS.get(), "Ghastly Spirits");
        add(DDItems.CREEPERILLA.get(), "Creeperilla");
        add(DDItems.SINIGANG.get(), "Sinigang");
        add(DDItems.GUNK_ARROW.get(), "Gunk Arrow");
        add(DDItems.SNUFFLEDOG.get(), "Snuffledog");
        add(DDItems.CHLOROPASTA.get(), "Chloropasta");
        add(DDItems.GUARDIAN_ANGEL.get(), "Plate of Guardian Angel");
        add(DDItems.CHICKEN_JOCKEY_SANDWICH.get(), "Chicken Jockey Sandwich");
        add(DDItems.BLOATED_BAKED_POTATO.get(), "Bloated Baked Potato");
        add(DDItems.AU_ROTTEN_POTATOES.get(), "Au Rotten Potatoes");
        add(DDItems.POISONOUS_POUTINE.get(), "Poisonous Poutine");
        add(DDItems.SPIDER_BUBBLE_TEA.get(), "Spider Bubble Tea");
        add(DDBlocks.SPIDER_DONUT.get(), "Spider Donut");
        add(DDItems.SILVERFISH_AND_CHIPS_BLOCK.get(), "Silverfish and Chips");
        add(DDItems.SILVERFISH_AND_CHIPS.get(), "Bowl of Silverfish and Chips");
        add(DDItems.CREEPERILLA_SQUIB.get(), "Creeperilla Squib");
        //TODO //add(DDItems.GUNPOWDER_BAKED_SPIDER.get(), "Gunpowder Baked Spider");
        //TODO //add(DDItems.COLESLAW.get(), "Coleslaw");
        //TODO //add(DDItems.DYNAMITE_ROLL.get(), "Dynamite Roll");
        add(DDItems.ROTBULB_CROP.get(), "Rotbulbling");
        add(DDItems.ROTBULB_PLANT.get(), "Wild Rotbulb");
        add(DDItems.RUBABOO.get(), "Rubaboo");
        add(DDItems.JELLY_BEANS.get(), "Jelly Beans");
        add(DDItems.WISPY_RICE_BALL.get(), "Wispy Rice Ball");
        add(DDItems.POLTERGHAST_PIZZA.get(), "Polterghast Pizza");
        add(DDItems.POLTERGHAST_PIZZA_SLICE.get(), "Polterghast Pizza Slice");
        add(DDItems.BREEZE_CREAM_CONE.get(), "Breeze Cream Cone");
        add(DDItems.MARSHBELLOW.get(), "Marshbellow");
        add(DDItems.ECHO_ROCK_CANDY.get(), "Echo Rock Candy");
        add(DDItems.TRIAL_FREAKSHAKE.get(), "Trial Freakshake");
        add(DDItems.BOGGED_BRAIN.get(), "Bogged Brain");
        add(DDItems.BRAINS_IN_A_BRICK.get(), "Brains in a Brick");
        add(DDItems.HAGGIS.get(), "Haggis");
        add(DDItems.CROAK_MONSTER.get(), "Croak Monster");
        add(DDItems.ROTGOURD_SLICE.get(), "Rotgourd Slice");
        add(DDItems.PUTRID_SPICE_LATTE.get(), "Putrid Spice Latte");
        add(DDItems.NECRONOG.get(), "Necronog");
        add(DDItems.ROTPOP.get(), "Rotpop");
        add(DDItems.ROT_ROAST.get(), "Rot Roast");
        add(DDItems.SPIDER_BISQUE.get(), "Spider Bisque");
        add(DDItems.ROT_AND_STEEL.get(), "Rot and Steel");
        //INTEGRATION
        add(TFItems.MAZE_SMORE.get(), "Maze Smore");
        add(MDItems.POI_CUP.get(), "Poi Cup");
        add(MDItems.RUBABOO_CUP.get(), "Rubaboo Cup");
        add(MDItems.SALT_SOAKED_STEW_CUP.get(), "Salt Soaked Stew Cup");
        add(MDItems.SPIDER_SALMAGUNDI_CUP.get(), "Spider Salmagundi Cup");
        add(FFItems.LUTEFISK.get(), "Lutefisk");
        add(ADItems.SCULK_DOGAPPLE.get(), "sculk dogapple");
        add(ADItems.SCULK_CATBLUEBERRY.get(), "sculk catblueberry");
        add(TFItems.BUG_CHOPS.get(), "Bug Chops");
        add(TFItems.FRIED_BUG_CHOPS.get(), "Fried Bug Chops");
        add(TFItems.LIVEROOT_BEER.get(), "Liveroot Beer");
        add(TFItems.TORCHBERRY_RAISINS.get(), "Torchberry Raisins");
        add(TFItems.WILDERNESS_LUNCHEON.get(), "Wilderness Luncheon");
        add(TFItems.MAZE_ROLL.get(), "Maze Roll");
        add(TFItems.TOWER_BOREITO.get(), "Tower Boreito");
        add(TFItems.AURORA_ICE_CREAM.get(), "Aurora Ice Cream");
        add(TFItems.MEEF_WELLINGTON.get(), "Meef Wellington");
        add(TFItems.BLAZING_BLOOD_SAUSAGE.get(), "Blazing Blood Sausage");
        add(TFItems.ARCANE_CHILI.get(), "Arcane Chili");
        add(TFItems.HYDRA_FRICASSEE.get(), "Hydra Fricassee");
        add(TFItems.TROLLBER_CHUTNEY.get(), "Trollber Chutney");
        add(TFItems.SWEETBREAD.get(), "Sweetbread");
        add(TFItems.SCALY_FIDDLEHEAD_RISOTTO.get(), "Scaly Fiddlehead Risotto");
        add(AEItems.ZANITE_KNIFE.get(), "Zanite Knife");
        add(AEItems.ZANITE_CLEAVER.get(), "Zanite Cleaver");
        add(AEItems.GRAVITITE_KNIFE.get(), "Gravitite Knife");
        add(AEItems.GRAVITITE_CLEAVER.get(), "Gravitite Cleaver");
        add(AEItems.MARBLED_MEAT.get(), "Marbled Meat");
        add(AEItems.COOKED_MARBLED_MEAT.get(), "Cooked Marbled Meat");
        add(AEItems.VOLAILLE.get(), "Volaille");
        add(AEItems.VENOMOUS_ONIGIRI.get(), "Venomous Onigiri");
        add(AEItems.FLUFFY_FLOSS.get(), "Fluffy Floss");
        add(AEItems.AMBROSIA_RING.get(), "Ambrosia Ring");
        add(AEItems.AMBER_E_OLIO.get(), "Amber e Olio");
        add(AEItems.SKYBERRY_BREW.get(), "Skyberry Brew");

        //ENTITIES
        add(DDEntities.MONSTER_YAM.get(), "Monster Yam");
        add(DDEntities.ZOMBIFIED_DRYAD.get(), "Zombified Dryad");
        add(DDEntities.CLEAVER.get(), "Cleaver");
        add(DDEntities.ANCIENT_EGG.get(), "Ancient Egg");
        add(DDEntities.RANCID_REDUCTION.get(), "Rancid Reduction");
        add(DDEntities.GUNK_ARROW.get(), "Gunk Arrow");
        add(DDEntities.ECHO_BLAST.get(), "Echo Blast");

        //ENCHANTMENTS
        add("enchantment.dungeonsdelight.ricochet", "Ricochet");
        add("enchantment.dungeonsdelight.serrated_strike", "Serrated Strike");

        //EFFECTS
        add(DDEffects.SERRATED, "Serrated");
        add(DDEffects.FERAL_BITE, "Feral Bite");
        add(DDEffects.RAVENOUS_RUSH, "Ravenous Rush");
        add(DDEffects.PUTRID_SCENT, "Putrid Scent");

        //MONSTER EFFECTS
        add(DDEffects.BURROW_GUT, "Burrow Gut"); //monster haste
        add(DDEffects.EXUDATION, "Exudation"); //monster absorption
        add(DDEffects.POUNCING, "Pouncing"); //monster leaping
        add(DDEffects.VORACITY, "Voracity"); //monster nourishment
        add(DDEffects.TENACITY, "Tenacity"); //monster comfort
        add(DDEffects.DECISIVE, "Decisive"); //monster strength
        add(DDEffects.SWIFT_STEP, "Swift Step"); //monster speed
        add(DDEffects.ROTGUT, "Rotgut"); //monster regeneration

        add("effect.dungeonsdelight.burrow_gut.description", "Consumes haste, destroying blocks will replenish hunger based on hardness, has a chance to grant ravenous rush which will increase block breaking speed based on duration.");
        add("effect.dungeonsdelight.exudation.description", "Consumes absorption, all absorption hearts become Exudation hearts and take 1.25x extra damage but unleash a deadly explosion upon getting damaged.");
        add("effect.dungeonsdelight.voracity.description", "Consumes nourishment, attacking mobs causes the user to consume them. Upon consuming a mob fully, the user will receive ravenous rush for a short time.");
        add("effect.dungeonsdelight.pouncing.description", "Consumes jump boost, allows the user to climb up blocks when sneaking and grants +5% increased movement speed per level.");
        add("effect.dungeonsdelight.tenacity.description", "Consumes comfort, the user heals faster based on how close they are to starving (slower when fuller).");
        add("effect.dungeonsdelight.decisive.description", "Consumes strength, the user has a chance to deal a critical strike hit that inflicts 1.5x extra damage of the original attack.");

        add("effect.dungeonsdelight.feral_bite.description", "The user of this effect can inflict serrated on attacked targets.");
        add("effect.dungeonsdelight.serrated.description", "The user of this effect will very slowly take damage, the damage bypasses most forms of protection.");
        add("effect.dungeonsdelight.ravenous_rush.description", "Increases the movement speed and attack speed of the user, grants additional effects based on other effects that are active.");
        add("effect.dungeonsdelight.putrid_scent.description", "Nearby hostile undead mobs will begin to prioritize and run towards the user of this effect.");

        //DAMAGE
        addDamage(DDDamageTypes.DUNGEON_STOVE_BURN, "%1$s was monstrously grilled to perfection",
                "%1$s was thrown on the grill by The Monstrous Chef %2$s");

        addDamage(DDDamageTypes.LIVING_ESSENCE, "%1$s had their life essence sapped out",
                "%2$s sapped out the life essence of %1$s");

        addDamage(DDDamageTypes.SKULL_HEART_BLAST, "%1$s was melted by a monstrous blast",
                "%1$s was melted by the monstrous blast of %2$s");

        addDamage(DDDamageTypes.ANCIENT_EGG, "%1$s had their body overtaken by sculk",
                "%2$s turned %1$s into a sculk cluster");

        addDamage(DDDamageTypes.CLEAVER, "%1$s was sliced and diced into a delight",
                "%2$s sliced and diced %1$s into a delight");

        addDamage(DDDamageTypes.SERRATED, "%1$s was left to bleed out their wounds",
                "%2$s left %1$s to bleed out their wounds");

        addDamage(DDDamageTypes.BLOODY_MARY, "%1$s had their entire body monsterized...",
                "%2$s watched %1$s have their body monsterize before them...");

        addDamage(DDDamageTypes.SHATTER, "%1$s was struck in the head with a bottle",
                "%2$s shattered the head of %1$s with a bottle");

        addDamage(DDDamageTypes.RAW_CREEPER, "%1$s combusted from the inside out",
                "%2$s watched %1$s combust from the inside out");

        addDamage(DDDamageTypes.ECHO_BLAST, "%1$s had their whole body reverberated by Echo Blast",
                "%2$s reverberated the body of %1$s using an Echo Blast");

        //EFFECT SUBTITLES
        add("subtitles.effect.decisive.crit", "Decisive slicing");
        add("subtitles.effect.monsterize.activate", "Status effect devoured");
        add("subtitles.effect.monsterize.cure", "Monster effect cured");
        //CLEAVER SUBTITLES
        add("subtitles.item.cleaver.ready", "Cleaver primes");
        add("subtitles.item.cleaver.flying", "Cleaver wooshes");
        add("subtitles.item.cleaver.throw", "Cleaver thrown");
        add("subtitles.item.cleaver.hit_block", "Cleaver hits");
        add("subtitles.item.cleaver.hit_entity", "Cleaver hits mob");
        add("subtitles.item.cleaver.ricochet", "Cleaver ricochets");
        add("subtitles.item.cleaver.serrated_strike", "Cleaver slices");
        //MONSTER YAM SUBTITLES
        add("subtitles.entity.monster_yam.ambient", "Monster Yam gurgles");
        add("subtitles.entity.monster_yam.hurt", "Monster Yam hurts");
        add("subtitles.entity.monster_yam.death", "Monster Yam dies");
        //ZOMBIFIED DRYAD SUBTITLES
        add("subtitles.entity.zombified_dryad.ambient", "Zombified Dryad gurgles");
        add("subtitles.entity.zombified_dryad.hurt", "Zombified Dryad hurts");
        add("subtitles.entity.zombified_dryad.death", "Zombified Dryad dies");
        //MISC SUBTITLES
        add("subtitles.item.rancid_reduction.rot", "Rancid Reduction rots");
        add("subtitles.random.acidic_hiss", "Acidic effect vaporizes");
        add("subtitles.item.rot_and_steel.use", "Rot and Steel click");

        //ADVANCEMENTS
        add("dungeonsdelight.advancement.root", "Dungeon's Delight");
        add("dungeonsdelight.advancement.root.desc", "A world of monsters await you!");

        add("dungeonsdelight.advancement.place_monster_pot", "Delicious in a Dungeon");
        add("dungeonsdelight.advancement.place_monster_pot.desc", "Put down a Monster Pot and start preparing monstrous meals!");

        add("dungeonsdelight.advancement.eat_biteable_food", "Chewy!");
        add("dungeonsdelight.advancement.eat_biteable_food.desc", "Take a bite from a biteable food, a food that can be eaten several times and refilled with specific items");

        add("dungeonsdelight.advancement.get_stained_scrap", "Heavy Metal");
        add("dungeonsdelight.advancement.get_stained_scrap.desc", "Destroy a spawner to obtain Stained Scrap, a piece of metal with lively capabilities");

        add("dungeonsdelight.advancement.place_dungeon_stove", "Uncaged, Unbound");
        add("dungeonsdelight.advancement.place_dungeon_stove.desc", "Place down a Dungeon Stove, allowing you to heat a Monster Pot without a spawner");

        add("dungeonsdelight.advancement.eat_monster_food", "Ah, Dungeon Food");
        add("dungeonsdelight.advancement.eat_monster_food.desc", "Combine monsters and ingredients together to create your first monster food");

        add("dungeonsdelight.advancement.get_slime_noodles", "Creepy Pasta");
        add("dungeonsdelight.advancement.get_slime_noodles.desc", "Cut a Slime Slab into gooey noodles");

        add("dungeonsdelight.advancement.get_sculk_polyp", "Apple of the Earth");
        add("dungeonsdelight.advancement.get_sculk_polyp.desc", "Cut a chunk of Sculk into a Sculk Polyp");

        add("dungeonsdelight.advancement.place_embedded_eggs", "Won’t Take a Century");
        add("dungeonsdelight.advancement.place_embedded_eggs.desc", "Bury some Eggs in Sculk and let it fester");

        add("dungeonsdelight.advancement.obtain_burrow_gut", "Eater of Worlds"); // Silverfish foods will allow you to consume blocks by destroying them but only can eat monster foods, transforms the Haste effect
        add("dungeonsdelight.advancement.obtain_burrow_gut.desc", "Monsterize Haste into Burrow Gut with Silverfish food, allowing you to consume blocks while only allowing you to eat monster foods");

        add("dungeonsdelight.advancement.obtain_voracity", "Don't Starve"); // Plated monster foods will allow you to eat monsters alive but only can eat monster foods, transforms the Nourishment effect
        add("dungeonsdelight.advancement.obtain_voracity.desc", "Monsterize Nourishment into Voracity with plated monster food, allowing you to eat mobs while only allowing you to eat monster foods");

        add("dungeonsdelight.advancement.obtain_tenacity", "Struggler");
        add("dungeonsdelight.advancement.obtain_tenacity.desc", "Monsterize Comfort into Tenacity with bowled monster food, naturally healing you based on how hungry you are");

        add("dungeonsdelight.advancement.obtain_pouncing", "Eye of The Spider"); // Spider foods will allow you to climb and slide down blocks, transforms the Leaping effect
        add("dungeonsdelight.advancement.obtain_pouncing.desc", "Monsterize Leaping into Pouncing with Spider food, allowing you climb up and down blocks like a Spider");

        add("dungeonsdelight.advancement.obtain_decisive", "Chance Roll"); // Stick held monster foods have a chance to land 1.75x critical hits, transforms the Strength effect
        add("dungeonsdelight.advancement.obtain_decisive.desc", "Monsterize Strength into Decisive with monster food on a stick, giving you a chance to deal 1.75x damage when attacking");

        add("dungeonsdelight.advancement.eat_horse", "How Hungry...?");
        add("dungeonsdelight.advancement.eat_horse.desc", "Consume an entire horse alive using the Voracity effect");

        add("dungeonsdelight.advancement.use_cleaver", "Heaven Pierce Her");
        add("dungeonsdelight.advancement.use_cleaver.desc", "Throw a Cleaver to pierce and scavenge extra goods from foes");

        add("dungeonsdelight.advancement.get_netherite_cleaver", "Cutlery of Apostasy");
        add("dungeonsdelight.advancement.get_netherite_cleaver.desc", "Upgrade your cleaver using Netherite");

        add("dungeonsdelight.advancement.obtain_perception", "I Can See Everything!");
        add("dungeonsdelight.advancement.obtain_perception.desc", "Glowing foods allow the user to see other entities through walls");

        add("dungeonsdelight.advancement.eat_sculk_food", "Paint the Town Blue"); // Sculk foods cause a shockwave that knocks entities far back
        add("dungeonsdelight.advancement.eat_sculk_food.desc", "Consume some Sculk food and create a damaging shockwave");

        add("dungeonsdelight.advancement.place_rotbulb_crop", "Corpsebloom");
        add("dungeonsdelight.advancement.place_rotbulb_crop.desc", "Plant a Rotbulbling");

        add("dungeonsdelight.advancement.obtain_exudation", "Evil Up"); // Rot foods will make your absorption hearts take additional damage but explode on entities that hit you, transforms the Absorption effect
        add("dungeonsdelight.advancement.obtain_exudation.desc", "Monsterize Absorption into Exudation with rotten foods, giving you additional health that will deal damage to mobs when depleted");

        add("dungeonsdelight.advancement.eat_bloody_mary", "Bloody Mary Challenge");
        add("dungeonsdelight.advancement.eat_bloody_mary.desc", "Consume a Bloody Mary...and face the consequences");

        add("dungeonsdelight.advancement.eat_sniffer_food", "Is It Worth It?");
        add("dungeonsdelight.advancement.eat_sniffer_food.desc", "Harmonize a Monster Effect by consuming a Sniffer food");

        add("dungeonsdelight.advancement.get_candied_sucker", "Sweet Revenge!");
        add("dungeonsdelight.advancement.get_candied_sucker.desc", "Imprison a Vex or Silverfish inside of some Amethyst Rock Candy");

        add("dungeonsdelight.advancement.eat_ghastly_spirits", "Fizzy Lifting Drink");
        add("dungeonsdelight.advancement.eat_ghastly_spirits.desc", "Consume a bottle of Ghastly Spirits");

        add("dungeonsdelight.advancement.all_monster_effects", "Monsters Smashed");
        add("dungeonsdelight.advancement.all_monster_effects.desc", "Obtain all monster effects at the same time. Who's the real monster now?");

        add("dungeonsdelight.advancement.all_monster_foods", "The Privilege of The Living");
        add("dungeonsdelight.advancement.all_monster_foods.desc", "Consume all monster foods and drinks...you, uh, monster");

        add("dungeonsdelight.advancement.all_knife_mob_drops", "Gastrocryptozoologist");
        add("dungeonsdelight.advancement.all_knife_mob_drops.desc", "Obtain all monster Knife drops");

        add("dungeonsdelight.advancement.all_dungeonsdelight_foods", "Meal of Champions");
        add("dungeonsdelight.advancement.all_dungeonsdelight_foods.desc", "It doesn't matter what it is, CONSUME THEM ALL.");

        add("dungeonsdelight.advancement.get_stained_weapon", "A Slice of Life");
        add("dungeonsdelight.advancement.get_stained_weapon.desc", "Obtain a Stained Knife or Stained Cleaver, and steal the life essence of foes to charge it.");

        add("dungeonsdelight.advancement.use_rancid_reduction", "Things are Grim Indeed...");
        add("dungeonsdelight.advancement.use_rancid_reduction.desc", "Obtain a Rancid Reduction, a putrid bottle that can rot organic matter");

        add("dungeonsdelight.advancement.break_bubblegunk", "...And I'm All Out of Gum");
        add("dungeonsdelight.advancement.break_bubblegunk.desc", "Fully consume some Bubblegunk, an amalgamation of rot that can decrease hunger");

        add("dungeonsdelight.advancement.feed_wormouth", "Symbiosis");
        add("dungeonsdelight.advancement.feed_wormouth.desc", "Feed a Wormouth and let it expel you a reward");

        add("dungeonsdelight.advancement.slime_food_not_consumed", "Choking Hazard");
        add("dungeonsdelight.advancement.slime_food_not_consumed.desc", "Eat some Slime food without consuming it");

        add("dungeonsdelight.advancement.use_gunk_arrow", "Air Pollution");
        add("dungeonsdelight.advancement.use_gunk_arrow.desc", "Shoot a Gunk Arrow at something");

        add("dungeonsdelight.advancement.use_gunk_arrow_on_monster_yam", "Septic Tank");
        add("dungeonsdelight.advancement.use_gunk_arrow_on_monster_yam.desc", "Shoot a Gunk Arrow at a Monster Yam and have its own horde attack it");

        add("dungeonsdelight.advancement.obtain_swift_step", "Easy Breezy!"); // Breeze foods will allow you to dash when sneaking midair but inflicts Weakness, transforms the Speed effect
        add("dungeonsdelight.advancement.obtain_swift_step.desc", "Monsterize Speed into Swift Step with Breeze foods, reducing your damage output but enabling you to dash in midair while sneaking");

        add("dungeonsdelight.advancement.sick_throw_dude", "Crackshot");
        add("dungeonsdelight.advancement.sick_throw_dude.desc", "Use a Cleaver to slice an airborne Ancient Egg in half");

        add("dungeonsdelight.advancement.knife_fight", "Knife to a Sniper Fight");
        add("dungeonsdelight.advancement.knife_fight.desc", "Kill a Skeleton from at least 15 meters away using a Cleaver");

        add("dungeonsdelight.advancement.free_dryad", "Free Spirit");
        add("dungeonsdelight.advancement.free_dryad.desc", "Free the spirit of a Zombified Dryad using a Cleaver");

        add("dungeonsdelight.advancement.obtain_rotgut", "The Rot Consumes"); // Rotgourd foods will grant rotten hearts which can be siphoned back into normal health, transforms the Regeneration effect
        add("dungeonsdelight.advancement.obtain_rotgut.desc", "Monsterize Regeneration into Rotgut with Rotgourd food, allowing you to restore lost health by attacking enemies");

        add("dungeonsdelight.advancement.monsterize_effect", "To Become the Monster...");
        add("dungeonsdelight.advancement.monsterize_effect.desc", "Monsterize an effect by having its Monster Effect equivalent");

        add("dungeonsdelight.advancement.create_spirit_fire", "Maybe in Another Life");
        add("dungeonsdelight.advancement.create_spirit_fire.desc", "Use Rot and Steel to create Spirit Fire on soul blocks");

        //ENCHANTMENT DESCRIPTIONS
        add("enchantment.dungeonsdelight.ricochet.desc", "Thrown cleavers now bounce, each bounce increases the damage by 1.25x.");
        add("enchantment.dungeonsdelight.serrated_strike.desc", "Cleavers inflict serrated onto struck entities causing protection bypassing damage.");

        //YAPPING TOOLTIPS
        add(YT_ID + ".block." + DD_ID + ".monster_pot.desc", "A mysterious cooking utensil that uses the heat of monster spawners to cook delicacies");
        add(YT_ID + ".block." + DD_ID + ".dungeon_stove.desc", "A stove powered by the energy of life that of which can be exploited for some tasty delights");
        add(YT_ID + ".item." + DD_ID + ".slime_bar.desc", "Sticky slime that has been congealed into a malleable block");
        add(YT_ID + ".item." + DD_ID + ".slime_noodles.desc", "Slippery noodles that almost snake and move on their own");
        add(YT_ID + ".item." + DD_ID + ".ghoulash.desc", "The most approachable of monster meals");
        add(YT_ID + ".item." + DD_ID + ".silverfish_abdomen.desc", "Emergency protein that’s rich with minerals!");
        add(YT_ID + ".item." + DD_ID + ".amethyst_rock_candy.desc", "Don’t tell Abigail!");
        add(YT_ID + ".item." + DD_ID + ".candied_vex_sucker.desc", "An even sweeter revenge!");
        add(YT_ID + ".item." + DD_ID + ".candied_silverfish_sucker.desc", "Edible pest control");
        add(YT_ID + ".item." + DD_ID + ".ghast_calamari.desc", "If it weren't for the ring shape it would slip out your hands!");
        add(YT_ID + ".item." + DD_ID + ".fried_ghast_calamari.desc", "A fried snack to enjoy on a journey");
        add(YT_ID + ".item." + DD_ID + ".ghast_tentacle.desc", "Hope you don't mind the slimy texture!");
        add(YT_ID + ".item." + DD_ID + ".silverfish_fried_rice.desc", "It did what now!?!?");
        add(YT_ID + ".item." + DD_ID + ".spider_meat.desc", "The least poisonous parts of the spider");
        add(YT_ID + ".item." + DD_ID + ".spider_extract.desc", "Don’t call it “Spider Milk”!");
        add(YT_ID + ".item." + DD_ID + ".smoked_spider_meat.desc", "Vague hint of acidity, slight hint of crab, and a big chunk of charcoal");
        add(YT_ID + ".item." + DD_ID + ".spider_tanghulu.desc", "You feel an evil presence watching you...");
        add(YT_ID + ".item." + DD_ID + ".spider_salmagundi.desc", "A violent mixture of various spider guts");
        add(YT_ID + ".block." + DD_ID + ".wormwood_cupboard.desc", "When a house is both hungry and awake, every room becomes a mouth");
        add(YT_ID + ".block." + DD_ID + ".wormroot_tendrils.desc", "It feels like its wrapping around you as you grasp it in your hand");
        add(YT_ID + ".block." + DD_ID + ".wormwood_planks.desc", "Peculiar planks constructed from wormroots");
        add(YT_ID + ".block." + DD_ID + ".wormwood_mosaic.desc", "Peculiar planks that have been finely chiseled");
        add(YT_ID + ".block." + DD_ID + ".wormwood_stairs.desc", "Sinister stairs constructed from wormroots");
        add(YT_ID + ".block." + DD_ID + ".wormwood_mosaic_stairs.desc", "Sinister stairs constructed from wormwood mosaic");
        add(YT_ID + ".block." + DD_ID + ".wormwood_slab.desc", "Insidious slabs constructed from wormroots");
        add(YT_ID + ".block." + DD_ID + ".wormwood_mosaic_slab.desc", "Insidious slabs constructed from wormwood mosaic");
        add(YT_ID + ".block." + DD_ID + ".wormwood_fence.desc", "To keep THEM out or to keep you in...");
        add(YT_ID + ".block." + DD_ID + ".wormwood_fence_gate.desc", "Can be opened, but who would want to do that...");
        add(YT_ID + ".block." + DD_ID + ".wormwood_door.desc", "When a house is both hungry and awake, every room becomes a mouth...");
        add(YT_ID + ".block." + DD_ID + ".wormwood_trapdoor.desc", "This sinister smirk seems to be beckoning you into a trap");
        add(YT_ID + ".block." + DD_ID + ".wormwood_pressure_plate.desc", "Produces a redstone signal when ANY entity makes contact with it but is pressed down longer the darker it is");
        add(YT_ID + ".block." + DD_ID + ".wormwood_button.desc", "Can be pushed by players, arrows, and tridents, stays pushed for longer the darker it is");
        add(YT_ID + ".block." + DD_ID + ".wormwood_cabinet.desc", "Place your food in a cool, damp, dark place where no one can find it");
        add(YT_ID + ".item." + DD_ID + ".bubblegunk.desc", "I have come to chew bubblegunk and kick cubes. And I'm all out of bubblegunk.");
        add(YT_ID + ".item." + DD_ID + ".sculk_polyp.desc", "It is still filled with a little experience...");
        add(YT_ID + ".block." + DD_ID + ".embedded_eggs.desc", "Not truly dead, because it is never truly born. A perfect catalyst for flavor");
        add(YT_ID + ".block." + DD_ID + ".heap_of_ancient_eggs.desc", "Souls in stasis or souls unrestrained? What would happen if you had a taste?");
        add(YT_ID + ".item." + DD_ID + ".ancient_egg.desc", "When sculk converts a life that is yet to die, an unimaginable delicacy");
        add(YT_ID + ".item." + DD_ID + ".cleaved_ancient_egg.desc", "An ancient egg pierced of it’s leathery skin");
        add(YT_ID + ".item." + DD_ID + ".sculk_mayo.desc", "A cacophony of rotten eggs and spoiled grapes");
        add(YT_ID + ".item." + DD_ID + ".flint_cleaver.desc", "A crude flint blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".iron_cleaver.desc", "A strong iron blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".golden_cleaver.desc", "A hasty golden blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".diamond_cleaver.desc", "A shimmering diamond blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".netherite_cleaver.desc", "A durable netherite blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".monster_mousse.desc", "A sweet squishy dish that illuminates your insides");
        add(YT_ID + ".block." + DD_ID + ".monster_mousse_block.desc", "A lantern just isn’t sweet enough");
        add(YT_ID + ".item." + DD_ID + ".rotten_tripe.desc", "Rotten flesh that has been trimmed of the most unsafe spots");
        add(YT_ID + ".item." + DD_ID + ".gelled_salad.desc", "Not really a soup, not really a salad but something all it’s own");
        add(YT_ID + ".block." + DD_ID + ".sculk_mayo_block.desc", "A cubic meter of solid mayonnaise");
        add(YT_ID + ".block." + DD_ID + ".rotbulb_crop.desc", "Spawns in clusters on top of mud underground");
        add(YT_ID + ".block." + DD_ID + ".rotbulb_plant.desc", "Spawns in clusters on top of mud underground");
        add(YT_ID + ".item." + DD_ID + ".rotbulb_crop.desc", "Spawns in clusters on top of mud underground");
        add(YT_ID + ".item." + DD_ID + ".rotbulb_plant.desc", "Spawns in clusters on top of mud underground");
        add(YT_ID + ".item." + DD_ID + ".rotbulb.desc", "Like biting into a sack of fetid wax");
        add(YT_ID + ".item." + DD_ID + ".gunk.desc", "The purest form of disgust localized within a clump in your hands");
        add(YT_ID + ".item." + DD_ID + ".monster_yam_spawn_egg.desc", "This crop won't let you harvest it so easily...");
        add(YT_ID + ".item." + DD_ID + ".zombified_dryad.desc", "A Dryad that has been reborn with the life power of a Monster Yam");
        add(YT_ID + ".block." + DD_ID + ".stained_scrap_block.desc", "A metal block constructed from a collection of stained scrap");
        add(YT_ID + ".block." + DD_ID + ".chiseled_stained_scrap.desc", "A metal block that has been engraved");
        add(YT_ID + ".block." + DD_ID + ".stained_scrap_bars.desc", "Metal bars constructed from stained scrap");
        add(YT_ID + ".block." + DD_ID + ".cut_stained_scrap.desc", "A stained scrap block that has been cut into tiles");
        add(YT_ID + ".block." + DD_ID + ".cut_stained_scrap_stairs.desc", "Metallic stairs constructed from stained scrap");
        add(YT_ID + ".block." + DD_ID + ".cut_stained_scrap_slab.desc", "Metallic slabs constructed from stained scrap");
        add(YT_ID + ".block." + DD_ID + ".rotbulb_crate.desc", "A crate filled to the brim with rotbulbs");
        add(YT_ID + ".item." + DD_ID + ".cob_n_candy.desc", "Careful it will dissolve in water!");
        add(YT_ID + ".item." + DD_ID + ".slicorice.desc", "Rawboy");
        add(YT_ID + ".item." + DD_ID + ".gritty_flesh.desc", "A refined palate can tell where husk flesh comes from just by the natural seasoning");
        add(YT_ID + ".item." + DD_ID + ".brined_flesh.desc", "Gelatinous hunk of meat that slips effortlessly through your fingers");
        add(YT_ID + ".item." + DD_ID + ".devilish_eggs.desc", "A sinister combination of the weirdest smelling egg and the weirdest smelling condiment");
        add(YT_ID + ".item." + DD_ID + ".ghast_roll.desc", "A delicious mixture of ghast and rotbulb");
        add(YT_ID + ".item." + DD_ID + ".tokayaki.desc", "A soft dish filled with some mushy sculk");
        add(YT_ID + ".item." + DD_ID + ".salt_soaked_stew.desc", "The flesh seems to have melted into the broth itself");
        add(YT_ID + ".item." + DD_ID + ".soaked_skewer.desc", "Wet flesh hangs flimsily onto the bone");
        add(YT_ID + ".item." + DD_ID + ".poi.desc", "No one will be a bigger hater than Yirmiri");
        add(YT_ID + ".item." + DD_ID + ".monster_muffin.desc", "Only cowards use yeast");
        add(YT_ID + ".item." + DD_ID + ".rancid_reduction.desc", "A little bottle of monster rot");
        add(YT_ID + ".block." + DD_ID + ".sculk_tart.desc", "Mouth full of souls!");
        add(YT_ID + ".item." + DD_ID + ".sculk_tart_slice.desc", "Slice full of souls!");
        add(YT_ID + ".block." + DD_ID + ".monster_cake.desc", "Sad the monsters aren't around for their own party");
        add(YT_ID + ".item." + DD_ID + ".monster_cake_slice.desc", "A slice of life... literally...");
        add(YT_ID + ".block." + DD_ID + ".ossobuco_block.desc", "Who would think after your fate you'd become a serving dish");
        add(YT_ID + ".item." + DD_ID + ".ossobuco.desc", "Be careful not to eat the bones... unless you want to.");
        add(YT_ID + ".block." + DD_ID + ".spider_pie.desc", "Natural sweeteners");
        add(YT_ID + ".item." + DD_ID + ".spider_pie_slice.desc", "When you want your spider guts on the go!");
        add(YT_ID + ".item." + DD_ID + ".sculk_apple.desc", "An apple that has been candied in sculk");
        add(YT_ID + ".item." + DD_ID + ".shiokara.desc", "A dish filled with the visceral insides of monsters");
        add(YT_ID + ".item." + DD_ID + ".bloody_mary.desc", "\"You swear you saw a silhouette in the reflection of the glass...\"");
        add(YT_ID + ".item." + DD_ID + ".wardenzola.desc", "A block of vile smelling cheese that tastes faintly of raisins");
        add(YT_ID + ".item." + DD_ID + ".wardenzola_crumbles.desc", "The sculk seems to grow just as new parts are exposed to air");
        add(YT_ID + ".item." + DD_ID + ".malicious_sandwich.desc", "Something malicious is brewing...");
        add(YT_ID + ".item." + DD_ID + ".taro_milk_tea.desc", "The milk and sugar seems to cancel out the spoiled rot");
        add(YT_ID + ".item." + DD_ID + ".sniffer_shank.desc", "Only a monster would cut the leg off such a joyful creature");
        add(YT_ID + ".item." + DD_ID + ".cooked_sniffer_shank.desc", "The cooked leg of a once joyful creature");
        add(YT_ID + ".item." + DD_ID + ".soft_serve_sniffer_egg.desc", "Well, I guess the egg comes first");
        add(YT_ID + ".item." + DD_ID + ".snifferwurst.desc", "A raw piece of meat that can regenerate anything but itself");
        add(YT_ID + ".item." + DD_ID + ".cooked_snifferwurst.desc", "A searing piece of delicious meat");
        add(YT_ID + ".item." + DD_ID + ".terrine_loaf.desc", "A horde of every rotten beast congealed into a flavour of unimaginable complexity");
        add(YT_ID + ".item." + DD_ID + ".gyudon.desc", "A challenging meal of grease and protein");
        add(YT_ID + ".item." + DD_ID + ".ghastly_spirits.desc", "The spirits of the sand are free... or just to get caught up in a bottle");
        add(YT_ID + ".item." + DD_ID + ".ominous_omelette.desc", "A peculiar tingling filling wrapped within a shroud of savory eggs");
        add(YT_ID + ".item." + DD_ID + ".creeperilla.desc", "A crunchy leaf-like plant filled with gunpowder, be careful with how you cut");
        add(YT_ID + ".item." + DD_ID + ".sinigang.desc", "Life and death, sweet and sour...");
        add(YT_ID + ".item." + DD_ID + ".gunk_arrow.desc", "An arrow that has been blunted by gunk, the foul spell attracts the undead");
        add(YT_ID + ".item." + DD_ID + ".snuffledog.desc", "Ketchup or mustard?");
        add(YT_ID + ".item." + DD_ID + ".guardian_angel.desc", "All that effort decorating your dish just to smash it onto a plate");
        add(YT_ID + ".item." + DD_ID + ".guardian_angel_block.desc", "Gel in the shape of a guardian... so like... a guardian angel?");
        add(YT_ID + ".item." + DD_ID + ".chicken_jockey_sandwich.desc", "CHICKEN JOCKEY!!!");
        add(YT_ID + ".item." + DD_ID + ".au_rotten_potatoes.desc", "A flower pot full of... a foul stench, yuck");
        add(YT_ID + ".item." + DD_ID + ".bloated_baked_potato.desc", "Bloated to the brim with rich (and mostly disgusting) ingredients");
        add(YT_ID + ".item." + DD_ID + ".poisonous_poutine.desc", "A poisonous mess of rotten and disgusting ingredients");
        add(YT_ID + ".block." + DD_ID + ".poisonous_potato_crate.desc", "A crate filled to the brim with poisonous potatoes");
        add(YT_ID + ".block." + DD_ID + ".rotten_tomato_crate.desc", "A crate filled to the brim with rotten tomatoes");
        add(YT_ID + ".item." + DD_ID + ".spider_bubble_tea.desc", "Don't choke on the eyeballs!");
        add(YT_ID + ".item." + DD_ID + ".chloropasta.desc", "A tasty salad full of slimy noodles");
        add(YT_ID + ".block." + DD_ID + ".rotten_spawner.desc", "All the life energy has been consumed into a new life...");
        add(YT_ID + ".block." + DD_ID + ".living_candle.desc", "Life essence keeps the flame ablaze");
        add(YT_ID + ".block." + DD_ID + ".living_lantern.desc", "Life essence keeps the flame ablaze");
        add(YT_ID + ".block." + DD_ID + ".living_campfire.desc", "A station used to cook up to 4 foods slowly");
        add(YT_ID + ".block." + DD_ID + ".living_torch.desc", "Life essence keeps the flame ablaze");
        add(YT_ID + ".item." + DD_ID + ".stained_scrap.desc", "A cold slice of metal that has the ability to bring together life");
        add(YT_ID + ".block." + DD_ID + ".spider_donut.desc", "Don't worry, Spider didn't.");
        add(YT_ID + ".item." + DD_ID + ".stained_scrap_fragment.desc", "A fragment of life");
        add(YT_ID + ".block." + DD_ID + ".stained_scrap_grate.desc", "A metallic grate constructed from stained scrap that allows items to pass through it, great!");
        add(YT_ID + ".item." + DD_ID + ".stained_knife.desc", "A stained blade prepared for stealing life essence, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".stained_cleaver.desc", "A stained blade prepared for stealing life essence, time to slice and dice!");
        add(YT_ID + ".block." + DD_ID + ".wormroot_stalk.desc", "It feels like its creeping around you as you grasp it in your hand");
        add(YT_ID + ".block." + DD_ID + ".wormroot_mouth.desc", "It feels like its tasting you as you grasp it in your hand");
        add(YT_ID + ".block." + DD_ID + ".silverfish_and_chips_block.desc", "Eyes and head included!");
        add(YT_ID + ".item." + DD_ID + ".silverfish_and_chips.desc", "The drooling slime and potato flakes cover the chunks of silverfish underneath");
        add(YT_ID + ".item." + DD_ID + ".creeperilla_squib.desc", "Why are you spending your precious time to read this...");
        add(YT_ID + ".item." + DD_ID + ".coleslaw.desc", "I love coleslaw - SIGNED DRISFISH");
        add(YT_ID + ".item." + DD_ID + ".dynamite_roll.desc", "A snack booming with flavors");
        add(YT_ID + ".item." + DD_ID + ".gunpowder_baked_spider.desc", "Two chunks of meat coated in an explosive powder");
        add(YT_ID + ".block." + DD_ID + ".stained_lantern.desc", "Not to be confused with Living Lanterns!");
        add(YT_ID + ".item." + DD_ID + ".rubaboo.desc", "Spicy meat chunks and sugary ingredients move around in the bowl");
        add(YT_ID + ".item." + DD_ID + ".wormwood_boat.desc", "\"Let's sail the seven seas!\"");
        add(YT_ID + ".item." + DD_ID + ".wormwood_chest_boat.desc", "Can be used as portable storage at the cost of a seat");
        add(YT_ID + ".item." + DD_ID + ".jelly_beans.desc", "Its a 50 50 chance, do you proceed?");
        add(YT_ID + ".item." + DD_ID + ".wispy_rice_ball.desc", "The sticky ball of wind takes forever to chew");
        add(YT_ID + ".item." + DD_ID + ".polterghast_pizza.desc", "There is strictly a zero refund policy.");
        add(YT_ID + ".item." + DD_ID + ".polterghast_pizza_slice.desc", "An explosion of freaky flavors fill your mouth");
        add(YT_ID + ".item." + DD_ID + ".breeze_cream_cone.desc", "A delicious treat for a harrowing feat");
        add(YT_ID + ".item." + DD_ID + ".marshbellow.desc", "A rope of soft deliciousness!");
        add(YT_ID + ".item." + DD_ID + ".echo_rock_candy.desc", "The candy causes any sound to echo no matter the environment");
        add(YT_ID + ".item." + DD_ID + ".bogged_brain.desc", "Actual brain rot");
        add(YT_ID + ".item." + DD_ID + ".brains_in_a_brick.desc", "Bricks make surprisingly great bowls when you are out of options");
        add(YT_ID + ".item." + DD_ID + ".haggis.desc", "Beat the Zombies at their own game!");
        add(YT_ID + ".item." + DD_ID + ".croak_monster.desc", "Ribbit Ribbit");
        add(YT_ID + ".block." + DD_ID + ".stained_scrap_door.desc", "A menacing skull that awaits those that pass...");
        add(YT_ID + ".block." + DD_ID + ".stained_scrap_trapdoor.desc", "This is just screaming to lead to a trap...");
        add(YT_ID + ".block." + DD_ID + ".stained_scrap_pillar.desc", "Metal that has been carved and filled with strange pink gems");
        add(YT_ID + ".item." + DD_ID + ".rotgourd_slice.desc", "A rotting slice of an ominous gourd");
        add(YT_ID + ".block." + DD_ID + ".rotgourd.desc", "A once lively gourd that has been monsterized");
        add(YT_ID + ".block." + DD_ID + ".carved_rotgourd.desc", "Putting this over your head might not be the brightest idea...");
        add(YT_ID + ".block." + DD_ID + ".living_jack_o_lantern.desc", "It's living glare stares back at you with hunger...");
        add(YT_ID + ".item." + DD_ID + ".putrid_spice_latte.desc", "The milk and sugar seems to cancel out the putrid taste");
        add(YT_ID + ".item." + DD_ID + ".rotpop.desc", "How many licks does it take to get to the center!?");
        add(YT_ID + ".item." + DD_ID + ".spider_bisque.desc", "A small rotgourd is growing in the middle");
        add(YT_ID + ".item." + DD_ID + ".rot_roast.desc", "The life essence enhances the delight...");
        add(YT_ID + ".item." + DD_ID + ".music_disc_malady.desc", "Can be inserted into a jukebox to play horrifying tunes");
        add(YT_ID + ".item." + DD_ID + ".necronog.desc", "A delicious treat saved for when its cold");
        //INTEGRATION
        add(YT_ID + ".item." + DD_ID + ".fluffy_floss.desc", "So soft it could evaporate in water... wait...?");
        add(YT_ID + ".item." + DD_ID + ".venomous_onigiri.desc", "Don't eat the venomous parts!");
        add(YT_ID + ".item." + DD_ID + ".skyberry_brew.desc", "An explosion of flavors ready to overtake your taste buds");
        add(YT_ID + ".item." + DD_ID + ".ambrosia_ring.desc", "Are slimes gelatinous or slimy?");
        add(YT_ID + ".item." + DD_ID + ".amber_e_olio.desc", "Also an approachable first");
        add(YT_ID + ".item." + DD_ID + ".zanite_knife.desc", "A strong zanite blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".gravitite_knife.desc", "A shimmering gravitite blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".zanite_cleaver.desc", "A strong zanite blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".gravitite_cleaver.desc", "A shimmering gravitite blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".maze_smore.desc", "The real maze is the gunk between your teeth");
        add(YT_ID + ".item." + DD_ID + ".rubaboo_cup.desc", "Spicy meat chunks and sugary ingredients move around in the cup");
        add(YT_ID + ".item." + DD_ID + ".spider_salmagundi_cup.desc", "A violent mixture of various spider guts");
        add(YT_ID + ".item." + DD_ID + ".salt_soaked_stew_cup.desc", "The flesh seems to have melted into the broth itself");
        add(YT_ID + ".item." + DD_ID + ".poi_cup.desc", "\"The cup makes it slightly better I guess...\"");
        add(YT_ID + ".item." + DD_ID + ".lutefisk.desc", "A size 1 fish that will instantly blind anyone who dares consume it");
        add(YT_ID + ".item." + DD_ID + ".sculk_blueberrycat.desc", "goth bluberycat");
        add(YT_ID + ".item." + DD_ID + ".sculk_dogapple.desc", "goth applog");
        add(YT_ID + ".item." + DD_ID + ".bug_chops.desc", "Tellio's favorite!");
        add(YT_ID + ".item." + DD_ID + ".fried_bug_chops.desc", "Fried bug ready for the tasting");
        add(YT_ID + ".item." + DD_ID + ".liveroot_beer.desc", "Bet it tastes good with ice cream!");
        add(YT_ID + ".item." + DD_ID + ".torchberry_raisins.desc", "Kinda smells like sculk");
        add(YT_ID + ".item." + DD_ID + ".wilderness_luncheon.desc", "A tasty sandwich filled with the immediate wildlife");
        add(YT_ID + ".item." + DD_ID + ".maze_roll.desc", "Bugs and plants rolled up into a treat!");
        add(YT_ID + ".item." + DD_ID + ".tower_boreito.desc", "A burrito filled with the tower's tasty monsters");
        add(YT_ID + ".item." + DD_ID + ".aurora_ice_cream.desc", "The ice cream faintly glows in differing colors");
        add(YT_ID + ".item." + DD_ID + ".meef_wellington.desc", "Is it a sandwich filled with monster meat or minotaur beef?");
        add(YT_ID + ".item." + DD_ID + ".blazing_blood_sausage.desc", "So hot it almost was given fire resistance II");
        add(YT_ID + ".item." + DD_ID + ".arcane_chili.desc", "\"There is no prize to perfection. Only an end to pursuit.\"");
        add(YT_ID + ".item." + DD_ID + ".hydra_fricassee.desc", "An extremely spicy dish filled with dragon meat");
        add(YT_ID + ".item." + DD_ID + ".trollber_chutney.desc", "A jar of glowing mush");
        add(YT_ID + ".item." + DD_ID + ".sweetbread.desc", "A sweet snack for an adventurer's journey");
        add(YT_ID + ".item." + DD_ID + ".scaly_fiddlehead_risotto.desc", "Once a large dangerous snake, now a creamy mix of rice and Naga meat");
        add(YT_ID + ".item." + DD_ID + ".ironwood_cleaver.desc", "A crude blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".ironwood_knife.desc", "A crude blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".steeleaf_cleaver.desc", "A lush blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".steeleaf_knife.desc", "A lush blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".knightmetal_cleaver.desc", "A heavy blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".knightmetal_knife.desc", "A heavy blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".fiery_cleaver.desc", "A searing blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".fiery_knife.desc", "A searing blade, time to slice and dice!");
        add(YT_ID + ".item." + DD_ID + ".steel_cleaver.desc", "A steel blade, time to slice and dice!");
    }

    private void addDamage(ResourceKey<DamageType> type, String deathMsg, String killMsg) {
        add(type.location().toLanguageKey(), deathMsg);
        add("death.attack." + type.location().toLanguageKey(), deathMsg);
        add("death.attack." + type.location().toLanguageKey() + ".player", killMsg);
    }

    public void add(Holder<MobEffect> key, String id) {
        this.add(key.value().getDescriptionId(), id);
    }
}
