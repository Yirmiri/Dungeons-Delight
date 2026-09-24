package net.yirmiri.dungeonsdelight;

import net.azurune.runiclib.core.platform.RLServices;
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
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WORMROOT_TENDRILS.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_GRATE.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.BLEETS.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.ENDELVES.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.MANALLIUMS.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.SOUL_PEPPERS.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.LIVING_TORCH.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WALL_LIVING_TORCH.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.GUNK.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.ROTBULB.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WILD_ROTBULB.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.ROTTEN_CROP.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.PUTRESCENT_CARROTS.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.POISONOUS_POTATOES.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.BLIGHTED_BEETROOTS.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.ROTTEN_SPAWNER.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.SPIKE_TRAP.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WILD_ENDELVES.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WILD_MANALLIUMS.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WILD_BLEETS.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.LIVING_CAMPFIRE.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.LIVING_CANDLE.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.LIVING_FIRE.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.SPIRIT_FIRE.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_CHAIN.get(), RenderType.cutout());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.LIVING_LANTERN.get(), RenderType.cutout());

        //MIPPED
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_DOOR.get(), RenderType.cutoutMipped());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_TRAPDOOR.get(), RenderType.cutoutMipped());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_BARS.get(), RenderType.cutoutMipped());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.STAINED_SCRAP_GATE.get(), RenderType.cutoutMipped());
        //TRANSLUCENT
        RLServices.loadClient().registerBlockRenderType(DDBlocks.ROTTEN_FLESH_BLOCK.get(), RenderType.translucent());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.SCULK_MAYONNAISE_BLOCK.get(), RenderType.translucent());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.TELEPOTAGE_BLOCK.get(), RenderType.translucent());
        RLServices.loadClient().registerBlockRenderType(DDBlocks.MONSTER_POT.get(), RenderType.translucent());
        //RLServices.loadClient().registerBlockRenderType(DDBlocks.ENAMELED_GLASS.get(), RenderType.translucent());
    }
}
