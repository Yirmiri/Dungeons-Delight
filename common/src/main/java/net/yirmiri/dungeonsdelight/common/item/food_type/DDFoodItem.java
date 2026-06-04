package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.DungeonsDelightConfig;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;
import java.util.Objects;

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
        ItemStack craftRemainderItem = new ItemStack(Objects.requireNonNull(stack.getItem().getCraftingRemainingItem()));

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

        if (stack.isEmpty()) {
            return craftRemainderItem;
        }

        if (consumer instanceof Player player && !player.getAbilities().instabuild) {
            if (!player.getInventory().add(craftRemainderItem)) {
                player.drop(craftRemainderItem, false);
            }
        }
        return stack;
    }
}
