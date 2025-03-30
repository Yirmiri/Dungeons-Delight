package net.yirmiri.dungeonsdelight.common.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.common.entity.misc.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityModel;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityRenderer;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDMenuTypes;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDClientEvents {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        registerBlockRenderLayers();

        event.enqueueWork(() -> MenuScreens.register(DDMenuTypes.MONSTER_POT.get(), MonsterPotScreen::new));

        Sheets.addWoodType(DDBlockSetTypes.WORMWOOD);
    }

    public static void registerBlockRenderLayers() {
        //CUTOUT
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.MONSTER_POT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_POTATOES.get(), RenderType.cutout());

        //CUTOUT MIPPED
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_CROP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_BARS.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.GUARDIAN_ANGEL_BLOCK.get(), RenderType.cutoutMipped());

        //TRANSLUCENT
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMROOTS.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void onEntityRendererLayerRegister(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MonsterYamEntityModel.LAYER_LOC, MonsterYamEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onEntityRendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.ANCIENT_EGG.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.MONSTER_YAM.get(), MonsterYamEntityRenderer::new);
        //event.registerEntityRenderer(DDEntities.GUNK_ARROW.get(), GunkArrowRenderer::new);
    }
}
