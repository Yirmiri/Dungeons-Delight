package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class DDFoodItem extends Item {
    private final boolean hasEffectTooltip;

    public DDFoodItem(boolean hasEffectTooltip, Properties properties) {
        super(properties);
        this.hasEffectTooltip = hasEffectTooltip;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        if (stack.getItem().getFoodProperties() != null && hasEffectTooltip && DungeonsDelight.CONFIG.getStatusEffectTooltips()) {
            if (DungeonsDelight.CONFIG.getShowChanceTooltips()) {
                DDUtil.addEffectTooltipWithChance(stack.getItem().getFoodProperties(), tooltipComponents, 1.0F);
            } else {
                DDUtil.addEffectTooltip(stack.getItem().getFoodProperties(), tooltipComponents, 1.0F);
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        Item craftRemainderItem = stack.getItem().getCraftingRemainingItem();

        if (stack.isEdible()) {
            super.finishUsingItem(stack, level, consumer);
        } else if (consumer instanceof Player player) {
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            }
            player.awardStat(Stats.ITEM_USED.get(this));

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        if (craftRemainderItem != null) {
            ItemStack stacky = new ItemStack(craftRemainderItem);
            if (stack.isEmpty()) {
                return stacky;
            }

            if (consumer instanceof Player player && !player.getAbilities().instabuild) {
                if (!player.getInventory().add(stacky)) {
                    player.drop(stacky, false);
                }
            }
        }
        return stack;
    }
}
