package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.yirmiri.dungeonsdelight.registry.DDItems;

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

        //ITEMS
        build.add(DDItems.LOGO_ITEM, "Logo Item");

        //EFFECTS
        build.add("effect.dungeonsdelight.burrow_gut", "Burrow Gut");
        build.add("effect.dungeonsdelight.voracity", "Voracity");
        build.add("effect.dungeonsdelight.tenacity", "Tenacity");
    }
}
