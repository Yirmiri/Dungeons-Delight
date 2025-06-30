package net.yirmiri.dungeonsdelight.integration.common;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class INKnifeItem extends KnifeItem {
    private String modid;

    public INKnifeItem(String modid, Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.modid = modid;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.is(DDTags.ItemT.FLAMING_KNIVES)) {
            target.setRemainingFireTicks(target.getRemainingFireTicks() + 80);
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return (Services.PLATFORM.isModLoaded(modid) || DDConfigCommon.FORCE_ENABLE_COMPAT_ITEMS.get() || !DDConfigCommon.DISABLE_CONTENT_INTEGRATION.get());
    }
}
