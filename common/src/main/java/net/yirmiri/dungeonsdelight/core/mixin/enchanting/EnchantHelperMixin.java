package net.yirmiri.dungeonsdelight.core.mixin.enchanting;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.yirmiri.dungeonsdelight.core.init.DDEnchantCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public class EnchantHelperMixin {
    @WrapOperation(method = "getAvailableEnchantmentResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentCategory;canEnchant(Lnet/minecraft/world/item/Item;)Z"))
    private static boolean dundelight$inwithstandEnch(EnchantmentCategory instance, Item item, Operation<Boolean> original, @Local Enchantment enchantment) {
        return original.call(instance, item) || DDEnchantCategory.customEnchantPasser(enchantment, item);
    }
}