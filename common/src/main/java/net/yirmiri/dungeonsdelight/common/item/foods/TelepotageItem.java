package net.yirmiri.dungeonsdelight.common.item.foods;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.food_type.CreeperFoodItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.common.util.data.HomewardData;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

import java.util.List;

public class TelepotageItem extends CreeperFoodItem {
    public TelepotageItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
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
//            if (Minecraft.getInstance().player instanceof HomewardData data) {
//                if (data.getHomewardPos() != null && level.dimension() == data.getHomewardDimension()) {
//                    tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.homeward_bound")
//                            .withStyle(style -> style.withColor(ChatFormatting.GRAY)));
//                } else {
//                    tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.homeward_unbound")
//                            .withStyle(style -> style.withColor(ChatFormatting.GRAY)));
//                }
//            }
            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.homeward_teleport")
                    .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
        }
    }
}
