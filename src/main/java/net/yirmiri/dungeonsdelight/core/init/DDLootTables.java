package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDLootTables {
    // Chests
    public static ResourceKey<LootTable> ROTTEN_DUNGEON_CHEST = ResourceKey.create(
            Registries.LOOT_TABLE, RunicLib.customid(DungeonsDelight.MOD_ID, "chests/rotten_dungeon"));

    // Gameplay
    public static ResourceKey<LootTable> WORMOUTH_PREFERRED = ResourceKey.create(
            Registries.LOOT_TABLE, RunicLib.customid(DungeonsDelight.MOD_ID, "gameplay/preferred_food"));
    public static ResourceKey<LootTable> WORMOUTH_DISLIKED = ResourceKey.create(
            Registries.LOOT_TABLE, RunicLib.customid(DungeonsDelight.MOD_ID, "gameplay/disliked_food"));

    private DDLootTables() {

    }
}
