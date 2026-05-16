package net.yirmiri.dungeonsdelight;

import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.core.registry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        DDSounds.load();
        DDSoundTypes.init(); //what
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
    }
}
//todo add new crops to changelog
//todo wild crops
//todo husk has rare chance to drop manallium
//todo drowned has rare chance to drop endelve