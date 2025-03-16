package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class EXPCandiedFoodItem extends EXPFoodItem {
    private final boolean hasFoodEffectTooltip;
    private final boolean hasCustomTooltip;
    private final boolean smallFood;

    public EXPCandiedFoodItem(Properties properties, int experience, boolean hasFoodEffectTooltip, boolean hasCustomTooltip, boolean smallFood) {
        super(properties, experience, hasFoodEffectTooltip);
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        this.hasCustomTooltip = hasCustomTooltip;
        this.smallFood = smallFood;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);

        if (!level.isClientSide && living instanceof Player player) {
            player.playSound(SoundEvents.SCULK_CATALYST_BLOOM);
            if (smallFood) {
                player.heal(2);
            } else player.heal(4);
        }
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        if (smallFood) {
            return 32;
        } else return 64;
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            if (this.hasCustomTooltip) {
                MutableComponent textEmpty = TextUtils.getTranslation("tooltip." + this);
                tooltip.add(textEmpty.withStyle(ChatFormatting.BLUE));
            }

            if (this.hasFoodEffectTooltip) {
                TextUtils.addFoodEffectTooltip(stack, tooltip, 1.0F);
            }
        }
    }
}
