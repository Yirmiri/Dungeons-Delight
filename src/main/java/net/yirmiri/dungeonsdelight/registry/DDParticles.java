package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.client.particle.BubblePopParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DungeonsDelight.MOD_ID);

    //PARTICLES
    public static final RegistryObject<SimpleParticleType> DUNGEON_FLAME = PARTICLE_TYPES.register("dungeon_flame", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SKULL_BLAST = PARTICLE_TYPES.register("skull_blast", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DUNGEON_BUBBLE = PARTICLE_TYPES.register("dungeon_bubble", () -> new SimpleParticleType(true));

    @OnlyIn(Dist.CLIENT) @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DUNGEON_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(DUNGEON_BUBBLE.get(), BubblePopParticle.Provider::new);
        event.registerSpriteSet(SKULL_BLAST.get(), SonicBoomParticle.Provider::new);
    }
}
