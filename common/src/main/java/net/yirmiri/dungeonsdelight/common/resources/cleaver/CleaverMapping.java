package net.yirmiri.dungeonsdelight.common.resources.cleaver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.Optional;

public record CleaverMapping(Optional<ResourceKey<EntityType<?>>> entityType, Optional<TagKey<EntityType<?>>> tag, ResourceLocation table) {
    public static final Codec<CleaverMapping> CODEC = RecordCodecBuilder.create((inst) -> inst.group(
                    Codec.optionalField("entity_type", ResourceKey.codec(Registries.ENTITY_TYPE)).forGetter(CleaverMapping::entityType),
                    Codec.optionalField("tag", TagKey.codec(Registries.ENTITY_TYPE)).forGetter(CleaverMapping::tag),
                    ResourceLocation.CODEC.fieldOf("table").forGetter(CleaverMapping::table)
            ).apply(inst, CleaverMapping::new)
    );
}
