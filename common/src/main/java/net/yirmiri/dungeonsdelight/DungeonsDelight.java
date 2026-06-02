package net.yirmiri.dungeonsdelight;

import net.azurune.runiclib.core.runiconfig.Runiconfig;
import net.yirmiri.dungeonsdelight.core.registry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static DungeonsDelightConfig CONFIG;

    public static void init() {
        Runiconfig.registerConfig(MOD_ID, DungeonsDelightConfig.class, DungeonsDelightConfig::new);
        CONFIG = Runiconfig.getConfig(MOD_ID);

        DDSounds.load();
        DDEffects.load();
        DDItems.load();
        DDBlocks.load();
        DDBlockEntities.load();
        DDFeatures.load();
        DDEntities.load();
        DDCreativeTabs.load();
        DDAttributes.load();
        DDEnchantments.load();
        DDParticles.load();
        DDRecipeTypes.load();
    }
}
//todo cleaver tech rework in changelog
//todo add new crops to changelog
//todo wild crops
//todo husk has rare chance to drop manallium
//todo drowned has rare chance to drop endelve
//todo serrated stacks up bleed per pierced entity instead of concurrent pierces, slower tick rate of damage per pierce maybe, maybe configurable
//todo make dungeondelight config have compat with forge config menu and whatever popular fabric mod had to do it themself it
//todo test all config values to be sure there are no crashes
//todo balance exudation, provide more configs
//todo heart and hunger icons for effects
//todo finish/concept decisive and burrow gut reworks
//todo classic pack on fabric