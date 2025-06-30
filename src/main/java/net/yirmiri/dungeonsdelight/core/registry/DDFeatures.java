package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.worldgen.feature.wormroot.WormrootFeature;

public class DDFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, DungeonsDelight.MOD_ID);

    public static final RegistryObject<WormrootFeature> WORMROOT = FEATURES.register("wormroot", () -> new WormrootFeature(MultifaceGrowthConfiguration.CODEC));
}
