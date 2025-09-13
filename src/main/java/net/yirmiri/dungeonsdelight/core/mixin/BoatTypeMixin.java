package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

@Mixin(Boat.Type.class)
public class BoatTypeMixin {

    @Shadow @Final @Mutable
    private static Boat.Type[] $VALUES;

    @Shadow @Final @Mutable
    public static StringRepresentable.EnumCodec<Boat.Type> CODEC;

    @Shadow @Final @Mutable
    private static IntFunction<Boat.Type> BY_ID;

    @Invoker("<init>")
    public static Boat.Type invokeInit(String name, int id, Block wood, String key) {
        throw new AssertionError();
    }

    static {
        List<Boat.Type> variants = new ArrayList<>(Arrays.asList($VALUES));

        //Game crashes if I put any modded item however planks dropping from boats as long since been discarded and is only used as a fallback anyway
        variants.add(invokeInit("WORMWOOD", variants.size(), Blocks.OAK_PLANKS, "wormwood"));

        $VALUES = variants.toArray(new Boat.Type[0]);
        CODEC = StringRepresentable.fromEnum(Boat.Type::values);
        BY_ID = ByIdMap.continuous(Enum::ordinal, $VALUES, ByIdMap.OutOfBoundsStrategy.ZERO);
    }
}