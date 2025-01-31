package net.yirmiri.dungeonsdelight.item.compat;

import net.minecraft.world.flag.FeatureFlagSet;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class CompatDrinkableItem extends DrinkableItem {
    private final String modid;

    public CompatDrinkableItem(String modid, Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasPotionEffectTooltip, hasCustomTooltip);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return DungeonsDelight.isModLoaded(modid);
    }
}
