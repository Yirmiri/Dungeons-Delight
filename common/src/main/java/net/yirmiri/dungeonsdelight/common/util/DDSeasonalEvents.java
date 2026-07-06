package net.yirmiri.dungeonsdelight.common.util;

import java.time.LocalDate;
import java.time.Month;

public class DDSeasonalEvents {
    public final boolean IS_APRIL_FOOLS;
    public final boolean IS_ANNIVERSARY;
    public final boolean IS_HALLOWEEN;

    public DDSeasonalEvents() {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        Month month = localDate.getMonth();

        this.IS_APRIL_FOOLS = month == Month.APRIL && day == 1;
        this.IS_ANNIVERSARY = month == Month.MAY && day == 5;
        this.IS_HALLOWEEN = this.isHalloween(day, month);
    }

    private boolean isHalloween(int day, Month month) {
        return month == Month.OCTOBER && day >= 22 || month == Month.NOVEMBER && day <= 4;
    }
}