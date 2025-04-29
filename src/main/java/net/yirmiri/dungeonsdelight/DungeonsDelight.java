package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yirmiri.dungeonsdelight.common.event.DDClientEvents;
import net.yirmiri.dungeonsdelight.common.event.DDCommonEvents;
import net.yirmiri.dungeonsdelight.core.registry.*;
import org.slf4j.Logger;

@Mod(DungeonsDelight.MOD_ID)
public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonsDelight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DungeonsDelightConfig.COMMON, "dungeonsdelight-config.toml");

        DDBlocks.BLOCKS.register(modEventBus);
        DDItems.ITEMS.register(modEventBus);
        DDEffects.MOB_EFFECTS.register(modEventBus);
        DDParticles.PARTICLE_TYPES.register(modEventBus);
        DDBlockEntities.BE_TYPES.register(modEventBus);
        DDRecipeRegistries.RECIPE_SERIALIZERS.register(modEventBus);
        DDRecipeRegistries.RECIPE_TYPES.register(modEventBus);
        DDMenuTypes.MENU_TYPES.register(modEventBus);
        DDCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        DDEntities.ENTITIES.register(modEventBus);
        DDLootFunctions.LOOT_FUNCTIONS.register(modEventBus);
        DDSounds.SOUNDS.register(modEventBus);
        DDEnchantments.ENCHANTMENTS.register(modEventBus);

        modEventBus.addListener(DDCommonEvents::commonSetup);
        modEventBus.addListener(DDClientEvents::clientSetup);
        modEventBus.addListener(DDClientEvents::onEntityRendererRegister);
        modEventBus.addListener(DDCommonEvents::addEntityAttributes);
        modEventBus.addListener(DDClientEvents::onEntityRendererLayerRegister);
        modEventBus.addListener(DDClientEvents::registerOverlays);
        modEventBus.addListener(DungeonsDelightDatagen::gatherData);
        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
