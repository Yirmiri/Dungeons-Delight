package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

import java.util.List;

public class HordeFoodItem extends DDFoodItem {
    public HordeFoodItem(boolean hasEffectTooltip, int useTicks, Properties properties) {
        super(hasEffectTooltip, useTicks, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!living.level().isClientSide && living.hasEffect(MobEffects.BAD_OMEN)) {
            DDUtil.applyMonsterEffectSwap(living, MobEffects.BAD_OMEN, DDEffects.HORDE_OMEN.get(), true);
        }
        return super.finishUsingItem(stack, level, living);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.getItemEffectTooltips()) {
            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.monsterize_bad_omen")
                    .withStyle(style -> style.withColor(DDUtil.MONSTER_COLOR)));
        }
    }
}