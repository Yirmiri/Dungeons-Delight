package net.yirmiri.dungeonsdelight.common.networking;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.crop_rotting.CropRottingMapping;
import net.yirmiri.dungeonsdelight.common.resources.crop_rotting.CropRottingMappings;

import java.util.HashMap;
import java.util.Map;

public class CropRottingRegS2CPacket {
    public static final ResourceLocation ID = RunicLib.customid(DungeonsDelight.MOD_ID, "crop_rotting_reg_sync");
    public final Map<ResourceLocation, CropRottingMapping> map;

    public CropRottingRegS2CPacket(Map<ResourceLocation, CropRottingMapping> map) {
        this.map = map;
    }

    public void encode (FriendlyByteBuf buf) {
        buf.writeInt(this.map.size());
        for (Map.Entry<ResourceLocation, CropRottingMapping> def : this.map.entrySet()) {
            buf.writeJsonWithCodec(ResourceLocation.CODEC, def.getKey());
            buf.writeJsonWithCodec(CropRottingMapping.CODEC, def.getValue());
        }
    }

    public static CropRottingRegS2CPacket decode(FriendlyByteBuf buf) {
        int size = buf.readInt();
        Map<ResourceLocation, CropRottingMapping> mapper = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            ResourceLocation id = buf.readJsonWithCodec(ResourceLocation.CODEC);
            CropRottingMapping def = buf.readJsonWithCodec(CropRottingMapping.CODEC);
            mapper.put(id, def);
        }
        return new CropRottingRegS2CPacket(mapper);
    }

    public void handle() {
        if (Services.PLATFORM.isClient()) {
            CropRottingMappings.clear();
            CropRottingMappings.MAPS.putAll(this.map);
        }
    }
}
