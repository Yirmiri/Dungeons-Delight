package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.yirmiri.dungeonsdelight.common.util.BlockGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class DDLootTableProvider extends FabricBlockLootTableProvider {
    private static final List<Block> manualBlocks = new ArrayList<>();

    public DDLootTableProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {

        // AUTO-RUNNING
        runAuto();
    }

    public LootTable.Builder createBasicMultiDrops(Block block) {
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .add(
                                        this.applyExplosionDecay(
                                                block,
                                                LootItem.lootTableItem(block)
                                                        .apply(
                                                                Direction.values(),
                                                                direction -> SetItemCountFunction.setCount(ConstantValue.exactly(1.0F), true)
                                                                        .when(
                                                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MultifaceBlock.getFaceProperty(direction), true))
                                                                        )
                                                        )
                                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true))
                                        )
                                )
                );
    }

    private void runAuto() {
        for (BlockGroup set : BlockGroup.SETS) {
            Map<Supplier<Block>, BlockGroup.ModelMode> modelmodes = set.models();
            for (Supplier<Block> supp : set.getRegisteredBlocks()) {
                Block block = supp.get();
                if (!manualBlocks.contains(block) && modelmodes.containsKey(supp)) {
                    BlockGroup.ModelMode mode = modelmodes.get(supp);
                    switch (mode) {
                        case SLAB -> add(block, createSlabItemTable(block));
                        case MULTIFACE -> add(block, createBasicMultiDrops(block));
                        case DOOR -> add(block, createDoorTable(block));
                        default -> dropSelf(block);
                    }
                }
            }
        }
    }
}
