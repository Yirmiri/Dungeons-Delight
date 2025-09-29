package net.yirmiri.dungeonsdelight.integration.common;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.flag.FeatureFlagSet;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.common.item.food_types.BiteableItem;

public class INBiteableItem extends BiteableItem {
    private String modid;

    public INBiteableItem(String modid, Properties properties, int stackSize, boolean hasPotionEffectTooltip) {
        super(properties, stackSize, hasPotionEffectTooltip);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return (Services.PLATFORM.isModLoaded(modid) || DDConfigCommon.FORCE_ENABLE_INTEGRATION_FEATURES.get());
    }
}
