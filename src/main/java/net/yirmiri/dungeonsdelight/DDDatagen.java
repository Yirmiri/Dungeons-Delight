package net.yirmiri.dungeonsdelight;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.yirmiri.dungeonsdelight.datagen.*;

import java.util.concurrent.CompletableFuture;

public class DDDatagen {
    @SubscribeEvent
    public static void gatherData(net.neoforged.neoforge.data.event.GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        net.neoforged.neoforge.common.data.ExistingFileHelper helper = event.getExistingFileHelper();

        DDBlockTagGen blockTagGen = generator.addProvider(true, new DDBlockTagGen(output, provider, helper));

        generator.addProvider(true, DDLootGen.create(output));

        generator.addProvider(true, new DDItemTagGen(output, provider, blockTagGen.contentsGetter(), helper));
        generator.addProvider(true, new DDBlockstateGen(output, helper));
        generator.addProvider(true, new DDItemModelGen(output, helper));
        generator.addProvider(true, new DDLangGen(output));
        generator.addProvider(true, new DDRecipeGen(output));
    }
}
