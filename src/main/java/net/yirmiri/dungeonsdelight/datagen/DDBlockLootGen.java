package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;

import java.util.ArrayList;
import java.util.Set;

public class DDBlockLootGen extends BlockLootSubProvider {
    public DDBlockLootGen() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(DDBlocks.DUNGEON_STOVE);
        dropSelf(DDBlocks.MONSTER_POT);
        dropSelf(DDBlocks.WORMWOOD_PLANKS);
        dropSelf(DDBlocks.WORMWOOD_STAIRS);
        createSlabItemTable(DDBlocks.WORMWOOD_SLAB);
        add(DDBlocks.WORMWOOD_SLAB.get(), createSlabItemTable(DDBlocks.WORMWOOD_SLAB.get()));
        dropSelf(DDBlocks.WORMWOOD_MOSAIC);
        dropSelf(DDBlocks.WORMWOOD_MOSAIC_STAIRS);
        dropSelf(DDBlocks.WORMWOOD_MOSAIC_SLAB);
        createDoorTable(DDBlocks.WORMWOOD_DOOR);
        add(DDBlocks.WORMWOOD_DOOR.get(), (Block block) -> createDoorTable(DDBlocks.WORMWOOD_DOOR.get()));
        dropSelf(DDBlocks.WORMWOOD_TRAPDOOR);
        dropSelf(DDBlocks.WORMWOOD_BUTTON);
        dropSelf(DDBlocks.WORMWOOD_PRESSURE_PLATE);
        dropSelf(DDBlocks.WORMWOOD_FENCE);
        dropSelf(DDBlocks.WORMWOOD_FENCE_GATE);
        dropSelf(DDBlocks.WORMROOTS);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return new ArrayList<>() {{
            addAll(DDBlocks.BLOCKS.getEntries().stream().filter((r) -> r.get().getLootTable() != BuiltInLootTables.EMPTY).map(RegistryObject::get).toList());
        }};
    }


    private void dropSelf(RegistryObject<Block> block) {
        dropSelf(block.get());
    }

    private void add(RegistryObject<Block> block, LootItemCondition.Builder builder) {
        add(block, builder);
    }

    protected LootTable.Builder createSlabItemTable(RegistryObject<Block> block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block.get(), LootItem.lootTableItem(block.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))))));
    }

    protected LootTable.Builder createDoorTable(RegistryObject<Block> block) {
        return this.createSinglePropConditionTable(block.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER);
    }
}
