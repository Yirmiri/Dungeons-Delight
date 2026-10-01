package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.advancements.CriterionTrigger;
import net.yirmiri.dungeonsdelight.common.advancement.*;
import net.yirmiri.dungeonsdelight.core.mixin.CriteriaTriggersAccessor;

public class DDCriteriaTriggers {
    public static final CleavingBoardTrigger CLEAVING_BOARD = register(new CleavingBoardTrigger());
    public static final SickThrowDude SICK_THROW_DUDE = register(new SickThrowDude());
    public static final MonsterizeEffectTrigger MONSTERIZE_EFFECT = register(new MonsterizeEffectTrigger());

    private static <T extends CriterionTrigger<?>> T register(T criterion) {
        return CriteriaTriggersAccessor.register(criterion);
    }

    public static void load() {
    }
}