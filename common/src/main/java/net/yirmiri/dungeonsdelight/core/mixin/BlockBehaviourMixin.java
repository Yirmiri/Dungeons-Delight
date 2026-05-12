package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDSoundTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Mixin(BlockBehaviour.class) TODO 1.21
@Mixin(Block.class)
public class BlockBehaviourMixin {

    @Inject(at = @At("HEAD"), method = "getSoundType", cancellable = true)
    private void dungeonsdelight$getSoundType(BlockState state, CallbackInfoReturnable<SoundType> cir) {
        if (state.is(Blocks.SPAWNER)) {
            cir.setReturnValue(DDSoundTypes.STAINED_SCRAP);
        }
    }
}