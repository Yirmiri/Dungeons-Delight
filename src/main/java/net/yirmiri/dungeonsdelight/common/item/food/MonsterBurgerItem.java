package net.yirmiri.dungeonsdelight.common.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.item.food_type.LengthConsumableItem;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class MonsterBurgerItem extends LengthConsumableItem {
    public MonsterBurgerItem(Properties properties) {
        super(properties, 64, false, true);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
           if (player.hasEffect(ModEffects.COMFORT)) { //forced due to specifically monster burgers on specifically comfort -> tenacity not converting
               int comfortDuration = player.getEffect(ModEffects.COMFORT).getDuration();
               player.addEffect(new MobEffectInstance(DDEffects.TENACITY, comfortDuration , 0));
               player.removeEffect(ModEffects.COMFORT);
           }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
            super.appendHoverText(stack, ctx, tooltip, isAdvanced);
            tooltip.add(TextUtils.getTranslation("tooltip.monster_burger_food").withStyle(ChatFormatting.BLUE));
        }
    }
}
