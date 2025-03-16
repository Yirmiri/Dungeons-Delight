package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.List;

public class EXPUndeadFoodItem extends EXPFoodItem {
    public EXPUndeadFoodItem(Properties properties, int experience, boolean hasFoodEffectTooltip) {
        super(properties, experience, hasFoodEffectTooltip);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
            List<MobEffect> monsterEffects = List.of(
                    DDEffects.DECISIVE.get(), DDEffects.POUNCING.get(), DDEffects.EXUDATION.get(),
                    DDEffects.VORACITY.get(), DDEffects.TENACITY.get(), DDEffects.BURROW_GUT.get()
            );

            if (monsterEffects.stream().noneMatch(player::hasEffect)) {
                List<MobEffect> effects = List.of(
                        MobEffects.DAMAGE_BOOST, MobEffects.JUMP, MobEffects.ABSORPTION,
                        ModEffects.NOURISHMENT.get(), ModEffects.COMFORT.get(), MobEffects.DIG_SPEED
                );

                List<MobEffect> newEffects = List.of(
                        DDEffects.DECISIVE.get(), DDEffects.POUNCING.get(), DDEffects.EXUDATION.get(),
                        DDEffects.VORACITY.get(), DDEffects.TENACITY.get(), DDEffects.BURROW_GUT.get()
                );

                for (int i = 0; i < effects.size(); i++) {
                    if (player.hasEffect(effects.get(i))) {
                        DDUtil.applyEffectSwap(player, effects.get(i), newEffects.get(i));
                        living.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED, 1.0F, 0.5F);
                        break;
                    }
                }
            }
        }
        return stack;
    }
}
