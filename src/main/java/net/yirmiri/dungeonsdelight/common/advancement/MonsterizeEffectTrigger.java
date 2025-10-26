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

public class MonsterizeEffectTrigger extends SimpleCriterionTrigger<MonsterizeEffectTrigger.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> codec() {
        return MonsterizeEffectTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {
        public static final Codec<MonsterizeEffectTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(MonsterizeEffectTrigger.TriggerInstance::player))
                        .apply(builder, MonsterizeEffectTrigger.TriggerInstance::new)
        );

        public static Criterion<TriggerInstance> simple() {
            return DDCriteriaTriggers.MONSTERIZE_EFFECT.get().createCriterion(
                    new MonsterizeEffectTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
