package net.yirmiri.dungeonsdelight.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class ExperienceFood extends ConsumableItem {
    private int experience;

    public ExperienceFood(Properties properties, int experience, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, true);
        this.experience = experience;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);

        if (!level.isClientSide && living instanceof Player player) {
            player.giveExperiencePoints(experience + player.level().random.nextInt((int) (experience * 1.33)));
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
        }
        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (stack.is(DDItems.SCULK_MAYO.get())) {
            return UseAnim.DRINK;
        }
        return UseAnim.EAT;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
