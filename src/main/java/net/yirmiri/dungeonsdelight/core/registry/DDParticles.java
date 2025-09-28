package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

@EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DDParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, DungeonsDelight.MOD_ID);

    //MISC
    public static final Supplier<SimpleParticleType> LIVING_FLAME = PARTICLE_TYPES.register("living_flame", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> SPIRIT_FLAME = PARTICLE_TYPES.register("spirit_flame", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> SKULL_HEART_BLAST = PARTICLE_TYPES.register("skull_heart_blast", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> DUNGEON_BUBBLE = PARTICLE_TYPES.register("dungeon_bubble", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> DECISIVE_CRITICAL = PARTICLE_TYPES.register("decisive_critical", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> ROTTEN_GLINT = PARTICLE_TYPES.register("rotten_glint", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> MONSTER_SMOKE = PARTICLE_TYPES.register("monster_smoke", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> MONSTER_STEAM = PARTICLE_TYPES.register("monster_steam", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> ROT_CLOUD = PARTICLE_TYPES.register("rot_cloud", () -> new SimpleParticleType(true));

    //EFFECT
    public static final Supplier<SimpleParticleType> DECISIVE = PARTICLE_TYPES.register("decisive", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> EXUDATION = PARTICLE_TYPES.register("exudation", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> TENACITY = PARTICLE_TYPES.register("tenacity", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> VORACITY = PARTICLE_TYPES.register("voracity", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> BURROW_GUT = PARTICLE_TYPES.register("burrow_gut", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> POUNCING = PARTICLE_TYPES.register("pouncing", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> SWIFT_STEP = PARTICLE_TYPES.register("swift_step", () -> new SimpleParticleType(true));

    @OnlyIn(Dist.CLIENT) @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        //MISC
        event.registerSpriteSet(LIVING_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(SPIRIT_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(DUNGEON_BUBBLE.get(), BubblePopParticle.Provider::new);
        event.registerSpriteSet(SKULL_HEART_BLAST.get(), SonicBoomParticle.Provider::new);
        event.registerSpriteSet(DECISIVE_CRITICAL.get(), CritParticle.Provider::new);
        event.registerSpriteSet(ROTTEN_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
        event.registerSpriteSet(MONSTER_SMOKE.get(), CampfireSmokeParticle.CosyProvider::new);
        event.registerSpriteSet(MONSTER_STEAM.get(), CampfireSmokeParticle.CosyProvider::new);
        event.registerSpriteSet(ROT_CLOUD.get(), SuspendedTownParticle.HappyVillagerProvider::new);

        //EFFECT
        event.registerSpriteSet(DECISIVE.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(EXUDATION.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(TENACITY.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(VORACITY.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(BURROW_GUT.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(POUNCING.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(SWIFT_STEP.get(), SpellParticle.Provider::new);
    }
}