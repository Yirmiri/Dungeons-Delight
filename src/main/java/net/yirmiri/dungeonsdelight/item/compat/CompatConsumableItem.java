package net.yirmiri.dungeonsdelight.item.compat;

import net.minecraft.world.flag.FeatureFlagSet;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class CompatConsumableItem extends ConsumableItem {
    private final String modid;

    public CompatConsumableItem(String modid, Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasPotionEffectTooltip, hasCustomTooltip);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return DungeonsDelight.isModLoaded(modid);
    }
}
