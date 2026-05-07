package net.yirmiri.dungeonsdelight.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true)
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Rarity getRarity();

    @ModifyReturnValue(method = "getDisplayName", at = @At(value = "RETURN"))
    private Component dundelight$appendRarityColor(Component original) {
        return DDRarities.tryToAppendOrReplace((MutableComponent)original, (ItemStack)(Object)this);
    }

    @ModifyVariable(method = "getTooltipLines", at = @At("STORE"), ordinal = 0)
    private MutableComponent dundelight$trySetOfKindColor(MutableComponent value) {
        return DDRarities.tryToAppendOrReplace(value, (ItemStack)(Object)this);
    }
}