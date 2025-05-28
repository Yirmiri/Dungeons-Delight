package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DDParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DungeonsDelight.MOD_ID);

    //PARTICLES
    public static final RegistryObject<SimpleParticleType> LIVING_FLAME = PARTICLE_TYPES.register("living_flame", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SKULL_HEART_BLAST = PARTICLE_TYPES.register("skull_heart_blast", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DUNGEON_BUBBLE = PARTICLE_TYPES.register("dungeon_bubble", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DECISIVE_CRITICAL = PARTICLE_TYPES.register("decisive_critical", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> ROTTEN_GLINT = PARTICLE_TYPES.register("rotten_glint", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MONSTER_SMOKE = PARTICLE_TYPES.register("monster_smoke", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MONSTER_STEAM = PARTICLE_TYPES.register("monster_steam", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> ROT_CLOUD = PARTICLE_TYPES.register("rot_cloud", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(LIVING_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(DUNGEON_BUBBLE.get(), BubblePopParticle.Provider::new);
        event.registerSpriteSet(SKULL_HEART_BLAST.get(), SonicBoomParticle.Provider::new);
        event.registerSpriteSet(DECISIVE_CRITICAL.get(), CritParticle.Provider::new);
        event.registerSpriteSet(ROTTEN_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
        event.registerSpriteSet(MONSTER_SMOKE.get(), CampfireSmokeParticle.CosyProvider::new);
        event.registerSpriteSet(MONSTER_STEAM.get(), CampfireSmokeParticle.CosyProvider::new);
        event.registerSpriteSet(ROT_CLOUD.get(), SuspendedTownParticle.HappyVillagerProvider::new);
    }
}