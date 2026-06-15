package net.yirmiri.dungeonsdelight.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.FluidTags;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

import java.util.concurrent.CompletableFuture;

public class DDFluidTagProvider extends FabricTagProvider.FluidTagProvider {
    public DDFluidTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendMaintainsTerrorPreta();
    }

    private void appendMaintainsTerrorPreta() {
        getOrCreateTagBuilder(DDTags.FluidT.MAINTAINS_TERROR_PRETA)
                .addOptionalTag(FluidTags.WATER)
        ;
    }
}
