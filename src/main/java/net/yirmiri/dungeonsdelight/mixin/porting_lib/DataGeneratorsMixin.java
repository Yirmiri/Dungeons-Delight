package net.yirmiri.dungeonsdelight.mixin.porting_lib;

import io.github.fabricators_of_create.porting_lib.tags.data.DataGenerators;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.yirmiri.dungeonsdelight.datagen.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DataGenerators.class)
public class DataGeneratorsMixin {

    /**
     * @author - Yirmiri
     * @reason - Probably really stupid way of getting around it but without this, this method prevents datagen from running sometimes due to Porting Lib
     */
    @Overwrite(remap = false)
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
