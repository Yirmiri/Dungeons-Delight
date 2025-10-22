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

public class FreeDryadTrigger extends SimpleCriterionTrigger<FreeDryadTrigger.TriggerInstance> {

    @Override
    public Codec<FreeDryadTrigger.TriggerInstance> codec() {
        return FreeDryadTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, FreeDryadTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<FreeDryadTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(FreeDryadTrigger.TriggerInstance::player))
                        .apply(builder, FreeDryadTrigger.TriggerInstance::new)
        );

        public static Criterion<FreeDryadTrigger.TriggerInstance> simple() {
            return DDCriteriaTriggers.FREE_DRYAD.get().createCriterion(
                    new FreeDryadTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
