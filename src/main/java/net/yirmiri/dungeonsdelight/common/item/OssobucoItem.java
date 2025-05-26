package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import java.util.Arrays;
import java.util.List;

public class OssobucoItem extends ConsumableItem {
    public OssobucoItem(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        List<MobEffect> monsterEffects = Arrays.asList(
                DDEffects.DECISIVE.get(), DDEffects.POUNCING.get(), DDEffects.EXUDATION.get(),
                DDEffects.VORACITY.get(), DDEffects.TENACITY.get(), DDEffects.BURROW_GUT.get()
        );

        for (MobEffect effect : monsterEffects) {
            MobEffectInstance currentEffect = consumer.getEffect(effect);
            if (currentEffect != null && currentEffect.getDuration() < 2400) {
                consumer.addEffect(new MobEffectInstance(effect, 2400, 0));
            }
        }
        return super.finishUsingItem(stack, level, consumer);
    }
}
