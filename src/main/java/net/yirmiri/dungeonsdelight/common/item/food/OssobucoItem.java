package net.yirmiri.dungeonsdelight.common.item.food;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.item.food_type.LengthConsumableItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class OssobucoItem extends LengthConsumableItem {
    public OssobucoItem(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, 64, hasFoodEffectTooltip, hasCustomTooltip);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        List<Holder<MobEffect>> effects = DDUtil.NORMAL_EFFECTS.stream().filter(effect -> {
            MobEffectInstance current = consumer.getEffect(effect);
                    return current != null && current.getDuration() < 2400;
                }).toList();

        if (!effects.isEmpty()) {
            Holder<MobEffect> chosenEffect = effects.get(level.random.nextInt(effects.size()));
            consumer.addEffect(new MobEffectInstance(chosenEffect, 2400, 0));
        }

        return super.finishUsingItem(stack, level, consumer);
    }
}
