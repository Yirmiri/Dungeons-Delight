package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.registries.BuiltInRegistries;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDRegistryAliases {
    public static void load() {
        itemAlias(DungeonsDelight.MOD_ID, "tokayaki", DungeonsDelight.MOD_ID, "takoyaki");
    }

    private static void blockAlias(String originalModId, String originalId, String newModId, String newId) {
        BuiltInRegistries.BLOCK.addAlias(RunicLib.customid(originalModId, originalId), RunicLib.customid(newModId, newId));
    }

    private static void itemAlias(String originalModId, String originalId, String newModId, String newId) {
        BuiltInRegistries.ITEM.addAlias(RunicLib.customid(originalModId, originalId), RunicLib.customid(newModId, newId));
    }
}