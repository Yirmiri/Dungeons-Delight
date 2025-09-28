package net.yirmiri.dungeonsdelight.common.item;

import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class EchoRockCandyItem extends BiteableItem {
    public EchoRockCandyItem(Properties properties, int stackSize, boolean hasPotionEffectTooltip) {
        super(properties, stackSize, hasPotionEffectTooltip);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
            player.giveExperiencePoints(4 + player.level().random.nextInt((int) (4 * 1.33)));
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
        }
        if (!level.isClientSide && living instanceof Player player) {
            if (player.hasEffect(MobEffects.BLINDNESS)) {
                player.removeEffect(MobEffects.BLINDNESS);
                player.removeEffect(RLMobEffects.PERCEPTION);
                player.playSound(SoundEvents.ZOMBIE_VILLAGER_CURE, 0.5F, 1.0F);
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            String translationKey = "tooltip.average_xp_bite";
            tooltip.add(TextUtils.getTranslation(translationKey).withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, ctx, tooltip, isAdvanced);
        }
    }
}
