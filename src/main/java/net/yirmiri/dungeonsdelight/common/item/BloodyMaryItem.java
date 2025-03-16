package net.yirmiri.dungeonsdelight.common.item;

import net.azurune.tipsylib.register.TLMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class BloodyMaryItem extends DrinkableItem {
    public BloodyMaryItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
            List<MobEffect> effects = List.of(
                    MobEffects.DAMAGE_BOOST, MobEffects.JUMP, MobEffects.ABSORPTION,
                    ModEffects.NOURISHMENT.get(), ModEffects.COMFORT.get()
            );

            List<MobEffect> newEffects = List.of(
                    DDEffects.DECISIVE.get(), DDEffects.POUNCING.get(), DDEffects.EXUDATION.get(),
                    DDEffects.VORACITY.get(), DDEffects.TENACITY.get()
            );

            for (int i = 0; i < effects.size(); i++) {
                if (player.hasEffect(effects.get(i))) {
                    DDUtil.applyEffectSwap(player, effects.get(i), newEffects.get(i));
                    break;
                }
            }
            player.hurt(ModDamageTypes.getSimpleDamageSource(level, DDDamageTypes.BLOODY_MARY), 6.0F);
            player.addEffect(new MobEffectInstance(TLMobEffects.BLEEDING.get(), 200, 0));
        }
        return stack;
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.bloody_mary").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, level, tooltip, isAdvanced);
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
