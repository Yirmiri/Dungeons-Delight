package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SculkPoisonPotatoesItem extends SculkFoodItem {
    private final int level;

    public SculkPoisonPotatoesItem(Properties properties, int level, boolean hasFoodEffectTooltip) {
        super(properties, level, hasFoodEffectTooltip);
        this.level = level;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
            if (player.hasEffect(MobEffects.POISON)) {
                player.removeEffect(MobEffects.POISON);
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, (this.level * 60), 0));
                player.playSound(SoundEvents.ZOMBIE_VILLAGER_CURE, 0.5F, 1.0F);
            }
        }
        return stack;
    }
}
