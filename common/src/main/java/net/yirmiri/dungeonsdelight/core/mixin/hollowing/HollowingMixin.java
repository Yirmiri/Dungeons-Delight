package net.yirmiri.dungeonsdelight.core.mixin.hollowing;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.IHollowable;
import net.yirmiri.dungeonsdelight.core.registry.DDCriteriaTriggers;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(Mob.class)
public abstract class HollowingMixin
{
    @Unique protected int dungeonsdelight$hollowingTime = -1;
    @Unique protected boolean dungeonsdelight$isHollowing = false;
    @Unique protected UUID dungeonsdelight$conversionStarter;

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$mobInteractAtHead(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        this.dungeonsdelight$blackAppleOnCommons(player, hand, cir);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void dungeonsdelight$tick(CallbackInfo ci) {
        if (this instanceof IHollowable hollowable) {
            Mob me = (Mob)(Object)this;
            if (this.dungeonsdelight$isHollowing && me.level() instanceof ServerLevel serverLevel) {
                this.dungeonsdelight$hollowingTime--;
                if (this.dungeonsdelight$hollowingTime <= 0) {
                    Player pl = (this.dungeonsdelight$conversionStarter != null)
                            ? serverLevel.getPlayerByUUID(this.dungeonsdelight$conversionStarter)
                            : null;
                    Mob mob = hollowable.dungeonsdelight$convertViaHollowable(serverLevel, pl);
                    this.dungeonsdelight$isHollowing = false;

                    if (pl instanceof ServerPlayer svpl) {
                        DDCriteriaTriggers.HOLLOW.trigger(svpl);
                    }

                    if (mob != null) {
                        mob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
                        mob.removeEffect(DDEffects.HOLLOWED.get());
                        mob.playSound(DDSounds.HOLLOW_INFECT.get(), 1.0F, 1.0F);
                    }
                }
            }
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.putBoolean("DD-IsHollowing", this.dungeonsdelight$isHollowing);
        compound.putInt("DD-HollowingTime", this.dungeonsdelight$hollowingTime);
        if (this.dungeonsdelight$conversionStarter != null) {
            compound.putUUID("DD-ConversionPlayer", this.dungeonsdelight$conversionStarter);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        this.dungeonsdelight$isHollowing = compound.getBoolean("DD-IsHollowing");
        this.dungeonsdelight$hollowingTime = compound.getInt("DD-HollowingTime");
        if (compound.hasUUID("DD-ConversionPlayer")) {
            this.dungeonsdelight$conversionStarter = compound.getUUID("DD-ConversionPlayer");
        }
    }

    @Unique
    protected void dungeonsdelight$startHollowing(UUID conversionStarter, Mob mob) {
        if (!mob.level().isClientSide) {
            if (conversionStarter != null) this.dungeonsdelight$conversionStarter = conversionStarter;
            this.dungeonsdelight$hollowingTime = DungeonsDelight.CONFIG.hollowingTicks.getValue() + mob.getRandom().nextInt(DungeonsDelight.CONFIG.hollowingMaxRandomTicks.getValue());
            mob.level().broadcastEntityEvent(mob, EntityEvent.ZOMBIE_CONVERTING);
            mob.playSound(DDSounds.GENERIC_HOLLOW.get(), 1.0F, 1.0F);
        }

        DungeonsDelight.LOGGER.info("swallow");
        this.dungeonsdelight$isHollowing = true;
    }

    @Unique
    protected void dungeonsdelight$blackAppleOnCommons(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);
        Mob me = (Mob)(Object)this;

        if (stack.is(DDItems.BLACK_APPLE.get()) && me.hasEffect(DDEffects.HOLLOWED.get()) && !this.dungeonsdelight$isHollowing && me instanceof IHollowable hollowable) {
            if (!me.level().isClientSide && !player.isCreative()) stack.shrink(1);
            dungeonsdelight$startHollowing(player.getUUID(), me);

            cir.setReturnValue(InteractionResult.sidedSuccess(me.level().isClientSide));
            cir.cancel();
        }
    }
}