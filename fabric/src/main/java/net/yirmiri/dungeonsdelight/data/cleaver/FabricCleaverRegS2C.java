package net.yirmiri.dungeonsdelight.data.cleaver;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.common.networking.CleaverRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMapping;

import java.util.Map;

public class FabricCleaverRegS2C extends CleaverRegS2CPacket implements FabricPacket {
    public FabricCleaverRegS2C(Map<ResourceLocation, CleaverMapping> maps) {
        super(maps);
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) { encode(friendlyByteBuf); }

    @Override
    public PacketType<?> getType() {
        return PacketType.create(CleaverRegS2CPacket.ID, (buf) -> {
            CleaverRegS2CPacket pass = CleaverRegS2CPacket.decode(buf);
            return new FabricCleaverRegS2C(pass.map);
        });
    }
}
