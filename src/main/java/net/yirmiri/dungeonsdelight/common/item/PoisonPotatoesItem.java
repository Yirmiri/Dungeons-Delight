package net.yirmiri.dungeonsdelight.common.item;

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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class PoisonPotatoesItem extends EXPFoodItem {
    private final int experience;

    public PoisonPotatoesItem(Properties properties, int experience, boolean hasFoodEffectTooltip) {
        super(properties, experience, hasFoodEffectTooltip);
        this.experience = experience;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
            if (player.hasEffect(MobEffects.POISON)) {
                player.removeEffect(MobEffects.POISON);
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, (experience * 20), 0));
                player.playSound(SoundEvents.ZOMBIE_VILLAGER_CURE, 0.5F, 1.0F);
            }
        }
        return stack;
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            String translationKey = experience < 6 ? "tooltip.small_xp_poison_potato" : experience < 15 ? "tooltip.average_xp_poison_potato" : "tooltip.large_xp_poison_potato";
            tooltip.add(TextUtils.getTranslation(translationKey).withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, level, tooltip, isAdvanced);
        }
    }
}
