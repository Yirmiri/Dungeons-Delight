package net.yirmiri.dungeonsdelight.common.enchantment.cleaver;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class ReboundEnchantment extends AbstractCleaverEnchantment {
    public ReboundEnchantment(Enchantment.Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 1 + (enchantmentLevel - 1) * 10;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 15;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }
}

