package net.yirmiri.dungeonsdelight.datagen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.yirmiri.dungeonsdelight.core.init.DDLootTables;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.BiConsumer;

public class DDChestLootProvider extends SimpleFabricLootTableProvider {
    public DDChestLootProvider(FabricDataOutput output) {
        super(output, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        generateWormouth(builder);
        generateChest(builder);
    }

    private static void generateChest(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        // Rotten Dungeon
        builder.accept(
                DDLootTables.ROTTEN_DUNGEON_CHEST,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(1.0F, 3.0F))
                                        .add(LootItem.lootTableItem(Items.SADDLE)
                                                .setWeight(20)
                                        )
                                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                                .setWeight(15)
                                        )
                                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                                                .setWeight(2)
                                        )
                                        .add(LootItem.lootTableItem(DDItems.MUSIC_DISC_MALADY.get())
                                                .setWeight(5)
                                        )
                                        .add(LootItem.lootTableItem(Items.NAME_TAG)
                                                .setWeight(25)
                                        )
                                        .add(LootItem.lootTableItem(Items.GOLDEN_HORSE_ARMOR)
                                                .setWeight(15)
                                        )
                                        .add(LootItem.lootTableItem(Items.IRON_HORSE_ARMOR)
                                                .setWeight(20)
                                        )
                                        .add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR)
                                                .setWeight(5)
                                        )
                                        .add(LootItem.lootTableItem(Items.BOOK)
                                                .setWeight(10)
                                                .apply(EnchantRandomlyFunction.randomApplicableEnchantment())
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(1.0F, 4.0F))
                                        .add(LootItem.lootTableItem(Items.IRON_INGOT)
                                                .setWeight(10)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        )
                                        .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                                .setWeight(5)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        )
                                        .add(LootItem.lootTableItem(Items.BREAD)
                                                .setWeight(20))
                                        .add(LootItem.lootTableItem(Items.WHEAT)
                                                .setWeight(20)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        )
                                        .add(LootItem.lootTableItem(Items.BUCKET)
                                                .setWeight(10))
                                        .add(LootItem.lootTableItem(Items.REDSTONE)
                                                .setWeight(15)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        )
                                        .add(LootItem.lootTableItem(Items.COAL)
                                                .setWeight(15)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        )
                                        .add(LootItem.lootTableItem(Items.MELON_SEEDS)
                                                .setWeight(10)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                        )
                                        .add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS)
                                                .setWeight(10)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                        )
                                        .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS)
                                                .setWeight(10)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                        )
                        )
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))
                                )
                                .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))
                                )
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))
                                )
                                .add(LootItem.lootTableItem(Items.STRING)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))
                                )
                        )
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(2.0F))
                                .add(LootItem.lootTableItem(DDItems.RANCID_REDUCTION.get())
                                        .setWeight(1)
                                        .setQuality(4)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                )
                                .add(LootItem.lootTableItem(DDItems.ROTBULB_SEEDS.get())
                                        .setWeight(5)
                                        .setQuality(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                )
                                .add(LootItem.lootTableItem(DDItems.GUNK.get())
                                        .setWeight(7)
                                        .setQuality(1)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(5.0F, 12.0F)))
                                )
                        )
        );
    }

    private static void generateWormouth(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(
                DDLootTables.WORMOUTH_MALADY,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(DDItems.MUSIC_DISC_MALADY.get()))
                        )
        );
        builder.accept(
                DDLootTables.WORMOUTH_MALADY_B_SIDE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(DDItems.MUSIC_DISC_MALADY_B_SIDE.get()))
                        )
        );
        builder.accept(
                DDLootTables.WORMOUTH_DUDE_ARE_YOU_FR,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        )
        );
        builder.accept(
                DDLootTables.WORMOUTH_GENERIC,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                        .add(LootItem.lootTableItem(Items.SLIME_BALL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                        .add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                                        .add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                        .add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        )
        );
        //TODO: Change this
        builder.accept(
                DDLootTables.WORMOUTH_GENERIC_PANIC,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(2.0F, 4.0F))
                                        .add(EmptyLootItem.emptyItem().setWeight(2))
                                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                        .add(LootItem.lootTableItem(Items.SLIME_BALL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                        .add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                                        .add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                        .add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        )
        );
    }
}
