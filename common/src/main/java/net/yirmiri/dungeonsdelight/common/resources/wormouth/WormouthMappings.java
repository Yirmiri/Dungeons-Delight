package net.yirmiri.dungeonsdelight.common.resources.wormouth;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.core.init.DDLootTables;

import java.util.HashMap;
import java.util.Map;

public class WormouthMappings {
    public static final Map<ResourceLocation, WormouthMapping> MAPS = new HashMap<>();
    public static final Map<ResourceLocation, WormouthMapping> TAG_MAPS = new HashMap<>();

    public static void clear() {
        MAPS.clear();
        TAG_MAPS.clear();
    }

    public static Pair<ResourceLocation, Boolean> test(ItemStack stack) {
        //ITEMS
        for (Map.Entry<ResourceLocation, WormouthMapping> entry : MAPS.entrySet())
        {
            if (entry.getValue().item().isPresent())
            {
                Item item = BuiltInRegistries.ITEM.get(entry.getValue().item().get());
                if (stack.is(item)) return Pair.of(entry.getValue().table(), entry.getValue().shouldExhaust());
            }
        }
        //TAGS
        for (Map.Entry<ResourceLocation, WormouthMapping> entrytags : TAG_MAPS.entrySet())
        {
            if (entrytags.getValue().tag().isPresent())
            {
                TagKey<Item> key = entrytags.getValue().tag().get();
                if (stack.is(key)) return Pair.of(entrytags.getValue().table(), entrytags.getValue().shouldExhaust());
            }
        }
        //BASIC FOODS (or null)
        if (stack.getItem().getFoodProperties() != null) return Pair.of(DDLootTables.WORMOUTH_GENERIC, true);
        else return null;
    }
}