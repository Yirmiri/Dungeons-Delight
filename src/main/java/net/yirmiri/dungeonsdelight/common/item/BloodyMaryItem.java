package net.yirmiri.dungeonsdelight.common.item;

import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class BloodyMaryItem extends DrinkableItem {
    public BloodyMaryItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
            List<Holder<MobEffect>> effects = List.of(
                    MobEffects.DAMAGE_BOOST, MobEffects.JUMP, MobEffects.ABSORPTION,
                    ModEffects.NOURISHMENT, ModEffects.COMFORT
            );

            List<Holder<MobEffect>> newEffects = List.of(
                    DDEffects.DECISIVE, DDEffects.POUNCING, DDEffects.EXUDATION,
                    DDEffects.VORACITY, DDEffects.TENACITY
            );

            for (int i = 0; i < effects.size(); i++) {
                if (player.hasEffect(effects.get(i))) {
                    DDUtil.applyEffectSwap(player, effects.get(i), newEffects.get(i));
                    break;
                }
            }
            player.hurt(ModDamageTypes.getSimpleDamageSource(level, DDDamageTypes.BLOODY_MARY), 6.0F);
            player.addEffect(new MobEffectInstance(RLMobEffects.BLEEDING, 200, 0));
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.bloody_mary").withStyle(ChatFormatting.BLUE));
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
