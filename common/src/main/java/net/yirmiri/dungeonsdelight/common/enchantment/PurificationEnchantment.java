package net.yirmiri.dungeonsdelight.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.core.init.DDMobTypes;

public class PurificationEnchantment extends Enchantment {
    public PurificationEnchantment(Rarity rarity, EquipmentSlot... applicableSlots) {
        super(rarity, EnchantmentCategory.WEAPON, applicableSlots);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 5 + (enchantmentLevel - 1) * 8;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public float getDamageBonus(int level, MobType mobType) {
        if (mobType == DDMobTypes.ROTTEN) {
            return level * 2.0F;
        }
        return super.getDamageBonus(level, mobType);
    }

    @Override
    public boolean checkCompatibility(Enchantment other) {
        return !(other instanceof DamageEnchantment) && super.checkCompatibility(other);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof AxeItem || stack.getItem() instanceof CleaverItem || super.canEnchant(stack);
    }
}
