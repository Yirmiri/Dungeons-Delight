package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;

import java.util.Collection;
import java.util.List;

public class RawCreeperFoodItem extends DDFoodItem {
    public RawCreeperFoodItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.getItemEffectTooltips()) {
            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.raw_creeper").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!living.level().isClientSide) {
            Collection<MobEffectInstance> effects = living.getActiveEffects();
            if (!effects.isEmpty()) {
                living.level().explode(living, living.getX(), living.getY(), living.getZ(), 2, Level.ExplosionInteraction.NONE);
                living.hurt(DDDamageTypes.getDamageSource(living.level(), DDDamageTypes.RAW_CREEPER), 3.0F);
                spawnLingeringCloud(living);
            }
        }
        return super.finishUsingItem(stack, level, living);
    }

    private void spawnLingeringCloud(LivingEntity living) {
        Collection<MobEffectInstance> effects = living.getActiveEffects();
        AreaEffectCloud cloud = new AreaEffectCloud(living.level(), living.getX(), living.getY(), living.getZ());
        cloud.setRadius(2.5F);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setWaitTime(10);
        cloud.setDuration(cloud.getDuration() / 2);
        cloud.setRadiusPerTick(-cloud.getRadius() / (float) cloud.getDuration());

        for (MobEffectInstance effectInstance : effects) {
            cloud.addEffect(new MobEffectInstance(effectInstance));
        }

        living.level().addFreshEntity(cloud);
        living.removeAllEffects();
    }
}
