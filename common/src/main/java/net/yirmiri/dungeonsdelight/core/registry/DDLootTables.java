package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDLootTables {
    private static final String WORMOUTH_ID = "gameplay/wormouth/";
    private static final String REAPING_ID = "gameplay/reaping/";

    // 1.21.1 - should be ResourceKey<LootTable>

    //REAPING
    public static ResourceLocation REAPING_SPIDER_MEAT = RunicLib.customid(DungeonsDelight.MOD_ID, REAPING_ID + "spider_meat");

    //WORMOUTH
    public static ResourceLocation WORMOUTH_MALADY_B_SIDE = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "malady_b_side");
    public static ResourceLocation WORMOUTH_MALADY = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "malady");
}
