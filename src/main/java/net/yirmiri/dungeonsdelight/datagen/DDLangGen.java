package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.LanguageProvider;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.*;
import net.yirmiri.dungeonsdelight.integration.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;

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
        add("dungeonsdelight_compat_tab", "Dungeon's Delight Compatibility");
        add("farmersdelight.container.monster_pot", "Monster Pot");
        add("farmersdelight.tooltip.wip", "Warning! This item is currently unfinished and is subject to change...");

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
        add("farmersdelight.tooltip.biteable", "Can be eaten multiple times");
        add("farmersdelight.tooltip.small_xp", "Grants a small amount of experience");
        add("farmersdelight.tooltip.average_xp", "Grants a sizeable amount of experience");
        add("farmersdelight.tooltip.large_xp", "Grants a large amount of experience");
        add("farmersdelight.tooltip.ossobusco", "Refills any active monster effects");
        add("farmersdelight.tooltip.chance_to_not_consume", "Chance to not consume when eaten");
        add("farmersdelight.tooltip.sculk_apple", "Instant Health");
        add("farmersdelight.tooltip.undead", "Monsterizes one effect if no monster effects are active");
        add("farmersdelight.tooltip.bloody_mary", "Monsterizes one effect... at a cost");
        add("farmersdelight.tooltip.rancid_reduction", "Can be thrown to rot fully grown crops");
        add("farmersdelight.tooltip.small_xp_poison_potato", "Grants a small amount of experience and cures poison");
        add("farmersdelight.tooltip.average_xp_poison_potato", "Grants a sizeable amount of experience and cures poison");
        add("farmersdelight.tooltip.large_xp_poison_potato", "Grants a large amount of experience and cures poison");
        add("farmersdelight.tooltip.spider_bubble_tea", "Amplifies a random level I effect but halves it's duration");
        add("farmersdelight.tooltip.spider_bubble_tea_pouncing", "Pouncing II (02:00) if consumer has no level I effects");
        add("farmersdelight.tooltip.monster_burger_food", "Monsterizes all effects with monster variants");
        //INTEGRATION
        add("farmersdelight.tooltip.sculk_dogapple", "Instant Health");
        add("farmersdelight.tooltip.arcane_chili", "Can be eaten multiple times, grants random effects");

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
        add(DDBlocks.WORMROOTS.get(), "Wormroots");
        add(DDBlocks.WORMWOOD_CABINET.get(), "Wormwood Cabinet");
        add(DDBlocks.EMBEDDED_EGGS.get(), "Embedded Eggs");
        add(DDBlocks.HEAP_OF_ANCIENT_EGGS.get(), "Heap of Ancient Eggs");
        add(DDBlocks.SCULK_MAYO_BLOCK.get(), "Block of Sculk Mayo");
        add(DDBlocks.WORMROOTS_BLOCK.get(), "Block of Wormroots");
        add(DDBlocks.ROTBULB_CROP.get(), "Rotbulbling");
        add(DDBlocks.ROTBULB_PLANT.get(), "Wild Rotbulb");
        add(DDBlocks.ROTBULB_CRATE.get(), "Rotbulb Crate");
        add(DDBlocks.STAINED_SCRAP_BLOCK.get(), "Block of Stained Scrap");
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
        add(DDBlocks.OSSOBUSCO_BLOCK.get(), "Ossobusco");
        add(DDBlocks.GLOW_BERRY_GELATIN_BLOCK.get(), "Glowberry Gelatin");
        add(DDBlocks.POISONOUS_POTATO_CRATE.get(), "Poisonous Potato Crate");
        add(DDBlocks.ROTTEN_TOMATO_CRATE.get(), "Rotten Tomato Crate");
        add(DDBlocks.LIVING_FIRE.get(), "Living Fire");
        add(DDBlocks.ROTTEN_SPAWNER.get(), "Rotten Spawner");

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
        add(DDItems.GLOW_BERRY_GELATIN.get(), "Bowl of Glowberry Gelatin");
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
        add(DDItems.OSSOBUSCO.get(), "Bowl of Ossobusco");
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
        add(DDItems.OMINOUS_OMELETTE.get(), "Ominous Omelette");
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
        //INTEGRATION
        add(ADItems.SCULK_DOGAPPLE.get(), "sculk dogapple");
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

        //ENTITIES
        add(DDEntities.MONSTER_YAM.get(), "Monster Yam");
        add(DDEntities.CLEAVER.get(), "Cleaver");
        add(DDEntities.ANCIENT_EGG.get(), "Ancient Egg");
        add(DDEntities.RANCID_REDUCTION.get(), "Rancid Reduction");

        //ENCHANTMENTS
        add(DDEnchantments.RICOCHET.get(), "Ricochet");
        add(DDEnchantments.SERRATED_STRIKE.get(), "Serrated Strike");

        //EFFECTS
        add(DDEffects.SERRATED.get(), "Serrated");
        add(DDEffects.FERAL_BITE.get(), "Feral Bite");
        add(DDEffects.RAVENOUS_RUSH.get(), "Ravenous Rush");

        //MONSTER EFFECTS
        add(DDEffects.BURROW_GUT.get(), "Burrow Gut"); //tainted haste
        add(DDEffects.EXUDATION.get(), "Exudation"); //tainted absorption
        add(DDEffects.POUNCING.get(), "Pouncing"); //tainted leaping
        add(DDEffects.VORACITY.get(), "Voracity"); //tainted nourishment
        add(DDEffects.TENACITY.get(), "Tenacity"); //tainted comfort
        add(DDEffects.DECISIVE.get(), "Decisive"); //tainted strength

        add("effect.dungeonsdelight.burrow_gut.description", "Consumes haste, the user can only eat monster foods but destroying blocks will replenish hunger based on hardness, has a chance to grant ravenous rush which will increase block breaking speed based on duration.");
        add("effect.dungeonsdelight.exudation.description", "Consumes absorption, all absorption hearts become Exudation hearts and take 1.25x extra damage but unleash a deadly explosion upon getting damaged.");
        add("effect.dungeonsdelight.pouncing.description", "Consumes jump boost, allows the user to climb up blocks when sneaking and grants +5% increased movement speed per level.");
        add("effect.dungeonsdelight.voracity.description", "Consumes nourishment, the user can only eat monster foods but attacking mobs causes the user to consume them. Upon consuming a mob fully, the user will receive ravenous rush for a short time.");
        add("effect.dungeonsdelight.tenacity.description", "Consumes comfort, the user heals faster based on how close they are to starving (slower when fuller).");
        add("effect.dungeonsdelight.decisive.description", "Consumes strength, the user has a chance to deal a critical strike hit that inflicts 1.5x extra damage of the original attack.");

        add("effect.dungeonsdelight.feral_bite.description", "The user of this effect can inflict serrated on attacked targets.");
        add("effect.dungeonsdelight.serrated.description", "The user of this effect will very slowly take damage, the damage bypasses most forms of protection.");
        add("effect.dungeonsdelight.ravenous_rush.description", "Increases the movement speed and attack speed of the user, grants additional effects based on other effects that are active.");

        //DAMAGE
        addDamage(DDDamageTypes.DUNGEON_STOVE_BURN, "%1$s was monstrously grilled to perfection",
                "%1$s was thrown on the grill by The Monstrous Chef %2$s");

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

        //EFFECT SUBTITLES
        add("subtitles.effect.decisive.crit", "Decisive slicing");
        //CLEAVER SUBTITLES
        add("subtitles.item.cleaver.flying", "Cleaver wooshing");
        add("subtitles.item.cleaver.throw", "Cleaver throws");
        add("subtitles.item.cleaver.hit_block", "Cleaver hits block");
        add("subtitles.item.cleaver.hit_entity", "Cleaver hits entity");
        add("subtitles.item.cleaver.ricochet", "Cleaver ricochets off block");
        add("subtitles.item.cleaver.serrated_strike", "Cleaver slices entity");
        //MONSTER YAM SUBTITLES
        add("subtitles.entity.monster_yam.ambient", "Monster Yam gurgles");
        add("subtitles.entity.monster_yam.hurt", "Monster Yam hurts");
        add("subtitles.entity.monster_yam.death", "Monster Yam dies");

        //ADVANCEMENTS
        add("dungeonsdelight.advancement.root", "Dungeon's Delight");
        add("dungeonsdelight.advancement.root.desc", "A world of monsters await you!");

        add("dungeonsdelight.advancement.place_monster_pot", "Delicious in a Dungeon");
        add("dungeonsdelight.advancement.place_monster_pot.desc", "Put down a Monster Pot and start preparing monstrous meals!");

        add("dungeonsdelight.advancement.eat_biteable_food", "Chewy!");
        add("dungeonsdelight.advancement.eat_biteable_food.desc", "Biteable foods will allow you to eat from it multiple times and refill it with specific items");

        add("dungeonsdelight.advancement.get_stained_scrap", "Heavy Metal");
        add("dungeonsdelight.advancement.get_stained_scrap.desc", "Destroy a spawner to obtain a stained scrap, a piece of metal with lively capabilities");

        add("dungeonsdelight.advancement.place_dungeon_stove", "Uncaged, Unbound");
        add("dungeonsdelight.advancement.place_dungeon_stove.desc", "Place down a dungeon stove which it's flame can heat a monster pot");

        add("dungeonsdelight.advancement.eat_monster_food", "Ah, Dungeon Food");
        add("dungeonsdelight.advancement.eat_monster_food.desc", "Combine monsters and ingredients together to create your first monster food");

        add("dungeonsdelight.advancement.get_slime_noodles", "Creepy Pasta");
        add("dungeonsdelight.advancement.get_slime_noodles.desc", "Cut a slab of slime into gooey noodles");

        add("dungeonsdelight.advancement.get_sculk_polyp", "Apple of the Earth");
        add("dungeonsdelight.advancement.get_sculk_polyp.desc", "Cut a chunk of sculk into a sculk polyp");

        add("dungeonsdelight.advancement.place_embedded_eggs", "Won’t Take a Century");
        add("dungeonsdelight.advancement.place_embedded_eggs.desc", "Combine eggs into a pile of sculk and let it fester");

        add("dungeonsdelight.advancement.obtain_burrow_gut", "Eater of Worlds");
        add("dungeonsdelight.advancement.obtain_burrow_gut.desc", "Silverfish foods will allow you to consume blocks by destroying them but only can eat monster foods, transforms the Haste effect");

        add("dungeonsdelight.advancement.obtain_voracity", "Don't Starve");
        add("dungeonsdelight.advancement.obtain_voracity.desc", "Plated monster foods will allow you to eat monsters alive but only can eat monster foods, transforms the Nourishment effect");

        add("dungeonsdelight.advancement.obtain_tenacity", "Struggler");
        add("dungeonsdelight.advancement.obtain_tenacity.desc", "Bowled monster foods naturally heal based on how hungry you are, transforms the Comfort effect");

        add("dungeonsdelight.advancement.obtain_pouncing", "Eye of The Spider");
        add("dungeonsdelight.advancement.obtain_pouncing.desc", "Spider foods will allow you to climb and slide down blocks, transforms the Leaping effect");

        add("dungeonsdelight.advancement.obtain_decisive", "Dice Roller");
        add("dungeonsdelight.advancement.obtain_decisive.desc", "Stick held monster foods have a chance to land 1.75x critical hits, transforms the Strength effect");

        add("dungeonsdelight.advancement.eat_horse", "How Hungry...?");
        add("dungeonsdelight.advancement.eat_horse.desc", "Consume an entire horse alive using the voracity effect");

        add("dungeonsdelight.advancement.use_cleaver", "Heaven Pierce Her");
        add("dungeonsdelight.advancement.use_cleaver.desc", "Throw a Cleaver to pierce and scavenge extra goods from foes");

        add("dungeonsdelight.advancement.get_netherite_cleaver", "Cutlery of Apostasy");
        add("dungeonsdelight.advancement.get_netherite_cleaver.desc", "Upgrade your cleaver using netherite and prepare to slice and dice like a master chef");

        add("dungeonsdelight.advancement.obtain_perception", "I Can See Everything!");
        add("dungeonsdelight.advancement.obtain_perception.desc", "Glowing foods allow the user to see other entities through walls");

        add("dungeonsdelight.advancement.eat_sculk_food", "Paint the Town Blue");
        add("dungeonsdelight.advancement.eat_sculk_food.desc", "Sculk foods grant a varying amount of experience based on the amount of sculk ingredients");

        add("dungeonsdelight.advancement.place_rotbulb_crop", "Corpsebloom");
        add("dungeonsdelight.advancement.place_rotbulb_crop.desc", "Plant a rotbulbling and prepare for a monstrous harvest");

        add("dungeonsdelight.advancement.obtain_exudation", "Evil Up");
        add("dungeonsdelight.advancement.obtain_exudation.desc", "Rot foods will make your absorption hearts take additional damage but explode on entities that hit you, transforms the Absorption effect");

        add("dungeonsdelight.advancement.eat_bloody_mary", "Bloody Mary Challenge");
        add("dungeonsdelight.advancement.eat_bloody_mary.desc", "Consume the disgusting concoction known as a bloody mary");

        add("dungeonsdelight.advancement.eat_sniffer_food", "Is It Worth It?");
        add("dungeonsdelight.advancement.eat_sniffer_food.desc", "Consume a Sniffer food... you monster");

        add("dungeonsdelight.advancement.get_candied_sucker", "Sweet Revenge!");
        add("dungeonsdelight.advancement.get_candied_sucker.desc", "Use an amethyst rock candy to imprison a Vex or Silverfish");

        add("dungeonsdelight.advancement.eat_ghastly_spirits", "Fizzy Lifting Drink");
        add("dungeonsdelight.advancement.eat_ghastly_spirits.desc", "Consume a bottle of Ghastly Spirits, a drink that can help you quickly escape your enemies!");

        add("dungeonsdelight.advancement.all_monster_effects", "Monsters Smashed");
        add("dungeonsdelight.advancement.all_monster_effects.desc", "Obtain all monster effects at the same time, who's the real monster now?");

        add("dungeonsdelight.advancement.use_rancid_reduction", "Things are Grim Indeed...");
        add("dungeonsdelight.advancement.use_rancid_reduction.desc", "Use Rancid Reduction to rot a crop");

        add("dungeonsdelight.advancement.all_monster_foods", "The Privilege of The Living");
        add("dungeonsdelight.advancement.all_monster_foods.desc", "Consume all monster foods and drinks... you monster");

        add("dungeonsdelight.advancement.all_knife_mob_drops", "Gastrocryptozoologist");
        add("dungeonsdelight.advancement.all_knife_mob_drops.desc", "Obtain all monster knife drops");

        add("dungeonsdelight.advancement.all_dungeonsdelight_foods", "Meal of Champions");
        add("dungeonsdelight.advancement.all_dungeonsdelight_foods.desc", "Doesn't matter what it is, CONSUME THEM ALL.");

        //ENCHANTMENT DESCRIPTIONS
        add("enchantment.dungeonsdelight.ricochet.desc", "Thrown cleavers now bounce and don't have a cooldown upon missing an entity, each bounce increases the damage by 1.1x.");
        add("enchantment.dungeonsdelight.serrated_strike.desc", "Cleavers deal less damage but inflict serrated onto struck entities causing protection bypassing damage.");

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
        add(YT_ID + ".item." + DD_ID + ".spider_salmagundi.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".wormwood_cupboard.desc", "When a house is both hungry and awake, every room becomes a mouth");
        add(YT_ID + ".block." + DD_ID + ".wormroots.desc", "It feels like its tasting you as you grasp it in your hand");
        add(YT_ID + ".block." + DD_ID + ".wormwood_planks.desc", "Peculiar planks constructed from wormroots");
        add(YT_ID + ".block." + DD_ID + ".wormwood_mosaic.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".wormwood_stairs.desc", "Sinister stairs constructed from wormroots");
        add(YT_ID + ".block." + DD_ID + ".wormwood_mosaic_stairs.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".wormwood_slab.desc", "Insidious slabs constructed from wormroots");
        add(YT_ID + ".block." + DD_ID + ".wormwood_mosaic_slab.desc", NA_DESC);
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
        add(YT_ID + ".item." + DD_ID + ".flint_cleaver.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".iron_cleaver.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".golden_cleaver.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".diamond_cleaver.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".netherite_cleaver.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".glowberry_gelatin.desc", "A sweet squishy dish that illuminates your insides");
        add(YT_ID + ".block." + DD_ID + ".glowberry_gelatin_block.desc", "A lantern just isn’t sweet enough");
        add(YT_ID + ".item." + DD_ID + ".rotten_tripe.desc", "Rotten flesh that has been trimmed of the most unsafe spots");
        add(YT_ID + ".item." + DD_ID + ".gelled_salad.desc", "Not really a soup, not really a salad but something all it’s own");
        add(YT_ID + ".block." + DD_ID + ".sculk_mayo_block.desc", "A cubic meter of solid mayonnaise");
        add(YT_ID + ".block." + DD_ID + ".rotbulb_crop.desc", "Spawns in clusters on top of mud underground");
        add(YT_ID + ".block." + DD_ID + ".rotbulb_plant.desc", "Spawns in clusters on top of mud underground");
        add(YT_ID + ".item." + DD_ID + ".rotbulb.desc", "Like biting into a sack of fetid wax");
        add(YT_ID + ".item." + DD_ID + ".gunk.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".monster_yam_spawn_egg.desc", "This crop won't let you harvest it so easily...");
        add(YT_ID + ".block." + DD_ID + ".stained_scrap_block.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".stained_scrap_bars.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".cut_stained_scrap.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".cut_stained_scrap_stairs.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".cut_stained_scrap_slab.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".rotbulb_crate.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".cob_n_candy.desc", "Dissolves in water");
        add(YT_ID + ".item." + DD_ID + ".slicorice.desc", "Rawboy");
        add(YT_ID + ".item." + DD_ID + ".gritty_flesh.desc", "A refined palate can tell where husk flesh comes from just by the natural seasoning");
        add(YT_ID + ".item." + DD_ID + ".brined_flesh.desc", "Gelatinous hunk of meat that slips effortlessly through your fingers");
        add(YT_ID + ".item." + DD_ID + ".devilish_eggs.desc", "A sinister combination of the weirdest smelling egg and the weirdest smelling condiment");
        add(YT_ID + ".item." + DD_ID + ".ghast_roll.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".tokayaki.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".salt_soaked_stew.desc", "The flesh seems to have melted into the broth itself");
        add(YT_ID + ".item." + DD_ID + ".soaked_skewer.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".poi.desc", "No one will be a bigger hater than Yirmiri");
        add(YT_ID + ".item." + DD_ID + ".monster_muffin.desc", "Only cowards use yeast");
        add(YT_ID + ".item." + DD_ID + ".rancid_reduction.desc", "A little bottle of monster rot");
        add(YT_ID + ".block." + DD_ID + ".sculk_tart.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".sculk_tart_slice.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".monster_cake.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".monster_cake_slice.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".ossobusco_block.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".ossobusco.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".spider_pie.desc", "Natural sweeteners");
        add(YT_ID + ".item." + DD_ID + ".spider_pie_slice.desc", "When you want your spider guts on the go!");
        add(YT_ID + ".item." + DD_ID + ".sculk_apple.desc", "An apple that has been candied in sculk");
        add(YT_ID + ".item." + DD_ID + ".shiokara.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".bloody_mary.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".wardenzola.desc", "A block of vile smelling cheese that tastes faintly of raisins");
        add(YT_ID + ".item." + DD_ID + ".wardenzola_crumbles.desc", "The sculk seems to grow just as new parts are exposed to air");
        add(YT_ID + ".item." + DD_ID + ".malicious_sandwich.desc", "Something malicious is brewing...");
        add(YT_ID + ".item." + DD_ID + ".taro_milk_tea.desc", "The milk and sugar seems to cancel out the spoiled rot");
        add(YT_ID + ".item." + DD_ID + ".sniffer_shank.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".cooked_sniffer_shank.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".soft_serve_sniffer_egg.desc", "Well, I guess the egg comes first");
        add(YT_ID + ".item." + DD_ID + ".snifferwurst.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".cooked_snifferwurst.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".terrine_loaf.desc", "A horde of every rotten beast congealed into a flavour of unimaginable complexity");
        add(YT_ID + ".item." + DD_ID + ".gyudon.desc", "A challenging meal of grease and protein");
        add(YT_ID + ".item." + DD_ID + ".ghastly_spirits.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".ominous_omelette.desc", "A peculiar tingling filling wrapped within a shroud of savory eggs");
        add(YT_ID + ".item." + DD_ID + ".creeperilla.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".sinigang.desc", "Life and death, sweet and sour...");
        add(YT_ID + ".item." + DD_ID + ".gunk_arrow.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".snuffledog.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".guardian_angel.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".guardian_angel_block.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".chicken_jockey_sandwich.desc", "CHICKEN JOCKEY!!!");
        add(YT_ID + ".item." + DD_ID + ".au_rotten_potatoes.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".bloated_baked_potato.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".poisonous_poutine.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".poisonous_potato_crate.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".rotten_tomato_crate.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".spider_bubble_tea.desc", NA_DESC);
        add(YT_ID + ".block." + DD_ID + ".rotten_spawner.desc", NA_DESC);
        //INTEGRATION
        add(YT_ID + ".item." + DD_ID + ".sculk_dogapple.desc", "goth applog");
        add(YT_ID + ".item." + DD_ID + ".bug_chops.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".fried_bug_chops.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".liveroot_beer.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".torchberry_raisins.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".wilderness_luncheon.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".maze_roll.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".tower_boreito.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".aurora_ice_cream.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".meef_wellington.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".blazing_blood_sausage.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".arcane_chili.desc", "\"There is no prize to perfection. Only an end to pursuit.\"");
        add(YT_ID + ".item." + DD_ID + ".hydra_fricassee.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".trollber_chutney.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".sweetbread.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".scaly_fiddlehead_risotto.desc", NA_DESC);
    }

    private void addDamage(ResourceKey<DamageType> type, String deathMsg, String killMsg) {
        add(type.location().toLanguageKey(), deathMsg);
        add("death.attack." + type.location().toLanguageKey(), deathMsg);
        add("death.attack." + type.location().toLanguageKey() + ".player", killMsg);
    }
}
