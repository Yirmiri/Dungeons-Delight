package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.yirmiri.dungeonsdelight.common.entity.cleaver.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

@Environment(EnvType.CLIENT)
public class FabricDungeonsDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DungeonsDelightClient.init();

        registerEntityRenderers();
    }

    private void registerEntityRenderers() {
        EntityRendererRegistry.register(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
    }
}
