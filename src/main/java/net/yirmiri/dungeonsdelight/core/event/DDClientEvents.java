package net.yirmiri.dungeonsdelight.core.event;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.DungeonStoveBlockEntityRenderer;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.common.entity.misc.GunkArrowRenderer;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityModel;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.rotten_zombie.RottenZombieRenderer;
import net.yirmiri.dungeonsdelight.core.event.overlay.effect.RavenousRushEffectOverlay;
import net.yirmiri.dungeonsdelight.core.event.overlay.effect.VoracityEffectOverlay;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDMenuTypes;

import java.util.function.Supplier;

@EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DDClientEvents {
    @SubscribeEvent
    public static void registerBlockRenderLayers(final FMLClientSetupEvent event) {
        //CUTOUT
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.MONSTER_POT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_POTATOES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_TOMATOES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WALL_LIVING_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_CAMPFIRE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMROOT_TENDRILS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.GUNK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.GUARDIAN_ANGEL_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.CANDLE_MONSTER_CAKE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_GRATE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.SILVERFISH_AND_CHIPS_BLOCK.get(), RenderType.cutout());

        //CUTOUT MIPPED
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_CROP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_BARS.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_FIRE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_SPAWNER.get(), RenderType.cutoutMipped());

        //TRANSLUCENT
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        Sheets.addWoodType(DDBlockSetTypes.WORMWOOD);
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(DDMenuTypes.MONSTER_POT.get(), MonsterPotScreen::new);
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        //event.registerAboveAll("voracity_gui_overlay", new VoracityGUIOverlay());
        //event.registerAboveAll("burrow_gut_gui_overlay", new BurrowGutGUIOverlay());
        event.registerBelowAll(RunicLib.customid(DungeonsDelight.MOD_ID, "ravenous_rush_vignette"), new RavenousRushEffectOverlay());
        event.registerBelowAll(RunicLib.customid(DungeonsDelight.MOD_ID, "voracity_overlay"), new VoracityEffectOverlay());
    }

    @SubscribeEvent
    public static void registerRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MonsterYamEntityModel.LAYER_LOC, MonsterYamEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        Supplier<LayerDefinition> main = () -> LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64);
        Supplier<LayerDefinition> innerArmor = () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION), 64, 32);
        Supplier<LayerDefinition> outerArmor = () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32);
        event.registerLayerDefinition(DDModelLayers.ROTTEN_ZOMBIE, main);
        event.registerLayerDefinition(DDModelLayers.ROTTEN_ZOMBIE_INNER_ARMOR, innerArmor);
        event.registerLayerDefinition(DDModelLayers.ROTTEN_ZOMBIE_OUTER_ARMOR, outerArmor);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DDBlockEntities.DUNGEON_STOVE.get(), DungeonStoveBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(DDBlockEntities.LIVING_CAMPFIRE.get(), CampfireRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.ANCIENT_EGG.get(), ThrownItemRenderer::new);
        //event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.MONSTER_YAM.get(), MonsterYamEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.RANCID_REDUCTION.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(DDEntities.GUNK_ARROW.get(), GunkArrowRenderer::new);
        event.registerEntityRenderer(DDEntities.ROTTEN_ZOMBIE.get(), RottenZombieRenderer::new);
    }

//    @SubscribeEvent
//    public static void onItemTooltipEvent(ItemTooltipEvent event) {
//
//    }
}
