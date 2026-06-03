package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class DDFoodItem extends Item {
    private final boolean hasEffectTooltip;

    public DDFoodItem(boolean hasEffectTooltip, Properties properties) {
        super(properties);
        this.hasEffectTooltip = hasEffectTooltip;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (stack.getItem().getFoodProperties() != null && hasEffectTooltip) {
            DDUtil.addEffectTooltip(stack.getItem().getFoodProperties(), tooltip, 1.0F);
        }
    }
}
