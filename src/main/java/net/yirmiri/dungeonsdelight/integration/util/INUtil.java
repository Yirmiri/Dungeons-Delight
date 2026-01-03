package net.yirmiri.dungeonsdelight.integration.util;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class INUtil {
    public static Item dynamicCraftRemainder(String modid, String id, Item fallback) {
        if (RLServices.PLATFORM.isModLoaded(modid)) {
            return BuiltInRegistries.ITEM.get(RunicLib.customid(modid, id));
        } else return fallback;
    }
}
