package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.enchantment.DartingEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.RicochetEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.SerratedStrikeEnchantment;

import java.util.function.Supplier;
//i miss non datadriven enchantments :(
// ...(after working with 1.20 enchantments maybe not anymore lol)
public class DDEnchantments {
    //CLEAVER ENCHANTMENTS
    public static final Supplier<Enchantment> RICOCHET = register("ricochet", () -> new
            RicochetEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.CROSSBOW, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final Supplier<Enchantment> SERRATED_STRIKE = register("serrated_strike", () -> new
            SerratedStrikeEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.CROSSBOW, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final Supplier<Enchantment> DARTING = register("darting", () -> new
            DartingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.CROSSBOW, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static Supplier<Enchantment> register(String id, Supplier<Enchantment> supplier) {
        return Services.REGISTRY.registerEnchantment(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
