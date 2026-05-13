package net.yirmiri.dungeonsdelight.data;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.yirmiri.dungeonsdelight.common.worldgen.feature.DDFeaturesPlaced;

public class FabricDDWorldGen {
    public static void generate() {
        // Features
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.VEGETAL_DECORATION, DDFeaturesPlaced.WORMOUTH_PLACED_KEY);
    }
}
