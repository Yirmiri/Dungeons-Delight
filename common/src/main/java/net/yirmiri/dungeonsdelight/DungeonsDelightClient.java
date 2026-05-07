package net.yirmiri.dungeonsdelight;

import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.client.renderer.RenderType;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

public class DungeonsDelightClient {

    public static void init() {
        renderLayers();
    }

    private static void renderLayers() {
        // CUTOUT
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WORMROOT_TENDRILS.get(), RenderType.cutout());

        // MIPPED
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
    }
}
