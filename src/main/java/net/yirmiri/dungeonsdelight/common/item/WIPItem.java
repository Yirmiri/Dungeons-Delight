package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class WIPItem extends Item {
    public WIPItem(Properties properties) {
        super(properties);
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        tooltip.add(TextUtils.getTranslation("tooltip.wip").withStyle(ChatFormatting.RED));
        super.appendHoverText(stack, level, tooltip, isAdvanced);
    }
}
