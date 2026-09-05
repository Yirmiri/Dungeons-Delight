package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskEntity;
import net.yirmiri.dungeonsdelight.common.entity.living.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappings;
import net.yirmiri.dungeonsdelight.common.resources.crop_rotting.CropRottingMappings;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappings;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDRegistries;
import net.yirmiri.dungeonsdelight.core.registry.DDStats;
import net.yirmiri.dungeonsdelight.data.FabricDDWorldGen;
import net.yirmiri.dungeonsdelight.data.cleaver.FabricCleaverMappingLoader;
import net.yirmiri.dungeonsdelight.data.cleaver.FabricCleaverRegS2C;
import net.yirmiri.dungeonsdelight.data.crop_rotting.FabricCropRottingMappingLoader;
import net.yirmiri.dungeonsdelight.data.crop_rotting.FabricCropRottingRegS2C;
import net.yirmiri.dungeonsdelight.data.wormouth.FabricWormouthMappingLoader;
import net.yirmiri.dungeonsdelight.data.wormouth.FabricWormouthRegS2C;

public class FabricDungeonsDelight implements ModInitializer {
    @Override
    public void onInitialize() {
        DungeonsDelight.init();
        DDRegistries.load();
        DDStats.finalizeCustomStats();

        FabricDDWorldGen.generate();

        DDBlocks.loadBanquetItems();

        //DEFAULT ATTRIBUTES
        FabricDefaultAttributeRegistry.register(DDEntities.CAMEL_HUSK.get(), CamelHuskEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(DDEntities.MONSTER_YAM.get(), MonsterYamEntity.createAttributes());
        //FabricDefaultAttributeRegistry.register(DDEntities.TREASURE_BUG.get(), TreasureBugEntity.createAttributes());

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricWormouthMappingLoader());
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricCleaverMappingLoader());
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricCropRottingMappingLoader());

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            FabricWormouthRegS2C pack = new FabricWormouthRegS2C(WormouthMappings.MAPS, WormouthMappings.TAG_MAPS);
            sender.sendPacket(pack);

            FabricCleaverRegS2C pack2 = new FabricCleaverRegS2C(CleaverMappings.MAPS);
            sender.sendPacket(pack2);

            FabricCropRottingRegS2C pack3 = new FabricCropRottingRegS2C(CropRottingMappings.MAPS);
            sender.sendPacket(pack3);
        });
    }
}
