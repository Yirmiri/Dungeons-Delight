package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.yirmiri.dungeonsdelight.common.util.data.DiverDownData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class DiverDownEntityMixin implements DiverDownData {
    @Unique protected int dundel$remainingCharge = DiverDownData.MAX_CHARGE;
    @Unique protected boolean dundel$lavaSwimming;

    @Override public int getCharge() { return this.dundel$remainingCharge; }
    @Override public void setCharge(int charge) { this.dundel$remainingCharge = Math.max(0, Math.min(DiverDownData.MAX_CHARGE, charge)); }
    @Override public boolean isLavaSwimming() { return this.dundel$lavaSwimming; }
    @Override public void setLavaSwimming(boolean swimming) { this.dundel$lavaSwimming = swimming; }

    @Inject(method = "saveWithoutId", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V",
            ordinal = 0,
            shift = At.Shift.AFTER
    ))
    private void dungeonsdelight$addAdditionalSaveData(CompoundTag compound, CallbackInfoReturnable<CompoundTag> cir) {
        compound.putInt(DiverDownData.DIVER_DOWN_CHARGE, this.dundel$remainingCharge);
        compound.putBoolean(DiverDownData.DIVER_DOWN_LAVA_SWIMMING, this.dundel$lavaSwimming);
    }

    @Inject(method = "load", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V",
            ordinal = 0,
            shift = At.Shift.AFTER
    ))
    private void dungeonsdelight$readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains(DiverDownData.DIVER_DOWN_CHARGE)) this.setCharge(tag.getInt(DiverDownData.DIVER_DOWN_CHARGE));
        if (tag.contains(DiverDownData.DIVER_DOWN_LAVA_SWIMMING)) this.setLavaSwimming(tag.getBoolean(DiverDownData.DIVER_DOWN_LAVA_SWIMMING));
    }
}
