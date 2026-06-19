package net.yirmiri.dungeonsdelight;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.client.renderer.RenderType;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

public class DungeonsDelightClient {

    public static void init() {
        renderLayers();
    }

    private static void renderLayers() {
        //CUTOUT
        Services.loadClient().registerBlockRenderType(DDBlocks.WORMROOT_TENDRILS.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_GRATE.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.BLEETS.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.ENDELVES.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.MANALLIUMS.get(), RenderType.cutout());
        //MIPPED
        Services.loadClient().registerBlockRenderType(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_DOOR.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_TRAPDOOR.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_BARS.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_GATE.get(), RenderType.cutoutMipped());
    }
}
