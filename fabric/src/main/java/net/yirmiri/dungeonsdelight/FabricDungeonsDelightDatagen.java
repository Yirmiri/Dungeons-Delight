package net.yirmiri.dungeonsdelight;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.yirmiri.dungeonsdelight.common.worldgen.feature.DDFeaturesConfigured;
import net.yirmiri.dungeonsdelight.common.worldgen.feature.DDFeaturesPlaced;
import net.yirmiri.dungeonsdelight.datagen.*;
import net.yirmiri.dungeonsdelight.datagen.DDRecipeProvider;
import net.yirmiri.dungeonsdelight.datagen.DDBlockTagProvider;
import net.yirmiri.dungeonsdelight.datagen.DDFluidTagProvider;
import net.yirmiri.dungeonsdelight.datagen.DDItemTagProvider;

public class FabricDungeonsDelightDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(DDAdvancementProvider::new);
        pack.addProvider(DDLangProvider::new);
        pack.addProvider(DDBlockTagProvider::new);
        pack.addProvider(DDItemTagProvider::new);
        pack.addProvider(DDBlockLootProvider::new);
        pack.addProvider(DDChestLootProvider::new);
        pack.addProvider(DDModelProvider::new);
        pack.addProvider(DDRecipeProvider::new);
        pack.addProvider(DDFluidTagProvider::new);
        pack.addProvider(DDWormouthProvider::new);
        pack.addProvider(DDEntityTagProvider::new);
        pack.addProvider(DDEntityLootProvider::new);
        pack.addProvider(DDDamageTagProvider::new);
        pack.addProvider(DDEffectTagProvider::new);
        pack.addProvider(DDWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, DDFeaturesConfigured::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, DDFeaturesPlaced::bootstrap);
    }

}
