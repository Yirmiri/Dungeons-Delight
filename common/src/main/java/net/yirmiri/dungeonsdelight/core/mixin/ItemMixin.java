package net.yirmiri.dungeonsdelight.core.mixin;

import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.common.util.data.SpikedFoodData;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.integration.DDIntegration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(at = @At("HEAD"), method = "appendHoverText")
    private void dungeonsdelight$appendTooltip(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced, CallbackInfo ci) {
        if (DungeonsDelight.CONFIG.vanillaStatusEffectTooltips.getValue()) {
            if (!RLServices.PLATFORM.isModLoaded(DDIntegration.BF_ID) && stack.getItem().getFoodProperties() != null && stack.is(DDTags.ItemT.HAS_EFFECT_TOOLTIP)) {
                if (DungeonsDelight.CONFIG.showChanceTooltips.getValue()) {
                    DDUtil.addEffectTooltipWithChance(stack.getItem().getFoodProperties(), tooltipComponents, 1.0F);
                } else {
                    DDUtil.addEffectTooltip(stack.getItem().getFoodProperties(), tooltipComponents, 1.0F);
                }
            }

            if (stack.is(Items.SUSPICIOUS_STEW) && !isAdvanced.isCreative()) {
                tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.unknown_effect").withStyle(ChatFormatting.GRAY));
            }

            if (DungeonsDelight.CONFIG.effectsOnVanillaMeals.getValue()
                    && (stack.is(Items.MUSHROOM_STEW) || stack.is(Items.BEETROOT_SOUP) || stack.is(Items.RABBIT_STEW))) {

                String time = "?:??";
                if (stack.is(Items.MUSHROOM_STEW)) time = "01:30";
                if (stack.is(Items.BEETROOT_SOUP)) time = "01:30";
                if (stack.is(Items.RABBIT_STEW)) time = "03:00";

                tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.fake_tenacity").append(Component.literal(" (").append(Component.literal(time).append(Component.literal(")")))).withStyle(ChatFormatting.BLUE));
            }
        }

        if (DungeonsDelight.CONFIG.vanillaItemEffectTooltips.getValue()) {
            if (!RLServices.PLATFORM.isModLoaded(DDIntegration.BF_ID)) {
                if (stack.is(Items.MILK_BUCKET)) {
                    DDUtil.addConsumeTooltip(tooltipComponents);
                    tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.cleanse_effects").withStyle(ChatFormatting.BLUE));
                }
                if (stack.is(Items.HONEY_BOTTLE)) {
                    DDUtil.addConsumeTooltip(tooltipComponents);
                    tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.cleanse_poison").withStyle(ChatFormatting.BLUE));
                }
            }
            if (stack.is(Items.CHORUS_FRUIT)) {
                DDUtil.addConsumeTooltip(tooltipComponents);
                tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.random_teleport").withStyle(ChatFormatting.BLUE));
            }
        }
    }

    @Inject(method = "overrideOtherStackedOnMe", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access, CallbackInfoReturnable<Boolean> cir) {
        if (slot.mayPlace(other)) {
            if (action == ClickAction.PRIMARY && ItemStack.isSameItem(stack, other) && stack.isStackable()) {
                int moved = Math.min(other.getCount(), stack.getMaxStackSize() - stack.getCount());
                if (moved > 0) {
                    SpikedFoodData.SpikeType spikeType = SpikedFoodData.getSpikeType(other);
                    if (spikeType != null) SpikedFoodData.copySpike(other, stack, spikeType);

                    stack.grow(moved);
                    other.shrink(moved);
                    cir.setReturnValue(true);
                }
                return;
            }
            if (action != ClickAction.SECONDARY || stack.getItem().getFoodProperties() == null) return;

            for (SpikedFoodData.SpikeType type : SpikedFoodData.SpikeType.values()) {
                if (SpikedFoodData.isSpikeItem(other, type) && !SpikedFoodData.isSpikeItem(stack, type) && !SpikedFoodData.isSameSpike(stack, type)) {
                    SpikedFoodData.spike(stack, other, type, player);
                    cir.setReturnValue(true);
                    return;
                }
            }
        }
    }
}