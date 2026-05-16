package net.yirmiri.dungeonsdelight;

import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.yirmiri.dungeonsdelight.common.block.entity.ItemGrateBlockEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.cleaver.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.particle.FlameEffectParticle;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeDungeonsDelightClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        DungeonsDelightClient.init();
    }

    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DDParticles.LIVING_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(DDParticles.LIVING_FLAME_EFFECT.get(), FlameEffectParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DDBlockEntities.ITEM_GRATE.get(), ItemGrateBlockEntityRenderer::new);
    }
}
