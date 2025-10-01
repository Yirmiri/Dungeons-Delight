package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.yirmiri.dungeonsdelight.core.registry.*;
import net.yirmiri.dungeonsdelight.integration.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.fishy_fiesta.FFItems;
import net.yirmiri.dungeonsdelight.integration.minersdelight.MDItems;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;
import org.slf4j.Logger;

@Mod(DungeonsDelight.MOD_ID)
public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonsDelight(IEventBus modEventBus, ModContainer modContainer) {
        //TODO FOR 1.4.0 MULTILOADER
        //VERSIONING FROM THERE ON WILL GO OVERHAUL/REWRITE, CONTENT UPDATE, PATCH
        //MOVE TO MULTILOADER TEMPLATE
        //UPDATE MOD LOADED CONDITION WITH RUNICLIB INSTEAD OF NEOFORGE
        //FIX META INF STUPID THING PLEASE FOR GOD SAKE YIRMIRI

        //TODO FOR FUTURE CONTENT UPDATE
        //BOGGED BRAIN FOODS REMEMBER AN EFFECT AND APPLIES IT
        //ROTBULBS IN TRIAL CHAMBERS
        //IMPROVE GLUTTONY SHERD GENERATION
        //CUSTOM MONSTER EFFECT PARTICLES
        //POLTERGEIST PIZZA CREEPER SQUIB INSTEAD OF CABBAGE
        //POLTERGEIST PIZZA TOPPING = EFFECT(?), CAN HAVE 3 TOPPINGS
        //OVERHAUL STAINED WEAPONS
        //JADEN'S NETHER EXPANSION INTEGRATION (NEW CONTENT)
        //NETHER'S DELIGHT INTEGRATION (NEW CONTENT)
        //AETHER INTEGRATION (NEW CONTENT)

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

        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);
    } // Magmaroni when - Hecco
}
