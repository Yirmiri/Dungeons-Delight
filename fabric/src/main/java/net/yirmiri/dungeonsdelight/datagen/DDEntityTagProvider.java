package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

import java.util.concurrent.CompletableFuture;

public class DDEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public DDEntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendMaintainsTerrorPreta();
    }

    private void appendMaintainsTerrorPreta() {
        getOrCreateTagBuilder(DDTags.EntityT.REAPS_SPIDER_MEAT)
                .add(EntityType.SPIDER)
                .add(EntityType.CAVE_SPIDER)
        ;
    }
}
