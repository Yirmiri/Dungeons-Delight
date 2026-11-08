package net.yirmiri.dungeonsdelight.common.item.foods;

import net.azurune.runiclib.core.platform.RLServices;
import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.food_type.DDFoodItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.integration.DDIntegration;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

import java.util.List;
import java.util.Random;

public class FoulFeastItem extends DDFoodItem {
    public FoulFeastItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.itemEffectTooltips.getValue()) {
            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.unknown_effect").withStyle(ChatFormatting.BLUE));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (!level.isClientSide) {
            MobEffectInstance effect = switch (level.getRandom().nextInt(8)) {
                case 0, 1 -> new MobEffectInstance(DDEffects.POUNCING.get(), randomDuration(600), 0);
                case 2, 3 -> new MobEffectInstance(DDEffects.VORACITY.get(), randomDuration(600), 0);
                case 4 -> new MobEffectInstance(DDEffects.DECISIVE.get(), randomDuration(200), 0);
                case 5, 6 -> null;
                default -> new MobEffectInstance(DDEffects.TENACITY.get(), randomDuration(600), 0);
            };
            if (effect != null) {
                consumer.addEffect(effect);
            }
            if (consumer instanceof Player player) {
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + randomNutrition());
                player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() + randomSaturation());

                if (level.random.nextFloat() >= 0.32F) {
                    player.removeEffect(MobEffects.POISON);
                }

                if (level.random.nextFloat() >= 0.08F) {
                    ResourceKey<DamageType> damageType = DDDamageTypes.FOUL_FEAST;

                    if (level.random.nextFloat() >= 0.27F) {
                        damageType = DDDamageTypes.DUBIOUS_DISH;
                    } else if (level.random.nextFloat() >= 0.39F) {
                        damageType = DDDamageTypes.TERRIBLE_TREAT;
                    }
                    player.hurt(DDDamageTypes.getDamageSource(level, damageType), randomDamage());
                }

                if (level.random.nextFloat() >= 0.07F) {
                    player.playSound(SoundEvents.AMBIENT_CAVE.value(), 1.0F, -1.0F);
                }

                if (level.random.nextFloat() >= 0.00052F && (RLServices.PLATFORM.isModLoaded(DDIntegration.AD_ID) || RLServices.PLATFORM.isModLoaded(DDIntegration.AEU_ID))) {
                    player.playSound(SoundEvents.MUSIC_END.value(), 1.0F, -1.22F);
                }

                if (level.random.nextFloat() >= 0.04F) {
                    player.addEffect(new MobEffectInstance(RLEffects.HEARTBREAK.get(), randomDuration(800), 3));
                }
            }
        }
        return super.finishUsingItem(stack, level, consumer);
    }

    private static int randomDuration(int base) {
        int value = base;
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            if (random.nextBoolean()) {
                value += random.nextBoolean() ? 200 : -100;
            }
        }
        return value;
    }

    private static int randomNutrition() {
        int value = 5;
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            if (random.nextBoolean()) {
                value += random.nextBoolean() ? 2 : -1;
            }
        }
        return value;
    }

    private static float randomSaturation() {
        float value = 0.3F;
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            if (random.nextBoolean()) {
                value += random.nextBoolean() ? 0.2F : -0.1F;
            }
        }
        return value;
    }

    private static float randomDamage() {
        float value = 5;
        Random random = new Random();

        for (float i = 0; i < 3; i++) {
            if (random.nextBoolean()) {
                value += random.nextBoolean() ? 1 : -2;
            }
        }
        return value;
    }
}
