package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDStats {
    public static final Identifier DASHES = register("dashes", StatFormatter.DEFAULT);

    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = Identifier.of(DungeonsDelight.MOD_ID, id);
        Registry.register(Registries.CUSTOM_STAT, id, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }

    public static void loadStats() {
    }
}
