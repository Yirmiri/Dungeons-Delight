package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WoodType.class)
public class WoodTypeMixin
{
    // ok bruh dont work then
    //@Inject(method = "values", at = @At("RETURN"), cancellable = true)
    //private static void dundelight$addStream(CallbackInfoReturnable<Stream<WoodType>> cir) {
    //    cir.setReturnValue(Stream.concat(cir.getReturnValue(), DDBlockSetTypes.WT_VALUES.stream()));
    //}
}
