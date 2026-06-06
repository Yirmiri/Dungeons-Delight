package net.yirmiri.dungeonsdelight.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

//Making a new enchantment category will make me jump a bridge so this is the next best answer
public abstract class AbstractCleaverEnchantment extends Enchantment {
    public AbstractCleaverEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof CleaverItem || stack.is(DDTags.ItemT.CLEAVERS);
    }
}
