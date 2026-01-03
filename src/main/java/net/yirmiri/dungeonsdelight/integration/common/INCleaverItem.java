package net.yirmiri.dungeonsdelight.integration.common;

import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Tier;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;

public class INCleaverItem extends CleaverItem {
    private String modid;

    public INCleaverItem(String modid, float range, Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(range, tier, attackDamage, attackSpeed, properties);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return (RLServices.PLATFORM.isModLoaded(modid) || DDConfigCommon.FORCE_ENABLE_INTEGRATION_FEATURES.get());
    }
}
