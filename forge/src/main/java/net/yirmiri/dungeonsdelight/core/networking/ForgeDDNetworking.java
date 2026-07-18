package net.yirmiri.dungeonsdelight.core.networking;

import net.azurune.runiclib.RunicLib;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.networking.CleaverRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.networking.CropRottingRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.networking.WormouthRegS2CPacket;

// TODO: In 1.21.1 please god turn these into payloads
// the fact i gen had to watch kaupenjoe to comprehend this makes me livid - artyrian
public class ForgeDDNetworking {
    public static SimpleChannel CHANNEL;

    private static int packetID = 0;
    private static int id() { return packetID++; }

    public static void init() {
        CHANNEL = NetworkRegistry.ChannelBuilder
                .named(RunicLib.customid(DungeonsDelight.MOD_ID, "networker"))
                .networkProtocolVersion(() -> "V1")
                .clientAcceptedVersions(predicate -> true)
                .serverAcceptedVersions(predicate -> true)
                .simpleChannel();

        CHANNEL.messageBuilder(WormouthRegS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(WormouthRegS2CPacket::decode)
                .encoder(WormouthRegS2CPacket::encode)
                .consumerMainThread(((pack, contextSupplier) -> pack.handle()))
                .add();

        CHANNEL.messageBuilder(CleaverRegS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CleaverRegS2CPacket::decode)
                .encoder(CleaverRegS2CPacket::encode)
                .consumerMainThread(((pack, contextSupplier) -> pack.handle()))
                .add();

        CHANNEL.messageBuilder(CropRottingRegS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CropRottingRegS2CPacket::decode)
                .encoder(CropRottingRegS2CPacket::encode)
                .consumerMainThread(((pack, contextSupplier) -> pack.handle()))
                .add();
    }

    public static <MSG> void sendToServer(MSG msg) { CHANNEL.sendToServer(msg);}
    public static <MSG> void sendToPlayer(ServerPlayer player, MSG msg) { CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);}
}
