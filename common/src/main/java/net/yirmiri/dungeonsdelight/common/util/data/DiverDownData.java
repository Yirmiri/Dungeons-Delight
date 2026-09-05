package net.yirmiri.dungeonsdelight.common.util.data;

public interface DiverDownData {
    int MAX_CHARGE = 140;

    int getCharge();

    void setCharge(int charge);

    boolean isLavaSwimming();

    void setLavaSwimming(boolean swimming);
}