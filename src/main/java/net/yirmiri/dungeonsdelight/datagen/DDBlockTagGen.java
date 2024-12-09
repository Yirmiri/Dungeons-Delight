package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.util.DDTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class DDBlockTagGen extends FabricTagProvider.BlockTagProvider {
    public DDBlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> future) {
        super(output, future);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapper) {
        appendMonsterHeatSources();
        appendPickaxeMineable();
        appendHeatSources();
        appendTrayHeatSources();
        appendHeatConductors();
        appendMonsterTrayHeatSources();
        appendMonsterHeatConductors();
    }

    public void appendPickaxeMineable() {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(DDBlocks.DUNGEON_POT)
                .add(DDBlocks.DUNGEON_STOVE)
        ;
    }

    public void appendMonsterHeatSources() {
        getOrCreateTagBuilder(DDTags.BlockT.MONSTER_HEAT_SOURCES)
                .add(Blocks.SPAWNER)
                .add(Blocks.TRIAL_SPAWNER)
                .add(DDBlocks.DUNGEON_STOVE)
        ;
    }

    public void appendMonsterTrayHeatSources() {
        getOrCreateTagBuilder(DDTags.BlockT.MONSTER_TRAY_HEAT_SOURCES)

        ;
    }

    public void appendMonsterHeatConductors() {
        getOrCreateTagBuilder(DDTags.BlockT.MONSTER_HEAT_CONDUCTORS)
                .add(Blocks.SPAWNER)
                .add(Blocks.TRIAL_SPAWNER)
                .add(DDBlocks.DUNGEON_STOVE)
        ;
    }

    //--- FARMER'S DELIGHT TAGS ---
    public void appendHeatSources() {
        getOrCreateTagBuilder(ModTags.HEAT_SOURCES)
                .add(DDBlocks.DUNGEON_STOVE)
        ;
    }

    public void appendTrayHeatSources() {
        getOrCreateTagBuilder(ModTags.TRAY_HEAT_SOURCES)

        ;
    }

    public void appendHeatConductors() {
        getOrCreateTagBuilder(ModTags.HEAT_CONDUCTORS)
                .add(DDBlocks.DUNGEON_STOVE)
        ;
    }
}
