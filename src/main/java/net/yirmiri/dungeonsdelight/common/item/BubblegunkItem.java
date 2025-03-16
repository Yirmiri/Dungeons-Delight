package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class BubblegunkItem extends BiteableItem {
    public BubblegunkItem(Properties properties, TagKey<Item> repairItem, boolean hasPotionEffectTooltip) {
        super(properties, repairItem, hasPotionEffectTooltip);
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.bubblegunk").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, level, tooltip, isAdvanced);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
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
