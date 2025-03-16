package net.yirmiri.dungeonsdelight.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class EXPFoodItem extends ConsumableItem {
    private final int experience;

    public EXPFoodItem(Properties properties, int experience, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
        this.experience = experience;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
            player.giveExperiencePoints(experience + player.level().random.nextInt((int) (experience * 1.33)));
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
        }
        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (stack.is(DDItems.SCULK_MAYO.get())) {
            return UseAnim.DRINK;
        }
        return UseAnim.EAT;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            String translationKey = experience < 6 ? "tooltip.small_xp" : experience < 15 ? "tooltip.average_xp" : "tooltip.large_xp";
            tooltip.add(TextUtils.getTranslation(translationKey).withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, level, tooltip, isAdvanced);
        }
    }
}
