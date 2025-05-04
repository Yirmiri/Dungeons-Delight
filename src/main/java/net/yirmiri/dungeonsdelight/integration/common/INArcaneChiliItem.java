package net.yirmiri.dungeonsdelight.integration.common;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class INArcaneChiliItem extends INBiteableItem {
    private final boolean hasFoodEffectTooltip;

    public INArcaneChiliItem(String modid, Properties properties, boolean hasPotionEffectTooltip) {
        super(modid, properties, hasPotionEffectTooltip);
        hasFoodEffectTooltip = hasPotionEffectTooltip;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (!level.isClientSide) {
            MobEffectInstance effect = switch (level.getRandom().nextInt(4)) {
                case 0 -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 1);
                case 1 -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0);
                case 2 -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0);
                case 3 -> new MobEffectInstance(MobEffects.JUMP, 2400, 2);
                default -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0);
            };
            consumer.addEffect(effect);
        }
        return super.finishUsingItem(stack, level, consumer);
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.arcane_chili").withStyle(ChatFormatting.BLUE));
            if (this.hasFoodEffectTooltip) {
                TextUtils.addFoodEffectTooltip(stack, tooltip, 1.0F);
            }
        }
    }
}
