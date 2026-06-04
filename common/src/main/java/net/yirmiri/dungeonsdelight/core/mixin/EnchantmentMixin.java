package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
    @Unique
    Enchantment enchantment = (Enchantment) (Object) this;
//Allows cleavers to accept vanilla enchantments, not a perfect solution but it works,,, i miss datadriven enchantments
    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$canEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (
                //NOT ON AXES
                enchantment == Enchantments.FIRE_ASPECT || enchantment == Enchantments.MOB_LOOTING
                //AXE ENCHANTMENTS
                        || enchantment == Enchantments.SHARPNESS || enchantment == Enchantments.BANE_OF_ARTHROPODS || enchantment == Enchantments.SMITE
                        || enchantment == Enchantments.BLOCK_FORTUNE || enchantment == Enchantments.SILK_TOUCH || enchantment == Enchantments.BLOCK_EFFICIENCY
                        || enchantment == Enchantments.MENDING || enchantment == Enchantments.UNBREAKING
    ) {
            cir.setReturnValue(stack.is(DDTags.ItemT.CLEAVERS));
        }
    }
}