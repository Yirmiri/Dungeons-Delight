package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappings;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappings;
import net.yirmiri.dungeonsdelight.data.FabricCleaverMappingLoader;
import net.yirmiri.dungeonsdelight.data.FabricCleaverRegS2C;
import net.yirmiri.dungeonsdelight.data.FabricWormouthMappingLoader;
import net.yirmiri.dungeonsdelight.data.FabricWormouthRegS2C;

public class FabricDungeonsDelight implements ModInitializer {
    @Override
    public void onInitialize() {
        DungeonsDelight.init();

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricWormouthMappingLoader());
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricCleaverMappingLoader());

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            FabricWormouthRegS2C pack = new FabricWormouthRegS2C(WormouthMappings.MAPS, WormouthMappings.TAG_MAPS);
            sender.sendPacket(pack);

            FabricCleaverRegS2C pack2 = new FabricCleaverRegS2C(CleaverMappings.MAPS);
            sender.sendPacket(pack2);
        });
    }
}
