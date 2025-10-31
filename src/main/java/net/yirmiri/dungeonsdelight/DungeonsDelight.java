package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.yirmiri.dungeonsdelight.core.registry.*;
import net.yirmiri.dungeonsdelight.integration.content.aether.AEItems;
import net.yirmiri.dungeonsdelight.integration.content.alloyed.ALItems;
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
        ALItems.ITEMS.register(modEventBus);

        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);
    } // Magmaroni when - Hecco

    //TODO CLEAVER REBALANCE
    //Charging cleaver now affects velocity and in turn range,, damage is stagnant so charge level feels optional
    //The more a cleaver is charged the more it pierces (max = current pierce)
    //New bar where attack indicator is to visualize cleaver charge, cuts at different levels to signify major bonuses like pierce or max velocity
    //When a cleaver is max charge a missed shot will have reduced cooldown to compensate
    //For Serrated instead of +2s per hit you now add +4s (+6s full charge) per hit to a max of +12s
    //Serrated damage tick rate increase 25% faster
    //Pierced mobs only get Serrated +2s per hit unless cleaver is fully charged then its +4s

    //TODO FOR MULTILOADER UPDATE + STANDALONE UPDATE(depending on FD's current version?)
    //Trial vault rewards drop stained scrap
    //Clean/add visuals in mod
    //Add new sounds
    //Gluttony Sherd obtain from Rot dungeons
    //Rot dungeons overhaul to be feature
    //New disc from Artyrian
    //Creeper foods
    //Stained Weapons overhaul
    //Move to Multiloader Template
    //Clean up codebase
    //Mod loaded condition needs to be in RunicLib instead of NeoForge for recipes
    //Composter changes
    //Rotgourd beacon function
    //Cleavers break into particles on despawn
    //Cuttable foods like pies can be cut with thrown Cleavers
    //Wormwood Boat is now a Raft(?)
    //Double stacked monster burger
    //Wormwood signs/hanging signs

    //TODO FOR ""IF"" WE DO STANDALONE UPDATE
    //Cleavers from Dispeners drop cut foods
    //Stained Cauldron that can be stirred for Monster Pot
    //Cleavers thrown at floating items instead of Cutting Board
}
