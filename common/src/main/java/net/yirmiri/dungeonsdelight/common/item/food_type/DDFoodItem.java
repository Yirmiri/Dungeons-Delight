package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class DDFoodItem extends EffectTooltipItem {
    private final SoundEvent consumeSound;
    private final UseAnim useAnimation;
    private final int useTicks;

    public DDFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, int useTicks, Properties properties) {
        super(hasEffectTooltip, properties);
        this.consumeSound = consumeSound;
        this.useAnimation = useAnimation;
        this.useTicks = useTicks;
    }

    public DDFoodItem(boolean hasEffectTooltip, Properties properties) {
        this(hasEffectTooltip, SoundEvents.GENERIC_EAT, UseAnim.EAT, 32, properties);
    }

    public DDFoodItem(boolean hasEffectTooltip, Properties properties, int useTicks) {
        this(hasEffectTooltip, SoundEvents.GENERIC_EAT, UseAnim.EAT, useTicks, properties);
    }

    public DDFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, Properties properties) {
        this(hasEffectTooltip, consumeSound, UseAnim.EAT, 32, properties);
    }

    public DDFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, Properties properties) {
        this(hasEffectTooltip, consumeSound, useAnimation, 32, properties);
    }

    public DDFoodItem(boolean hasEffectTooltip, int useTicks, Properties properties) {
        this(hasEffectTooltip, SoundEvents.GENERIC_EAT, UseAnim.EAT, useTicks, properties);
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
            ItemStack stackRemainder = new ItemStack(craftRemainderItem);
            if (stack.isEmpty()) {
                return stackRemainder;
            }

            if (consumer instanceof Player player && !player.getAbilities().instabuild) {
                if (!player.getInventory().add(stackRemainder)) {
                    player.drop(stackRemainder, false);
                }
            }
        }
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return useTicks;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return useAnimation;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return consumeSound;
    }

    @Override
    public SoundEvent getEatingSound() {
        return consumeSound;
    }
}
