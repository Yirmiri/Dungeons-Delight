package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;

import java.util.concurrent.CompletableFuture;

public class DDLootTableGen extends FabricBlockLootTableProvider {
    public DDLootTableGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> future) {
        super(output, future);
    }

    @Override
    public void generate() {
        drops(DDBlocks.DUNGEON_POT);
    }
}
