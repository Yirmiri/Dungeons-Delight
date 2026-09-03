package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Locale;

@Mixin(PotionUtils.class)
public class PotionUtilsMixin {

    @Inject(method = "addPotionTooltip(Ljava/util/List;Ljava/util/List;F)V", at = @At("TAIL"))
    private static void dungeonsdelight$addPotionTooltip(List<MobEffectInstance> effects, List<Component> tooltips, float durationFactor, CallbackInfo ci) {
        if (!DungeonsDelight.CONFIG.getInvisibilityDetectionRangeTooltip()) return;
        boolean invisibility = false;

        for (MobEffectInstance effect : effects) {
            if (effect.getEffect() == MobEffects.INVISIBILITY) {
                invisibility = true;
                break;
            }
        }

        if (!invisibility) return;
        if (Minecraft.getInstance().player == null) return;

        int equippedArmor = 0;

        for (ItemStack armorStack : Minecraft.getInstance().player.getArmorSlots()) {
            if (!armorStack.isEmpty()) {
                equippedArmor++;
            }
        }

        double detectionRange = 93.0D - equippedArmor * 17.5D;
        Component detectionRangeTooltip = Component.translatable("attribute.modifier.take.1",
                (detectionRange % 1 == 0 ? String.format("%.0f", detectionRange) : String.format("%.1f", detectionRange)),
                Component.translatable("tooltip.dungeonsdelight.detection_range")).withStyle(ChatFormatting.BLUE);

        Component whenDrankTooltip = Component.translatable("potion.whenDrank").withStyle(ChatFormatting.DARK_PURPLE);
        int whenDrankIndex = -1;

        for (int i = 0; i < tooltips.size(); i++) {
            if (tooltips.get(i).getString().equals(whenDrankTooltip.getString())) {
                whenDrankIndex = i;
                break;
            }
        }

        if (whenDrankIndex >= 0) {
            tooltips.add(whenDrankIndex + 1, detectionRangeTooltip);
        } else {
            tooltips.add(CommonComponents.EMPTY);
            tooltips.add(whenDrankTooltip);
            tooltips.add(detectionRangeTooltip);
        }
    }
}