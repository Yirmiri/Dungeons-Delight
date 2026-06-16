package net.yirmiri.dungeonsdelight.core.mixin.enchanting;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnchantmentHelper.class)
public class EnchantHelperMixin { //crash with forge
//    @WrapOperation(method = "getAvailableEnchantmentResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentCategory;canEnchant(Lnet/minecraft/world/item/Item;)Z"))
//    private static boolean dundelight$inwithstandEnch(EnchantmentCategory instance, Item item, Operation<Boolean> original, @Local Enchantment enchantment) {
//        return original.call(instance, item) || DDEnchantCategory.customEnchantPasser(enchantment, item);
//    }
}