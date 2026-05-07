package net.yirmiri.dungeonsdelight;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.ModInitializer;

public class FabricDungeonsDelight implements ModInitializer {
    @Override
    public void onInitialize() {
        DungeonsDelight.init();
    }
}
