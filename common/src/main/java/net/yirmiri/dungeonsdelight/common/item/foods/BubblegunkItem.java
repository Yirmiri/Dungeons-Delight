package net.yirmiri.dungeonsdelight.common.item.foods;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.food_type.BiteableFoodItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class BubblegunkItem extends BiteableFoodItem {
    private final int hungerReduction;

    public BubblegunkItem(int hungerReduction, boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, SoundEvents.HONEY_BLOCK_HIT, UseAnim.EAT, 24, properties);
        this.hungerReduction = hungerReduction;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.getItemEffectTooltips()) {
            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.reduce_hunger")
                    .withStyle(style -> style.withColor(ChatFormatting.GRAY)));
        }
    }

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
                    player.getFoodData().setFoodLevel(Math.max(player.getFoodData().getFoodLevel() - 1, 0));
                }
            }).start();
            player.getFoodData().setSaturation(Math.max(player.getFoodData().getSaturationLevel() - 0.2F, 0));
        }
        return super.finishUsingItem(stack, level, consumer);
    }
}
