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

public class SlimeFoodTrigger extends SimpleCriterionTrigger<SlimeFoodTrigger.TriggerInstance> {

    @Override
    public Codec<SlimeFoodTrigger.TriggerInstance> codec() {
        return SlimeFoodTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, SlimeFoodTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<SlimeFoodTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(SlimeFoodTrigger.TriggerInstance::player))
                        .apply(builder, SlimeFoodTrigger.TriggerInstance::new)
        );

        public static Criterion<SlimeFoodTrigger.TriggerInstance> simple() {
            return DDCriteriaTriggers.SLIME_FOOD.get().createCriterion(
                    new SlimeFoodTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
