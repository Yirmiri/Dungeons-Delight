package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class EffectTooltipItem extends Item {
    private final boolean hasEffectTooltip;

    public EffectTooltipItem(boolean hasEffectTooltip, Item.Properties properties) {
        super(properties);
        this.hasEffectTooltip = hasEffectTooltip;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        if (stack.getItem().getFoodProperties() != null && hasEffectTooltip && DungeonsDelight.CONFIG.getStatusEffectTooltips()) {
            if (DungeonsDelight.CONFIG.getShowChanceTooltips()) {
                DDUtil.addEffectTooltipWithChance(stack.getItem().getFoodProperties(), tooltipComponents, 1.0F);
            } else {
                DDUtil.addEffectTooltip(stack.getItem().getFoodProperties(), tooltipComponents, 1.0F);
            }
        }
    }
}
