package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.yirmiri.dungeonsdelight.common.block.entity.item_grate.ItemGrateRenderer;
import net.yirmiri.dungeonsdelight.common.block.entity.wavy_block.WavyRenderer;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskModel;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskRenderer;
import net.yirmiri.dungeonsdelight.common.entity.misc.EmptyEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.networking.CleaverRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.networking.WormouthRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.particle.AnimatedFlameParticle;
import net.yirmiri.dungeonsdelight.common.particle.EchoBlastParticle;
import net.yirmiri.dungeonsdelight.common.particle.FlameEffectParticle;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

@Environment(EnvType.CLIENT)
public class FabricDungeonsDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DungeonsDelightClient.init();

        registerBlockEntityRenderers();
        registerS2CPackets();

        //PARTICLES
        ParticleFactoryRegistry.getInstance().register(DDParticles.LIVING_FLAME.get(), AnimatedFlameParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.LIVING_FLAME_EFFECT.get(), FlameEffectParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.EXUDATION_BLAST.get(), SonicBoomParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.LARGE_ECHO_BLAST.get(), EchoBlastParticle.Large::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.MEDIUM_ECHO_BLAST.get(), EchoBlastParticle.Medium::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.SMALL_ECHO_BLAST.get(), EchoBlastParticle.Small::new);

        //ENTITY
        EntityRendererRegistry.register(DDEntities.CAMEL_HUSK.get(), CamelHuskRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DDModelLayers.CAMEL_HUSK, CamelHuskModel::createBodyLayer);

        EntityRendererRegistry.register(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
        EntityRendererRegistry.register(DDEntities.ECHO_BLAST.get(), EmptyEntityRenderer::new);
        EntityRendererRegistry.register(DDEntities.ANCIENT_EGG.get(), ThrownItemRenderer::new);
    }

    private void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(WormouthRegS2CPacket.ID, ((minecraft, clientPacketListener, friendlyByteBuf, packetSender) -> {
            WormouthRegS2CPacket packet = WormouthRegS2CPacket.decode(friendlyByteBuf);
            packet.handle();
        }));
        ClientPlayNetworking.registerGlobalReceiver(CleaverRegS2CPacket.ID, ((minecraft, clientPacketListener, friendlyByteBuf, packetSender) -> {
            CleaverRegS2CPacket packet = CleaverRegS2CPacket.decode(friendlyByteBuf);
            packet.handle();
        }));
    }

    private void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(DDBlockEntities.ITEM_GRATE.get(), ItemGrateRenderer::new);
        BlockEntityRenderers.register(DDBlockEntities.WAVY_BLOCK.get(), WavyRenderer::new);
    }
}
