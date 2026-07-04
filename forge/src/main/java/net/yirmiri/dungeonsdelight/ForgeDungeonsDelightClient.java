package net.yirmiri.dungeonsdelight;

import net.minecraft.SharedConstants;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
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
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.resource.PathPackResources;
import net.yirmiri.dungeonsdelight.common.block.entity.item_grate.ItemGrateRenderer;
import net.yirmiri.dungeonsdelight.common.block.entity.wavy_block.WavyRenderer;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskEntity;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskModel;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskRenderer;
import net.yirmiri.dungeonsdelight.common.entity.misc.EmptyEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.particle.*;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;
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
        event.registerSpriteSet(DDParticles.SINGLE_LIVING_FLAME.get(), FlameEffectParticle.Provider::new);
        event.registerSpriteSet(DDParticles.EXUDATION_BLAST.get(), SonicBoomParticle.Provider::new);
        event.registerSpriteSet(DDParticles.LARGE_ECHO_BLAST.get(), EchoBlastParticle.Large::new);
        event.registerSpriteSet(DDParticles.MEDIUM_ECHO_BLAST.get(), EchoBlastParticle.Medium::new);
        event.registerSpriteSet(DDParticles.SMALL_ECHO_BLAST.get(), EchoBlastParticle.Small::new);
        event.registerSpriteSet(DDParticles.MONSTER_RESIDUE.get(), ResidueParticle.Provider::new);
        event.registerSpriteSet(DDParticles.MONSTER_STEAM.get(), CampfireSmokeParticle.CosyProvider::new);
        event.registerSpriteSet(DDParticles.DUNGEON_BUBBLE.get(), AnimatedParticle.Provider::new);
        event.registerSpriteSet(DDParticles.ROTTEN_RESIDUE.get(), ResidueParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.CAMEL_HUSK.get(), CamelHuskRenderer::new);
        event.registerEntityRenderer(DDEntities.ECHO_BLAST.get(), EmptyEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.ANCIENT_EGG.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(DDEntities.CAMEL_HUSK.get(), CamelHuskEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DDModelLayers.CAMEL_HUSK, CamelHuskModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DDBlockEntities.ITEM_GRATE.get(), ItemGrateRenderer::new);
        event.registerBlockEntityRenderer(DDBlockEntities.WAVY_BLOCK.get(), WavyRenderer::new);
    }

    @SubscribeEvent
    public static void registerPacks(AddPackFindersEvent event) {
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

        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(DungeonsDelight.MOD_ID).getFile().findResource("resourcepacks/dungeonsdelight_vanilla_overrides");
            PathPackResources packResources = new PathPackResources(ModList.get().getModFileById(DungeonsDelight.MOD_ID).getFile().getFileName() + ":"
                    + resourcePath, true, resourcePath);
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("resourcepacks.dungeonsdelight.dungeonsdelight_vanilla_overrides.desc"),
                    SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create("builtin/dungeonsdelight_vanilla_overrides",
                            Component.translatable("resourcepacks.dungeonsdelight.dungeonsdelight_vanilla_overrides.title"), true,
                            (string) -> packResources, new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA),
                                    metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), packResources.isHidden()),
                            PackType.CLIENT_RESOURCES, Pack.Position.TOP, false, PackSource.BUILT_IN)));
        }
    }
}
