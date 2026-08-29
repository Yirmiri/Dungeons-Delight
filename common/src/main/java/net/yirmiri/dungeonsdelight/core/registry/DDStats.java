package net.yirmiri.dungeonsdelight.core.registry;

import com.mojang.datafixers.util.Pair;
import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DDStats {
    private static final List<Pair<Supplier<ResourceLocation>, StatFormatter>> CUSTOMS = new ArrayList<>();

    public static final Supplier<ResourceLocation> INTERACT_WITH_MONSTER_POT = makeCustomStat("interact_with_monster_pot", StatFormatter.DEFAULT);
    public static final Supplier<ResourceLocation> ITEMS_CUT_WITH_CLEAVING_BOARD = makeCustomStat("interact_with_cleaving_board", StatFormatter.DEFAULT);
    public static final Supplier<ResourceLocation> ITEMS_SPIKED = makeCustomStat("items_spiked", StatFormatter.DEFAULT);
    public static final Supplier<ResourceLocation> MOBS_ENCASED_WITH_ROCK_CANDY = makeCustomStat("mobs_encased_with_rock_candy", StatFormatter.DEFAULT);
    public static final Supplier<ResourceLocation> HOMEWARD = makeCustomStat("homeward", StatFormatter.DEFAULT);
    public static final Supplier<ResourceLocation> EXPERIENCE_STORED = makeCustomStat("experience_stored", StatFormatter.DEFAULT);
    public static final Supplier<ResourceLocation> CLEAVERS_THROWN = makeCustomStat("cleavers_thrown", StatFormatter.DEFAULT);

    public static void load() {
    }

    private static Supplier<ResourceLocation> makeCustomStat(String key, StatFormatter formatter) {
        Supplier<ResourceLocation> rlc = () -> RunicLib.customid(DungeonsDelight.MOD_ID, key);
        Supplier<ResourceLocation> ret = Services.REGISTRY.register(BuiltInRegistries.CUSTOM_STAT, DungeonsDelight.MOD_ID, key, rlc);
        CUSTOMS.add(Pair.of(ret, formatter));
        return ret;
    }

    // Done in post due to platform limitations - Artyrian
    public static void finalizeCustomStats() {
        for (Pair<Supplier<ResourceLocation>, StatFormatter> resc : CUSTOMS) {
            Stats.CUSTOM.get(resc.getFirst().get(), resc.getSecond());
        }
    }
}
