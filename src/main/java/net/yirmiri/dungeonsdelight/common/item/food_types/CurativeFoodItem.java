package net.yirmiri.dungeonsdelight.common.item.food_types;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class CurativeFoodItem extends ConsumableItem {
    public CurativeFoodItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!level.isClientSide && living instanceof Player player) {
            for (int i = 0; i < DDUtil.MONSTER_EFFECTS.size(); i++) {
                if (player.hasEffect(DDUtil.MONSTER_EFFECTS.get(i))) {
                    DDUtil.applyEffectSwap(player, DDUtil.MONSTER_EFFECTS.get(i), DDUtil.NORMAL_EFFECTS.get(i));
                    break;
                }
            }
        }
        super.finishUsingItem(stack, level, living);
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.cure").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, ctx, tooltip, isAdvanced);
        }
    }
}
