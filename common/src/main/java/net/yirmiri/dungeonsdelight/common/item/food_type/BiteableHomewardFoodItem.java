package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

import java.util.List;

public class BiteableHomewardFoodItem extends BiteableFoodItem {
    public BiteableHomewardFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, int useTicks, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, useTicks, properties);
    }

    public BiteableHomewardFoodItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
    }

    public BiteableHomewardFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, Properties properties) {
        super(hasEffectTooltip, consumeSound, properties);
    }

    public BiteableHomewardFoodItem(boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, properties);
    }

    public BiteableHomewardFoodItem(boolean hasEffectTooltip, int useTicks, Properties properties) {
        super(hasEffectTooltip, useTicks, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        //todo make hit distance based on block reach in 1.21.1
        if (player.pick(4.5D, 0.0F, false) instanceof BlockHitResult blockHit) {
            if (level.getBlockState(blockHit.getBlockPos()).is(DDBlocks.TELEPOTAGE_BLOCK.get())) {
                return InteractionResultHolder.fail(player.getItemInHand(hand));
            }
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.getItemEffectTooltips()) {
            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.homeward_teleport")
                    .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
        }
    }
}
