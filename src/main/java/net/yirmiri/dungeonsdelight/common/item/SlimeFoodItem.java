package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDCriteriaTriggers;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class SlimeFoodItem extends Item {
    private final boolean hasFoodEffectTooltip;
    private final float chance;

    public SlimeFoodItem(Properties properties, float chance, boolean hasFoodEffectTooltip) {
        super(properties);
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        this.chance = chance;
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (!level.isClientSide) {
            this.affectConsumer(stack, level, consumer);
        }

        boolean successfulChance = level.random.nextFloat() < chance;
        ItemStack containerStack = stack.getCraftingRemainingItem();
        Player player;

        if (stack.getFoodProperties(consumer) != null) {
            super.finishUsingItem(stack, level, consumer);

            if (consumer instanceof Player && successfulChance) {
                player = (Player) consumer;
                player.getInventory().add(new ItemStack(this, 1));
                player.playSound(SoundEvents.SLIME_JUMP, 0.7F, 0.7F);
                if (player instanceof ServerPlayer serverPlayer) {
                    DDCriteriaTriggers.SLIME_FOOD.get().trigger(serverPlayer);
                }
            }

        } else {
            player = consumer instanceof Player ? (Player)consumer : null;
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
            }

            if (player != null) {
                player.awardStat(Stats.ITEM_USED.get(this));
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
        }

        if (stack.isEmpty() && !successfulChance) {
            return containerStack;
        } else {
            if (consumer instanceof Player) {
                player = (Player)consumer;

                if (!successfulChance && !((Player)consumer).getAbilities().instabuild && !player.getInventory().add(containerStack)) {
                    player.drop(containerStack, false);
                }
            }

            return stack;
        }
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            int percent = Math.round(chance * 100);

            tooltip.add(Component.literal(percent + "% ")
                    .append(Component.translatable("farmersdelight.tooltip.chance_to_not_consume")).withStyle(ChatFormatting.BLUE));
        }

        if (this.hasFoodEffectTooltip) {
            TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0F, ctx.tickRate());
        }
    }
}