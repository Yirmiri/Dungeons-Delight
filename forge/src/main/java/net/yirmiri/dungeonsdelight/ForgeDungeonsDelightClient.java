package net.yirmiri.dungeonsdelight;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.yirmiri.dungeonsdelight.common.entity.cleaver.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeDungeonsDelightClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        DungeonsDelightClient.init();
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
    }
}
