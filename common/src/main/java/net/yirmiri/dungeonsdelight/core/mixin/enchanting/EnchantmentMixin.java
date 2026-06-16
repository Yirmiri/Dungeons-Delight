package net.yirmiri.dungeonsdelight.core.mixin.enchanting;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.yirmiri.dungeonsdelight.core.init.DDEnchantCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
    //Allows cleavers to accept vanilla enchantments,,,screw hardcoding - artyrian
    @ModifyReturnValue(method = "canEnchant", at = @At("TAIL"))
    private boolean dungeonsdelight$canEnchant(boolean original, @Local(argsOnly = true) ItemStack stack) {
        if (!original) return DDEnchantCategory.customEnchantPasser((Enchantment)(Object)this, stack.getItem());
        return original;
    }
}