package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.util.DDTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class DDBlockTagGen extends BlockTagsProvider {
    public DDBlockTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
        super(output, future, DungeonsDelight.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendMineableWithPickaxe();
        appendMonsterHeatConductors();
        appendMonsterTrayHeatSources();
        appendMonsterHeatSources();
        appendHeatSources();
        appendHeatConductors();
        appendTrayHeatSources();
    }

    private void appendMineableWithPickaxe() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(DDBlocks.DUNGEON_STOVE.get())
                .add(DDBlocks.MONSTER_POT.get())
        ;
    }

    private void appendMonsterHeatConductors() {
        tag(DDTags.BlockT.MONSTER_HEAT_CONDUCTORS)
                .add(Blocks.SPAWNER)
                .add(DDBlocks.DUNGEON_STOVE.get())
        ;
    }

    private void appendMonsterHeatSources() {
        tag(DDTags.BlockT.MONSTER_HEAT_SOURCES)
                .add(Blocks.SPAWNER)
                .add(DDBlocks.DUNGEON_STOVE.get())
        ;
    }
    private void appendMonsterTrayHeatSources() {
        tag(DDTags.BlockT.MONSTER_TRAY_HEAT_SOURCES)

        ;
    }

    //--- FARMER'S DELIGHT TAGS ---
    public void appendHeatSources() {
        tag(ModTags.HEAT_SOURCES)
                .add(DDBlocks.DUNGEON_STOVE.get())
        ;
    }

    public void appendTrayHeatSources() {
        tag(ModTags.TRAY_HEAT_SOURCES)

        ;
    }

    public void appendHeatConductors() {
        tag(ModTags.HEAT_CONDUCTORS)
                .add(DDBlocks.DUNGEON_STOVE.get())
        ;
    }
}
