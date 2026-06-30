package net.yirmiri.dungeonsdelight.common.item.foods;

import net.azurune.runiclib.RunicLib;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.food_type.DDFoodItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.integration.IntegrationIds;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.List;
import java.util.Optional;

public class BubbleEyeTeaItem extends DDFoodItem {
    public BubbleEyeTeaItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, SoundEvents.HONEY_DRINK, UseAnim.DRINK, 32, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide) {
            List<MobEffectInstance> effectList = living.getActiveEffects().stream().filter(effect -> !(effect.getAmplifier() < DungeonsDelight.CONFIG.getBubbleEyeTeaMaxAmplifier()) //todo 1.21// && !effect.getEffect().is(DDTags.EffectT.UNMODIFIABLE_EFFECTS)
            ).toList();
            if (!effectList.isEmpty()) { //also holder in 1.21
                Optional<MobEffect> acidicIfExists = BuiltInRegistries.MOB_EFFECT.getOptional(RunicLib.customid(IntegrationIds.BF_ID, "acidic"));
                if (acidicIfExists.isPresent() && living.hasEffect(acidicIfExists.get())) {
                    living.removeEffect(acidicIfExists.get());
                    level.playSound(null, living.getX(), living.getY(), living.getZ(), DDSounds.ACIDIC_HISS.get(), SoundSource.PLAYERS, 1.2F, 2.0F);
                }
                else {
                    MobEffectInstance randomEffect = effectList.get(level.getRandom().nextInt(effectList.size()));
                    living.removeEffect(randomEffect.getEffect());
                    living.addEffect(new MobEffectInstance(randomEffect.getEffect(), randomEffect.getDuration() / 2, randomEffect.getAmplifier() + 1));
                }
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.getItemEffectTooltips()) {
            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.increase_amp_cut_dur")
                    .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
        }
    }
}