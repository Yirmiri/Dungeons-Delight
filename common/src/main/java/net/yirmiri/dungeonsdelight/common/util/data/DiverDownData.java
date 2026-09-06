package net.yirmiri.dungeonsdelight.common.util.data;

import net.yirmiri.dungeonsdelight.DungeonsDelight;

public interface DiverDownData {
    int MAX_CHARGE = DungeonsDelight.CONFIG.getDiverDownMaxLengthTicks();

    int getCharge();

    void setCharge(int charge);

    boolean isLavaSwimming();

    void setLavaSwimming(boolean swimming);
}