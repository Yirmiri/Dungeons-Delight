package net.yirmiri.dungeonsdelight.integration.common;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.flag.FeatureFlagSet;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.common.item.BiteableItem;

public class INBiteableItem extends BiteableItem {
    private String modid;

    public INBiteableItem(String modid, Properties properties, boolean hasPotionEffectTooltip) {
        super(properties, hasPotionEffectTooltip);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return Services.PLATFORM.isModLoaded(modid) || DDConfigCommon.FORCE_ENABLE_COMPAT_ITEMS.get();
    }
}
