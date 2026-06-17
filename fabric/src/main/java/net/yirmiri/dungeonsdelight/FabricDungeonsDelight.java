package net.yirmiri.dungeonsdelight;

import net.azurune.runiclib.core.runiconfig.Runiconfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskEntity;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappings;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappings;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDRegistries;
import net.yirmiri.dungeonsdelight.data.*;

public class FabricDungeonsDelight implements ModInitializer {
    @Override
    public void onInitialize() {
        DungeonsDelight.init();
        DDRegistries.loadCompostables();

        FabricDDWorldGen.generate();

        //DEFAULT ATTRIBUTES
        FabricDefaultAttributeRegistry.register(DDEntities.CAMEL_HUSK.get(), CamelHuskEntity.createAttributes());

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
