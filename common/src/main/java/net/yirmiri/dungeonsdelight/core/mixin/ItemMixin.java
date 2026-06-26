package net.yirmiri.dungeonsdelight.core.mixin;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.yirmiri.dungeonsdelight.core.integration.IntegrationIds;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
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
        if (DungeonsDelight.CONFIG.getVanillaStatusEffectTooltips() && !Services.PLATFORM.isModLoaded(IntegrationIds.BF_ID)) {
            if (stack.getItem().getFoodProperties() != null && stack.is(DDTags.ItemT.HAS_EFFECT_TOOLTIP)) {
                if (DungeonsDelight.CONFIG.getShowChanceTooltips()) {
                    DDUtil.addEffectTooltipWithChance(stack.getItem().getFoodProperties(), tooltipComponents, 1.0F);
                } else {
                    DDUtil.addEffectTooltip(stack.getItem().getFoodProperties(), tooltipComponents, 1.0F);
                }
            }
        }

        if (DungeonsDelight.CONFIG.getVanillaItemEffectTooltips()) {
            if (!Services.PLATFORM.isModLoaded(IntegrationIds.BF_ID)) {
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
    private void dungeonsdelight$overrideOtherStackedOnMe(ItemStack food, ItemStack stack, Slot slot, ClickAction action, Player player, SlotAccess access, CallbackInfoReturnable<Boolean> cir) {
        if (action == ClickAction.PRIMARY && ItemStack.isSameItem(food, stack) && food.isStackable()) {
            int moved = Math.min(stack.getCount(), food.getMaxStackSize() - food.getCount());
            if (moved > 0) {
                for (SpikedFoodData.SpikeType type : SpikedFoodData.getSpikeTypes(stack)) {
                    if (!SpikedFoodData.hasSpike(food, type)) {
                        SpikedFoodData.copySpike(stack, food, type);
                    }
                }
                food.grow(moved);
                stack.shrink(moved);
                cir.setReturnValue(true);
            }
            return;
        }
        if (action != ClickAction.SECONDARY || food.getItem().getFoodProperties() == null) return;
        if (stack.is(DDItems.SPIDER_EXTRACT.get()) && !SpikedFoodData.hasSpike(food, SpikedFoodData.SpikeType.SPIDER) && !food.is(DDItems.SPIDER_EXTRACT.get())) {
            SpikedFoodData.addEffect(SoundEvents.BREWING_STAND_BREW, player, food,
                    SpikedFoodData.SpikeType.SPIDER, new MobEffectInstance(MobEffects.POISON, 240, 1));
            stack.shrink(1);

            if (stack.getItem().getCraftingRemainingItem() != null) {
                ItemStack container = new ItemStack(stack.getItem().getCraftingRemainingItem());
                if (!player.getInventory().add(container)) {
                    player.drop(container, false);
                }
            }
            cir.setReturnValue(true);
        }
    }
}