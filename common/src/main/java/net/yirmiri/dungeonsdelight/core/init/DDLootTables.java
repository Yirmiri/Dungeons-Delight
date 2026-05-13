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
    public static ResourceLocation REAPING_GHAST_TENTACLE = RunicLib.customid(DungeonsDelight.MOD_ID, REAPING_ID + "ghast_tentacle");
    public static ResourceLocation REAPING_SNIFFER_SHANK = RunicLib.customid(DungeonsDelight.MOD_ID, REAPING_ID + "sniffer_shank");
    public static ResourceLocation REAPING_SILVERFISH_ABDOMEN = RunicLib.customid(DungeonsDelight.MOD_ID, REAPING_ID + "silverfish_abdomen");

    //WORMOUTH
    public static ResourceLocation WORMOUTH_MALADY_B_SIDE = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "malady_b_side");
    public static ResourceLocation WORMOUTH_MALADY = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "malady");
    public static ResourceLocation WORMOUTH_DUDE_ARE_YOU_FR = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "dude_are_you_fr");
    public static ResourceLocation WORMOUTH_GENERIC = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "generic");
    public static ResourceLocation WORMOUTH_GENERIC_LOVED = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "generic_loved");
    public static ResourceLocation WORMOUTH_GENERIC_PANIC = RunicLib.customid(DungeonsDelight.MOD_ID, WORMOUTH_ID + "generic_panic");
}