package net.yirmiri.dungeonsdelight.integration.common;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.flag.FeatureFlagSet;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class INConsumableItem extends ConsumableItem {
    private String modid;

    public INConsumableItem(String modid, Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasPotionEffectTooltip, hasCustomTooltip);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return Services.PLATFORM.isModLoaded(modid) || DDConfigCommon.FORCE_ENABLE_COMPAT_ITEMS.get();
    }
}
