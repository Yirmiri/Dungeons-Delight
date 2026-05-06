package net.yirmiri.dungeonsdelight;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeDungeonsDelightClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        DungeonsDelightClient.init();
    }
}
