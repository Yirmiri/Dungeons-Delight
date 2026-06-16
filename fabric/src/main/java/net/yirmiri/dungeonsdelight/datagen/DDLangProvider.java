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
        build.add("tooltip.dungeonsdelight.when_consumed", "When Consumed:");
        build.add("tooltip.dungeonsdelight.effect.cleanse_effects", "Cleanses active effects");
        build.add("tooltip.dungeonsdelight.effect.cleanse_poison", "Cleanses Poison");
        build.add("tooltip.dungeonsdelight.effect.random_teleport", "Randomly teleports consumer");
        build.add("tooltip.dungeonsdelight.effect.raw_creeper", "Explodes and leaks active effects");
        build.add("tooltip.dungeonsdelight.effect.chance_to_not_consume", "chance to not consume");

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
        addWithYT(build, DDBlocks.STAINED_SCRAP_BARS.get(), "Stained Scrap Bars", "Metal bars constructed from stained scrap");
        addWithYT(build, DDBlocks.CUT_STAINED_SCRAP.get(), "Cut Stained Scrap", "A stained scrap block that has been cut into tiles");
        addWithYT(build, DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), "Cut Stained Scrap Stairs", "Metallic stairs constructed from stained scrap");
        addWithYT(build, DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), "Cut Stained Scrap Slab", "Metallic slabs constructed from stained scrap");
        addWithYT(build, DDBlocks.STAINED_SCRAP_GRATE.get(), "Stained Scrap Grate", "A metallic grate constructed from stained scrap that allows items to pass through it - isn't that great?");
        build.add(DDBlocks.BLEETS.get(), "Bleets");
        addWithYT(build, DDBlocks.MONSTER_POT.get(), "Monster Pot", "A sinister cooking station that uses the essence of life to cook delicacies");
        addWithYT(build, DDBlocks.WORMWOOD_CLEAVING_BOARD.get(), "Wormwood Cleaving Board", "Not to be confused with a cutting board");
        addWithYT(build, DDBlocks.BAMBOO_CLEAVING_BOARD.get(), "Bamboo Cleaving Board", "Not to be confused with a cutting board");
        addWithYT(build, DDBlocks.ROTTEN_FLESH_BLOCK.get(), "Block of Rotten Flesh", "A foul block constructed from a collection of rotten flesh");

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
        addWithYT(build, DDItems.SPIDER_SALMAGUNDI.get(), "Spider Salmagundi", "A violent mixture of various spider guts");
        addWithYT(build, DDItems.SILVERFISH_FRIED_RICE.get(), "Silverfish Fried Rice", "It did what now!?");
        addWithYT(build, DDItems.GUNPOWDER_BAKED_SPIDER.get(), "Gunpowder Baked Spider", "Rice was out of stock so gunpowder was the next best option...");
        addWithYT(build, DDItems.BLACK_APPLE.get(), "Foul Apple", "Foul repulsion.");
        addWithYT(build, DDItems.CAMEL_HUSK_SPAWN_EGG.get(), "Camel Husk Spawn Egg", "A camel that succumbed a terrible fate");

        //-------------------------ENTITIES-------------------------
        build.add("entity.dungeonsdelight.cleaver", "Cleaver");
        build.add("entity.dungeonsdelight.camel_husk", "Camel Husk");

        //-------------------------ATTRIBUTES-------------------------
        build.add("attribute.dungeonsdelight.throwing_range", "Throwing Range");

        //-------------------------EFFECTS-------------------------
        build.add(DDEffects.RAVENOUS_RUSH.get(), "Ravenous Rush");
        build.add(DDEffects.HOLLOWED.get(), "Hollowed");
        build.add(DDEffects.SERRATED.get(), "Serrated");
        build.add(DDEffects.TENACITY.get(), "Tenacity");
        build.add(DDEffects.PUTRID_SCENT.get(), "Putrid Scent");

        //MONSTER EFFECTS
        build.add(DDEffects.EXUDATION.get(), "Exudation");
        build.add(DDEffects.BURROW_GUT.get(), "Burrow Gut");
        build.add(DDEffects.VORACITY.get(), "Voracity");
        build.add(DDEffects.DEBRIDEMENT.get(), "Debridement");
        build.add(DDEffects.POUNCING.get(), "Pouncing");
        build.add(DDEffects.DECISIVE.get(), "Decisive");

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

        //MONSTER EFFECTS
        build.add("effect.dungeonsdelight.burrow_gut.description",
                "Monsterizes Haste, desc NA."); //todo

        build.add("effect.dungeonsdelight.voracity.description",
                "Monsterizes Haste, desc NA."); //todo

        build.add("effect.dungeonsdelight.debridement.description",
                "Monsterizes Haste, desc NA."); //todo

        build.add("effect.dungeonsdelight.pouncing.description",
                "Monsterizes Haste, desc NA."); //todo

        build.add("effect.dungeonsdelight.decisive.description",
                "Monsterizes Haste, desc NA."); //todo

        build.add("effect.dungeonsdelight.exudation.description",
                "Monsterizes Absorption, grants exudation hearts which unleash a vile blast when damaged but the user takes increased damage to all sources.");

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

        //-------------------------SUBTITLES-------------------------
        build.add("subtitles.effect.generic.monsterize", "Effect monsterizes");

        build.add("subtitles.item.cleaver.hit_block", "Cleaver hits block");
        build.add("subtitles.item.cleaver.hit_entity", "Cleaver cuts entity");
        build.add("subtitles.item.cleaver.ready", "Cleaver fully readies");
        build.add("subtitles.item.cleaver.flying", "Cleaver whooshes");
        build.add("subtitles.item.cleaver.throw", "Cleaver throws");
        build.add("subtitles.item.cleaver.ricochet", "Cleaver ricochets");
        build.add("subtitles.item.cleaver.serrated_strike", "Cleaver serrates entity");

        build.add("subtitles.block.wormouth.eat", "Wormouth chews");
        build.add("subtitles.block.wormouth.open", "Wormouth spits");
        build.add("subtitles.block.wormouth.panic", "Wormouth panics");
        build.add("subtitles.block.wormouth.shut", "Wormouth hides from light");
        build.add("subtitles.block.wormouth.unshut", "Wormouth reopens");

        //-------------------------ADVANCEMENTS-------------------------
        build.add("advancement.dungeonsdelight.root", "Dungeon's Delight");
        build.add("advancement.dungeonsdelight.root.desc", "A world of monsters await you!");
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
