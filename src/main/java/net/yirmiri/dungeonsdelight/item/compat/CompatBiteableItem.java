package net.yirmiri.dungeonsdelight.item.compat;

import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.item.BiteableItem;

public class CompatBiteableItem extends BiteableItem {
    private final String modid;

    public CompatBiteableItem(String modid, TagKey<Item> repairItem, Properties properties, boolean hasPotionEffectTooltip) {
        super(properties, repairItem, hasPotionEffectTooltip);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return DungeonsDelight.isModLoaded(modid);
    }
}
