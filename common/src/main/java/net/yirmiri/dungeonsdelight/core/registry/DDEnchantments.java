package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.enchantment.PurificationEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.cleaver.DartingEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.cleaver.RicochetEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.cleaver.SerratedStrikeEnchantment;

import java.util.function.Supplier;
//i miss non datadriven enchantments :(
// ...(after working with 1.20 enchantments maybe not anymore lol)
public class DDEnchantments {
    //CLEAVER
    public static final Supplier<Enchantment> RICOCHET = register("ricochet", () -> new
            RicochetEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.CROSSBOW, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final Supplier<Enchantment> SERRATED_STRIKE = register("serrated_strike", () -> new
            SerratedStrikeEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.CROSSBOW, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final Supplier<Enchantment> DARTING = register("darting", () -> new
            DartingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.CROSSBOW, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    //WEAPON
    public static final Supplier<Enchantment> PURIFICATION = register("purification", () -> new
            PurificationEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

    public static Supplier<Enchantment> register(String id, Supplier<Enchantment> supplier) {
        return Services.REGISTRY.registerEnchantment(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
