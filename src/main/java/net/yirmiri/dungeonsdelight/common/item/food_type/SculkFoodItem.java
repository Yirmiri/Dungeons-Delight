package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class SculkFoodItem extends ConsumableItem {
    private final int level;

    public SculkFoodItem(Properties properties, int level, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
        this.level = level;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
            switch (this.level) {
                case 1 -> DDUtil.echoBlastSmall(level, player);
                case 2 -> DDUtil.echoBlastMedium(level, player);
                case 3 -> DDUtil.echoBlastLarge(level, player);
                default -> throw new IllegalStateException("Unexpected value: " + this.level);
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get() && !stack.is(DDItems.NECRONOG.get())) {
            String translationKey = "tooltip.sculk_level_" + level;
            tooltip.add(TextUtils.getTranslation(translationKey).withStyle(ChatFormatting.BLUE));
        }
        super.appendHoverText(stack, ctx, tooltip, isAdvanced);
    }
}
