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

public class SickThrowDude extends SimpleCriterionTrigger<SickThrowDude.TriggerInstance> {

    @Override
    public Codec<SickThrowDude.TriggerInstance> codec() {
        return SickThrowDude.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, SickThrowDude.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<SickThrowDude.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(SickThrowDude.TriggerInstance::player))
                        .apply(builder, SickThrowDude.TriggerInstance::new)
        );

        public static Criterion<SickThrowDude.TriggerInstance> simple() {
            return DDCriteriaTriggers.SICK_THROW_DUDE.get().createCriterion(
                    new SickThrowDude.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}