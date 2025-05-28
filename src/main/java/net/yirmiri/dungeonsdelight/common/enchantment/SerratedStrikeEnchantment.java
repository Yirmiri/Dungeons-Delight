//package net.yirmiri.dungeonsdelight.common.enchantment;
//
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.entity.MobType;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//import net.minecraft.world.item.enchantment.Enchantments;
//import net.yirmiri.dungeonsdelight.core.registry.DDEnchantments;
//
//public class SerratedStrikeEnchantment extends Enchantment {
//    public SerratedStrikeEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
//        super(rarity, category, slots);
//    }
//
//    @Override
//    public int getMinLevel() {
//        return 1;
//    }
//
//    @Override
//    public int getMaxLevel() {
//        return 1;
//    }
//
//    @Override
//    public boolean checkCompatibility(Enchantment enchantment) {
//        return super.checkCompatibility(enchantment) && enchantment != DDEnchantments.PERSISTENCE.get();
//    }
//}
