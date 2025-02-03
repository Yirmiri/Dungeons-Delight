package net.yirmiri.dungeonsdelight.item.compat;

import net.minecraft.world.flag.FeatureFlagSet;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.item.BiteableItem;

public class CompatBiteableItem extends BiteableItem {
    private final String modid;

    public CompatBiteableItem(String modid, Properties properties, boolean hasPotionEffectTooltip) {
        super(properties, hasPotionEffectTooltip);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return DungeonsDelight.isModLoaded(modid);
    }
}
