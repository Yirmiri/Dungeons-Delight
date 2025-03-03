package net.yirmiri.dungeonsdelight.item;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class NotConsumeChanceItem extends ConsumableItem {
    private final float chance;

    public NotConsumeChanceItem(Properties properties, float chance, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
        this.chance = chance;
    }

    //TODO MAKE CONSUME SOMETIMES

//    @Override @OnlyIn(Dist.CLIENT)
//    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
//        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
//            tooltip.add(TextUtils.getTranslation("tooltip.chance_to_not_consume").withStyle(ChatFormatting.BLUE));
//            super.appendHoverText(stack, level, tooltip, isAdvanced);
//        }
//    }
}
