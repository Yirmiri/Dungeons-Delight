package net.yirmiri.dungeonsdelight.core.integration;

import net.azurune.runiclib.core.platform.Services;
import net.yirmiri.dungeonsdelight.core.integration.nirvana.NVEffects;
import net.yirmiri.dungeonsdelight.core.integration.nirvana.NVItems;

public class DDIntegration {
    public static final String AE_ID = "aether";
    public static final String BF_ID = "bountifulfares";
    public static final String FR_ID = "frontiers";
    public static final String TF_ID = "twilightforest";
    public static final String CC_ID = "cannibal_conundrum";
    public static final String NV_ID = "nirvana";

    public static boolean anyContentIntegrationLoaded() {
        return Services.PLATFORM.isModLoaded(DDIntegration.NV_ID);
    }

    public static void load() {
        if (DDIntegration.anyContentIntegrationLoaded()) {
            DDIntegrationTabs.load();
        }

        if (Services.PLATFORM.isModLoaded(DDIntegration.NV_ID)) {
            NVItems.load();
            NVEffects.load();
        }
    }
}
