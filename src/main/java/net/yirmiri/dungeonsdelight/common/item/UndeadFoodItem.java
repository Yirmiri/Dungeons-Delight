package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class UndeadFoodItem extends ConsumableItem {
    public UndeadFoodItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!level.isClientSide && living instanceof Player player) {
            List<MobEffect> monsterEffects = List.of(
                    DDEffects.DECISIVE.get(), DDEffects.POUNCING.get(), DDEffects.EXUDATION.get(),
                    DDEffects.VORACITY.get(), DDEffects.TENACITY.get(), DDEffects.BURROW_GUT.get()
            );

            if (monsterEffects.stream().noneMatch(player::hasEffect)) {
                for (int i = 0; i < DDUtil.NORMAL_EFFECTS.size(); i++) {
                    if (player.hasEffect(DDUtil.NORMAL_EFFECTS.get(i))) {
                        DDUtil.applyEffectSwap(player, DDUtil.NORMAL_EFFECTS.get(i), DDUtil.MONSTER_EFFECTS.get(i));
                        player.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED, 1.0F, 0.5F);
                        break;
                    }
                }
            }
        }
        super.finishUsingItem(stack, level, living);
        return stack;
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.undead").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, level, tooltip, isAdvanced);
        }
    }
}
