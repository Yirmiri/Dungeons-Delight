package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yirmiri.dungeonsdelight.core.event.DDClientEvents;
import net.yirmiri.dungeonsdelight.core.event.DDCommonEvents;
import net.yirmiri.dungeonsdelight.core.registry.*;
import net.yirmiri.dungeonsdelight.integration.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;
import org.slf4j.Logger;

@Mod(DungeonsDelight.MOD_ID)
public class DungeonsDelight {
    //TODO FOR FINAL 1.20 UPDATE (not everything is listed)
    //cob n candy breaks in water
    //cleaver rebalance from 1.4
    //normal pot for some foods like amethyst and sniffer
    //sniffer foods cure monster effects and arent slow anymore
    //maybe add stained blocks from v1.3 if im really feeling in the mood
    //candied sucker buffs
    //new advancements from 1.21
    //cut ancient egg with cleaver mid air
    //hecco new cleaver animation updates and sound changes from v1.4
    //monster room updates
    //rotten dryad (maybe if im feeling nice)
    //stat buffs from previous updates
    //glowberry -> monster mousse
    //zoglin and zombie pigman from rotten tripe with knife
    //Re-parented get_netherite_cleaver advancement to use_cleaver
    //port most fixes
    //Any instance of "...can be eaten multiple times..." has been changed to "...can be chewed multiple times..."
    //ravenous rush is not a monster effect but keeps colored text
    //rotten zombie not burn in day
    //recipe book
    //zombie horse drop tripe with knife
    //remove stained knife advancement
    //reorder creative

    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonsDelight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DDConfigCommon.COMMON, "dungeonsdelight-config.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DDConfigClient.CLIENT, "dungeonsdelight-client-config.toml");

        MinecraftForge.EVENT_BUS.register(this);

        DDParticles.PARTICLE_TYPES.register(modEventBus);
        DDBlocks.BLOCKS.register(modEventBus);
        DDItems.ITEMS.register(modEventBus);
        DDEffects.MOB_EFFECTS.register(modEventBus);
        DDBlockEntities.BE_TYPES.register(modEventBus);
        DDRecipeRegistries.RECIPE_SERIALIZERS.register(modEventBus);
        DDRecipeRegistries.RECIPE_TYPES.register(modEventBus);
        DDMenuTypes.MENU_TYPES.register(modEventBus);
        DDCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        DDEntities.ENTITIES.register(modEventBus);
        DDLootFunctions.LOOT_FUNCTIONS.register(modEventBus);
        DDSounds.SOUNDS.register(modEventBus);
        DDEnchantments.ENCHANTMENTS.register(modEventBus);
        DDFeatures.FEATURES.register(modEventBus);
        DDCriteriaTriggers.loadCriteriaTriggers();

        //INTEGRATION
        ADItems.ITEMS.register(modEventBus);
        TFItems.ITEMS.register(modEventBus);

        modEventBus.addListener(DDCommonEvents::commonSetup);
        modEventBus.addListener(DDClientEvents::clientSetup);
        modEventBus.addListener(this::registerBlockRenderLayers);
        modEventBus.addListener(DDCommonEvents::registerEntityAttributes);
        modEventBus.addListener(DDClientEvents::registerEntityRenderers);
        modEventBus.addListener(DDClientEvents::registerRenderLayers);
        modEventBus.addListener(DDClientEvents::registerLayerDefinitions);
        modEventBus.addListener(DDClientEvents::registerRenderers);
        modEventBus.addListener(DDClientEvents::registerOverlays);
        modEventBus.addListener(DDDatagen::gatherData);
        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);
    } // Magmaroni when - Hecco

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
}
