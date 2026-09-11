package net.yirmiri.dungeonsdelight.common.util.data;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

public interface DiverDownData {
    int MAX_CHARGE = DungeonsDelight.CONFIG.getDiverDownMaxLengthTicks();
    float DIVER_DOWN_LAVA_SWIM_SPEED = 1.15F;

    String DIVER_DOWN_CHARGE = "DungeonsDelight:DiverDownCharge";
    String DIVER_DOWN_LAVA_SWIMMING = "DungeonsDelight:DiverDownLavaSwimming";

    static boolean isLavaSwimming(LivingEntity entity) {
        if (!(entity instanceof DiverDownData data) || !entity.hasEffect(DDEffects.DIVER_DOWN.get())) return false;

        boolean creative = (entity instanceof Player player && player.getAbilities().instabuild);
        if (!creative && data.getCharge() <= 0) return false;

        return (entity.isInLava() && entity.isSprinting() && entity.isEyeInFluid(FluidTags.LAVA));
    }

    int getCharge();
    void setCharge(int charge);
    boolean isLavaSwimming();
    void setLavaSwimming(boolean swimming);
}