package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.concurrent.CompletableFuture;

public class DDItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public DDItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendMusicDiscs();
        appendCleavers();
        appendFlamingCleavers();
    }

    private void appendCleavers() {
        getOrCreateTagBuilder(DDTags.ItemT.CLEAVERS)
                .add(DDItems.FLINT_CLEAVER.get())
                .add(DDItems.IRON_CLEAVER.get())
                .add(DDItems.GOLDEN_CLEAVER.get())
                .add(DDItems.DIAMOND_CLEAVER.get())
                .add(DDItems.NETHERITE_CLEAVER.get())
        ;
    }

    //TODO will not exist in 1.21
    private void appendMusicDiscs() {
        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(DDItems.MUSIC_DISC_MALADY.get())
                .add(DDItems.MUSIC_DISC_MALADY_B_SIDE.get())
        ;
    }

    //INTEGRATION
    private void appendFlamingCleavers() {
        getOrCreateTagBuilder(DDTags.ItemT.FLAMING_CLEAVERS)

        ;
    }
}
