package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.yirmiri.dungeonsdelight.common.block.entity.ItemGrateBlockEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.cleaver.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

@Environment(EnvType.CLIENT)
public class FabricDungeonsDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DungeonsDelightClient.init();

        registerEntityRenderers();
        registerBlockEntityRenderers();
    }

    private void registerEntityRenderers() {
        EntityRendererRegistry.register(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
    }

    private void registerBlockEntityRenderers() {
        //BlockEntityRenderers.register(DDBlockEntities.DUNGEON_STOVE.get(), DefaultStoveRenderer::new);
        //BlockEntityRenderers.register(DDBlockEntities.LIVING_CAMPFIRE.get(), CampfireRenderer::new);
        BlockEntityRenderers.register(DDBlockEntities.ITEM_GRATE.get(), ItemGrateBlockEntityRenderer::new);
    }
}
