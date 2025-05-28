package net.yirmiri.dungeonsdelight.core.event;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityModel;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityRenderer;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

@EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDClientEvents {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
//        event.enqueueWork(() -> MenuScreens.register(DDMenuTypes.MONSTER_POT.get(), MonsterPotScreen::new));
        Sheets.addWoodType(DDBlockSetTypes.WORMWOOD);
    }

//    @SubscribeEvent
//    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
//        event.registerBelowAll("ravenous_rush_vignette", new RavenousRushEffectOverlay());
//        event.registerBelowAll("voracity_overlay", new VoracityEffectOverlay());
//    }

    @SubscribeEvent
    public static void onEntityRendererLayerRegister(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MonsterYamEntityModel.LAYER_LOC, MonsterYamEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //event.registerBlockEntityRenderer(DDBlockEntities.DUNGEON_STOVE.get(), DungeonStoveBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(DDBlockEntities.LIVING_CAMPFIRE.get(), CampfireRenderer::new);
    }

    @SubscribeEvent
    public static void onEntityRendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.ANCIENT_EGG.get(), ThrownItemRenderer::new);
        //event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.MONSTER_YAM.get(), MonsterYamEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.RANCID_REDUCTION.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void onItemTooltipEvent(ItemTooltipEvent event) {

    }
}
