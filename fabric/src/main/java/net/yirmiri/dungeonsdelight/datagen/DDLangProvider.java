package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.Supplier;

public class DDLangProvider extends FabricLanguageProvider {
    public DDLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder build) {
        //MISC
        build.add("itemgroup.dungeonsdelight", "Dungeon's Delight");
        build.add("item.dungeonsdelight.music_disc_malady.desc", "Artyrian - Malady");
        build.add("item.dungeonsdelight.music_disc_malady_b_side.desc", "Artyrian - Malady (B-Side)");

        //BLOCKS
        addWithYT(build, DDBlocks.MORBID_MUSH.get(), "Morbid Mush", "Mud that has been 'fertilized' to allow putrid flora to grow, requires water within 4 blocks and will burn while in sunlight");
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

        //ITEMS
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

        //SUBTITLES
        build.add("subtitles.item.cleaver.hit_block", "Cleaver hits block");
        build.add("subtitles.item.cleaver.hit_entity", "Cleaver cuts entity");
        build.add("subtitles.item.cleaver.ready", "Cleaver fully readies");
        build.add("subtitles.item.cleaver.flying", "Cleaver whooshes");
        build.add("subtitles.item.cleaver.throw", "Cleaver throws");
        build.add("subtitles.item.cleaver.ricochet", "Cleaver ricochets");
        build.add("subtitles.item.cleaver.serrated_strike", "Cleaver serrates entity");

        //ATTRIBUTES
        build.add("attribute.dungeonsdelight.throwing_range", "Throwing Range");
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
