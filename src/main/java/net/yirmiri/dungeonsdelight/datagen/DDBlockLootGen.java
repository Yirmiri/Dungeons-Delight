package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import net.yirmiri.dungeonsdelight.util.misc.CopyMonsterMealFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DDBlockLootGen extends BlockLootSubProvider {
    private final Set<Block> generatedLootTables = new HashSet<>();

    public DDBlockLootGen() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(DDBlocks.DUNGEON_STOVE);

        add(DDBlocks.MONSTER_POT.get(), (block) -> LootTable.lootTable().withPool(this.applyExplosionCondition(block, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block)
                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)).apply(CopyMonsterMealFunction.builder())))));

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
        dropNamedContainer(DDBlocks.WORMWOOD_CABINET);
        add(DDBlocks.WORMROOTS.get(), (Block block) -> createMultifaceBlockDrops(DDBlocks.WORMROOTS));
        dropSelf(DDBlocks.EMBEDDED_EGGS);
        add(DDBlocks.HEAP_OF_ANCIENT_EGGS.get(), createAncientEggsDrops(DDBlocks.HEAP_OF_ANCIENT_EGGS));
        dropSelf(DDBlocks.SCULK_MAYO_BLOCK);
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        this.generatedLootTables.add(block);
        this.map.put(block.getLootTable(), builder);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return generatedLootTables;
    }

    private void dropSelf(RegistryObject<Block> block) {
        dropSelf(block.get());
    }

    protected LootTable.Builder createSlabItemTable(RegistryObject<Block> block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block.get(), LootItem.lootTableItem(block.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))))));
    }

    protected void dropNamedContainer(RegistryObject<Block> block) {
        add(block.get(), this::createNameableBlockEntityTable);
    }

    protected LootTable.Builder createDoorTable(RegistryObject<Block> block) {
        return this.createSinglePropConditionTable(block.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER);
    }

    protected LootTable.Builder createMultifaceBlockDrops(RegistryObject<Block> block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().add((LootPoolEntryContainer.Builder)this.applyExplosionDecay(block.get(), ((LootPoolSingletonContainer.Builder)((LootPoolSingletonContainer.Builder)LootItem.lootTableItem(block.get())).apply(Direction.values(), (object) -> SetItemCountFunction.setCount(ConstantValue.exactly(1.0F), true).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MultifaceBlock.getFaceProperty((Direction) object), true))))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)))));
    }

    protected LootTable.Builder createAncientEggsDrops(RegistryObject<Block> block) {
        return createSilkTouchDispatchTable(block.get(), this.applyExplosionDecay(block.get(), LootItem.lootTableItem(DDItems.ANCIENT_EGG.get())
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
}
