package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

import java.util.concurrent.CompletableFuture;

public class DDBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public DDBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendCleaverMineable();
    }

    private void appendCleaverMineable() {
        getOrCreateTagBuilder(DDTags.BlockT.CLEAVER_MINEABLE)
                .add(Blocks.CAKE)
                .addTag(BlockTags.CANDLE_CAKES)
        ;
    }
}
