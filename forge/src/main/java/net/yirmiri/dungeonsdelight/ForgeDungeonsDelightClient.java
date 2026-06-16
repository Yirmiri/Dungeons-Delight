package net.yirmiri.dungeonsdelight;

import net.minecraft.SharedConstants;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.resource.PathPackResources;
import net.yirmiri.dungeonsdelight.common.block.entity.item_grate.ItemGrateBlockEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.cleaver.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.particle.AnimatedFlameParticle;
import net.yirmiri.dungeonsdelight.common.particle.FlameEffectParticle;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

import java.nio.file.Path;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeDungeonsDelightClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        DungeonsDelightClient.init();
    }

    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DDParticles.LIVING_FLAME.get(), AnimatedFlameParticle.Provider::new);
        event.registerSpriteSet(DDParticles.LIVING_FLAME_EFFECT.get(), FlameEffectParticle.Provider::new);
        event.registerSpriteSet(DDParticles.EXUDATION_BLAST.get(), SonicBoomParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DDBlockEntities.ITEM_GRATE.get(), ItemGrateBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void setupColorblindPack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(DungeonsDelight.MOD_ID).getFile().findResource("resourcepacks/dungeonsdelight_classic");
            PathPackResources packResources = new PathPackResources(ModList.get().getModFileById(DungeonsDelight.MOD_ID).getFile().getFileName() + ":"
                    + resourcePath, true, resourcePath);
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("resourcepacks.dungeonsdelight.dungeonsdelight_classic.desc"),
                    SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create("builtin/dungeonsdelight_classic",
                            Component.translatable("resourcepacks.dungeonsdelight.dungeonsdelight_classic.title"), false,
                            (string) -> packResources, new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA),
                                    metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), packResources.isHidden()),
                            PackType.CLIENT_RESOURCES, Pack.Position.TOP, false, PackSource.BUILT_IN)));
        }
    }
}
