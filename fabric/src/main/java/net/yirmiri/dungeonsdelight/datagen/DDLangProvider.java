package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDEnchantments;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.Supplier;

public class DDLangProvider extends FabricLanguageProvider {
    public DDLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder build) {
        //-------------------------MISC-------------------------
        build.add("itemgroup.dungeonsdelight", "Dungeon's Delight");
        build.add("item.dungeonsdelight.music_disc_malady.desc", "Artyrian - Malady");
        build.add("item.dungeonsdelight.music_disc_malady_b_side.desc", "Artyrian - Malady (B-Side)");
        build.add("resourcepacks.dungeonsdelight.dungeonsdelight_classic.title", "Dungeon's Delight Classic");
        build.add("resourcepacks.dungeonsdelight.dungeonsdelight_classic.desc", "The original textures of Dungeon's Delight.");
        build.add("resourcepacks.dungeonsdelight.dungeonsdelight_vanilla_overrides.title", "Dungeon's Delight Vanilla Overrides");
        build.add("resourcepacks.dungeonsdelight.dungeonsdelight_vanilla_overrides.desc", "The tweaked vanilla textures of Dungeon's Delight.");

        //-------------------------TOOLTIPS-------------------------
        build.add("tooltip.dungeonsdelight.grate.desc1", "Interact with Item:");
        build.add("tooltip.dungeonsdelight.grate.desc2", "Sets Item Displayed");
        build.add("tooltip.dungeonsdelight.homeward.no_spawn", "You have no homeward point or it was obstructed");
        build.add("tooltip.dungeonsdelight.homeward.no_spawn_point_in_dimension", "Must be in dimension of homeward point");
        build.add("tooltip.dungeonsdelight.homeward.bound", "Homeward point set");
        build.add("tooltip.dungeonsdelight.homeward.empty_or_no_pearl", "Homeward point is missing an ender pearl to teleport to");
        build.add("tooltip.dungeonsdelight.homeward.missing_telepotage", "The position you're attempting to homeward to is not a Telepotage Cauldron");
        build.add("tooltip.dungeonsdelight.when_consumed", "When Consumed:");
        build.add("tooltip.dungeonsdelight.effect.cleanse_effects", "Cleanses active effects");
        build.add("tooltip.dungeonsdelight.effect.cleanse_poison", "Cleanses Poison");
        build.add("tooltip.dungeonsdelight.effect.random_teleport", "Randomly teleports user");
        build.add("tooltip.dungeonsdelight.effect.raw_creeper", "Explodes and leaks active effects");
        build.add("tooltip.dungeonsdelight.effect.chance_to_not_consume", "chance to not consume");
        build.add("tooltip.dungeonsdelight.effect.chance_to_blast_1", "chance to conjure small Echo Blast");
        build.add("tooltip.dungeonsdelight.effect.chance_to_blast_2", "chance to conjure sizeable Echo Blast");
        build.add("tooltip.dungeonsdelight.effect.chance_to_blast_3", "chance to conjure large Echo Blast");
        build.add("tooltip.dungeonsdelight.effect.monsterize_bad_omen", "Monsterizes Bad Omen");
        build.add("tooltip.dungeonsdelight.effect.homeward_teleport", "Teleports user to Homeward Point");
        build.add("tooltip.dungeonsdelight.effect.homeward_bound", "Homeward Point found");
        build.add("tooltip.dungeonsdelight.effect.homeward_unbound", "Homeward Point not found");

        //-------------------------BLOCKS-------------------------
        addWithYT(build, DDBlocks.TERROR_PRETA.get(), "Terror Preta", "Mud that has been 'fertilized' to allow putrid flora to grow, requires water within 4 blocks and will burn while in sunlight");
        addWithYT(build, DDBlocks.WORMOUTH.get(), "Wormouth",  "It feels like it's tasting you as you grasp it in your hand");
        addWithYT(build, DDBlocks.WORMROOT_STALK.get(), "Wormroot Stalk",  "It feels like it's creeping around you as you grasp it in your hand");
        addWithYT(build, DDBlocks.WORMWOOD_PLANKS.get(), "Wormwood Planks", "Peculiar planks constructed from wormroots");
        addWithYT(build, DDBlocks.WORMWOOD_MOSAIC.get(), "Wormwood Mosaic", "Peculiar planks that have been finely chiseled");
        addWithYT(build, DDBlocks.WORMWOOD_STAIRS.get(), "Wormwood Stairs", "Sinister stairs constructed from wormroots");
        addWithYT(build, DDBlocks.WORMWOOD_MOSAIC_STAIRS.get(), "Wormwood Mosaic Stairs", "Sinister stairs constructed from wormwood mosaic");
        addWithYT(build, DDBlocks.WORMWOOD_SLAB.get(), "Wormwood Slab", "Insidious slabs constructed from wormroots");
        addWithYT(build, DDBlocks.WORMWOOD_MOSAIC_SLAB.get(), "Wormwood Mosaic Slab", "Insidious slabs constructed from wormwood mosaic");
        addWithYT(build, DDBlocks.WORMWOOD_FENCE.get(), "Wormwood Fence", "To keep THEM out or to keep you in...");
        addWithYT(build, DDBlocks.WORMWOOD_FENCE_GATE.get(), "Wormwood Fence Gate", "Can be opened, but who would want to do that...");
        addWithYT(build, DDBlocks.WORMWOOD_DOOR.get(), "Wormwood Door", "When a house is both hungry and awake, every room becomes a mouth...");
        addWithYT(build, DDBlocks.WORMWOOD_TRAPDOOR.get(), "Wormwood Trapdoor", "This sinister smirk seems to be beckoning you into a trap");
        addWithYT(build, DDBlocks.WORMWOOD_BUTTON.get(), "Wormwood Button", "Can be pushed by players, arrows, and tridents, stays pushed for longer the darker it is");
        addWithYT(build, DDBlocks.WORMWOOD_PRESSURE_PLATE.get(), "Wormwood Pressure Plate", "Produces a redstone signal when ANY entity makes contact with it");
        addWithYT(build, DDBlocks.WORMROOT_TENDRILS.get(), "Wormroot Tendrils", "It feels like it's wrapping around you as you grasp it in your hand");
        addWithYT(build, DDBlocks.WORMROOTS_BLOCK.get(), "Block of Wormroots", "Wormroots compacted into a block");
        addWithYT(build, DDBlocks.STAINED_SCRAP_BLOCK.get(), "Block of Stained Scrap", "A metal block constructed from a collection of stained scrap");
        addWithYT(build, DDBlocks.CHISELED_STAINED_SCRAP.get(), "Chiseled Stained Scrap", "A metal block that has been engraved");
        addWithYT(build, DDBlocks.STAINED_SCRAP_PILLAR.get(), "Stained Scrap Pillar", "Metal that has been carved and filled with strange pink gems");
        addWithYT(build, DDBlocks.STAINED_SCRAP_DOOR.get(), "Stained Scrap Door", "A menacing skull that awaits those that pass...");
        addWithYT(build, DDBlocks.STAINED_SCRAP_TRAPDOOR.get(), "Stained Scrap Trapdoor", "This is just screaming to lead to a trap...");
        addWithYT(build, DDBlocks.STAINED_SCRAP_BARS.get(), "Stained Scrap Bars", "Metal bars constructed from stained scrap, prevents jumping or climbing over");
        addWithYT(build, DDBlocks.STAINED_SCRAP_GATE.get(), "Stained Scrap Gate", "A metal gate constructed from stained scrap, prevents jumping or climbing over");
        addWithYT(build, DDBlocks.CUT_STAINED_SCRAP.get(), "Cut Stained Scrap", "A stained scrap block that has been cut into tiles");
        addWithYT(build, DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), "Cut Stained Scrap Stairs", "Metallic stairs constructed from stained scrap");
        addWithYT(build, DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), "Cut Stained Scrap Slab", "Metallic slabs constructed from stained scrap");
        addWithYT(build, DDBlocks.STAINED_SCRAP_GRATE.get(), "Stained Scrap Grate", "A metallic grate constructed from stained scrap that allows items to pass through it - isn't that great?");
        build.add(DDBlocks.BLEETS.get(), "Bleets");
        addWithYT(build, DDBlocks.MONSTER_POT.get(), "Monster Pot", "A sinister cooking station that uses the essence of life to cook delicacies");
        addWithYT(build, DDBlocks.WORMWOOD_CLEAVING_BOARD.get(), "Wormwood Cleaving Board", "Not to be confused with a cutting board");
        addWithYT(build, DDBlocks.BAMBOO_CLEAVING_BOARD.get(), "Bamboo Cleaving Board", "Not to be confused with a cutting board");
        addWithYT(build, DDBlocks.ROTTEN_FLESH_BLOCK.get(), "Block of Rotten Flesh", "A foul block constructed from a collection of rotten flesh");
        addWithYT(build, DDBlocks.SCULK_MAYONNAISE_BLOCK.get(), "Block of Sculk Mayonnaise", "A cubic meter of goopy mayonnaise");
        addWithYT(build, DDBlocks.EMBEDDED_EGGS.get(), "Heap of Embedded Eggs", "A heap of eggs that when left to fester will become a perfect vessel for souls");

        //-------------------------ITEMS-------------------------
        addWithYT(build, DDItems.LOGO_ITEM.get(), "Logo Item", "you probably aren't meant to have this");
        addWithYT(build, DDItems.MUSIC_DISC_MALADY.get(), "Music Disc", "Can be inserted into a jukebox to play horrifying tunes");
        addWithYT(build, DDItems.MUSIC_DISC_MALADY_B_SIDE.get(), "Music Disc", "Can be inserted into a jukebox to play horrifying tunes");
        addWithYT(build, DDItems.FLINT_CLEAVER.get(), "Flint Cleaver", "A crude flint blade, time to slice and dice!");
        addWithYT(build, DDItems.IRON_CLEAVER.get(), "Iron Cleaver", "A strong iron blade, time to slice and dice!");
        addWithYT(build, DDItems.GOLDEN_CLEAVER.get(), "Golden Cleaver", "A hasty golden blade, time to slice and dice!");
        addWithYT(build, DDItems.DIAMOND_CLEAVER.get(), "Diamond Cleaver", "A shimmering diamond blade, time to slice and dice!");
        addWithYT(build, DDItems.NETHERITE_CLEAVER.get(), "Netherite Cleaver", "A durable netherite blade, time to slice and dice!");
        addWithYT(build, DDItems.SPIDER_MEAT.get(), "Raw Spider Meat", "A raw chunk of spider, probably will make you sick");
        addWithYT(build, DDItems.COOKED_SPIDER_MEAT.get(), "Cooked Spider Meat", "Vague hint of acidity, slight hint of crab, and a big chunk of charcoal");
        addWithYT(build, DDItems.STAINED_SCRAP.get(), "Stained Scrap", "A cold slice of metal that has the ability to conduct living essence");
        addWithYT(build, DDItems.STAINED_SCRAP_FRAGMENT.get(), "Stained Scrap Fragment", "A fragment with a weak life conduction");
        addWithYT(build, DDItems.CREEPERILLA.get(), "Creeperilla", "A crunchy leaf-like plant filled with gunpowder, be careful with how you cut");
        addWithYT(build, DDItems.CREEPERILLA_SQUIB.get(), "Creeperilla Squib", "Why are you wasting your time reading this...");
        addWithYT(build, DDItems.ROTTEN_TRIPE.get(), "Rotten Tripe", "Rotten flesh that has been trimmed of the most unsafe spots");
        addWithYT(build, DDItems.SLIME_NOODLES.get(), "Slime Noodles", "Slippery noodles that almost snake and move on their own");
        addWithYT(build, DDItems.MAGMARONI.get(), "Magmaroni", "Hecco's favorite!!!");
        addWithYT(build, DDItems.GHAST_TENTACLE.get(), "Ghast Tentacle", "A slimy limb cut straight from a Ghast, hope you don't mind the texture of suction cups");
        addWithYT(build, DDItems.SILVERFISH_ABDOMEN.get(), "Silverfish Abdomen", "Protein rich with minerals to burrow your face into");
        addWithYT(build, DDItems.SNIFFER_SHANK.get(), "Raw Sniffer Shank", "Only a monster would consume such a joyful creature");
        addWithYT(build, DDItems.COOKED_SNIFFER_SHANK.get(), "Cooked Sniffer Shank", "The cooked leg of a once joyful creature");
        addWithYT(build, DDItems.BLEET.get(), "Bleet", "A sanguine beet-like fruit that pulses slowly in your hands");
        addWithYT(build, DDItems.BLEET_SEEDS.get(), "Bleet Seeds", "When planted they will grow into a bleet crop");
        addWithYT(build, DDItems.ENDELVE.get(), "Endelve", "A rotten leafy vegetable that provides many health benefits to your diet");
        addWithYT(build, DDItems.MANALLIUM.get(), "Manallium", "A rotting flower-like vegetable that grows it's roots in eerie shapes");
        addWithYT(build, DDItems.AMETHYST_ROCK_CANDY.get(), "Amethyst Rock Candy", "Abigail's favorite treat! Can be used on small mobs to imprison them");
        addWithYT(build, DDItems.CANDIED_SILVERFISH_SUCKER.get(), "Candied Silverfish Sucker", "Edible pest control");
        addWithYT(build, DDItems.CANDIED_VEX_SUCKER.get(), "Candied Vex Sucker", "An even sweeter revenge");
        addWithYT(build, DDItems.GHOULASH.get(), "Ghoulash", "The most approachable of monster delights");
        addWithYT(build, DDItems.SPIDER_TANGHULU.get(), "Spider Tanghulu", "You feel an evil presence watching you...");
        addWithYT(build, DDItems.FOUL_SKEWER.get(), "Foul Skewer", "Foul flesh hangs flimsily onto the bone");
        addWithYT(build, DDItems.SALMAGUNDI.get(), "Salmagundi", "A violent mixture of various spider guts");
        addWithYT(build, DDItems.SILVERFISH_FRIED_RICE.get(), "Silverfish Fried Rice", "It did what now!?");
        addWithYT(build, DDItems.GUNPOWDER_BAKED_ARACHNID.get(), "Gunpowder Baked Arachnid", "Rice was out of stock so gunpowder was the next best option...");
        addWithYT(build, DDItems.BLACK_APPLE.get(), "Foul Apple", "Foul repulsion.");
        addWithYT(build, DDItems.CAMEL_HUSK_SPAWN_EGG.get(), "Camel Husk Spawn Egg", "A camel that succumbed a terrible fate");
        addWithYT(build, DDItems.SPIDER_EXTRACT.get(), "Spider Extract", "Don't ask where it comes from.");
        addWithYT(build, DDItems.DYNAMITE_ROLL.get(), "Dynamite Roll", "An explosive range of tastes that will surely keep you busy elsewhere...");
        addWithYT(build, DDItems.SPIDER_PIE.get(), "Spider Pie", "Natural sweeteners");
        addWithYT(build, DDItems.SLICORICE.get(), "Slicorice", "Rawboy");
        addWithYT(build, DDItems.RAVAGER_HAUNCH.get(), "Ravager Haunch", "A thick hunk of meat that takes a mighty bite to tear apart");
        addWithYT(build, DDItems.GHAST_ROLL.get(), "Ghast Roll", "A delicious mixture of ghast and flesh");
        addWithYT(build, DDItems.GHAST_CALAMARI.get(), "Ghast Calamari", "If it weren't for the ring shape it would slip out your hands");
        addWithYT(build, DDItems.COOKED_GHAST_CALAMARI.get(), "Fried Ghast Calamari", "Basically knockoff onion rings");
        addWithYT(build, DDItems.TELEPOTAGE.get(), "Telepotage", "Teleports the user to their homeward position if not damaged before homeward expires");
        addWithYT(build, DDBlocks.TELEPOTAGE_BLOCK.get(), "Cauldron of Telepotage", "Can set a homeward when interacted with telepotage");
        addWithYT(build, DDItems.SCULK_POLYP.get(), "Sculk Polyp", "Spirit essence floats around within");
        addWithYT(build, DDItems.ANCIENT_EGG.get(), "Ancient Egg", "An unborn delicacy");
        addWithYT(build, DDItems.CLEAVED_ANCIENT_EGG.get(), "Cleaved Ancient Egg", "An ancient egg pierced straight through it’s leathery skin");
        addWithYT(build, DDItems.SCULK_MAYONNAISE.get(), "Sculk Mayonnaise", "You either love it or you hate it");
        addWithYT(build, DDItems.SCULK_APPLE.get(), "Sculk Apple", "...and my sculk armor and my sculk sword for my sculk dimension");
        addWithYT(build, DDItems.LIVING_TORCH.get(), "Living Torch", "Life essence keeps the flame ablaze");

        //-------------------------ENTITIES-------------------------
        build.add("entity.dungeonsdelight.cleaver", "Cleaver");
        build.add("entity.dungeonsdelight.echo_blast", "Echo Blast");
        build.add("entity.dungeonsdelight.camel_husk", "Camel Husk");

        //-------------------------ATTRIBUTES-------------------------
        build.add("attribute.dungeonsdelight.throwing_range", "Throwing Range");
        build.add("attribute.dungeonsdelight.air_control", "Air Control");

        //-------------------------EFFECTS-------------------------
        build.add(DDEffects.RAVENOUS_RUSH.get(), "Ravenous Rush");
        build.add(DDEffects.HOLLOWED.get(), "Hollowing Curse");
        build.add(DDEffects.HOMEWARD.get(), "Homeward");
        build.add(DDEffects.SERRATED.get(), "Serrated");
        build.add(DDEffects.TENACITY.get(), "Tenacity");
        build.add(DDEffects.PUTRID_SCENT.get(), "Putrid Scent");
        build.add(DDEffects.SERENDIPITY.get(), "Serendipity");

        //MONSTER EFFECTS
        build.add(DDEffects.EXUDATION.get(), "Exudation");
        build.add(DDEffects.BURROW_GUT.get(), "Burrow Gut");
        build.add(DDEffects.VORACITY.get(), "Voracity");
        build.add(DDEffects.DEBRIDEMENT.get(), "Debridement");
        build.add(DDEffects.POUNCING.get(), "Pouncing");
        build.add(DDEffects.DECISIVE.get(), "Decisive");
        build.add(DDEffects.HORDE_OMEN.get(), "Horde Omen");
        build.add(DDEffects.DIVER_DOWN.get(), "Diver Down");

        //-------------------------JEED (INTEGRATION)-------------------------
        build.add("effect.dungeonsdelight.ravenous_rush.description",
                "The user of this effect gains unique bonuses for each monster effect they have.");

        build.add("effect.dungeonsdelight.hollowed.description",
                "The user of this effect becomes undead temporarily, becoming undead comes with both bonuses and downsides."); //todo

        build.add("effect.dungeonsdelight.serrated.description",
                "The user of this effect takes armor piercing damage over time.");

        build.add("effect.dungeonsdelight.tenacity.description",
                "Natural regeneration of the user increases in speed the lower their hunger is (slower when near or at full hunger) and prevents the user from taking starving damage.");

        build.add("effect.dungeonsdelight.putrid_scent.description",
                "The user of this effect is targeted by all undead in the surrounding area.");

        build.add("effect.dungeonsdelight.homeward.description",
                "The user of this effect is sent back to their spawn point when the effect ends, taking damage will cancel the teleportation.");

        build.add("effect.dungeonsdelight.serendipity.description",
                "The user of this effect has increased luck and some chance based things are in the user's favor.");

        //MONSTER EFFECTS
        build.add("effect.dungeonsdelight.burrow_gut.description",
                "Monsterizes Haste, desc NA."); //todo

        build.add("effect.dungeonsdelight.voracity.description",
                "Monsterizes Tenacity, desc NA."); //todo

        build.add("effect.dungeonsdelight.debridement.description",
                "Monsterizes Regeneration, desc NA."); //todo

        build.add("effect.dungeonsdelight.pouncing.description",
                "Monsterizes Speed, the user of this effect can sneak midair to pounce forwards.");

        build.add("effect.dungeonsdelight.decisive.description",
                "Monsterizes Strength, desc NA."); //todo

        build.add("effect.dungeonsdelight.exudation.description",
                "Monsterizes Absorption, grants exudation hearts which unleash a vile blast when damaged but the user takes increased damage to all sources.");

        build.add("effect.dungeonsdelight.horde_omen.description",
                "Monsterizes Bad Omen, the user of this effect initiates a horde when entering a village.");

        build.add("effect.dungeonsdelight.diver_down.description",
                "Monsterizes Fire Resistance, desc NA."); //todo

        //-------------------------ENCHANTMENTS-------------------------
        build.add(DDEnchantments.RICOCHET.get(), "Ricochet");
        build.add(DDEnchantments.SERRATED_STRIKE.get(), "Serrated Strike");
        build.add(DDEnchantments.DARTING.get(), "Darting");

        //-------------------------ENCHANTMENT DESCRIPTIONS (INTEGRATION)-------------------------
        build.add("enchantment.dungeonsdelight.ricochet.desc",
                "Thrown cleavers now bounce and don't have a cooldown upon missing an entity, each bounce increases the damage by 1.1x.");

        build.add("enchantment.dungeonsdelight.serrated_strike.desc",
                "Cleavers inflict serrated onto struck entities causing protection bypassing damage.");

        build.add("enchantment.dungeonsdelight.darting.desc",
                "Increased throwing range of cleavers and decreased charge time.");

        //-------------------------DAMAGE TYPES-------------------------
        addDamage(build, DDDamageTypes.CLEAVER, "%1$s was sliced and diced into a delight",
                "%2$s sliced and diced %1$s into a delight");

        addDamage(build, DDDamageTypes.SERRATED, "%1$s was left to bleed out their wounds",
                "%2$s left %1$s to bleed out their wounds");

        addDamage(build, DDDamageTypes.RAW_CREEPER, "%1$s combusted from the inside out",
                "%2$s watched %1$s combust from the inside out");

        addDamage(build, DDDamageTypes.EXUDATION_BLAST, "%1$s was vaporized by a monstrous blast",
                "%1$s was vaporized by the monstrous blast of %2$s");

        addDamage(build, DDDamageTypes.TRAMPLED, "%1$s was trampled upon by a mount",
                "%2$s walked on %1$s like a set of stairs");

        addDamage(build, DDDamageTypes.HORSE_TRAMPLED, "%1$s is attempting to change the leading cause of death",
                "%2$s had their horse kick %1$s in attempt to change the leading causes of death");

        addDamage(build, DDDamageTypes.DONKEY_TRAMPLED, "%1$s was kicked by a Donkey",
                "%2$s had their donkey kick the light out of %1$s");

        addDamage(build, DDDamageTypes.ECHO_BLAST, "%1$s had their whole body reverberated by Echo Blast",
                "%1$s had their whole body reverberated by the Echo Blast of %2$s");

        addDamage(build, DDDamageTypes.ANCIENT_EGG, "%1$s was had their soul festered by sculk",
                "%2$s watched as %1$s had their soul festered by sculk");

        //POTION
        build.add("item.minecraft.potion.effect.dungeonsdelight.hollowed", "Potion of Hollowing Curse");
        build.add("item.minecraft.splash_potion.effect.dungeonsdelight.hollowed", "Splash Potion of Hollowing Curse");
        build.add("item.minecraft.lingering_potion.effect.dungeonsdelight.hollowed", "Lingering Potion of Hollowing Curse");
        build.add("item.minecraft.tipped_arrow.effect.dungeonsdelight.hollowed", "Arrow of Hollowing Curse");
        build.add("item.minecraft.potion.effect.dungeonsdelight.long_hollowed", "Potion of Hollowing Curse");
        build.add("item.minecraft.splash_potion.effect.dungeonsdelight.long_hollowed", "Splash Potion of Hollowing Curse");
        build.add("item.minecraft.lingering_potion.effect.dungeonsdelight.long_hollowed", "Lingering Potion of Hollowing Curse");
        build.add("item.minecraft.tipped_arrow.effect.dungeonsdelight.long_hollowed", "Arrow of Hollowing Curse");

        //-------------------------SUBTITLES-------------------------
        build.add("subtitles.effect.generic.monsterize", "Effect monsterizes");

        build.add("subtitles.item.cleaver.hit_block", "Cleaver hits block");
        build.add("subtitles.item.cleaver.hit_entity", "Cleaver cleaves entity");
        build.add("subtitles.item.cleaver.ready", "Cleaver fully readies");
        build.add("subtitles.item.cleaver.flying", "Cleaver whooshes");
        build.add("subtitles.item.cleaver.throw", "Cleaver throws");
        build.add("subtitles.item.cleaver.ricochet", "Cleaver ricochets");
        build.add("subtitles.item.cleaver.serrated_strike", "Cleaver serrates entity");
        build.add("subtitles.item.cleaver.cleave", "Cleaver cleaves");

        build.add("subtitles.block.wormouth.eat", "Wormouth chews");
        build.add("subtitles.block.wormouth.open", "Wormouth spits");
        build.add("subtitles.block.wormouth.panic", "Wormouth panics");
        build.add("subtitles.block.wormouth.shut", "Wormouth hides from light");
        build.add("subtitles.block.wormouth.unshut", "Wormouth reopens");

        //-------------------------ADVANCEMENTS-------------------------
        build.add("advancement.dungeonsdelight.root", "Dungeon's Delight");
        build.add("advancement.dungeonsdelight.root.desc", "A world of monsters await you!");

        build.add("advancement.dungeonsdelight.obtain_stained_scrap", "Heavy Metal");
        build.add("advancement.dungeonsdelight.obtain_stained_scrap.desc", "Collect a scrap of stained metal, a unique material with the power to conduct life");

        build.add("advancement.dungeonsdelight.place_monster_pot", "Delicious in a Dungeon");
        build.add("advancement.dungeonsdelight.place_monster_pot.desc", "Set down a Monster Pot and start preparing monstrous meals imbued with the living properties of monstrous remains");

        build.add("advancement.dungeonsdelight.eat_monster_food", "Delicious in a Dungeon");
        build.add("advancement.dungeonsdelight.eat_monster_food.desc", "Conjure various monsters and ingredients together to create and consume your first monster food");

        build.add("advancement.dungeonsdelight.monsterize_effect", "To Become the Monster...");
        build.add("advancement.dungeonsdelight.monsterize_effect.desc", "While under the effect on a monsterizable effect obtain it's monsterized effect to transform it");

        build.add("advancement.dungeonsdelight.use_cleaver", "Heaven Pierce Her");
        build.add("advancement.dungeonsdelight.use_cleaver.desc", "Throw a cleaver at an entity to pierce and reap extra goods from foes");

        build.add("advancement.dungeonsdelight.obtain_netherite_cleaver", "Hell's Kitchen");
        build.add("advancement.dungeonsdelight.obtain_netherite_cleaver.desc", "Upgrade your cleaver using a Netherite Ingot");

        build.add("advancement.dungeonsdelight.knife_fight", "Knife to a Sniper Duel");
        build.add("advancement.dungeonsdelight.knife_fight.desc", "Kill a Skeleton with a thrown cleaver from at least 25 meters away");

        build.add("advancement.dungeonsdelight.cleaving_board", "Death by a Thousand Cleaves");
        build.add("advancement.dungeonsdelight.cleaving_board.desc", "Throw a cleaver at a cleaving board to chop various things hung on it into usable ingredients");

        build.add("advancement.dungeonsdelight.obtain_slime_noodles", "Creepy Pasta");
        build.add("advancement.dungeonsdelight.obtain_slime_noodles.desc", "Cleave a Slime or it's remaining ball into Slime Noodles");

        build.add("advancement.dungeonsdelight.obtain_sculk_polyp", "Apple of the Earth");
        build.add("advancement.dungeonsdelight.obtain_sculk_polyp.desc", "Cleave a block of Sculk into a Sculk Polyp");

        build.add("advancement.dungeonsdelight.place_embedded_eggs", "Won't Take a Century");
        build.add("advancement.dungeonsdelight.place_embedded_eggs.desc", "Place down a Heap of Embedded Eggs and let them fester in the darkness");

        build.add("advancement.dungeonsdelight.obtain_candied_sucker", "Sweet Revenge!");
        build.add("advancement.dungeonsdelight.obtain_candied_sucker.desc", "Imprison a Vex or Silverfish in Amethyst Rock Candy");

        build.add("advancement.dungeonsdelight.obtain_terror_preta", "Morbid Mush");
        build.add("advancement.dungeonsdelight.obtain_terror_preta.desc", "Obtain Terror Preta, a soil with the properties of the undead due to it's volume of foul matter which can sustain rotten crops");

        build.add("advancement.dungeonsdelight.plant_all_rotten_crops", "Harvest Moon");
        build.add("advancement.dungeonsdelight.plant_all_rotten_crops.desc", "Plant all rotten crops and watch them flourish in darkness");
    }

    private void addDamage(TranslationBuilder build, ResourceKey<DamageType> type, String deathMsg, String killMsg) {
        build.add(type.location().toLanguageKey(), deathMsg);
        build.add("death.attack." + type.location().toLanguageKey(), deathMsg);
        build.add("death.attack." + type.location().toLanguageKey() + ".player", killMsg);
    }

    public static void addYT(FabricLanguageProvider.TranslationBuilder build, Block block, String tooltip) {
        build.add("yapping_tooltips." + block.getDescriptionId() + ".desc", tooltip);
    }

    public static void addYT(FabricLanguageProvider.TranslationBuilder build, Supplier<Block> block, String tooltip) {
        build.add("yapping_tooltips." + block.get().getDescriptionId() + ".desc", tooltip);
    }

    public static void addYT(FabricLanguageProvider.TranslationBuilder builder, Item item, String tooltip) {
        builder.add("yapping_tooltips." + item.getDescriptionId() + ".desc", tooltip);
    }

    public static void addWithYT(FabricLanguageProvider.TranslationBuilder build, Item item, String name, String ytDesc) {
        build.add(item, name);
        addYT(build, item, ytDesc);
    }

    public static void addWithYT(FabricLanguageProvider.TranslationBuilder build, Block block, String name, String ytDesc) {
        build.add(block, name);
        addYT(build, block, ytDesc);
    }
}
