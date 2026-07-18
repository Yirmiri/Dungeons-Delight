package net.yirmiri.dungeonsdelight.data.cleaver;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappingResourceLoader;

public class FabricCleaverMappingLoader extends CleaverMappingResourceLoader implements IdentifiableResourceReloadListener {
    @Override
    public ResourceLocation getFabricId() {
        return RunicLib.customid(DungeonsDelight.MOD_ID, CleaverMappingResourceLoader.LOCATION);
    }
}