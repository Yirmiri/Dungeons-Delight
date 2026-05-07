package net.yirmiri.dungeonsdelight;

import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.core.registry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        DDItems.load();
        DDBlocks.load();
        DDSounds.load();
        DDEntities.load();
        DDCreativeTabs.load();
        DDEffects.load();
        DDAttributes.load();
    }
}