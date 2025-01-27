package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.LanguageProvider;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDDamageTypes;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.registry.DDItems;

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
        add("farmersdelight.container.monster_pot", "Monster Pot");
        add("dungeonsdelight.jei.monster_cooking", "Monster Cooking");
        add("farmersdelight.tooltip.monster_burger", "Every sin, disease, and unhealthy treat, merged together between two slices of bread...");

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

        //ITEMS
        add(DDItems.LOGO_ITEM.get(), "Logo Item");
        add(DDItems.STAINED_SCRAP.get(), "Stained Scrap");

        //FOODS
        add(DDItems.SLIME_NOODLES.get(), "Slime Noodles");
        add(DDItems.SLIME_SLAB.get(), "Slime Slab");
        add(DDItems.GHOULASH.get(), "Ghoulash");
        add(DDItems.AMETHYST_ROCK_CANDY.get(), "Amethyst Rock Candy");
        add(DDItems.CANDIED_SILVERFISH_SUCKER.get(), "Candied Silverfish Sucker");
        add(DDItems.CANDIED_VEX_SUCKER.get(), "Candied Vex Sucker");
        add(DDItems.SILVERFISH_THORAX.get(), "Silverfish Thorax");
        add(DDItems.GHAST_CALAMARI.get(), "Ghast Calamari");
        add(DDItems.FRIED_GHAST_CALAMARI.get(), "Fried Ghast Calamari");
        add(DDItems.GHAST_TENTACLE.get(), "Ghast Tentacle");
        add(DDItems.SILVERFISH_FRIED_RICE.get(), "Silverfish Fried Rice");
        add(DDItems.SPIDER_MEAT.get(), "Spider Meat");
        add(DDItems.SPIDER_EXTRACT.get(), "Spider Extract");
        add(DDItems.SMOKED_SPIDER_MEAT.get(), "Smoked Spider Meat");
        add(DDItems.SPIDER_TANGHULU.get(), "Spider Eye Tanghulu");
        add(DDItems.SPIDER_EYE_SALMAGUNDI.get(), "Spider Eye Salmagundi");
        add(DDItems.MONSTER_BURGER.get(), "§5The Monster Burger");

        //EFFECTS
        add(DDEffects.BURROW_GUT.get(), "Burrow Gut");
        add(DDEffects.EXUDATION.get(), "Exudation");
        add(DDEffects.ROTGUT.get(), "Rotgut");
        add(DDEffects.POUNCING.get(), "Pouncing");
        add(DDEffects.VORACITY.get(), "Voracity");
        add(DDEffects.TENACITY.get(), "Tenacity");

        //DAMAGE
        addDamageType(DDDamageTypes.DUNGEON_STOVE_BURN, "%1$s was monstrously grilled to perfection", "%1$s was thrown on the grill by The Monstrous Chef %2$s");
        addDamageType(DDDamageTypes.SKULL_HEART_BLAST, "%1$s was melted by a monstrous blast", "%1$s was melted by the monstrous blast of %2$s");

        //YAPPING TOOLTIPS COMPAT
        add(YT_ID + ".block." + DD_ID + ".monster_pot.desc", "A mysterious cooking utensil that uses the heat of monster spawners to cook delicacies");
        add(YT_ID + ".block." + DD_ID + ".dungeon_stove.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".slime_slab.desc", "Sticky slime that has been congealed into a malleable block");
        add(YT_ID + ".item." + DD_ID + ".slime_noodles.desc", "Slippery noodles that almost snake and move on their own");
        add(YT_ID + ".item." + DD_ID + ".ghoulash.desc", "The most approachable of monster meals");
        add(YT_ID + ".item." + DD_ID + ".silverfish_thorax.desc", "Emergency protein that’s rich with minerals!");
        add(YT_ID + ".item." + DD_ID + ".amethyst_rock_candy.desc", "Don’t tell Abigail!");
        add(YT_ID + ".item." + DD_ID + ".candied_vex_sucker.desc", "An even sweeter revenge!");
        add(YT_ID + ".item." + DD_ID + ".candied_silverfish_sucker.desc", "Edible pest control");
        add(YT_ID + ".item." + DD_ID + ".ghast_calamari.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".fried_ghast_calamari.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".ghast_tentacle.desc", NA_DESC);
        add(YT_ID + ".item." + DD_ID + ".silverfish_fried_rice.desc", "It did what now!?!?");
        add(YT_ID + ".item." + DD_ID + ".spider_meat.desc", "The least poisonous parts of the spider");
        add(YT_ID + ".item." + DD_ID + ".spider_extract.desc", "Don’t call it “Spider Milk”!");
        add(YT_ID + ".item." + DD_ID + ".smoked_spider_meat.desc", "Vague hint of acidity, slight hint of crab, and a big chunk of charcoal");
        add(YT_ID + ".item." + DD_ID + ".spider_tanghulu.desc", "It's got their eyes on you");
        add(YT_ID + ".item." + DD_ID + ".spider_eye_salmagundi.desc", NA_DESC);
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
        add(YT_ID + ".block." + DD_ID + ".wormwood_trapdoor.desc", "This sinister smirk seems to be beckoning you into a trap.");
        add(YT_ID + ".block." + DD_ID + ".wormwood_pressure_plate.desc", "Produces a redstone signal when ANY entity makes contact with it but is pressed down longer the darker it is.");
        add(YT_ID + ".block." + DD_ID + ".wormwood_button.desc", "Can be pushed by players, arrows, and tridents, stays pushed for longer the darker it is");
    }

    private void addDamageType(ResourceKey<DamageType> type, String deathMsg, String killMsg) {
        add(type.location().toLanguageKey(), deathMsg);
        add("death.attack." + type.location().toLanguageKey(), deathMsg);
        add("death.attack." + type.location().toLanguageKey() + ".player", killMsg);
    }
}
