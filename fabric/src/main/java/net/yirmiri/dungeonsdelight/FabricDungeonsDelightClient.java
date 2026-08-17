package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.entity.EvokerFangsRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.yirmiri.dungeonsdelight.common.block.entity.item_grate.ItemGrateRenderer;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.common.block.entity.wavy_block.WavyRenderer;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskModel;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskRenderer;
import net.yirmiri.dungeonsdelight.common.entity.living.monster_yam.MonsterYamModel;
import net.yirmiri.dungeonsdelight.common.entity.living.monster_yam.MonsterYamRenderer;
import net.yirmiri.dungeonsdelight.common.entity.living.treasure_bug.TreasureBugModel;
import net.yirmiri.dungeonsdelight.common.entity.living.treasure_bug.TreasureBugRenderer;
import net.yirmiri.dungeonsdelight.common.entity.misc.EmptyEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.misc.vexing_fangs.VexingFangsModel;
import net.yirmiri.dungeonsdelight.common.entity.misc.vexing_fangs.VexingFangsRenderer;
import net.yirmiri.dungeonsdelight.common.networking.CleaverRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.networking.CropRottingRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.networking.WormouthRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.particle.*;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDMenus;
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
        ParticleFactoryRegistry.getInstance().register(DDParticles.SINGLE_LIVING_FLAME.get(), FlameEffectParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.EXUDATION_BLAST.get(), SonicBoomParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.LARGE_ECHO_BLAST.get(), EchoBlastParticle.Large::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.MEDIUM_ECHO_BLAST.get(), EchoBlastParticle.Medium::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.SMALL_ECHO_BLAST.get(), EchoBlastParticle.Small::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.MONSTER_RESIDUE.get(), ResidueParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.MONSTER_STEAM.get(), CampfireSmokeParticle.CosyProvider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.DUNGEON_BUBBLE.get(), AnimatedParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.ROTTEN_RESIDUE.get(), ResidueParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.FLY.get(), FlyParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.ROT_CLOUD.get(), CampfireSmokeParticle.CosyProvider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.ROTTEN_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.LIVING_LAVA.get(), AnimatedLavaParticle.Provider::new);

        //ENTITY
        EntityRendererRegistry.register(DDEntities.CAMEL_HUSK.get(), CamelHuskRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DDModelLayers.CAMEL_HUSK, CamelHuskModel::createBodyLayer);

        EntityRendererRegistry.register(DDEntities.VEXING_FANGS.get(), VexingFangsRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DDModelLayers.VEXING_FANGS, VexingFangsModel::createBodyLayer);

        EntityRendererRegistry.register(DDEntities.MONSTER_YAM.get(), MonsterYamRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DDModelLayers.MONSTER_YAM, MonsterYamModel::createBodyLayer);

        //EntityRendererRegistry.register(DDEntities.TREASURE_BUG.get(), TreasureBugRenderer::new);
        //EntityModelLayerRegistry.registerModelLayer(DDModelLayers.TREASURE_BUG, TreasureBugModel::createBodyLayer);

        EntityRendererRegistry.register(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
        EntityRendererRegistry.register(DDEntities.ECHO_BLAST.get(), EmptyEntityRenderer::new);
        EntityRendererRegistry.register(DDEntities.ANCIENT_EGG.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(DDEntities.RANCID_REDUCTION.get(), ThrownItemRenderer::new);

        MenuScreens.register(DDMenus.MONSTER_POT.get(), MonsterPotScreen::new);
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
        ClientPlayNetworking.registerGlobalReceiver(CropRottingRegS2CPacket.ID, ((minecraft, clientPacketListener, friendlyByteBuf, packetSender) -> {
            CropRottingRegS2CPacket packet = CropRottingRegS2CPacket.decode(friendlyByteBuf);
            packet.handle();
        }));
    }

    private void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(DDBlockEntities.ITEM_GRATE.get(), ItemGrateRenderer::new);
        BlockEntityRenderers.register(DDBlockEntities.WAVY_BLOCK.get(), WavyRenderer::new);
        BlockEntityRenderers.register(DDBlockEntities.LIVING_CAMPFIRE.get(), CampfireRenderer::new);
    }
}
