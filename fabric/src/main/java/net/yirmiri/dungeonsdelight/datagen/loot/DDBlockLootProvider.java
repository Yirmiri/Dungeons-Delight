package net.yirmiri.dungeonsdelight.datagen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.yirmiri.dungeonsdelight.common.block.EmbeddedEggsBlock;
import net.yirmiri.dungeonsdelight.common.block.banquets.TelepotageBlock;
import net.yirmiri.dungeonsdelight.common.block.crops.BleetsCropBlock;
import net.yirmiri.dungeonsdelight.common.block.crops.EndelveCropBlock;
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
        dropSelf(DDBlocks.MONSTER_POT.get()); //TODO: change when inventory added maybe
        dropSelf(DDBlocks.BAMBOO_CLEAVING_BOARD.get()); //TODO: change when inventory added maybe
        dropSelf(DDBlocks.WORMWOOD_CLEAVING_BOARD.get()); //TODO: change when inventory added maybe
        manualBlocks.addAll(
                List.of(DDBlocks.MONSTER_POT.get(), DDBlocks.BAMBOO_CLEAVING_BOARD.get(), DDBlocks.WORMWOOD_CLEAVING_BOARD.get()));

        dropSelf(DDBlocks.DUNGEON_STOVE.get());
        manualBlocks.add(DDBlocks.DUNGEON_STOVE.get());

        dropSelf(DDBlocks.ROTTEN_FLESH_BLOCK.get());
        manualBlocks.add(DDBlocks.ROTTEN_FLESH_BLOCK.get());

        dropSelf(DDBlocks.SCULK_MAYONNAISE_BLOCK.get());
        manualBlocks.add(DDBlocks.SCULK_MAYONNAISE_BLOCK.get());

        dropSelf(DDBlocks.GUNK_BLOCK.get());
        manualBlocks.add(DDBlocks.GUNK_BLOCK.get());

        add(DDBlocks.WORMOUTH.get(), noDrop());
        manualBlocks.add(DDBlocks.WORMOUTH.get());

        add(DDBlocks.ROTBULB.get(), noDrop());
        manualBlocks.add(DDBlocks.ROTBULB.get());

        manualBlocks.add(DDBlocks.WILD_ROTBULB.get());
        add(DDBlocks.WILD_ROTBULB.get(), createWildCropDrops(DDBlocks.WILD_ROTBULB.get(), DDItems.WILD_ROTBULB.get(), DDItems.ROTBULB_SEEDS.get(),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.WILD_ROTBULB.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
        );

        manualBlocks.add(DDBlocks.BLEETS.get());
        add(DDBlocks.BLEETS.get(), createCropDrops(DDBlocks.BLEETS.get(), DDItems.BLEET.get(), DDItems.BLEET_SEEDS.get(),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.BLEETS.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BleetsCropBlock.AGE, 5)))
        );

        manualBlocks.add(DDBlocks.ENDELVES.get());
        add(DDBlocks.ENDELVES.get(), applyExplosionDecay(DDBlocks.ENDELVES.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(DDItems.ENDELVE.get()))).withPool(LootPool.lootPool()
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.ENDELVES.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(EndelveCropBlock.AGE, 7))).add(LootItem.lootTableItem(DDItems.ENDELVE.get())
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5F, 2))))));

        manualBlocks.add(DDBlocks.MANALLIUMS.get());
        add(DDBlocks.MANALLIUMS.get(), applyExplosionDecay(DDBlocks.MANALLIUMS.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(DDItems.MANALLIUM.get()))).withPool(LootPool.lootPool()
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.MANALLIUMS.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(EndelveCropBlock.AGE, 7))).add(LootItem.lootTableItem(DDItems.MANALLIUM.get())
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5F, 2))))));

        manualBlocks.add(DDBlocks.TELEPOTAGE_BLOCK.get());
        add(DDBlocks.TELEPOTAGE_BLOCK.get(), createBanquetDrops(DDBlocks.TELEPOTAGE_BLOCK.get(),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.TELEPOTAGE_BLOCK.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(TelepotageBlock.SERVINGS, 3)
                                .hasProperty(TelepotageBlock.FULL, true))
        ));

        manualBlocks.add(DDBlocks.EMBEDDED_EGGS.get());
        add(DDBlocks.EMBEDDED_EGGS.get(), applyExplosionDecay(DDBlocks.EMBEDDED_EGGS.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(DDItems.ANCIENT_EGG.get())
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(5)))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.EMBEDDED_EGGS.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(EmbeddedEggsBlock.AGE, EmbeddedEggsBlock.getMaxAge()))))
                        .add(LootItem.lootTableItem(DDBlocks.EMBEDDED_EGGS.get()).when(InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(DDBlocks.EMBEDDED_EGGS.get()).setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(EmbeddedEggsBlock.AGE, EmbeddedEggsBlock.getMaxAge()))))))));

        dropSelf(DDBlocks.LIVING_TORCH.get());
        manualBlocks.add(DDBlocks.LIVING_TORCH.get());

        dropSelf(DDBlocks.WALL_LIVING_TORCH.get());
        manualBlocks.add(DDBlocks.WALL_LIVING_TORCH.get());

        manualBlocks.add(DDBlocks.ROTTEN_CROP.get());
        add(DDBlocks.ROTTEN_CROP.get(), applyExplosionDecay(DDBlocks.ROTTEN_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(DDItems.GUNK.get()))).withPool(LootPool.lootPool()
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.ROTTEN_CROP.get()))
                .add(LootItem.lootTableItem(DDItems.GUNK.get())
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.25F, 1))))));

        manualBlocks.add(DDBlocks.PUTRESCENT_CARROTS.get());
        add(DDBlocks.PUTRESCENT_CARROTS.get(), applyExplosionDecay(DDBlocks.PUTRESCENT_CARROTS.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.CARROT))).withPool(LootPool.lootPool()
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.PUTRESCENT_CARROTS.get()))
                .add(LootItem.lootTableItem(DDItems.GUNK.get())
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.25F, 1))))));

        manualBlocks.add(DDBlocks.POISONOUS_POTATOES.get());
        add(DDBlocks.POISONOUS_POTATOES.get(), applyExplosionDecay(DDBlocks.POISONOUS_POTATOES.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.POISONOUS_POTATO))).withPool(LootPool.lootPool()
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.POISONOUS_POTATOES.get()))
                .add(LootItem.lootTableItem(DDItems.GUNK.get())
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.25F, 1))))));

        manualBlocks.add(DDBlocks.BLIGHTED_BEETROOTS.get());
        add(DDBlocks.BLIGHTED_BEETROOTS.get(), applyExplosionDecay(DDBlocks.BLIGHTED_BEETROOTS.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.BEETROOT))).withPool(LootPool.lootPool()
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(DDBlocks.BLIGHTED_BEETROOTS.get()))
                .add(LootItem.lootTableItem(DDItems.GUNK.get())
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.25F, 1))))));

        manualBlocks.add(DDBlocks.ROTTEN_SPAWNER.get()); //does not drop stained scrap because the loot modifier handles that
        add(DDBlocks.ROTTEN_SPAWNER.get(), applyExplosionDecay(DDBlocks.ROTTEN_SPAWNER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(DDItems.GUNK.get()))).withPool(LootPool.lootPool()
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8)))
                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 1.0F, 4))

                .add(LootItem.lootTableItem(DDItems.ROTBULB_SEEDS.get()))).withPool(LootPool.lootPool()
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.33F, 1))
        ))); //todo fix

        runAuto();
    }

    public LootTable.Builder createBanquetDrops(Block banquetBlock, LootItemCondition.Builder condition) {
        return this.applyExplosionDecay(banquetBlock, LootTable.lootTable()
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(banquetBlock).when(condition))));
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
