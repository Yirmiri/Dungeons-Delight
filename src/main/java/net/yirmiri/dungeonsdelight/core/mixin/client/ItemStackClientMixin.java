package net.yirmiri.dungeonsdelight.core.mixin.client;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackClientMixin {

    @Shadow public abstract Item getItem();

    @Inject(method = "getTooltipLines", at = @At(value = "TAIL"))
    public void dungeonsDelight$getTooltipLines(Player player, TooltipFlag flag, CallbackInfoReturnable<List<Component>> cir) {
        if (this.getItem() instanceof CleaverItem cleaverItem) {
            List<Component> tooltip = cir.getReturnValue();
            int insertIndex = tooltip.size();
            if (flag.isAdvanced() && !tooltip.isEmpty()) {
                insertIndex = tooltip.size() - 3;
            }
            tooltip.add(insertIndex, Component.literal(" " + cleaverItem.range)
                    .append(CommonComponents.space()).append(Component.translatable("dungeonsdelight.tooltip.attribute.range")).withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}

