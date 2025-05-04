package net.yirmiri.dungeonsdelight.integration.common;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.flag.FeatureFlagSet;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class INDrinkableItem extends DrinkableItem {
    private String modid;

    public INDrinkableItem(String modid, Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasPotionEffectTooltip, hasCustomTooltip);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return Services.PLATFORM.isModLoaded(modid) || DDConfigCommon.FORCE_ENABLE_COMPAT_ITEMS.get();
    }
}
