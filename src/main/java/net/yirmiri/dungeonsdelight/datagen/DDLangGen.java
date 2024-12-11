package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import net.yirmiri.dungeonsdelight.util.DDTags;

import java.util.concurrent.CompletableFuture;

public class DDLangGen extends FabricLanguageProvider {
    public static final String ID = "minecraft.";
    public static final String DD_ID = "dungeonsdelight.";

    public DDLangGen(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> lookup) {
        super(dataGenerator, lookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder build) {
        //MISC
        build.add("itemgroup.dungeonsdelight", "Dungeon's Delight");
        build.add("stat.dungeonsdelight.dashes", "Breeze Blasts");
        build.add("farmersdelight.container.dungeon_pot", "Dungeon Pot");

        //BLOCKS
        build.add(DDBlocks.DUNGEON_POT, "Dungeon Pot");
        build.add(DDBlocks.DUNGEON_STOVE, "Dungeon Stove");

        //ITEMS
        build.add(DDItems.LOGO_ITEM, "Logo Item");
        build.add(DDItems.BREEZE_CREAM_CONE, "Breeze Cream Cone");
        build.add(DDItems.TRIAL_FREAKSHAKE, "Trial Freakshake");
        build.add(DDItems.SLIME_SLAB, "Slime Slab");
        build.add(DDItems.SLIME_NOODLES, "Slime Noodles");

        //TOOLTIPS
        build.add("farmersdelight.tooltip.trial_freakshake", "Clears 1 Harmful Effect");

        //DAMAGE
        build.add("death.attack.dungeonsdelight.dungeon_stove_heat", "%1$s was monstrously grilled to perfection");
        build.add("death.attack.dungeonsdelight.dungeon_stove_heat.player", "%1$s was thrown on the grill by The Monstrous Chef %2$s");
        build.add("death.attack.dungeonsdelight.skull_heart_blast", "%1$s was melted by a monstrous blast");
        build.add("death.attack.dungeonsdelight.skull_heart_blast.player", "%1$s was melted by the monstrous blast of %2$s");

        //EFFECTS
        build.add("effect.dungeonsdelight.burrow_gut", "Burrow Gut");
        build.add("effect.dungeonsdelight.voracity", "Voracity");
        build.add("effect.dungeonsdelight.tenacity", "Tenacity");
        build.add("effect.dungeonsdelight.breeze_blast", "Breeze Blast");
        build.add("effect.dungeonsdelight.pouncing", "Pouncing");
        build.add("effect.dungeonsdelight.exudation", "Exudation");

        //BLOCK TAGS
        build.add(DDTags.BlockT.MONSTER_HEAT_SOURCES, "Monster Heat Sources");
        build.add(DDTags.BlockT.MONSTER_HEAT_CONDUCTORS, "Monster Heat Conductors");
        build.add(DDTags.BlockT.MONSTER_TRAY_HEAT_SOURCES, "Monster Tray Heat Sources");

        //ITEM TAGS
        build.add(DDTags.ItemT.MONSTER_FOODS, "Monster Foods");
        build.add(DDTags.ItemT.DUNGEONS_DELIGHT_FOODS, "Dungeon's Delight Foods");
    }
}
