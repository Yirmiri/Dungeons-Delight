package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.yirmiri.dungeonsdelight.common.block.crops.BleetsBlock;
import net.yirmiri.dungeonsdelight.common.util.BlockGroup;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class DDBlockLootProvider extends FabricBlockLootTableProvider {
    private static final List<Block> manualBlocks = new ArrayList<>();

    public DDBlockLootProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        add(DDBlocks.WORMOUTH.get(), noDrop());
        manualBlocks.add(DDBlocks.WORMOUTH.get());

        manualBlocks.add(DDBlocks.BLEETS.get());
        add(DDBlocks.BLEETS.get(), createCropDrops(DDBlocks.BLEETS.get(), DDItems.BLEET.get(), DDItems.BLEET_SEEDS.get(),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.BLEETS.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BleetsBlock.AGE, 5)))
        );

        manualBlocks.add(DDBlocks.WILD_BLEETS.get()); //maybe make drop self if u have shears?
        add(DDBlocks.WILD_BLEETS.get(), createWildCropDrops(DDBlocks.WILD_BLEETS.get(), DDItems.BLEET.get(), DDItems.BLEET_SEEDS.get(),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.WILD_BLEETS.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
        );

        runAuto();
    }

    public LootTable.Builder createWildCropDrops(Block cropBlock, Item grownCropItem, Item seedsItem, LootItemCondition.Builder dropGrownCropCondition) {
        return this.applyExplosionDecay(cropBlock, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(grownCropItem)
                .when(dropGrownCropCondition).otherwise(LootItem.lootTableItem(seedsItem)))).withPool(LootPool.lootPool().when(dropGrownCropCondition)
                .add(LootItem.lootTableItem(seedsItem))));
    }

    public LootTable.Builder createBasicMultiDrops(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().add(applyExplosionDecay(block, LootItem.lootTableItem(block).apply(Direction.values(), direction -> SetItemCountFunction.setCount(ConstantValue.exactly(1.0F), true).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MultifaceBlock.getFaceProperty(direction), true)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)))));
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
