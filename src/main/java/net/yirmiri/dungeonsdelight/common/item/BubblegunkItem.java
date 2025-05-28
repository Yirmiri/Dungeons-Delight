package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class BubblegunkItem extends BiteableItem {
    private final boolean hasFoodEffectTooltip;

    public BubblegunkItem(Properties properties, boolean hasPotionEffectTooltip) {
        super(properties, hasPotionEffectTooltip);
        this.hasFoodEffectTooltip = hasPotionEffectTooltip;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.bubblegunk").withStyle(ChatFormatting.BLUE));
            if (this.hasFoodEffectTooltip) {
                TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0F, ctx.tickRate());
            }
        }
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_BLOCK_STEP;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_BLOCK_STEP;
    }
}
