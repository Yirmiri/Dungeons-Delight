package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

@Mixin(WoodType.class)
public class WoodTypeMixin
{
    @Inject(method = "values", at = @At("RETURN"), cancellable = true)
    private static void dundelight$addStream(CallbackInfoReturnable<Stream<WoodType>> cir) {
        cir.setReturnValue(Stream.concat(cir.getReturnValue(), DDBlockSetTypes.WT_VALUES.stream()));
    }
}
