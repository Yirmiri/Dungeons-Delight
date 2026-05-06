package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class FabricDungeonsDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DungeonsDelightClient.init();
    }
}
