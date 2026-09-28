package net.yirmiri.dungeonsdelight.common.resources.cleaving_board;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Optional;

public record CleavingBoardMapping(
        Optional<ResourceKey<Item>> item,
        Optional<TagKey<Item>> tag,
        ResourceLocation table,
        int expGrant
) {
    public static final String TAG = "tag";
    public static final String ITEM = "item";
    public static final String LOOT = "table";
    public static final String EXP = "experience";

    public static final Codec<CleavingBoardMapping> CODEC = RecordCodecBuilder.create((inst) -> inst.group(
                    Codec.optionalField(ITEM, ResourceKey.codec(Registries.ITEM)).forGetter(CleavingBoardMapping::item),
                    Codec.optionalField(TAG, TagKey.codec(Registries.ITEM)).forGetter(CleavingBoardMapping::tag),
                    ResourceLocation.CODEC.fieldOf(LOOT).forGetter(CleavingBoardMapping::table),
                    Codec.INT.fieldOf(EXP).forGetter(CleavingBoardMapping::expGrant)
            ).apply(inst, CleavingBoardMapping::new)
    );

    // TODO: WILL NEED STREAM CODEC IN 1.21.1 OF <RegistryFriendlyByteBuf, CleavingBoardMapping>
}