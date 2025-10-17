package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.yirmiri.dungeonsdelight.core.registry.*;
import net.yirmiri.dungeonsdelight.integration.content.aether.AEItems;
import net.yirmiri.dungeonsdelight.integration.content.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.content.fishy_fiesta.FFItems;
import net.yirmiri.dungeonsdelight.integration.content.minersdelight.MDItems;
import net.yirmiri.dungeonsdelight.integration.content.twilightforest.TFItems;
import org.slf4j.Logger;

@Mod(DungeonsDelight.MOD_ID)
public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonsDelight(IEventBus modEventBus, ModContainer modContainer) {
        //TODO FOR 1.4.0
        //Gluttony Sherd obtain

        //TODO FOR MULTILOADER UPDATE
        //Stained Weapons overhaul
        //Move to Multiloader Template
        //Clean up codebase
        //Mod loaded condition needs to be in RunicLib instead of NeoForge for recipes

        modContainer.registerConfig(ModConfig.Type.COMMON, DDConfigCommon.COMMON, "dungeonsdelight-config.toml");
        modContainer.registerConfig(ModConfig.Type.CLIENT, DDConfigClient.CLIENT, "dungeonsdelight-client-config.toml");

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
        DDSounds.SOUNDS.register(modEventBus);
        DDDataComponents.ENCHANTMENT.register(modEventBus);
        DDFeatures.FEATURES.register(modEventBus);
        DDCriteriaTriggers.TRIGGERS.register(modEventBus);

        //INTEGRATION
        ADItems.ITEMS.register(modEventBus);
        TFItems.ITEMS.register(modEventBus);
        FFItems.ITEMS.register(modEventBus);
        MDItems.ITEMS.register(modEventBus);
        AEItems.ITEMS.register(modEventBus);

        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);
    } // Magmaroni when - Hecco
}
