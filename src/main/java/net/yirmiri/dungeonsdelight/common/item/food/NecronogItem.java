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
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;
import java.util.Optional;

public class NecronogItem extends DrinkableItem {
    public NecronogItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide) {
            List<MobEffectInstance> effectList = living.getActiveEffects().stream().filter(effect -> effect.getAmplifier() > 0 && !effect.getEffect().is(DDTags.EffectT.UNMODIFIABLE_EFFECTS)).toList();
            if (!effectList.isEmpty()) {
                Optional<Holder.Reference<MobEffect>> acidicIfExists = BuiltInRegistries.MOB_EFFECT.getHolder(RunicLib.customid(IntegrationIds.BOUNTIFULFARES, "acidic"));
                if (acidicIfExists.isPresent() && living.hasEffect(acidicIfExists.get())) {
                    living.removeEffect(acidicIfExists.get());
                    level.playSound(
                            null,
                            living.getX(),
                            living.getY(),
                            living.getZ(),
                            DDSounds.ACIDIC_HISS.get(),
                            SoundSource.PLAYERS,
                            1.2F,
                            2.0F
                    );
                    // YEAH. I SEE YOU STAT STACKING FREAKS. :stare: - Artyrian
                }
                else {
                    MobEffectInstance randomEffect = effectList.get(level.getRandom().nextInt(effectList.size()));
                    living.removeEffect(randomEffect.getEffect());
                    living.addEffect(new MobEffectInstance(randomEffect.getEffect(), randomEffect.getDuration() * 2, randomEffect.getAmplifier() - 1));
                }
            }
        }
        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 48;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.necronog").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, ctx, tooltip, isAdvanced);
        }
    }
}
