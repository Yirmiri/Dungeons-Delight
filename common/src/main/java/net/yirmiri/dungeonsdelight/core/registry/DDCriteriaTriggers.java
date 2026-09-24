package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.advancements.CriterionTrigger;
import net.yirmiri.dungeonsdelight.common.advancement.*;
import net.yirmiri.dungeonsdelight.core.mixin.CriteriaTriggersAccessor;

public class DDCriteriaTriggers {

    public static final SlimeFoodTrigger SLIME_FOOD = register(new SlimeFoodTrigger());
    public static final CleavingBoardTrigger CLEAVING_BOARD = register(new CleavingBoardTrigger());
    public static final FeedWormouthTrigger FEED_WORMOUTH = register(new FeedWormouthTrigger());
    public static final SickThrowDude SICK_THROW_DUDE = register(new SickThrowDude());
    public static final FreeDryadTrigger FREE_DRYAD = register(new FreeDryadTrigger());
    public static final UseRancidReductionTrigger USE_RANCID_REDUCTION = register(new UseRancidReductionTrigger());
    public static final RicochetKillTrigger RICOCHET_KILL = register(new RicochetKillTrigger());
    public static final HardRicochetKillTrigger HARD_RICOCHET_KILL = register(new HardRicochetKillTrigger());
    public static final CureEffectTrigger CURE_EFFECT = register(new CureEffectTrigger());
    public static final MonsterizeEffectTrigger MONSTERIZE_EFFECT = register(new MonsterizeEffectTrigger());
    public static final SpiritFlameTrigger CREATE_SPIRIT_FIRE = register(new SpiritFlameTrigger());

    private static <T extends CriterionTrigger<?>> T register(T criterion) {
        return CriteriaTriggersAccessor.register(criterion);
    }

    public static void load() {
    }
}
