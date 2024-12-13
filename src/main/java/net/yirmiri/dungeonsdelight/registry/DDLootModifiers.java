package net.yirmiri.dungeonsdelight.registry;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.item.ItemPredicate;
import vectorwing.farmersdelight.common.tag.ModTags;

public class DDLootModifiers {
    public static void modifyLoot() {
        LootTableEvents.MODIFY.register((id, tableBuilder, source, registries) -> {

            if (EntityType.SILVERFISH.getLootTableId() == id && source.isBuiltin()) {
                LootPool.Builder builder = LootPool.builder()
                        .with(ItemEntry.builder(DDItems.SILVERFISH_THORAX));
                        //.conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ModTags.KNIVES)));

                tableBuilder.pool(builder).build();
            }
        }); //TODO: FIX CONDITIONAL
    }
}