package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class MonsterBurgerItem extends ConsumableItem {
    public MonsterBurgerItem(Properties properties) {
        super(properties, false, true);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 64;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        if (!level.isClientSide && living instanceof Player player) {
           if (player.hasEffect(ModEffects.COMFORT.get())) {
               int comfortDuration = player.getEffect(ModEffects.COMFORT.get()).getDuration();
               player.addEffect(new MobEffectInstance(DDEffects.TENACITY.get(), comfortDuration , 0));
               player.removeEffect(ModEffects.COMFORT.get());
           }
        }
        return stack;
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            super.appendHoverText(stack, level, tooltip, isAdvanced);
            tooltip.add(TextUtils.getTranslation("tooltip.monster_burger_food").withStyle(ChatFormatting.BLUE));
        }
    }
}
