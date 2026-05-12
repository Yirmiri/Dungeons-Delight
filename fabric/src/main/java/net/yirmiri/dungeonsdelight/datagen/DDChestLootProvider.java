package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.init.DDLootTables;

import java.util.function.BiConsumer;

public class DDChestLootProvider extends SimpleFabricLootTableProvider {
    public DDChestLootProvider(FabricDataOutput output) {
        super(output, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        generateWormouth(builder);
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
    }
}
