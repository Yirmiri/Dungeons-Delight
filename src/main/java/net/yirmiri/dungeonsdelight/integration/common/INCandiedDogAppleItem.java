package net.yirmiri.dungeonsdelight.integration.common;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.common.item.EXPCandiedFoodItem;
import net.yirmiri.dungeonsdelight.integration.appledog.ADItems;

public class INCandiedDogAppleItem extends EXPCandiedFoodItem {
    private String modid;

    public INCandiedDogAppleItem(String modid, Properties properties, int experience, boolean hasFoodEffectTooltip, boolean hasCustomTooltip, boolean smallFood) {
        super(properties, experience, hasFoodEffectTooltip, hasCustomTooltip, smallFood);
        this.modid = modid;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet flagSet) {
        return Services.PLATFORM.isModLoaded(modid) || DDConfigCommon.FORCE_ENABLE_COMPAT_ITEMS.get();
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (stack.is(ADItems.SCULK_DOGAPPLE.get())) {
            living.playSound(SoundEvents.WOLF_DEATH);
        } else living.playSound(SoundEvents.CAT_DEATH);
        return super.finishUsingItem(stack, level, living);
    }
}
