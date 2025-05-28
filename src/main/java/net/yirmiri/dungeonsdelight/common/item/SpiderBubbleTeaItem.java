package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SpiderBubbleTeaItem extends DrinkableItem {
    public SpiderBubbleTeaItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide) {
            if (!living.getActiveEffects().isEmpty()) {
                List<MobEffectInstance> effectList = new ArrayList<>(living.getActiveEffects());
                MobEffectInstance randomEffect = effectList.get(level.getRandom().nextInt(effectList.size()));
                if (randomEffect.getAmplifier() == 0) {
                    living.removeEffect(randomEffect.getEffect());
                    living.addEffect(new MobEffectInstance(randomEffect.getEffect(), randomEffect.getDuration() / 2, 1));
                } else living.addEffect(new MobEffectInstance(DDEffects.POUNCING, 2400, 1));
            } else living.addEffect(new MobEffectInstance(DDEffects.POUNCING, 2400, 1));
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.spider_bubble_tea").withStyle(ChatFormatting.BLUE));
            tooltip.add(TextUtils.getTranslation("tooltip.spider_bubble_tea_pouncing").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, ctx, tooltip, isAdvanced);
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
