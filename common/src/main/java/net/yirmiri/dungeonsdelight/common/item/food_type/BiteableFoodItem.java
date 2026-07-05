package net.yirmiri.dungeonsdelight.common.item.food_type;

import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;

public class BiteableFoodItem extends DDFoodItem {
    public BiteableFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, int useTicks, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, useTicks, properties);
    }

    public BiteableFoodItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
    }

    public BiteableFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, Properties properties) {
        super(hasEffectTooltip, consumeSound, properties);
    }

    public BiteableFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, properties);
    }

    public BiteableFoodItem(boolean hasEffectTooltip, int useTicks, Properties properties) {
        super(hasEffectTooltip, useTicks, properties);
    }

    //todo 1.21
//    @Override
//    public boolean isRepairable(ItemStack stack) {
//        return false;
//    }
//
//    @Override
//    public boolean isBookEnchantable(ItemStack stack, ItemStack enchantBook) {
//        return false;
//    }

    @Override
    public int getBarColor(ItemStack stack) {
        return TextColor.fromRgb(DDUtil.MONSTER_COLOR).getValue();
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        Item craftRemainderItem = stack.getItem().getCraftingRemainingItem();
        if (stack.isEdible()) {
            level.playSound(null, consumer.getX(), consumer.getY(), consumer.getZ(), consumer.getEatingSound(stack), SoundSource.NEUTRAL, 1.0F, 1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F);
            if (stack.getItem().getFoodProperties() != null) {
                for (Pair<MobEffectInstance, Float> pair : stack.getItem().getFoodProperties().getEffects()) {
                    if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                        consumer.addEffect(new MobEffectInstance(pair.getFirst()));
                    }
                }
            }
            consumer.gameEvent(GameEvent.EAT);
        }
        if (consumer instanceof Player player) {
            player.awardStat(Stats.ITEM_USED.get(this));

            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(player.getUsedItemHand()));
                if (stack.getDamageValue() >= stack.getMaxDamage()) {
                    if (craftRemainderItem != null) {
                        ItemStack stackRemainder = new ItemStack(craftRemainderItem);
                        if (stack.isEmpty()) {
                            return stackRemainder;
                        }

                        if (!player.getAbilities().instabuild) {
                            if (!player.getInventory().add(stackRemainder)) {
                                player.drop(stackRemainder, false);
                            }
                        }
                    }
                    stack.setDamageValue(0);
                    player.playSound(SoundEvents.PLAYER_BURP);
                }
            }

            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            }
        }
        return stack;
    }
}