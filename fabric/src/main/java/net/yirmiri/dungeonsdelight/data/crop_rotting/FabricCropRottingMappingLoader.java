package net.yirmiri.dungeonsdelight.data.crop_rotting;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.crop_rotting.CropRottingMappingResourceLoader;

public class FabricCropRottingMappingLoader extends CropRottingMappingResourceLoader implements IdentifiableResourceReloadListener {
    @Override
    public ResourceLocation getFabricId() {
        return RunicLib.customid(DungeonsDelight.MOD_ID, CropRottingMappingResourceLoader.LOCATION);
    }
}