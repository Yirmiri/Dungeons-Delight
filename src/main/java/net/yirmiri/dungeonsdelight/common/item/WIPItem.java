package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class WIPItem extends Item {
    public WIPItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        tooltip.add(TextUtils.getTranslation("tooltip.wip").withStyle(ChatFormatting.RED));
        super.appendHoverText(stack, ctx, tooltip, isAdvanced);
    }
}
