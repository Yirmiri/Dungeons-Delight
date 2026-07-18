package net.yirmiri.dungeonsdelight.common.resources.crop_rotting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public record CropRottingMapping(Optional<ResourceLocation> block, Optional<ResourceLocation> rottenBlock) {
    public static final Codec<CropRottingMapping> CODEC = RecordCodecBuilder.create((inst) -> inst.group(
            ResourceLocation.CODEC.optionalFieldOf("block").forGetter(CropRottingMapping::block),
            ResourceLocation.CODEC.optionalFieldOf("rotten_block").forGetter(CropRottingMapping::rottenBlock)
            ).apply(inst, CropRottingMapping::new)
    );
}
