package net.yirmiri.dungeonsdelight.common.util.misc;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public record S2CRottenHeartsPacket(int hearts) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<S2CRottenHeartsPacket> TYPE =
            new CustomPacketPayload.Type<>(RunicLib.customid(DungeonsDelight.MOD_ID, "rotten_hearts"));

    public static final StreamCodec<FriendlyByteBuf, S2CRottenHeartsPacket> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.INT, S2CRottenHeartsPacket::hearts, S2CRottenHeartsPacket::new);

    public S2CRottenHeartsPacket(final FriendlyByteBuf buf) {
        this(buf.readInt());
    }

    public static void handle(S2CRottenHeartsPacket msg, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                RottenHeartManager.get(player).setRottenHearts(msg.hearts());
            }
        });
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
