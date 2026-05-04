package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class BiteableItem extends ConsumableItem {
    private int stackSize;

    public BiteableItem(Properties properties, int stackSize, boolean hasPotionEffectTooltip) {
        super(properties, hasPotionEffectTooltip, false);
        this.stackSize = stackSize;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.biteable").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, ctx, tooltip, isAdvanced);
        }
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false; //this.canRepair && this.isDamageable(stack);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack enchantBook) {
        return false;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return TextColor.fromRgb(0xc875c2).getValue();
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return stackSize;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (!level.isClientSide) {
            this.affectConsumer(stack, level, consumer);
        }

        ItemStack containerStack = stack.getCraftingRemainingItem();
        Player player = consumer instanceof Player ? (Player) consumer : null;

        if (stack.getFoodProperties(consumer) != null) {
            FoodProperties foodProperties = stack.getFoodProperties(consumer);

            if (player != null) {
                player.getFoodData().eat(foodProperties.nutrition(), foodProperties.saturation());
            }

            if (!level.isClientSide) {
                if (!player.level().isClientSide()) {
                    for (FoodProperties.PossibleEffect possibleEffect : foodProperties.effects()) {
                        if (player.getRandom().nextFloat() < possibleEffect.probability()) {
                            player.addEffect(possibleEffect.effect());
                        }
                    }
                }
            }
        } else {
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));

            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));

                if (stack.getDamageValue() >= stack.getMaxDamage()) {
                    if (!containerStack.isEmpty()) {
                        if (!player.getInventory().add(containerStack)) {
                            player.drop(containerStack, false);
                        }
                    }
                    stack.setDamageValue(0);
                    player.playSound(SoundEvents.PLAYER_BURP);
                }
            }
        }
        return stack;
    }
}
