package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.particles.SimpleParticleType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDParticles {
    public static final Supplier<SimpleParticleType> LIVING_FLAME = register("living_flame");
    public static final Supplier<SimpleParticleType> LIVING_FLAME_EFFECT = register("living_flame_effect");
    public static final Supplier<SimpleParticleType> SINGLE_LIVING_FLAME = register("single_living_flame");
    public static final Supplier<SimpleParticleType> EXUDATION_BLAST = register("exudation_blast");
    public static final Supplier<SimpleParticleType> LARGE_ECHO_BLAST = register("large_echo_blast");
    public static final Supplier<SimpleParticleType> MEDIUM_ECHO_BLAST = register("medium_echo_blast");
    public static final Supplier<SimpleParticleType> SMALL_ECHO_BLAST = register("small_echo_blast");
    public static final Supplier<SimpleParticleType> MONSTER_RESIDUE = register("monster_residue");
    public static final Supplier<SimpleParticleType> MONSTER_STEAM = register("monster_steam");
    public static final Supplier<SimpleParticleType> DUNGEON_BUBBLE = register("dungeon_bubble");
    public static final Supplier<SimpleParticleType> ROTTEN_RESIDUE = register("rotten_residue");
    public static final Supplier<SimpleParticleType> FLY = register("fly");
    public static final Supplier<SimpleParticleType> ROT_CLOUD = register("rot_cloud");
    public static final Supplier<SimpleParticleType> ROTTEN_GLINT = register("rotten_glint");
    public static final Supplier<SimpleParticleType> LIVING_LAVA = register("living_lava");

    public static Supplier<SimpleParticleType> register(String id) {
        return Services.REGISTRY.registerParticle(DungeonsDelight.MOD_ID, id);
    }

    public static void load() {
    }
}
