package net.yirmiri.dungeonsdelight.item.compat;

import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class CompatBiteableItem extends CompatConsumableItem {
    public CompatBiteableItem(String modid, Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        super(modid, properties, hasPotionEffectTooltip, hasCustomTooltip);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
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
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        ItemStack containerStack = stack.getCraftingRemainingItem();
        Player player = consumer instanceof Player ? (Player) consumer : null;

        if (!level.isClientSide) {
            this.affectConsumer(stack, level, consumer);
        }

        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, stack);
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                Player finalPlayer = player;
                stack.hurtAndBreak(1, player, (playerIn) -> playerIn.broadcastBreakEvent(finalPlayer.getUsedItemHand()));

                player.getFoodData().eat(stack.getItem(), stack, player);
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
                addEatEffect(stack, level, player);
                player.gameEvent(GameEvent.EAT);
            }

            if (stack.getDamageValue() == 0) {
                return containerStack;
            }
        }
        return stack;
    }

    private void addEatEffect(ItemStack stack, Level level, LivingEntity living) {
        Item item = stack.getItem();
        if (item.isEdible()) {
            for (Pair<MobEffectInstance, Float> mobEffectInstanceFloatPair : stack.getFoodProperties(living).getEffects()) {
                Pair<MobEffectInstance, Float> pair = mobEffectInstanceFloatPair;
                if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                    living.addEffect(new MobEffectInstance(pair.getFirst()));
                }
            }
        }
    }
}
