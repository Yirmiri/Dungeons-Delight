package net.yirmiri.dungeonsdelight;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.yirmiri.dungeonsdelight.datagen.*;
import net.yirmiri.dungeonsdelight.datagen.recipe.DDRecipeProvider;

public class FabricDungeonsDelightDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(DDLangProvider::new);
        pack.addProvider(DDBlockTagProvider::new);
        pack.addProvider(DDItemTagProvider::new);
        pack.addProvider(DDLootTableProvider::new);
        pack.addProvider(DDModelProvider::new);
        pack.addProvider(DDRecipeProvider::new);
    }
}
