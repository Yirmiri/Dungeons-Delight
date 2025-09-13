package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.advancements.CriteriaTriggers;
import net.yirmiri.dungeonsdelight.common.advancement.FeedWormouthTrigger;
import net.yirmiri.dungeonsdelight.common.advancement.SlimeFoodTrigger;

public class DDCriteriaTriggers {
    public static SlimeFoodTrigger SLIME_FOOD = new SlimeFoodTrigger();
    public static FeedWormouthTrigger FEED_WORMOUTH = new FeedWormouthTrigger();

    public static void loadCriteriaTriggers() {
        CriteriaTriggers.register(SLIME_FOOD);
        CriteriaTriggers.register(FEED_WORMOUTH);
    }
}
