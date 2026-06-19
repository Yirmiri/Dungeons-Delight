package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.common.block.StainedScrapGateBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IronBarsBlock.class)
public class IronBarsBlockMixin {

    @Inject(at = @At("HEAD"), method = "attachsTo", cancellable = true)
    private void dungeonsdelight$attachsTo(BlockState state, boolean solidSide, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() instanceof StainedScrapGateBlock) {
            cir.setReturnValue(true);
        }
    }
}
