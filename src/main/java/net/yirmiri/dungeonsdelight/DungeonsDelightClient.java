package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.particle.BubblePopParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.client.render.RenderLayer;
import net.yirmiri.dungeonsdelight.client.DungeonPotScreen;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDScreenHandlers;
import net.yirmiri.dungeonsdelight.registry.DDParticles;
import vectorwing.farmersdelight.client.particle.SteamParticle;

@Environment(EnvType.CLIENT)
public class DungeonsDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //CUTOUT
        BlockRenderLayerMap.INSTANCE.putBlock(DDBlocks.DUNGEON_POT, RenderLayer.getCutout());

        //PARTICLES
        ParticleFactoryRegistry.getInstance().register(DDParticles.DUNGEON_FLAME, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.DUNGEON_BUBBLE, BubblePopParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DDParticles.SKULL_HEART_BLAST, SonicBoomParticle.Factory::new);

        //MISC
        HandledScreens.register(DDScreenHandlers.DUNGEON_POT_MENU, DungeonPotScreen::new);
    }
}
