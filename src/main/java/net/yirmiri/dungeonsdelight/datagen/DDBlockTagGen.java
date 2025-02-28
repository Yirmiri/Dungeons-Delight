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
import vectorwing.farmersdelight.common.registry.ModBlocks;
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
        appendMineableWithAxe();
        appendWoodenDoors();
        appendWoodenTrapDoors();
        appendWoodenStairs();
        appendWoodenSlabs();
        appendPlanks();
        appendWoodenPressurePlates();
        appendWoodenButtons();
        appendWoodenFenceGates();
        appendWoodenFences();
        appendMineableWithHoe();
        appendSculkingActivators();
        appendMineableWithKnife();
        appendCrops();
        appendRotbulbGrowableOn();
        appendBeaconBaseBlocks();
    }

    private void appendSculkingActivators() {
        tag(DDTags.BlockT.SCULKING_ACTIVATORS)
                .add(Blocks.SCULK)
                .add(Blocks.SCULK_CATALYST)
                .add(Blocks.SCULK_SENSOR)
                .add(Blocks.SCULK_SHRIEKER)
                .add(Blocks.SCULK_VEIN)
                .add(Blocks.CALIBRATED_SCULK_SENSOR)
                .add(DDBlocks.SCULK_MAYO_BLOCK.get())
        ;
    }

    private void appendRotbulbGrowableOn() {
        tag(DDTags.BlockT.ROTBULB_GROWABLE_ON)
                .addTag(BlockTags.MOSS_REPLACEABLE)
                .add(Blocks.FARMLAND)
                .add(ModBlocks.RICH_SOIL.get())
                .add(ModBlocks.RICH_SOIL_FARMLAND.get())
        ;
    }

    private void appendMineableWithHoe() {
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(DDBlocks.HEAP_OF_ANCIENT_EGGS.get())
                .add(DDBlocks.EMBEDDED_EGGS.get())
                .add(DDBlocks.SCULK_MAYO_BLOCK.get())
        ;
    }

    private void appendCrops() {
        tag(BlockTags.CROPS)
                .add(DDBlocks.ROTBULB_CROP.get())
        ;
    }

    private void appendMineableWithAxe() {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(DDBlocks.WORMWOOD_PLANKS.get())
                .add(DDBlocks.WORMWOOD_STAIRS.get())
                .add(DDBlocks.WORMWOOD_SLAB.get())
                .add(DDBlocks.WORMWOOD_MOSAIC.get())
                .add(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get())
                .add(DDBlocks.WORMWOOD_MOSAIC_SLAB.get())
                .add(DDBlocks.WORMWOOD_DOOR.get())
                .add(DDBlocks.WORMWOOD_TRAPDOOR.get())
                .add(DDBlocks.WORMWOOD_FENCE.get())
                .add(DDBlocks.WORMWOOD_FENCE_GATE.get())
                .add(DDBlocks.WORMWOOD_BUTTON.get())
                .add(DDBlocks.WORMWOOD_PRESSURE_PLATE.get())
                .add(DDBlocks.WORMROOTS.get())
                .add(DDBlocks.WORMWOOD_CABINET.get())
                .add(DDBlocks.WORMROOTS_BLOCK.get())
                .add(DDBlocks.ROTBULB_CRATE.get())
        ;
    }

    private void appendMineableWithKnife() {
        tag(ModTags.MINEABLE_WITH_KNIFE)
                .add(DDBlocks.GLOWBERRY_GELATIN_BLOCK.get())
        ;
    }

    private void appendMineableWithPickaxe() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(DDBlocks.DUNGEON_STOVE.get())
                .add(DDBlocks.MONSTER_POT.get())
                .add(DDBlocks.STAINED_SCRAP_BLOCK.get())
                .add(DDBlocks.STAINED_SCRAP_BARS.get())
                .add(DDBlocks.CUT_STAINED_SCRAP.get())
                .add(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get())
                .add(DDBlocks.CUT_STAINED_SCRAP_SLAB.get())
        ;
    }

    private void appendBeaconBaseBlocks() {
        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(DDBlocks.STAINED_SCRAP_BLOCK.get())
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
        tag(DDTags.BlockT.MONSTER_TRAY_HEAT_SOURCES) //TODO

        ;
    }

    private void appendPlanks() {
        tag(BlockTags.PLANKS)
                .add(DDBlocks.WORMWOOD_PLANKS.get())
        ;
    }

    private void appendWoodenStairs() {
        tag(BlockTags.WOODEN_STAIRS)
                .add(DDBlocks.WORMWOOD_STAIRS.get())
                .add(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get())
        ;
    }

    private void appendWoodenSlabs() {
        tag(BlockTags.WOODEN_SLABS)
                .add(DDBlocks.WORMWOOD_SLAB.get())
                .add(DDBlocks.WORMWOOD_MOSAIC_SLAB.get())
        ;
    }

    private void appendWoodenDoors() {
        tag(BlockTags.WOODEN_DOORS)
                .add(DDBlocks.WORMWOOD_DOOR.get())
        ;
    }

    private void appendWoodenTrapDoors() {
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(DDBlocks.WORMWOOD_TRAPDOOR.get())
        ;
    }

    private void appendWoodenPressurePlates() {
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(DDBlocks.WORMWOOD_PRESSURE_PLATE.get())
        ;
    }

    private void appendWoodenButtons() {
        tag(BlockTags.WOODEN_BUTTONS)
                .add(DDBlocks.WORMWOOD_BUTTON.get())
        ;
    }

    private void appendWoodenFences() {
        tag(BlockTags.WOODEN_FENCES)
                .add(DDBlocks.WORMWOOD_FENCE.get())
        ;
    }

    private void appendWoodenFenceGates() {
        tag(BlockTags.FENCE_GATES)
                .add(DDBlocks.WORMWOOD_FENCE_GATE.get())
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
