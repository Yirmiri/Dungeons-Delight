package net.yirmiri.dungeonsdelight.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.yirmiri.dungeonsdelight.common.util.BlockGroup;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class DDBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public DDBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        automateGroups();
        appendPlanks();
        appendCleaverMineable();
        appendMineableWithHoe();
        appendMineableWithAxe();
        appendCrops();
        appendMineableWithPickaxe();
        appendPreventsClimbing();
        appendLivingHeatSources();
        appendWildCropGrowableOn();
        appendFire();
        appendCampfires();
        appendCandles();
        appendLivingFireBaseBlocks();
    }

    private void appendCleaverMineable() {
        getOrCreateTagBuilder(DDTags.BlockT.CLEAVER_MINEABLE)
                .add(Blocks.CAKE)
                .addOptionalTag(BlockTags.CANDLE_CAKES)
                .add(DDBlocks.ROTTEN_FLESH_BLOCK.get())
                .add(DDBlocks.SCULK_MAYONNAISE_BLOCK.get())
                .add(DDBlocks.EMBEDDED_EGGS.get())
                .add(Blocks.SCULK)
                .add(Blocks.SCULK_CATALYST)
                .add(Blocks.SCULK_SENSOR)
                .add(Blocks.CALIBRATED_SCULK_SENSOR)
                .add(Blocks.SCULK_VEIN)
                .add(Blocks.SCULK_SHRIEKER)
        ;
    }

    private void appendMineableWithPickaxe() {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(DDBlocks.MONSTER_POT.get())
                .add(DDBlocks.DUNGEON_STOVE.get())
                .add(DDBlocks.TELEPOTAGE_BLOCK.get())
                .add(DDBlocks.ROTTEN_SPAWNER.get())
                .add(DDBlocks.SPIKE_TRAP.get())
                .add(DDBlocks.LIVING_CANDLE.get())
                .add(DDBlocks.STAINED_SCRAP_CHAIN.get())
                .add(DDBlocks.LIVING_LANTERN.get())
        ;
    }

    private void appendMineableWithAxe() {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(DDBlocks.BAMBOO_CLEAVING_BOARD.get())
                .add(DDBlocks.WORMWOOD_CLEAVING_BOARD.get())
                .add(DDBlocks.LIVING_CAMPFIRE.get())
        ;
    }

    private void appendMineableWithHoe() {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(DDBlocks.TERROR_PRETA.get())
                .add(DDBlocks.SCULK_MAYONNAISE_BLOCK.get())
                .add(DDBlocks.EMBEDDED_EGGS.get())
        ;
    }

    private void appendLivingHeatSources() {
        getOrCreateTagBuilder(DDTags.BlockT.LIVING_HEAT_SOURCES)
                .add(DDBlocks.DUNGEON_STOVE.get())
                .add(DDBlocks.LIVING_CAMPFIRE.get())
                .add(DDBlocks.LIVING_FIRE.get())
                .add(DDBlocks.SPIRIT_FIRE.get())
        ;
    }

    private void appendLivingFireBaseBlocks() {
        getOrCreateTagBuilder(DDTags.BlockT.LIVING_FIRE_BASE_BLOCKS)
                .add(Blocks.SPAWNER)
                .add(DDBlocks.DUNGEON_STOVE.get())
                .add(DDBlocks.STAINED_SCRAP_BLOCK.get())
                .add(DDBlocks.STAINED_SCRAP_PILLAR.get())
                .add(DDBlocks.CUT_STAINED_SCRAP.get())
                .add(DDBlocks.CHISELED_STAINED_SCRAP.get())
                .add(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get())
                .add(DDBlocks.CUT_STAINED_SCRAP_SLAB.get())
                .add(DDBlocks.STAINED_SCRAP_TRAPDOOR.get())
                .add(DDBlocks.STAINED_SCRAP_GRATE.get())
                .add(DDBlocks.ROTTEN_FLESH_BLOCK.get())
                .add(DDBlocks.GUNK_BLOCK.get())
        ;
    }

    private void appendCrops() {
        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(DDBlocks.BLEETS.get())
                .add(DDBlocks.ENDELVES.get())
                .add(DDBlocks.MANALLIUMS.get())
                .add(DDBlocks.ROTBULB.get())
                .add(DDBlocks.SOUL_PEPPERS.get())
        ;
    }

    private void appendPlanks() {
        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(DDBlocks.WORMWOOD_PLANKS.get())
        ;
    }

    private void appendFire() {
        getOrCreateTagBuilder(BlockTags.FIRE)
                .add(DDBlocks.LIVING_FIRE.get())
                .add(DDBlocks.SPIRIT_FIRE.get())
        ;
    }

    private void appendCampfires() {
        getOrCreateTagBuilder(BlockTags.CAMPFIRES)
                .add(DDBlocks.LIVING_CAMPFIRE.get())
        ;
    }

    private void appendCandles() {
        getOrCreateTagBuilder(BlockTags.CANDLES)
                .add(DDBlocks.LIVING_CANDLE.get())
        ;
    }

    private void appendWildCropGrowableOn() {
        getOrCreateTagBuilder(DDTags.BlockT.WILD_CROP_GROWABLE_ON)
                .addOptionalTag(BlockTags.MOSS_REPLACEABLE)
                .addOptionalTag(BlockTags.BASE_STONE_OVERWORLD)
                .addOptionalTag(BlockTags.DIRT)
                .add(Blocks.FARMLAND)
                .add(DDBlocks.TERROR_PRETA.get())
        ;
    }

    private void appendPreventsClimbing() {
        getOrCreateTagBuilder(DDTags.BlockT.CANNOT_CLIMB)
                .add(DDBlocks.STAINED_SCRAP_BARS.get())
                .add(DDBlocks.STAINED_SCRAP_GATE.get())
        ;
    }

    private void automateGroups() {
        for (BlockGroup set : BlockGroup.SETS) {
            List<Supplier<Block>> blocks = set.getRegisteredBlocks();
            Map<Supplier<Block>, BlockGroup.ModelMode> models = set.models();
            boolean wood = set.isWooden();
            for (Supplier<Block> block : blocks) {
                for (TagKey<Block> tag : set.commonBlockTag) getOrCreateTagBuilder(tag).add(block.get());
                if (models.containsKey(block)) {
                    switch (models.get(block)) {
                        case TRAPDOOR -> getOrCreateTagBuilder((wood) ? BlockTags.WOODEN_TRAPDOORS : BlockTags.TRAPDOORS).add(block.get());
                        case DOOR -> getOrCreateTagBuilder((wood) ? BlockTags.WOODEN_DOORS : BlockTags.DOORS).add(block.get());
                        case SLAB -> getOrCreateTagBuilder((wood) ? BlockTags.WOODEN_SLABS : BlockTags.SLABS).add(block.get());
                        case STAIRS -> getOrCreateTagBuilder((wood) ? BlockTags.WOODEN_STAIRS : BlockTags.STAIRS).add(block.get());
                        case FENCE -> getOrCreateTagBuilder((wood) ? BlockTags.WOODEN_FENCES : BlockTags.FENCES).add(block.get());
                        case FENCE_GATE -> getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(block.get());
                        case PLATE -> getOrCreateTagBuilder((wood) ? BlockTags.WOODEN_PRESSURE_PLATES : BlockTags.PRESSURE_PLATES).add(block.get());
                        case BUTTON -> getOrCreateTagBuilder((wood) ? BlockTags.WOODEN_BUTTONS : BlockTags.BUTTONS).add(block.get());
                    }
                }
            }
        }
    }
}
