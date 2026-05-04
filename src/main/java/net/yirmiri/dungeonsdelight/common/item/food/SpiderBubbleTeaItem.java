package net.yirmiri.dungeonsdelight.common.item.food;

import net.azurune.runiclib.RunicLib;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpiderBubbleTeaItem extends DrinkableItem {
    public SpiderBubbleTeaItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide) {
            List<MobEffectInstance> effectList = living.getActiveEffects().stream().filter(effect -> effect.getAmplifier() == 0 && !effect.getEffect().is(DDTags.EffectT.UNMODIFIABLE_EFFECTS)).toList();
            if (!effectList.isEmpty()) {
                Optional<Holder.Reference<MobEffect>> acidicIfExists = BuiltInRegistries.MOB_EFFECT.getHolder(RunicLib.customid(IntegrationIds.BOUNTIFULFARES, "acidic"));
                if (acidicIfExists.isPresent() && living.hasEffect(acidicIfExists.get())) {
                    living.removeEffect(acidicIfExists.get());
                    ((ServerLevel)level).playSound(
                            null,
                            living.getX(),
                            living.getY(),
                            living.getZ(),
                            DDSounds.ACIDIC_HISS.get(),
                            SoundSource.PLAYERS,
                            1.2F,
                            2.0F
                    );
                    living.addEffect(new MobEffectInstance(DDEffects.POUNCING, 2400, 1));
                }
                else {
                    MobEffectInstance randomEffect = effectList.get(level.getRandom().nextInt(effectList.size()));
                    living.removeEffect(randomEffect.getEffect());
                    living.addEffect(new MobEffectInstance(randomEffect.getEffect(), randomEffect.getDuration() / 2, 1));
                }
            } else living.addEffect(new MobEffectInstance(DDEffects.POUNCING, 2400, 1));
        }
        return stack;
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
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
