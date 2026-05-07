package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class DDRarity {
    static {
        Rarity.values();
    }

    public static final String MONSTER_STRING = "DUNGEONSDELIGHT_MONSTER";
    public static final int MONSTER_COLOR = 0xC875C2;

    public static Rarity MONSTER;

    public static MutableComponent tryToAppendOrReplace(MutableComponent existing, ItemStack stack) {
        if (stack.getRarity() == MONSTER) {
            Style updated = existing.getStyle().withColor(TextColor.fromRgb(MONSTER_COLOR));
            return existing.withStyle(updated);
        }
        else return existing;
    }

    // FOR 1.21
    //public static MutableComponent tryToAppendOrReplace(MutableComponent existing, ItemStack stack) {
    //    if (stack.getRarity() == MONSTER) {
    //        return existing.withColor(MONSTER_COLOR);
    //    }
    //    else return existing;
    //}
}
