package net.yirmiri.dungeonsdelight;

import net.yirmiri.dungeonsdelight.core.registry.DDCreativeTabs;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        DDItems.load();
        DDCreativeTabs.load();
    }
}