package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.particles.SimpleParticleType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDParticles {
    public static final Supplier<SimpleParticleType> LIVING_FLAME = register("living_flame");
    public static final Supplier<SimpleParticleType> LIVING_FLAME_EFFECT = register("living_flame_effect");

    public static Supplier<SimpleParticleType> register(String id) {
        return Services.REGISTRY.registerParticle(DungeonsDelight.MOD_ID, id);
    }

    public static void load() {
    }
}
