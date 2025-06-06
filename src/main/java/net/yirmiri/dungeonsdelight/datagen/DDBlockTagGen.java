package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;
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
        appendMonsterTrayHeatSources();
        appendMonsterHeatSources();
        appendHeatSources();
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
        appendLivingFireBaseBlocks();
        appendFire();
        appendCandles();
        appendCampfires();
        appendCandleCakes();
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
                .add(DDBlocks.SCULK_TART.get())
        ;
    }

    private void appendLivingFireBaseBlocks() {
        tag(DDTags.BlockT.LIVING_FIRE_BASE_BLOCKS)
                .add(Blocks.SPAWNER)
                .add(DDBlocks.STAINED_SCRAP_BLOCK.get())
                .add(DDBlocks.CUT_STAINED_SCRAP.get())
                .add(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get())
                .add(DDBlocks.CUT_STAINED_SCRAP_SLAB.get())
                .add(DDBlocks.STAINED_SCRAP_GRATE.get())
        ;
    }

    private void appendFire() {
        tag(BlockTags.FIRE)
                .add(DDBlocks.LIVING_FIRE.get())
        ;
    }

    private void appendCandleCakes() {
        tag(BlockTags.CANDLE_CAKES)
                .add(DDBlocks.CANDLE_MONSTER_CAKE.get())
        ;
    }

    private void appendCandles() {
        tag(BlockTags.CANDLES)
                .add(DDBlocks.LIVING_CANDLE.get())
        ;
    }

    private void appendCampfires() {
        tag(BlockTags.CAMPFIRES)
                .add(DDBlocks.LIVING_CAMPFIRE.get())
        ;
    }

    private void appendRotbulbGrowableOn() {
        tag(DDTags.BlockT.ROTBULB_GROWABLE_ON)
                .addTag(BlockTags.MOSS_REPLACEABLE)
                .addTag(BlockTags.BASE_STONE_OVERWORLD)
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
                .add(DDBlocks.ROTTEN_CROP.get())
                .add(DDBlocks.ROTTEN_POTATOES.get())
                .add(DDBlocks.ROTTEN_TOMATOES.get())
                .add(DDBlocks.GUNK.get())
        ;
    }

    private void appendMineableWithKnife() {
        tag(ModTags.MINEABLE_WITH_KNIFE)
                .add(DDBlocks.GLOW_BERRY_GELATIN_BLOCK.get())
                .add(DDBlocks.SCULK_TART.get())
                .add(DDBlocks.MONSTER_CAKE.get())
                .add(DDBlocks.SPIDER_PIE.get())
                .add(DDBlocks.GUARDIAN_ANGEL_BLOCK.get())
                .add(DDBlocks.CANDLE_MONSTER_CAKE.get())
                .add(DDBlocks.GUNK.get())
                .add(DDBlocks.SPIDER_DONUT.get())
                .add(DDBlocks.SCULK_MAYO_BLOCK.get())
                .add(Blocks.SCULK)
                .add(Blocks.SCULK_CATALYST)
                .add(Blocks.SCULK_VEIN)
                .add(Blocks.SCULK_SENSOR)
                .add(Blocks.CALIBRATED_SCULK_SENSOR)
                .add(Blocks.SCULK_SHRIEKER)
                .add(DDBlocks.EMBEDDED_EGGS.get())
                .add(DDBlocks.HEAP_OF_ANCIENT_EGGS.get())
        ;
    }

    private void appendCrops() {
        tag(BlockTags.CROPS)
                .add(DDBlocks.ROTBULB_CROP.get())
        ;
    }

    private void appendMineableWithAxe() {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(DDBlocks.LIVING_CAMPFIRE.get())
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
                .add(DDBlocks.ROTTEN_TOMATO_CRATE.get())
                .add(DDBlocks.POISONOUS_POTATO_CRATE.get())
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
                .add(DDBlocks.ROTTEN_SPAWNER.get())
                .add(DDBlocks.STAINED_SCRAP_CHAIN.get())
                .add(DDBlocks.LIVING_LANTERN.get())
                .add(DDBlocks.LIVING_CANDLE.get())
                .add(DDBlocks.STAINED_SCRAP_GRATE.get())
        ;
    }

    private void appendBeaconBaseBlocks() {
        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(DDBlocks.STAINED_SCRAP_BLOCK.get())
        ;
    }

    private void appendMonsterHeatSources() { //Rotten spawner intentionally absent due to all it's life energy being used
        tag(DDTags.BlockT.MONSTER_HEAT_SOURCES)
                .add(Blocks.SPAWNER)
                .add(DDBlocks.DUNGEON_STOVE.get())
                .addTag(DDTags.BlockT.MONSTER_TRAY_HEAT_SOURCES)
                .addOptional(new ResourceLocation(IntegrationIds.JNE, "treacherous_candle"))
        ;
    }
    private void appendMonsterTrayHeatSources() {
        tag(DDTags.BlockT.MONSTER_TRAY_HEAT_SOURCES)
                .add(DDBlocks.LIVING_FIRE.get())
                .add(DDBlocks.LIVING_CAMPFIRE.get())
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

    public void appendHeatSources() { //does not take monster_heat_sources due to spawners
        tag(ModTags.HEAT_SOURCES)
                .add(DDBlocks.DUNGEON_STOVE.get())
                .addOptional(new ResourceLocation(IntegrationIds.JNE, "treacherous_candle"))
        ;
    }

    public void appendTrayHeatSources() { //does not take monster_tray_heat_sources due to spawners
        tag(ModTags.TRAY_HEAT_SOURCES)
                .add(DDBlocks.LIVING_FIRE.get())
        ;
    }
}
