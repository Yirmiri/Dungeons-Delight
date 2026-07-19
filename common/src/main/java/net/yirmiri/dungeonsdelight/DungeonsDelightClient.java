package net.yirmiri.dungeonsdelight;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.renderer.RenderType;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

public class DungeonsDelightClient {

    public static void init() {
        renderLayers();

        // This needs to be called to force any related mixins ASAP - artyrian
        RecipeBookCategories.values();
    }

    private static void renderLayers() {
        //CUTOUT
        Services.loadClient().registerBlockRenderType(DDBlocks.WORMROOT_TENDRILS.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_GRATE.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.BLEETS.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.ENDELVES.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.MANALLIUMS.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.LIVING_TORCH.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.WALL_LIVING_TORCH.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.GUNK.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.ROTBULB.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.WILD_ROTBULB.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.ROTTEN_CROP.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.PUTRESCENT_CARROTS.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.POISONOUS_POTATOES.get(), RenderType.cutout());
        Services.loadClient().registerBlockRenderType(DDBlocks.BLIGHTED_BEETROOTS.get(), RenderType.cutout());

        //MIPPED
        Services.loadClient().registerBlockRenderType(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_DOOR.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_TRAPDOOR.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_BARS.get(), RenderType.cutoutMipped());
        Services.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_GATE.get(), RenderType.cutoutMipped());
        //TRANSLUCENT
        Services.loadClient().registerBlockRenderType(DDBlocks.ROTTEN_FLESH_BLOCK.get(), RenderType.translucent());
        Services.loadClient().registerBlockRenderType(DDBlocks.SCULK_MAYONNAISE_BLOCK.get(), RenderType.translucent());
        Services.loadClient().registerBlockRenderType(DDBlocks.TELEPOTAGE_BLOCK.get(), RenderType.translucent());
    }
}
