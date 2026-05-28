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
        DDSounds.load();
        DDItems.load();
        DDBlocks.load();
        DDBlockEntities.load();
        DDFeatures.load();
        DDEntities.load();
        DDCreativeTabs.load();
        DDEffects.load();
        DDAttributes.load();
        DDEnchantments.load();
        DDParticles.load();
        DDRecipeTypes.load();

        Runiconfig.registerConfig(MOD_ID, DungeonsDelightConfig.class, DungeonsDelightConfig::new);
        CONFIG = Runiconfig.getConfig(MOD_ID);
    }
}
//todo cleaver tech rework in changelog
//todo add new crops to changelog
//todo wild crops
//todo husk has rare chance to drop manallium
//todo drowned has rare chance to drop endelve
//todo serrated stacks up bleed per pierced entity instead
//todo make dungeondelight config have compat with forge config menu and whatever popular fabric mod had to do it themself it
//todo config for cleaver stats since people think they are op