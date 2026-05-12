package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.yirmiri.dungeonsdelight.common.block.entity.ItemGrateBlockEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.cleaver.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.networking.WormouthRegS2CPacket;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

@Environment(EnvType.CLIENT)
public class FabricDungeonsDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DungeonsDelightClient.init();

        registerEntityRenderers();
        registerBlockEntityRenderers();
        registerS2CPackets();
    }

    private void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(WormouthRegS2CPacket.ID, ((minecraft, clientPacketListener, friendlyByteBuf, packetSender) -> {
            WormouthRegS2CPacket packet = WormouthRegS2CPacket.decode(friendlyByteBuf);
            packet.handle();
        }));
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
