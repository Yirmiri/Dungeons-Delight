package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Boat.Type.class)
public class BoatTypeMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Boat.Type newBoatType(String internalName, int ordinal, Block planks, String name) {
        throw new AssertionError();
    }

    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    Boat.Type[] $VALUES;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = 179, target = "Lnet/minecraft/world/entity/vehicle/Boat$Type;$VALUES:[Lnet/minecraft/world/entity/vehicle/Boat$Type;", shift = At.Shift.AFTER))
    private static void dungeonsDelight$addBoatTypes(CallbackInfo ci) {
        var values = new ArrayList<>(Arrays.asList($VALUES));
        var last = values.getLast();

        var wormwood = newBoatType("WORMWOOD", last.ordinal() + 1, Blocks.OAK_PLANKS, "wormwood");
        values.add(wormwood);
        $VALUES = values.toArray(new Boat.Type[0]);
    }
}