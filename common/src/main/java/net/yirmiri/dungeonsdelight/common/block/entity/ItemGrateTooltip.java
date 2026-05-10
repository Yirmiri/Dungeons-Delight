package net.yirmiri.dungeonsdelight.common.block.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.List;

public interface ItemGrateTooltip {
    static void appendHoverText(List<Component> tooltipLines) {
        tooltipLines.add(CommonComponents.EMPTY);
        tooltipLines.add(Component.translatable("block.dungeonsdelight.grate.desc1").withStyle(ChatFormatting.GRAY));
        tooltipLines.add(CommonComponents.space().append(Component.translatable("block.dungeonsdelight.grate.desc2").withStyle(ChatFormatting.BLUE)));
    }
}
