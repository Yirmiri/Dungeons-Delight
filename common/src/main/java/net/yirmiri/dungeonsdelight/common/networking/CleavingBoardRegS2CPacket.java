package net.yirmiri.dungeonsdelight.common.networking;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.cleaving_board.CleavingBoardMapping;
import net.yirmiri.dungeonsdelight.common.resources.cleaving_board.CleavingBoardMappings;

import java.util.HashMap;
import java.util.Map;

// TODO: Turn into payload in 1.21.1 - use Frontiers & Subterrous as reference
public class CleavingBoardRegS2CPacket {
    public static final ResourceLocation ID = RunicLib.customid(DungeonsDelight.MOD_ID, "cleaving_board_reg_sync");

    public final Map<ResourceLocation, CleavingBoardMapping> items;
    public final Map<ResourceLocation, CleavingBoardMapping> tagItems;

    public CleavingBoardRegS2CPacket(Map<ResourceLocation, CleavingBoardMapping> items, Map<ResourceLocation, CleavingBoardMapping> tagItems) {
        this.items = items;
        this.tagItems = tagItems;
    }

    public void encode (FriendlyByteBuf buf) {
        buf.writeInt(this.items.size());
        buf.writeInt(this.tagItems.size());
        for (Map.Entry<ResourceLocation, CleavingBoardMapping> def : this.items.entrySet()) {
            buf.writeJsonWithCodec(ResourceLocation.CODEC, def.getKey());
            buf.writeJsonWithCodec(CleavingBoardMapping.CODEC, def.getValue());
        }
        for (Map.Entry<ResourceLocation, CleavingBoardMapping> def : this.tagItems.entrySet()) {
            buf.writeJsonWithCodec(ResourceLocation.CODEC, def.getKey());
            buf.writeJsonWithCodec(CleavingBoardMapping.CODEC, def.getValue());
        }
    }

    public static CleavingBoardRegS2CPacket decode(FriendlyByteBuf buf) {
        int sizeItem = buf.readInt();
        int sizeTag = buf.readInt();

        Map<ResourceLocation, CleavingBoardMapping> item = new HashMap<>(sizeItem);
        Map<ResourceLocation, CleavingBoardMapping> tag = new HashMap<>(sizeTag);
        for (int i = 0; i < sizeItem; i++) {
            ResourceLocation id = buf.readJsonWithCodec(ResourceLocation.CODEC);
            CleavingBoardMapping def = buf.readJsonWithCodec(CleavingBoardMapping.CODEC);
            item.put(id, def);
        }
        for (int i = 0; i < sizeTag; i++) {
            ResourceLocation id = buf.readJsonWithCodec(ResourceLocation.CODEC);
            CleavingBoardMapping def = buf.readJsonWithCodec(CleavingBoardMapping.CODEC);
            tag.put(id, def);
        }
        return new CleavingBoardRegS2CPacket(item, tag);
    }

    public void handle() {
        if (RLServices.PLATFORM.isClient()) {
            CleavingBoardMappings.clear();
            CleavingBoardMappings.MAPS.putAll(this.items);
            CleavingBoardMappings.TAG_MAPS.putAll(this.tagItems);
        }
    }
}
