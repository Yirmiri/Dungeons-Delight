package net.yirmiri.dungeonsdelight.integration.common;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.flag.FeatureFlagSet;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.common.item.food_types.SlimeFoodItem;

public class INSlimeFoodItem extends SlimeFoodItem {
    private String modid;

    public INSlimeFoodItem(String modid, Properties properties, float chance, boolean hasFoodEffectTooltip) {
        super(properties, chance, hasFoodEffectTooltip);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return (Services.PLATFORM.isModLoaded(modid) || DDConfigCommon.FORCE_ENABLE_INTEGRATION_FEATURES.get());
    }
}
