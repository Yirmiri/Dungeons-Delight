package net.yirmiri.dungeonsdelight.common.item.food_types;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class SlimeCureFoodItem extends SlimeFoodItem {
    private boolean hasFoodEffectTooltip;
    private float chance;

    public SlimeCureFoodItem(Properties properties, float chance, boolean hasFoodEffectTooltip) {
        super(properties, chance, hasFoodEffectTooltip);
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        this.chance = chance;
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
            int percent = Math.round(chance * 100);

            tooltip.add(Component.literal(percent + "% ")
                    .append(Component.translatable("farmersdelight.tooltip.chance_to_cure_not_consume")).withStyle(ChatFormatting.BLUE));

            if (this.hasFoodEffectTooltip) {
                TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0F, ctx.tickRate());
            }
        }
    }
}
