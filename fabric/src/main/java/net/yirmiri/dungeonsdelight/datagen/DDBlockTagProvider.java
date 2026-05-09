package net.yirmiri.dungeonsdelight.datagen;

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
    }

    private void appendCleaverMineable() {
        getOrCreateTagBuilder(DDTags.BlockT.CLEAVER_MINEABLE)
                .add(Blocks.CAKE)
                .addOptionalTag(BlockTags.CANDLE_CAKES)
        ;
    }

    private void appendMineableWithHoe() {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(DDBlocks.TERROR_PRETA.get())
        ;
    }

    private void appendPlanks() {
        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(DDBlocks.WORMWOOD_PLANKS.get())
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
