package net.yirmiri.dungeonsdelight.common.resources.wormouth;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Optional;

public record WormouthMapping(
        Optional<ResourceKey<Item>> item,
        Optional<TagKey<Item>> tag,
        ResourceLocation table,
        float closingChance,
        float rancidIncrease,
        int expGrant
) {
    public static final String TAG = "tag";
    public static final String ITEM = "item";
    public static final String LOOT = "table";
    public static final String CLOSING = "closing_chance";
    public static final String RANCID = "rancid_reduction_percent";
    public static final String EXP = "experience";

    public static final Codec<WormouthMapping> CODEC = RecordCodecBuilder.create((inst) -> inst.group(
            Codec.optionalField(ITEM, ResourceKey.codec(Registries.ITEM)).forGetter(WormouthMapping::item),
            Codec.optionalField(TAG, TagKey.codec(Registries.ITEM)).forGetter(WormouthMapping::tag),
            ResourceLocation.CODEC.fieldOf(LOOT).forGetter(WormouthMapping::table),
            Codec.FLOAT.fieldOf(CLOSING).forGetter(WormouthMapping::closingChance),
            Codec.FLOAT.fieldOf(RANCID).forGetter(WormouthMapping::rancidIncrease),
            Codec.INT.fieldOf(EXP).forGetter(WormouthMapping::expGrant)
        ).apply(inst, WormouthMapping::new)
    );

    // TODO: WILL NEED STREAM CODEC IN 1.21.1 OF <RegistryFriendlyByteBuf, WormouthMapping>

    public record Unpacked(ResourceLocation table, float closingChance, float rancidIncrease, int expGrant)
    {
    }
}