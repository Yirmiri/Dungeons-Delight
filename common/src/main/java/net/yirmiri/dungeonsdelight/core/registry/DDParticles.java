package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.particles.SimpleParticleType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDParticles {
    public static final Supplier<SimpleParticleType> LIVING_FLAME = register("living_flame");
    public static final Supplier<SimpleParticleType> LIVING_FLAME_EFFECT = register("living_flame_effect");
    public static final Supplier<SimpleParticleType> EXUDATION_BLAST = register("exudation_blast");
    public static final Supplier<SimpleParticleType> LARGE_ECHO_BLAST = register("large_echo_blast");
    public static final Supplier<SimpleParticleType> MEDIUM_ECHO_BLAST = register("medium_echo_blast");
    public static final Supplier<SimpleParticleType> SMALL_ECHO_BLAST = register("small_echo_blast");

    public static Supplier<SimpleParticleType> register(String id) {
        return Services.REGISTRY.registerParticle(DungeonsDelight.MOD_ID, id);
    }

    public static void load() {
    }
}
