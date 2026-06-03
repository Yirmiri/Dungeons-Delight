package net.yirmiri.dungeonsdelight.core.mixin;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(at = @At("HEAD"), method = "appendHoverText")
    private void dungeonsdelight$appendTooltip(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced, CallbackInfo ci) {
        if (DungeonsDelight.CONFIG.getVanillaStatusEffectTooltips() && !Services.PLATFORM.isModLoaded("bountifulfares")) {
            if (stack.getItem().getFoodProperties() != null && stack.is(DDTags.ItemT.HAS_EFFECT_TOOLTIP)) {
                DDUtil.addEffectTooltip(stack.getItem().getFoodProperties(), tooltipComponents, 1.0F);
            }
        }

        if (DungeonsDelight.CONFIG.getVanillaItemEffectTooltips()) {
            if (!Services.PLATFORM.isModLoaded("bountifulfares")) {
                if (stack.is(Items.MILK_BUCKET)) {
                    DDUtil.addConsumeTooltip(tooltipComponents);
                    tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.cleanse_effects").withStyle(ChatFormatting.BLUE));
                }
                if (stack.is(Items.HONEY_BOTTLE)) {
                    DDUtil.addConsumeTooltip(tooltipComponents);
                    tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.cleanse_poison").withStyle(ChatFormatting.BLUE));
                }
            }
        }
    }
}