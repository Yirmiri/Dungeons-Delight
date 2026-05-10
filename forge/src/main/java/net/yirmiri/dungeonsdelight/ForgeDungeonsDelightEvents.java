package net.yirmiri.dungeonsdelight;

import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeDungeonsDelightEvents
{
    @SubscribeEvent
    public static void reloadResourcesSetup(AddReloadListenerEvent event) {
    }
}
