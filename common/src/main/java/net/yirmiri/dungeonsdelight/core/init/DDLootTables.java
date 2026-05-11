package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDLootTables {
    private static final String WORMOUTH_ID = "gameplay/wormouth/";
    private static final String REAPING_ID = "gameplay/reaping/";

    // 1.21.1 - should be ResourceKey<LootTable>

    //REAPING
    public static ResourceLocation REAPING_SPIDER_MEAT = RunicLib.customid(DungeonsDelight.MOD_ID, REAPING_ID + "spider_meat");
    public static ResourceLocation REAPING_ROTTEN_TRIPE = RunicLib.customid(DungeonsDelight.MOD_ID, REAPING_ID + "rotten_tripe");
    public static ResourceLocation REAPING_SLIME_NOODLES = RunicLib.customid(DungeonsDelight.MOD_ID, REAPING_ID + "slime_noodles");
    public static ResourceLocation REAPING_CREEPERILLA = RunicLib.customid(DungeonsDelight.MOD_ID, REAPING_ID + "creeperilla");

    //WORMOUTH
    public static ResourceLocation WORMOUTH_MALADY_B_SIDE = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "malady_b_side");
    public static ResourceLocation WORMOUTH_MALADY = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "malady");
}
