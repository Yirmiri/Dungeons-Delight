package net.yirmiri.dungeonsdelight.common.resources.crop_rotting;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CropRottingMappings {
    public static final Map<ResourceLocation, CropRottingMapping> MAPS = new HashMap<>();

    public static void clear() {
        MAPS.clear();
    }
}