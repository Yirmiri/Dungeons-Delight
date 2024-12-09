package net.yirmiri.dungeonsdelight.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDParticles {
    public static final SimpleParticleType DUNGEON_FLAME = FabricParticleTypes.simple();

    public static void loadParticles() {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(DungeonsDelight.MOD_ID, "dungeon_flame"), DUNGEON_FLAME);
    }
}
