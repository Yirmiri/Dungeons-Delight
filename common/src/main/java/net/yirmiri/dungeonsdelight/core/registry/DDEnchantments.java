package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.enchantment.ReapingEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.RicochetEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.SerratedStrikeEnchantment;

import java.util.function.Supplier;
//i miss non datadriven enchantments :(
// ...(maybe not anymore lol)
public class DDEnchantments {
    //todo EnchantmentCategory for CLEAVER (temp using VANISHABLE) also make cleavers accept any sword enchantment

    //CLEAVER ENCHANTMENTS
    public static final Supplier<Enchantment> RICOCHET = register("ricochet", () -> new
            RicochetEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.VANISHABLE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final Supplier<Enchantment> SERRATED_STRIKE = register("serrated_strike", () -> new
            SerratedStrikeEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.VANISHABLE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final Supplier<Enchantment> REAPING = register("reaping", () -> new
            ReapingEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static Supplier<Enchantment> register(String id, Supplier<Enchantment> supplier) {
        return RLServices.REGISTRY.registerEnchantment(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
