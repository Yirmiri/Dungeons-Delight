package net.yirmiri.dungeonsdelight.data.crop_rotting;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.common.networking.CropRottingRegS2CPacket;
import net.yirmiri.dungeonsdelight.common.resources.crop_rotting.CropRottingMapping;

import java.util.Map;

public class FabricCropRottingRegS2C extends CropRottingRegS2CPacket implements FabricPacket {
    public FabricCropRottingRegS2C(Map<ResourceLocation, CropRottingMapping> maps) {
        super(maps);
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) { encode(friendlyByteBuf); }

    @Override
    public PacketType<?> getType() {
        return PacketType.create(CropRottingRegS2CPacket.ID, (buf) -> {
            CropRottingRegS2CPacket pass = CropRottingRegS2CPacket.decode(buf);
            return new FabricCropRottingRegS2C(pass.map);
        });
    }
}
