package net.yirmiri.dungeonsdelight.core.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.core.registry.DDEnchantments;

import java.util.List;

public class DDEnchantCategory {
    static {
        EnchantmentCategory.values();
    }

    public static final List<Enchantment> CLEAVER_VANILLA_ENCHS = List.of(
            //NOT ON AXES
            Enchantments.FIRE_ASPECT,
            Enchantments.MOB_LOOTING,
            //AXE ENCHANTMENTS
            Enchantments.SHARPNESS,
            Enchantments.BANE_OF_ARTHROPODS,
            Enchantments.SMITE,
            Enchantments.BLOCK_FORTUNE,
            Enchantments.SILK_TOUCH,
            Enchantments.BLOCK_EFFICIENCY,
            Enchantments.MENDING,
            Enchantments.UNBREAKING
    );

    public static boolean customEnchantPasser(Enchantment enchantment, Item item) {
        if (CLEAVER_VANILLA_ENCHS.contains(enchantment) && item instanceof CleaverItem) {
            return true;
        }
        return false;
    }
}
