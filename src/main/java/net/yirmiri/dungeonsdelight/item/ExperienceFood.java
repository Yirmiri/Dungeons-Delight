package net.yirmiri.dungeonsdelight.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class ExperienceFood extends ConsumableItem {
    private final boolean drinkable;

    public ExperienceFood(Properties properties, boolean hasFoodEffectTooltip, boolean drinkable) {
        super(properties, hasFoodEffectTooltip, true);
        this.drinkable = drinkable;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);

        if (!level.isClientSide && living instanceof Player player) {
            player.giveExperiencePoints(3 + player.level().random.nextInt(5) + player.level().random.nextInt(5));
        }
        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (drinkable) {
            return UseAnim.DRINK;
        }
        return UseAnim.EAT;
    }
}
