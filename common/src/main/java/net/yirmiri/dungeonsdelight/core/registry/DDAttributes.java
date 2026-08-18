package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDAttributes {
    public static final Supplier<Attribute> THROWING_RANGE = register("generic.throwing_range", () -> new RangedAttribute(
            "attribute.dungeonsdelight.throwing_range", 0.0, 0, 1024.0).setSyncable(true));

    public static final Supplier<Attribute> CHARGE_MULTIPLIER = register("generic.charge_multiplier", () -> new RangedAttribute(
            "attribute.dungeonsdelight.charge_multiplier", 0.0, -1024.0, 1024.0).setSyncable(true));

    public static final Supplier<Attribute> AIR_CONTROL = register("generic.air_control", () -> new RangedAttribute(
            "attribute.dungeonsdelight.air_control", 0.0, -100.0, 100.0).setSyncable(true));

    public static Supplier<Attribute> register(String id, Supplier<Attribute> supplier) {
        return Services.REGISTRY.registerAttribute(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
