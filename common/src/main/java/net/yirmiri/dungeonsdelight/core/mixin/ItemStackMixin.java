package net.yirmiri.dungeonsdelight.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    ItemStack stack = ((ItemStack) (Object) this);

    @Shadow public abstract Rarity getRarity();
    @Shadow public abstract Item getItem();

    @Inject(method = "getHoverName", at = @At("RETURN"), cancellable = true)
    private void dungeonsdelight$getHoverName(CallbackInfoReturnable<Component> cir) {
        if (stack.getTag() != null && stack.getTag().getBoolean("TreasureBugInfested")) {
            cir.setReturnValue(cir.getReturnValue().copy().append(Component.literal("...?")));
        }
    }

    @ModifyReturnValue(method = "getDisplayName", at = @At(value = "RETURN"))
    private Component dungeonsdelight$appendRarityColor(Component original) {
        return DDRarities.tryToAppendOrReplace((MutableComponent) original, (ItemStack) (Object) this);
    }

    @ModifyVariable(method = "getTooltipLines", at = @At("STORE"), ordinal = 0)
    private MutableComponent dungeonsdelight$trySetOfKindColor(MutableComponent value) {
        return DDRarities.tryToAppendOrReplace(value, (ItemStack) (Object) this);
    }


    @Inject(method = "getMaxStackSize", at = @At(value = "HEAD"), cancellable = true)
    private void dungeonsdelight$getMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        if (stack.is(DDTags.ItemT.HAS_MEAL_STACK_SIZE) && DungeonsDelight.CONFIG.getIncreasedVanillaMealStackSize()) {
            cir.setReturnValue(16);
        }
    }

    @Inject(method = "finishUsingItem", at = @At(value = "HEAD"))
    private void dungeonsdelight$finishUsingItem(Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir) {
        if ((stack.is(Items.MUSHROOM_STEW) || stack.is(Items.BEETROOT_SOUP) || stack.is(Items.RABBIT_STEW)) && DungeonsDelight.CONFIG.getEffectsOnVanillaMeals()) {
            int ticks = 0;
            if (stack.is(Items.MUSHROOM_STEW)) ticks = 1200;
            if (stack.is(Items.BEETROOT_SOUP)) ticks = 2400;
            if (stack.is(Items.RABBIT_STEW)) ticks = 3600;

            livingEntity.addEffect(new MobEffectInstance(DDEffects.TENACITY.get(), ticks, 0));
        }
    }
}