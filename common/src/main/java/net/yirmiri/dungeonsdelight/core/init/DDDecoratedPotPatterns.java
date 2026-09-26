package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.Map;

// TODO: 1.21.1 replaces all inst of ResourceKey<String> with ResourceKey<DecoratedPotPattern>. See DecoratedPotPatternsMixin.
public class DDDecoratedPotPatterns {
    private static Map<Item, ResourceKey<String>> ITEM_TO_POT_TEXTURE;

    // TODO: Remove "_pottery_pattern" suffix from resourcekeys in 1.21.1
    public static final ResourceKey<String> GLUTTONY = create("gluttony_pottery_pattern");
    public static final ResourceKey<String> TITANIC = create("titanic_pottery_pattern");

    private static ResourceKey<String> create(String id) {
        return ResourceKey.create(Registries.DECORATED_POT_PATTERNS, RunicLib.customid(DungeonsDelight.MOD_ID, id));
    }

    public static String register(Registry<String> registry, ResourceKey<String> resourceKey, String id) {
        return Registry.register(registry, resourceKey, id);
    }

    public static Map<Item, ResourceKey<String>> map() { return ITEM_TO_POT_TEXTURE; }

    public static void load() {

    }

    public static void postLoad() {
        ITEM_TO_POT_TEXTURE = Map.ofEntries(
                Map.entry(DDItems.GLUTTONY_POTTERY_SHERD.get(), GLUTTONY),
                Map.entry(DDItems.TITANIC_POTTERY_SHERD.get(), TITANIC)
        );
    }
}
