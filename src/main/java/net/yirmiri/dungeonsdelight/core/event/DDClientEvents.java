package net.yirmiri.dungeonsdelight.core.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.DungeonStoveBlockEntityRenderer;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.common.entity.misc.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityModel;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityRenderer;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDMenuTypes;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDClientEvents {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(DDMenuTypes.MONSTER_POT.get(), MonsterPotScreen::new));
        Sheets.addWoodType(DDBlockSetTypes.WORMWOOD);
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerBelowAll("ravenous_rush_vignette", new RavenousRushVignette());
        event.registerBelowAll("voracity_overlay", new VoracityOverlay());
    }

    @SubscribeEvent
    public static void onEntityRendererLayerRegister(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MonsterYamEntityModel.LAYER_LOC, MonsterYamEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DDBlockEntities.DUNGEON_STOVE.get(), DungeonStoveBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onEntityRendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.ANCIENT_EGG.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.MONSTER_YAM.get(), MonsterYamEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.RANCID_REDUCTION.get(), ThrownItemRenderer::new);
    }
}
