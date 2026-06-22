package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.advancements.CriterionTrigger;
import net.yirmiri.dungeonsdelight.core.mixin.CritTrigAccessor;

public class DDCriteriaTriggers {
    private static <T extends CriterionTrigger<?>> T register(T criterion) {
        return CritTrigAccessor.register(criterion);
    }
}
