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

public class SpiritFlameTrigger extends SimpleCriterionTrigger<SpiritFlameTrigger.TriggerInstance> {

    @Override
    public Codec<SpiritFlameTrigger.TriggerInstance> codec() {
        return SpiritFlameTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, SpiritFlameTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<SpiritFlameTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(SpiritFlameTrigger.TriggerInstance::player))
                        .apply(builder, SpiritFlameTrigger.TriggerInstance::new)
        );

        public static Criterion<SpiritFlameTrigger.TriggerInstance> simple() {
            return DDCriteriaTriggers.CREATE_SPIRIT_FIRE.get().createCriterion(
                    new SpiritFlameTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
