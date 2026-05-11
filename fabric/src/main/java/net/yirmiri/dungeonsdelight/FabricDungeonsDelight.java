package net.yirmiri.dungeonsdelight;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import net.yirmiri.dungeonsdelight.data.FabricCleaverMappingLoader;
import net.yirmiri.dungeonsdelight.data.FabricWormouthMappingLoader;

public class FabricDungeonsDelight implements ModInitializer {
    @Override
    public void onInitialize() {
        DungeonsDelight.init();

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricWormouthMappingLoader());
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricCleaverMappingLoader());
    }
}
