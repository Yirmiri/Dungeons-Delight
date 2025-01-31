package net.yirmiri.dungeonsdelight.item.compat;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Tier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class CompatKnifeItem extends KnifeItem {
    private final String modid;

    public CompatKnifeItem(String modid, Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return DungeonsDelight.isModLoaded(modid);
    }
}
