package net.yirmiri.dungeonsdelight.common.worldgen.feature;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.List;

public class DDFeaturesPlaced {
    public static final ResourceKey<PlacedFeature> WORMOUTH_PLACED_KEY = registerKey("wormouth_placed");

    // TODO 1.21.1 - IMAGINE BEING OWNED BY A MULTI BILLION DOLLAR COMPANY AND SPELLING BOOTSTRAP "BOOTSTAP"
    public static void bootstrap(BootstapContext<PlacedFeature> context){
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, WORMOUTH_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(DDFeaturesConfigured.WORMOUTH_KEY),
                List.of(
                        CountPlacement.of(new WeightedListInt(
                                SimpleWeightedRandomList.<IntProvider>builder()
                                        .add(ConstantInt.of(1), 2)
                                        .add(ConstantInt.of(0), 2)
                                        .build()
                                )
                        ),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(
                                VerticalAnchor.aboveBottom(-48),
                                VerticalAnchor.absolute(32))
                        ),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.matchesTag(BlockTags.BASE_STONE_OVERWORLD),
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                6
                        ),
                        BiomeFilter.biome()
                )
        );

    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, RunicLib.customid(DungeonsDelight.MOD_ID, name));
    }
}
