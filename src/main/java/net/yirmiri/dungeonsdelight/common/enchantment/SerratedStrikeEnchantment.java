package net.yirmiri.dungeonsdelight.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SerratedStrikeEnchantment extends Enchantment {
    public SerratedStrikeEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public float getDamageBonus(int level, MobType mobType) {
        return -1.0F;
    }
}
