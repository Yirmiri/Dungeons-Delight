package net.yirmiri.dungeonsdelight.data.wormouth;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappingResourceLoader;

public class FabricWormouthMappingLoader extends WormouthMappingResourceLoader implements IdentifiableResourceReloadListener {
    @Override
    public ResourceLocation getFabricId() {
        return RunicLib.customid(DungeonsDelight.MOD_ID, WormouthMappingResourceLoader.LOCATION);
    }
}