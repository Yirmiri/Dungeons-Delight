package net.yirmiri.dungeonsdelight;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.yirmiri.dungeonsdelight.common.networking.CleaverRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.networking.WormouthRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappingResourceLoader;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappings;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappingResourceLoader;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappings;
import net.yirmiri.dungeonsdelight.core.networking.ForgeDDNetworking;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class ForgeDungeonsDelightEvents {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        WormouthRegS2CPacket pack = new WormouthRegS2CPacket(WormouthMappings.MAPS, WormouthMappings.TAG_MAPS);
        ForgeDDNetworking.sendToPlayer(player, pack);

        CleaverRegS2CPacket pack2 = new CleaverRegS2CPacket(CleaverMappings.MAPS);
        ForgeDDNetworking.sendToPlayer(player, pack2);
    }

    @SubscribeEvent
    public static void reloadResourcesSetup(AddReloadListenerEvent event) {
        event.addListener(new WormouthMappingResourceLoader());
        event.addListener(new CleaverMappingResourceLoader());
    }
}
