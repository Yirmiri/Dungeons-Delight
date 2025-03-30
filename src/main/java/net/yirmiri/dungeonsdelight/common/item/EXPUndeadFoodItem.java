package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EXPUndeadFoodItem extends UndeadFoodItem {
    private final int experience;

    public EXPUndeadFoodItem(Properties properties, int experience, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
        this.experience = experience;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!level.isClientSide && living instanceof Player player) {
            player.giveExperiencePoints(experience + player.level().random.nextInt((int) (experience * 1.33)));
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
        }
        super.finishUsingItem(stack, level, living);
        return stack;
    }
}
