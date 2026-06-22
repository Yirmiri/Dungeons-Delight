package net.yirmiri.dungeonsdelight.common.advancement;

import com.google.gson.JsonObject;
import net.azurune.runiclib.RunicLib;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class MonsterizeEffectTrigger extends SimpleCriterionTrigger<MonsterizeEffectTrigger.TriggerInstance> {
    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate ctx, DeserializationContext deserializationContext) {
        return new TriggerInstance(ctx);
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, TriggerInstance::test);
    }

    @Override
    public ResourceLocation getId() {
        return RunicLib.customid(DungeonsDelight.MOD_ID, "monsterize_effect");
    }


    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        public TriggerInstance(ResourceLocation resourceLocation, ContextAwarePredicate ctx) {
            super(resourceLocation, ctx);
        }

        public TriggerInstance(ContextAwarePredicate player) {
            super(new ResourceLocation(DungeonsDelight.MOD_ID, "monsterize_effect"), player);
        }

        public static TriggerInstance trigger() {
            return new TriggerInstance(ContextAwarePredicate.ANY);
        }

        public boolean test() {
            return true;
        }
    }
}