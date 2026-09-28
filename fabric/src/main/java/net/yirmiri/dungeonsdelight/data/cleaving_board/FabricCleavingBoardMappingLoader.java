package net.yirmiri.dungeonsdelight.data.cleaving_board;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.cleaving_board.CleavingBoardMappingResourceLoader;

public class FabricCleavingBoardMappingLoader extends CleavingBoardMappingResourceLoader implements IdentifiableResourceReloadListener {
    @Override
    public ResourceLocation getFabricId() {
        return RunicLib.customid(DungeonsDelight.MOD_ID, CleavingBoardMappingResourceLoader.LOCATION);
    }
}