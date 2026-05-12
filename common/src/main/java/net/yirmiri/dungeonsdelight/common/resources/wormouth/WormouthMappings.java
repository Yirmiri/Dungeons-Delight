package net.yirmiri.dungeonsdelight.common.resources.wormouth;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class WormouthMappings {
    public static final Map<ResourceLocation, WormouthMapping> MAPS = new HashMap<>();
    public static final Map<ResourceLocation, WormouthMapping> TAG_MAPS = new HashMap<>();

    public static void clear() {
        MAPS.clear();
        TAG_MAPS.clear();
    }
}