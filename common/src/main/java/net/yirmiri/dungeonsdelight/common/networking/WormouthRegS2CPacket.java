package net.yirmiri.dungeonsdelight.common.networking;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMapping;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappings;

import java.util.HashMap;
import java.util.Map;

// TODO: Turn into payload in 1.21.1 - use Frontiers & That One Caving Mod as reference
public class WormouthRegS2CPacket {
    public static final ResourceLocation ID = RunicLib.customid(DungeonsDelight.MOD_ID, "wormouth_reg_sync");

    public final Map<ResourceLocation, WormouthMapping> items;
    public final Map<ResourceLocation, WormouthMapping> tagItems;

    public WormouthRegS2CPacket(Map<ResourceLocation, WormouthMapping> items, Map<ResourceLocation, WormouthMapping> tagItems) {
        this.items = items;
        this.tagItems = tagItems;
    }

    public void encode (FriendlyByteBuf buf) {
        buf.writeInt(this.items.size());
        buf.writeInt(this.tagItems.size());
        for (Map.Entry<ResourceLocation, WormouthMapping> def : this.items.entrySet()) {
            buf.writeJsonWithCodec(ResourceLocation.CODEC, def.getKey());
            buf.writeJsonWithCodec(WormouthMapping.CODEC, def.getValue());
        }
        for (Map.Entry<ResourceLocation, WormouthMapping> def : this.tagItems.entrySet()) {
            buf.writeJsonWithCodec(ResourceLocation.CODEC, def.getKey());
            buf.writeJsonWithCodec(WormouthMapping.CODEC, def.getValue());
        }
    }

    public static WormouthRegS2CPacket decode(FriendlyByteBuf buf) {
        int sizeItem = buf.readInt();
        int sizeTag = buf.readInt();

        Map<ResourceLocation, WormouthMapping> item = new HashMap<>(sizeItem);
        Map<ResourceLocation, WormouthMapping> tag = new HashMap<>(sizeTag);
        for (int i = 0; i < sizeItem; i++) {
            ResourceLocation id = buf.readJsonWithCodec(ResourceLocation.CODEC);
            WormouthMapping def = buf.readJsonWithCodec(WormouthMapping.CODEC);
            item.put(id, def);
        }
        for (int i = 0; i < sizeTag; i++) {
            ResourceLocation id = buf.readJsonWithCodec(ResourceLocation.CODEC);
            WormouthMapping def = buf.readJsonWithCodec(WormouthMapping.CODEC);
            tag.put(id, def);
        }
        return new WormouthRegS2CPacket(item, tag);
    }

    public void handle() {
        if (Services.PLATFORM.isClient()) {
            WormouthMappings.clear();
            WormouthMappings.MAPS.putAll(this.items);
            WormouthMappings.TAG_MAPS.putAll(this.tagItems);
        }
    }
}
