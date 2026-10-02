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
    private static final ResourceLocation ID = RunicLib.customid(DungeonsDelight.MOD_ID, "cleaving_board");

    @Override
    public TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate ctx, DeserializationContext deserializationContext) {
        return new TriggerInstance(ctx, ItemPredicate.fromJson(jsonObject.get("item")));
    }

    public void trigger(ServerPlayer player, ItemStack item) { this.trigger(player, (tr) -> { return tr.matches(item); }); }

    @Override public ResourceLocation getId() { return ID; }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ItemPredicate item;

        public TriggerInstance(ContextAwarePredicate ctx, ItemPredicate item) {
            super(new ResourceLocation(DungeonsDelight.MOD_ID, "cleaving_board"), ctx);
            this.item = item;
        }

        public static TriggerInstance any() {
            return new TriggerInstance(ContextAwarePredicate.ANY, ItemPredicate.ANY);
        }

        public static TriggerInstance ofItem(ItemPredicate item) {
            return new TriggerInstance(ContextAwarePredicate.ANY, item);
        }

        public static TriggerInstance ofItem(ItemLike item) {
            return new TriggerInstance(
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

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("item", this.item.serializeToJson());
            return jsonObject;
        }
    }
}
