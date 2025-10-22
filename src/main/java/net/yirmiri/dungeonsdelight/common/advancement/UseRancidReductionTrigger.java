package net.yirmiri.dungeonsdelight.common.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.yirmiri.dungeonsdelight.core.registry.DDCriteriaTriggers;

import java.util.Optional;

public class UseRancidReductionTrigger extends SimpleCriterionTrigger<UseRancidReductionTrigger.TriggerInstance> {

    @Override
    public Codec<UseRancidReductionTrigger.TriggerInstance> codec() {
        return UseRancidReductionTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, UseRancidReductionTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<UseRancidReductionTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(UseRancidReductionTrigger.TriggerInstance::player))
                        .apply(builder, UseRancidReductionTrigger.TriggerInstance::new)
        );

        public static Criterion<UseRancidReductionTrigger.TriggerInstance> simple() {
            return DDCriteriaTriggers.USE_RANCID_REDUCTION.get().createCriterion(
                    new UseRancidReductionTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
