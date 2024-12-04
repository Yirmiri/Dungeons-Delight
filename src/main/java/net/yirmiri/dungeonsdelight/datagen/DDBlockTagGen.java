package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.yirmiri.dungeonsdelight.util.DDTags;

import java.util.concurrent.CompletableFuture;

public class DDBlockTagGen extends FabricTagProvider.BlockTagProvider {
    public DDBlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> future) {
        super(output, future);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapper) {
        appendMonsterHeatSources();
    }

    public void appendMonsterHeatSources() {
        getOrCreateTagBuilder(DDTags.BlockT.MONSTER_HEAT_SOURCES)
                .add(Blocks.SPAWNER)
                .add(Blocks.TRIAL_SPAWNER)
        ;
    }
}
