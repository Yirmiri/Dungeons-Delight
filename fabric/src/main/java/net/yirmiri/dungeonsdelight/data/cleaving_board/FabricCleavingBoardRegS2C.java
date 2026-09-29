package net.yirmiri.dungeonsdelight.data.cleaving_board;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.common.networking.CleavingBoardRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.resources.cleaving_board.CleavingBoardMapping;

import java.util.Map;

// TODO 1.21.1 : remove and replace with universal
public class FabricCleavingBoardRegS2C extends CleavingBoardRegS2CPacket implements FabricPacket {
    public FabricCleavingBoardRegS2C(Map<ResourceLocation, CleavingBoardMapping> items, Map<ResourceLocation, CleavingBoardMapping> tagItems) {
        super(items, tagItems);
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) { encode(friendlyByteBuf); }

    @Override
    public PacketType<?> getType() {
        return PacketType.create(CleavingBoardRegS2CPacket.ID, (buf) -> {
            CleavingBoardRegS2CPacket pass = CleavingBoardRegS2CPacket.decode(buf);
            return new FabricCleavingBoardRegS2C(pass.items, pass.tagItems);
        });
    }
}