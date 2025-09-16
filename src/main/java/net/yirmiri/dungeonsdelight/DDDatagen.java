package net.yirmiri.dungeonsdelight;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.yirmiri.dungeonsdelight.datagen.*;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DDDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        DDBlockTagGen blockTags = new DDBlockTagGen(output, provider, helper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new DDItemTagGen(output, provider, blockTags.contentsGetter(), helper));
        generator.addProvider(true, new DDBlockstateGen(output, helper));
        generator.addProvider(true, new DDItemModelGen(output, helper));
        generator.addProvider(true, new DDLangGen(output));
        generator.addProvider(true, new DDRecipeGen(output, provider));
    }
}
