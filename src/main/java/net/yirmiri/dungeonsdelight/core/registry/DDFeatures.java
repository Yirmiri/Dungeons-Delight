package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.worldgen.feature.wormroot.WormrootFeature;

import java.util.function.Supplier;

public class DDFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, DungeonsDelight.MOD_ID);

    public static final Supplier<WormrootFeature> WORMROOT = FEATURES.register("wormroot", () -> new WormrootFeature(MultifaceGrowthConfiguration.CODEC));
}
