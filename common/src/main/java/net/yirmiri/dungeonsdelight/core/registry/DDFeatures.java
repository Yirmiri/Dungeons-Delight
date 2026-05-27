package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.worldgen.feature.wormroot.WormrootFeature;

import java.util.function.Supplier;

public class DDFeatures {
    public static final Supplier<WormrootFeature> WORMROOT = register("wormroot", () -> new WormrootFeature(MultifaceGrowthConfiguration.CODEC));

    private static <T extends FeatureConfiguration, R extends Feature<T>> Supplier<R> register(String name, Supplier<R> feature) {
        return Services.REGISTRY.register((Registry<R>)BuiltInRegistries.FEATURE, DungeonsDelight.MOD_ID, name, feature);
    }

    public static void load() {}
}
