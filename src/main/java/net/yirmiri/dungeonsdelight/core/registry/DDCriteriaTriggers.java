package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.advancement.*;

import java.util.function.Supplier;

public class DDCriteriaTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, DungeonsDelight.MOD_ID);

    public static final Supplier<SlimeFoodTrigger> SLIME_FOOD = TRIGGERS.register("slime_food", SlimeFoodTrigger::new);
    public static final Supplier<FeedWormouthTrigger> FEED_WORMOUTH = TRIGGERS.register("feed_wormouth", FeedWormouthTrigger::new);
    public static final Supplier<SickThrowDude> SICK_THROW_DUDE = TRIGGERS.register("sick_throw_dude", SickThrowDude::new);
    public static final Supplier<FreeDryadTrigger> FREE_DRYAD = TRIGGERS.register("free_dryad", FreeDryadTrigger::new);
    public static final Supplier<UseRancidReductionTrigger> USE_RANCID_REDUCTION = TRIGGERS.register("use_rancid_reduction", UseRancidReductionTrigger::new);
}
