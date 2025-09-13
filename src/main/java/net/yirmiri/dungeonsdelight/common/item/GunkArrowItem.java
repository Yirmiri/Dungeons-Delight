package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.entity.misc.GunkArrowEntity;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.Configuration;

import java.util.List;

public class GunkArrowItem extends ArrowItem {
    public GunkArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public GunkArrowEntity createArrow(Level level, ItemStack stack, LivingEntity living) {
        return new GunkArrowEntity(living, level);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(Component.translatable("dungeonsdelight.tooltip.gunk_arrow").withStyle(ChatFormatting.BLUE));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
