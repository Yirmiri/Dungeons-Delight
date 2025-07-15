package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.Collection;
import java.util.List;

public class RawCreeperFoodItem extends ConsumableItem {
    public RawCreeperFoodItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip.raw_creeper_food").withStyle(ChatFormatting.RED));
            super.appendHoverText(stack, level, tooltip, isAdvanced);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        explodeRawCreeper(living);
        return super.finishUsingItem(stack, level, living);
    }

    private void explodeRawCreeper(LivingEntity living) {
        if (!living.level().isClientSide) {
            living.level().explode(living, living.getX(), living.getY(), living.getZ(), 3, Level.ExplosionInteraction.BLOCK);
            living.hurt(ModDamageTypes.getSimpleDamageSource(living.level(), DDDamageTypes.RAW_CREEPER), 4.0F);
            this.spawnLingeringCloud(living);
        }
    }

    private void spawnLingeringCloud(LivingEntity living) {
        Collection<MobEffectInstance> effects = living.getActiveEffects();
        if (!effects.isEmpty()) {
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
}
