package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class DDItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public DDItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendMusicDiscs();
    }

    private void appendMusicDiscs() {
        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)

        ;
    }
}
