package net.yirmiri.dungeonsdelight.item.compat;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class CompatItem extends Item {
    private final String modid;

    public CompatItem(String modid, Properties properties) {
        super(properties);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return DungeonsDelight.isModLoaded(modid);
    }
}
