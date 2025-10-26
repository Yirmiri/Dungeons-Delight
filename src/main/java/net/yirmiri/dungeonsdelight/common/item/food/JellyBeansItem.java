package net.yirmiri.dungeonsdelight.common.item.food;

import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class JellyBeansItem extends ConsumableItem {
    public JellyBeansItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (!level.isClientSide) {
            //Every odd number is negative, every even is positive,, both sets should have some correlation
            MobEffectInstance effect = switch (level.getRandom().nextInt(10)) {
                case 1 -> new MobEffectInstance(DDEffects.SWIFT_STEP, 1200, 2); //POSITIVE
                case 2 -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1); //NEGATIVE

                case 3 -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1); //POSITIVE
                case 4 -> new MobEffectInstance(RLMobEffects.SHATTERSPLEEN, 600, 0); //NEGATIVE

                case 5 -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1); //POSITIVE
                case 6 -> new MobEffectInstance(RLMobEffects.CREATIVE_SHOCK, 600, 0); //NEGATIVE

                case 7 -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 1); //POSITIVE
                case 8 -> new MobEffectInstance(RLMobEffects.HEARTBREAK, 1200, 3); //NEGATIVE

                case 9 -> new MobEffectInstance(DDEffects.TENACITY, 1200, 1); //POSITIVE
                case 10 -> new MobEffectInstance(RLMobEffects.BLEEDING, 1200, 0); //NEGATIVE

                default -> new MobEffectInstance(MobEffects.LUCK, 1200, 0); //fallback, should never apply
            };
            consumer.addEffect(effect);
        }
        return super.finishUsingItem(stack, level, consumer);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.jelly_beans").withStyle(ChatFormatting.BLUE));
        }
    }
}
