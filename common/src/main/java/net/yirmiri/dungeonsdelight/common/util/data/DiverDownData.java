package net.yirmiri.dungeonsdelight.common.util.data;

import net.yirmiri.dungeonsdelight.DungeonsDelight;

public interface DiverDownData {
    int MAX_CHARGE = DungeonsDelight.CONFIG.getDiverDownMaxLengthTicks();
    float DIVER_DOWN_LAVA_SWIM_SPEED = 1.15F;

    String DIVER_DOWN_CHARGE = "DungeonsDelight:DiverDownCharge";
    String DIVER_DOWN_LAVA_SWIMMING = "DungeonsDelight:DiverDownLavaSwimming";

    int getCharge();

    void setCharge(int charge);

    boolean isLavaSwimming();

    void setLavaSwimming(boolean swimming);
}