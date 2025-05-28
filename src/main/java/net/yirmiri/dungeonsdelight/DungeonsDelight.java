package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.yirmiri.dungeonsdelight.core.event.DDClientEvents;
import net.yirmiri.dungeonsdelight.core.event.DDCommonEvents;
import net.yirmiri.dungeonsdelight.core.registry.*;
import net.yirmiri.dungeonsdelight.integration.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;
import org.slf4j.Logger;

@Mod(DungeonsDelight.MOD_ID)
public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonsDelight(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, DDConfigCommon.COMMON, "dungeonsdelight-config.toml");
        modContainer.registerConfig(ModConfig.Type.CLIENT, DDConfigClient.CLIENT, "dungeonsdelight-client-config.toml");

        //NeoForge.EVENT_BUS.register(this);

        DDParticles.PARTICLE_TYPES.register(modEventBus);
        DDBlocks.BLOCKS.register(modEventBus);
        DDItems.ITEMS.register(modEventBus);
        DDEffects.MOB_EFFECTS.register(modEventBus);
        DDBlockEntities.BE_TYPES.register(modEventBus);
        //DDRecipeRegistries.RECIPE_SERIALIZERS.register(modEventBus);
        //DDRecipeRegistries.RECIPE_TYPES.register(modEventBus);
        //DDMenuTypes.MENU_TYPES.register(modEventBus);
        DDCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        DDEntities.ENTITIES.register(modEventBus);
        DDLootFunctions.LOOT_FUNCTIONS.register(modEventBus);
        DDSounds.SOUNDS.register(modEventBus);
        //DDEnchantments.ENCHANTMENTS.register(modEventBus);

        //INTEGRATION
        ADItems.ITEMS.register(modEventBus);
        TFItems.ITEMS.register(modEventBus);

        modEventBus.addListener(DDCommonEvents::commonSetup);
        modEventBus.addListener(DDClientEvents::clientSetup);
        modEventBus.addListener(this::registerBlockRenderLayers);
        modEventBus.addListener(DDCommonEvents::addEntityAttributes);
        modEventBus.addListener(DDClientEvents::onEntityRendererRegister);
        modEventBus.addListener(DDClientEvents::onEntityRendererLayerRegister);
        modEventBus.addListener(DDClientEvents::onRegisterRenderers);
        //modEventBus.addListener(DDClientEvents::registerOverlays);
        //modEventBus.addListener(DDDatagen::gatherData);
        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);
    } // Magmaroni when - Hecco
//TODO: Meat (bug chops, fried bug chops, silverfish thorax, spider meat, smoked spider meat, ghast tentacle, ghast calamari, rotten tripe)
    public void registerBlockRenderLayers(final FMLClientSetupEvent event) {
        //CUTOUT
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.MONSTER_POT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_POTATOES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_TOMATOES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WALL_LIVING_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_CAMPFIRE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_CHAIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMROOTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.GUNK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.GUARDIAN_ANGEL_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.CANDLE_MONSTER_CAKE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_GRATE.get(), RenderType.cutout());

        //CUTOUT MIPPED
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_CROP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_BARS.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_FIRE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_SPAWNER.get(), RenderType.cutoutMipped());
    }
}
