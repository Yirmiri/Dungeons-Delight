package net.yirmiri.dungeonsdelight.common.advancement;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonObject;
import net.azurune.runiclib.RunicLib;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class CleavingBoardTrigger extends SimpleCriterionTrigger<CleavingBoardTrigger.TriggerInstance> {
    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate ctx, DeserializationContext deserializationContext) {
        return new TriggerInstance(ctx, ItemPredicate.fromJson(jsonObject.get("item")));
    }

    public void trigger(ServerPlayer player, ItemStack item) { this.trigger(player, (tr) -> tr.matches(item)); }

    @Override
    public ResourceLocation getId() {
        return RunicLib.customid(DungeonsDelight.MOD_ID, "cleaving_board");
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ItemPredicate item;

        public TriggerInstance(ResourceLocation resourceLocation, ContextAwarePredicate ctx, ItemPredicate item) {
            super(resourceLocation, ctx);
            this.item = item;
        }

        public TriggerInstance(ContextAwarePredicate player, ItemPredicate item) {
            this(new ResourceLocation(DungeonsDelight.MOD_ID, "cleaving_board"), player, item);
        }

        public static ConsumeItemTrigger.TriggerInstance any() {
            return new ConsumeItemTrigger.TriggerInstance(ContextAwarePredicate.ANY, ItemPredicate.ANY);
        }

        public static ConsumeItemTrigger.TriggerInstance ofItem(ItemPredicate item) {
            return new ConsumeItemTrigger.TriggerInstance(ContextAwarePredicate.ANY, item);
        }

        public static ConsumeItemTrigger.TriggerInstance ofItem(ItemLike item) {
            return new ConsumeItemTrigger.TriggerInstance(
                    ContextAwarePredicate.ANY,
                    new ItemPredicate(
                            null,
                            ImmutableSet.of(item.asItem()),
                            MinMaxBounds.Ints.ANY,
                            MinMaxBounds.Ints.ANY,
                            EnchantmentPredicate.NONE,
                            EnchantmentPredicate.NONE,
                            null,
                            NbtPredicate.ANY)
            );
        }

        public boolean matches(ItemStack item) {
            return this.item.matches(item);
        }
    }
}
