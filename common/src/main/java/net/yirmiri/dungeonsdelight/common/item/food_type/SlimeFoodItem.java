package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
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
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class SlimeFoodItem extends EffectTooltipItem {
    private final float consumeChance;

    public SlimeFoodItem(float consumeChance, boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
        this.consumeChance = consumeChance;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.getItemEffectTooltips()) {
            int percent = Math.round(consumeChance * 100);

            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.literal(percent + "% ")
                    .append(Component.translatable("tooltip.dungeonsdelight.effect.chance_to_not_consume")).withStyle(ChatFormatting.BLUE));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (!level.isClientSide) {
            this.affectConsumer(stack, level, consumer);
        }

        boolean successfulChance = level.random.nextFloat() < consumeChance;
        Item craftRemainderItem = stack.getItem().getCraftingRemainingItem();
        ItemStack stackRemainder = new ItemStack(craftRemainderItem);
        Player player;

        if (stack.isEdible()) {
            super.finishUsingItem(stack, level, consumer);

            if (consumer instanceof Player && successfulChance) {
                player = (Player)consumer;
                player.getInventory().add(new ItemStack(this, 1));
                player.playSound(SoundEvents.SLIME_JUMP, 0.7F, 0.7F);
                //DDCriteriaTriggers.SLIME_FOOD.trigger((ServerPlayer) player); todo
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
            return stackRemainder;
        } else {
            if (consumer instanceof Player) {
                player = (Player)consumer;

                if (!successfulChance && !((Player)consumer).getAbilities().instabuild && !player.getInventory().add(stackRemainder)) {
                    player.drop(stackRemainder, false);
                }
            }

            return stack;
        }
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
    }
}
