package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockSetType.class)
public class BlockSetMixin {
    // dont work either
    //@Inject(method = "values", at = @At("RETURN"), cancellable = true)
    //private static void dundelight$addStream(CallbackInfoReturnable<Stream<BlockSetType>> cir) {
    //    cir.setReturnValue(Stream.concat(cir.getReturnValue(), DDBlockSetTypes.BS_VALUES.values().stream()));
    //}
}
