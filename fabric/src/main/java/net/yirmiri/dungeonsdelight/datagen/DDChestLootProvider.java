package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
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
    }
}
