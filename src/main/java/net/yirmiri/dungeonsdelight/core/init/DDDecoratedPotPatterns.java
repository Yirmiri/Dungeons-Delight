package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDDecoratedPotPatterns {
    public static final ResourceKey<DecoratedPotPattern> GLUTTONY = create("gluttony");

    private static ResourceKey<DecoratedPotPattern> create(String id) {
        return ResourceKey.create(Registries.DECORATED_POT_PATTERN, RunicLib.customid(DungeonsDelight.MOD_ID, id));
    }

    public static DecoratedPotPattern register(Registry<DecoratedPotPattern> registry, ResourceKey<DecoratedPotPattern> resourceKey, String id) {
        return Registry.register(registry, resourceKey, new DecoratedPotPattern(RunicLib.customid(DungeonsDelight.MOD_ID, id)));
    }
}
