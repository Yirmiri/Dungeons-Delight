package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

import java.util.concurrent.CompletableFuture;

public class DDFluidTagProvider extends FabricTagProvider.FluidTagProvider {
    public DDFluidTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendMaintainsMorbidMush();
    }

    private void appendMaintainsMorbidMush() {
        getOrCreateTagBuilder(DDTags.FluidT.MAINTAINS_MORBID_MUSH)
                .addOptionalTag(FluidTags.WATER)
        ;
    }
}
