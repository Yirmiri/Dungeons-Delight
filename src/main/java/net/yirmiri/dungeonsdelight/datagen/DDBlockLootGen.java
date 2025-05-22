package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
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
import net.yirmiri.dungeonsdelight.common.util.misc.CopyMonsterMealFunction;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

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
        dropSelf(DDBlocks.WORMROOTS_BLOCK);
        dropSelf(DDBlocks.ROTBULB_CRATE);
        dropSelf(DDBlocks.STAINED_SCRAP_BLOCK);
        dropSelf(DDBlocks.CUT_STAINED_SCRAP);
        dropSelf(DDBlocks.CUT_STAINED_SCRAP_STAIRS);
        dropSelf(DDBlocks.CUT_STAINED_SCRAP_SLAB);
        dropSelf(DDBlocks.STAINED_SCRAP_BARS);
        add(DDBlocks.ROTTEN_CROP.get(), createRotCropDrops(DDBlocks.ROTTEN_CROP, DDItems.GUNK.get()));
        add(DDBlocks.ROTTEN_POTATOES.get(), createRotCropDrops(DDBlocks.ROTTEN_POTATOES, Items.POISONOUS_POTATO));
        add(DDBlocks.ROTTEN_TOMATOES.get(), createRotCropDrops(DDBlocks.ROTTEN_TOMATOES, ModItems.ROTTEN_TOMATO.get()));
        dropOther(DDBlocks.CANDLE_MONSTER_CAKE.get(), DDBlocks.LIVING_CANDLE.get());
        dropSelf(DDBlocks.POISONOUS_POTATO_CRATE);
        dropSelf(DDBlocks.ROTTEN_TOMATO_CRATE);
        add(DDBlocks.GUNK.get(), (Block block) -> createMultifaceBlockDrops(DDBlocks.GUNK));
        add(DDBlocks.ROTTEN_SPAWNER.get(), createRottenSpawnerDrops(DDBlocks.ROTTEN_SPAWNER));
        dropSelf(DDBlocks.STAINED_SCRAP_CHAIN);
        dropSelf(DDBlocks.LIVING_TORCH);
        dropSelf(DDBlocks.WALL_LIVING_TORCH);
        dropSelf(DDBlocks.LIVING_LANTERN);
        this.add(DDBlocks.LIVING_CAMPFIRE.get(), (block) -> createSilkTouchDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(DDItems.STAINED_SCRAP.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
        dropSelf(DDBlocks.LIVING_CANDLE);
        dropSelf(DDBlocks.STAINED_SCRAP_GRATE);
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
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 4.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createRotCropDrops(RegistryObject<Block> block, Item item) {
        return this.applyExplosionDecay(block.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(item)
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));
    }

    protected LootTable.Builder createRottenSpawnerDrops(RegistryObject<Block> block) {
        return this.applyExplosionDecay(block.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(DDItems.GUNK.get())
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
    }
}
