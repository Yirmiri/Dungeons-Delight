package net.yirmiri.dungeonsdelight.common.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.item.food_type.BiteableItem;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class BubblegunkItem extends BiteableItem {
    private final int hungerReduction;
    private final boolean hasFoodEffectTooltip;

    public BubblegunkItem(Properties properties, int stackSize, int hungerReduction, boolean hasPotionEffectTooltip) {
        super(properties, stackSize, hasPotionEffectTooltip);
        this.hungerReduction = hungerReduction;
        this.hasFoodEffectTooltip = hasPotionEffectTooltip;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.bubblegunk").withStyle(ChatFormatting.BLUE));
            if (this.hasFoodEffectTooltip) {
                TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0F, ctx.tickRate());
            }
        }
    }

//    @Override
//    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
//        Player player = consumer instanceof Player ? (Player) consumer : null;
//        if (player != null) {
//            if (!player.isCrouching()) {
//                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - hungerReduction);
//            } else {
//                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 2);
//            }
//
//            //Set food to 0 if after eating the Bubblegunk current food is below 0 to prevent negative hunger
//            if (player.getFoodData().getFoodLevel() < 0) {
//                player.getFoodData().setFoodLevel(0);
//            }
//        }
//        return super.finishUsingItem(stack, level, consumer);
//    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        Player player = consumer instanceof Player ? (Player) consumer : null;
        if (player != null) {
            int totalReduction = player.isCrouching() ? 2 : hungerReduction;

            new Thread(() -> {
                for (int i = 0; i < totalReduction; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException trace) {
                        trace.printStackTrace();
                    }

                    int currentFood = player.getFoodData().getFoodLevel();
                    player.getFoodData().setFoodLevel(Math.max(currentFood - 1, 0));
                }
            }).start();
        }
        return super.finishUsingItem(stack, level, consumer);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 24;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_BLOCK_STEP;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_BLOCK_STEP;
    }
}
