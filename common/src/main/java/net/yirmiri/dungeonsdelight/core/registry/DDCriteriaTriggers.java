package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.advancements.CriterionTrigger;
import net.yirmiri.dungeonsdelight.common.advancement.MonsterizeEffectTrigger;
import net.yirmiri.dungeonsdelight.core.mixin.CriteriaTriggersAccessor;

import java.util.function.Supplier;

public class DDCriteriaTriggers {
    public static final MonsterizeEffectTrigger MONSTERIZE_EFFECT = register(new MonsterizeEffectTrigger());

    private static <T extends CriterionTrigger<?>> T register(T criterion) {
        return CriteriaTriggersAccessor.register(criterion);
    }

    public static void load() {
    }
}
