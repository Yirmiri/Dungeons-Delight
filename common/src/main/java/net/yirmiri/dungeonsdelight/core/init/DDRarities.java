package net.yirmiri.dungeonsdelight.core.init;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

public class DDRarities {
    static {
        Rarity.values();
    }

    public static final String MONSTER_STRING = "DUNGEONSDELIGHT_MONSTER";
    public static Rarity MONSTER;

    public static MutableComponent tryToAppendOrReplace(MutableComponent existing, ItemStack stack) {
        if (stack.getRarity() == MONSTER) {
            Style updated = existing.getStyle().withColor(TextColor.fromRgb(DDUtil.MONSTER_COLOR));
            return existing.withStyle(updated);
        }
        else return existing;
    }

    // FOR 1.21
    //public static MutableComponent tryToAppendOrReplace(MutableComponent existing, ItemStack stack) {
    //    if (stack.getRarity() == MONSTER) {
    //        return existing.withColor(DDUtil.MONSTER_COLOR);
    //    }
    //    else return existing;
    //}
}
