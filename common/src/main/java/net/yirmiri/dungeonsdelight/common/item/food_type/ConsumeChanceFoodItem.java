package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.EffectTooltipItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

import java.util.List;

public class ConsumeChanceFoodItem extends EffectTooltipItem {
    private final float consumeChance;

    public ConsumeChanceFoodItem(float consumeChance, boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
        this.consumeChance = consumeChance;
    }

    private float getConsumeChance(LivingEntity living) {
        return this.consumeChance + (DDUtil.getSeredipityLuck(living) / 10);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.getItemEffectTooltips()) {
            Player player = Minecraft.getInstance().player;
            int percent = Math.round(consumeChance * 100);
            int seredipityPercent = Math.round((DDUtil.getSeredipityLuck(player) / 10) * 100);

            DDUtil.addConsumeTooltip(tooltipComponents);
            if (!player.hasEffect(DDEffects.SERENDIPITY.get())) {
                tooltipComponents.add(Component.literal(percent + "% ")
                        .append(Component.translatable("tooltip.dungeonsdelight.effect.chance_to_not_consume")).withStyle(ChatFormatting.BLUE));
            } else {
                tooltipComponents.add(Component.literal(percent + "% ").withStyle(ChatFormatting.BLUE)
                        .append(Component.literal("(+" + seredipityPercent + "%) ").withStyle(ChatFormatting.DARK_GREEN))
                        .append(Component.translatable("tooltip.dungeonsdelight.effect.chance_to_not_consume").withStyle(ChatFormatting.BLUE)));
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!level.isClientSide) {
            this.affectConsumer(stack, level, living);
        }

        boolean successfulChance = level.random.nextFloat() < getConsumeChance(living);
        Item craftRemainderItem = stack.getItem().getCraftingRemainingItem();
        ItemStack stackRemainder = new ItemStack(craftRemainderItem);
        Player player;

        if (stack.isEdible()) {
            super.finishUsingItem(stack, level, living);

            if (living instanceof Player && successfulChance) {
                player = (Player)living;
                player.getInventory().add(new ItemStack(this, 1));
                player.playSound(SoundEvents.SLIME_JUMP, 0.7F, 0.7F);
                //DDCriteriaTriggers.SLIME_FOOD.trigger((ServerPlayer) player); todo
            }

        } else {
            player = living instanceof Player ? (Player)living : null;
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
            return stackRemainder;
        } else {
            if (living instanceof Player) {
                player = (Player)living;

                if (!successfulChance && !((Player)living).getAbilities().instabuild && !player.getInventory().add(stackRemainder)) {
                    player.drop(stackRemainder, false);
                }
            }
            return stack;
        }
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
    }
}
