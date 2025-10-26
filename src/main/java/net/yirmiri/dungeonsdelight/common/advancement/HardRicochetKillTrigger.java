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

public class HardRicochetKillTrigger extends SimpleCriterionTrigger<HardRicochetKillTrigger.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> codec() {
        return HardRicochetKillTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {
        public static final Codec<HardRicochetKillTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(HardRicochetKillTrigger.TriggerInstance::player))
                        .apply(builder, HardRicochetKillTrigger.TriggerInstance::new)
        );

        public static Criterion<TriggerInstance> simple() {
            return DDCriteriaTriggers.HARD_RICOCHET_KILL.get().createCriterion(
                    new HardRicochetKillTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
