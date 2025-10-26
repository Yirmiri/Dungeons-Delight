package net.yirmiri.dungeonsdelight.common.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.item.food_type.BiteableItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class AURottenPotatoesItem extends BiteableItem {
    public AURottenPotatoesItem(Properties properties, int stackSize, boolean hasPotionEffectTooltip) {
        super(properties, stackSize, hasPotionEffectTooltip);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
            DDUtil.echoBlastSmall(level, player);

            if (player.hasEffect(MobEffects.POISON)) {
                player.removeEffect(MobEffects.POISON);
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, (60), 0));
                player.playSound(SoundEvents.ZOMBIE_VILLAGER_CURE, 0.5F, 1.0F);
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            String translationKey = "tooltip.sculk_level_" + 1;
            tooltip.add(TextUtils.getTranslation(translationKey).withStyle(ChatFormatting.BLUE));
        }
        super.appendHoverText(stack, ctx, tooltip, isAdvanced);
    }
}
