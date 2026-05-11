package net.yirmiri.dungeonsdelight;

import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappingResourceLoader;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappingResourceLoader;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class ForgeDungeonsDelightEvents {
    @SubscribeEvent
    public static void reloadResourcesSetup(AddReloadListenerEvent event) {
        event.addListener(new WormouthMappingResourceLoader());
        event.addListener(new CleaverMappingResourceLoader());
    }
}
