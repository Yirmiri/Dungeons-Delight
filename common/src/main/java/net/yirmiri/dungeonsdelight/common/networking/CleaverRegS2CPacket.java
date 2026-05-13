package net.yirmiri.dungeonsdelight.common.networking;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMapping;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappings;

import java.util.HashMap;
import java.util.Map;

public class CleaverRegS2CPacket {
    public static final ResourceLocation ID = RunicLib.customid(DungeonsDelight.MOD_ID, "reaping_reg_sync");

    public final Map<ResourceLocation, CleaverMapping> map;

    public CleaverRegS2CPacket(Map<ResourceLocation, CleaverMapping> map) {
        this.map = map;
    }

    public void encode (FriendlyByteBuf buf) {
        buf.writeInt(this.map.size());
        for (Map.Entry<ResourceLocation, CleaverMapping> def : this.map.entrySet()) {
            buf.writeJsonWithCodec(ResourceLocation.CODEC, def.getKey());
            buf.writeJsonWithCodec(CleaverMapping.CODEC, def.getValue());
        }
    }

    public static CleaverRegS2CPacket decode(FriendlyByteBuf buf) {
        int size = buf.readInt();
        Map<ResourceLocation, CleaverMapping> mapper = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            ResourceLocation id = buf.readJsonWithCodec(ResourceLocation.CODEC);
            CleaverMapping def = buf.readJsonWithCodec(CleaverMapping.CODEC);
            mapper.put(id, def);
        }
        return new CleaverRegS2CPacket(mapper);
    }

    public void handle() {
        if (RLServices.PLATFORM.isClient()) {
            CleaverMappings.clear();
            CleaverMappings.MAPS.putAll(this.map);
        }
    }
}
