package net.yirmiri.dungeonsdelight.common.resources.cleaving_board;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CleavingBoardMappings {
    public static final Map<ResourceLocation, CleavingBoardMapping> MAPS = new HashMap<>();
    public static final Map<ResourceLocation, CleavingBoardMapping> TAG_MAPS = new HashMap<>();

    public static void clear() {
        MAPS.clear();
        TAG_MAPS.clear();
    }

    public static Pair<ResourceLocation, Integer> test(ItemStack stack) {
        //ITEMS
        for (Map.Entry<ResourceLocation, CleavingBoardMapping> entry : MAPS.entrySet()) {
            if (entry.getValue().item().isPresent()) {
                Item item = BuiltInRegistries.ITEM.get(entry.getValue().item().get());
                if (stack.is(item)) {
                    CleavingBoardMapping val = entry.getValue();
                    return Pair.of(val.table(), val.expGrant());
                }
            }
        }

        //TAGS
        for (Map.Entry<ResourceLocation, CleavingBoardMapping> entrytags : TAG_MAPS.entrySet()) {
            if (entrytags.getValue().tag().isPresent()) {
                TagKey<Item> key = entrytags.getValue().tag().get();
                if (stack.is(key)) {
                    CleavingBoardMapping val = entrytags.getValue();
                    return Pair.of(val.table(), val.expGrant());
                }
            }
        }

        return null;
    }
}
