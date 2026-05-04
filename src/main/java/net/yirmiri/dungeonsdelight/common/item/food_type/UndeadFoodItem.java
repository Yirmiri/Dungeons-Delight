package net.yirmiri.dungeonsdelight.common.item.food_type;

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

public class UndeadFoodItem extends ConsumableItem {
    public UndeadFoodItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!level.isClientSide && living instanceof Player player) {
            if (DDUtil.MONSTER_EFFECTS.stream().noneMatch(player::hasEffect)) {
                for (int i = 0; i < DDUtil.NORMAL_EFFECTS.size(); i++) {
                    if (player.hasEffect(DDUtil.NORMAL_EFFECTS.get(i))) {
                        DDUtil.applyMonsterEffectSwap(player, DDUtil.NORMAL_EFFECTS.get(i), DDUtil.MONSTER_EFFECTS.get(i));
                        player.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED, 1.0F, 0.5F);
                        break;
                    }
                }
            }
        }
        super.finishUsingItem(stack, level, living);
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.undead").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, ctx, tooltip, isAdvanced);
        }
    }
}
