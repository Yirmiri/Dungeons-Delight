package net.yirmiri.dungeonsdelight;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.yirmiri.dungeonsdelight.datagen.*;

public class DungeonsDelightDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(DDItemTagGen::new);
		pack.addProvider(DDLangGen::new);
		pack.addProvider(DDModelGen::new);
		pack.addProvider(DDRecipeGen::new);
		pack.addProvider(DDBlockTagGen::new);
		pack.addProvider(DDLootTableGen::new);
	}
}
