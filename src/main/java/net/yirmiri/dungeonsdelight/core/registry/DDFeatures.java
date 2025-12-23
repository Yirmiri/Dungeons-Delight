package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.worldgen.feature.rotten_monster_room.RottenMonsterRoomFeature;
import net.yirmiri.dungeonsdelight.common.worldgen.feature.wormroot.WormrootFeature;

import java.util.function.Supplier;

public class DDFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, DungeonsDelight.MOD_ID);

    public static final Supplier<WormrootFeature> WORMROOT = FEATURES.register("wormroot", () -> new WormrootFeature(MultifaceGrowthConfiguration.CODEC));
    public static final Supplier<RottenMonsterRoomFeature> ROTTEN_MONSTER_ROOM = FEATURES.register("rotten_monster_room", () -> new RottenMonsterRoomFeature(NoneFeatureConfiguration.CODEC));
}
