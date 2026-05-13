package net.yirmiri.dungeonsdelight.common.worldgen.feature;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.WormrootTendrilsBlock;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDFeatures;

import java.util.List;

public class DDFeaturesConfigured {
    public static final ResourceKey<ConfiguredFeature<?, ?>> WORMOUTH_KEY = registerKey("wormouth");

    // TODO 1.21.1 - W "BOOTSTAP"
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(
                context,
                WORMOUTH_KEY,
                DDFeatures.WORMROOT.get(),
                new MultifaceGrowthConfiguration(
                        (WormrootTendrilsBlock)DDBlocks.WORMROOT_TENDRILS.get(),
                        20,
                        true,
                        true,
                        true,
                        0.9F,
                        HolderSet.direct(
                                BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.STONE),
                                BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.ANDESITE),
                                BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.DIORITE),
                                BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.GRANITE),
                                BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.DRIPSTONE_BLOCK),
                                BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.CALCITE),
                                BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.TUFF),
                                BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.DEEPSLATE),
                                BuiltInRegistries.BLOCK.wrapAsHolder(DDBlocks.WORMROOTS_BLOCK.get())
                        )
                )
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, RunicLib.customid(DungeonsDelight.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register (BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
