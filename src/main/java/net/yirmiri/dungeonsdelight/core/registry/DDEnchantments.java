package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.enchantment.LifeGraspEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.PersistenceEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.RicochetEnchantment;
import net.yirmiri.dungeonsdelight.common.enchantment.SerratedStrikeEnchantment;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.common.item.StainedCleaverItem;
import net.yirmiri.dungeonsdelight.common.item.StainedKnifeItem;

public class DDEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, DungeonsDelight.MOD_ID);
    public static final EnchantmentCategory CLEAVER = EnchantmentCategory.create("cleaver", (item) -> item instanceof CleaverItem);
    public static final EnchantmentCategory STAINED = EnchantmentCategory.create("stained", (item) -> item instanceof StainedKnifeItem || item instanceof StainedCleaverItem);

    //CLEAVER ENCHANTMENTS
    public static final RegistryObject<Enchantment> RICOCHET = ENCHANTMENTS.register("ricochet", () -> new
            RicochetEnchantment(Enchantment.Rarity.UNCOMMON, CLEAVER, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final RegistryObject<Enchantment> SERRATED_STRIKE = ENCHANTMENTS.register("serrated_strike", () -> new
            SerratedStrikeEnchantment(Enchantment.Rarity.COMMON, CLEAVER, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final RegistryObject<Enchantment> PERSISTENCE = ENCHANTMENTS.register("persistence", () -> new
            PersistenceEnchantment(Enchantment.Rarity.UNCOMMON, CLEAVER, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

//    public static final RegistryObject<Enchantment> RETRACTION = ENCHANTMENTS.register("retraction", () -> new
//            RetractionEnchantment(Enchantment.Rarity.UNCOMMON, CLEAVER, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final RegistryObject<Enchantment> LIFE_GRASP = ENCHANTMENTS.register("life_grasp", () -> new
            LifeGraspEnchantment(Enchantment.Rarity.RARE, STAINED, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
}
