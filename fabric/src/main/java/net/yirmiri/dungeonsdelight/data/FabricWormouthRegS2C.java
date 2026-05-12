package net.yirmiri.dungeonsdelight.data;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.common.networking.WormouthRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMapping;

import java.util.Map;

// TODO 1.21.1 : remove and replace with universal
public class FabricWormouthRegS2C extends WormouthRegS2CPacket implements FabricPacket
{
    public FabricWormouthRegS2C(Map<ResourceLocation, WormouthMapping> items, Map<ResourceLocation, WormouthMapping> tagItems) {
        super(items, tagItems);
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) { encode(friendlyByteBuf); }

    @Override
    public PacketType<?> getType() {
        return PacketType.create(WormouthRegS2CPacket.ID, (buf) -> {
            WormouthRegS2CPacket pass = WormouthRegS2CPacket.decode(buf);
            return new FabricWormouthRegS2C(pass.items, pass.tagItems);
        });
    }
}
