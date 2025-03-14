package net.yirmiri.dungeonsdelight.util.misc;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.entity.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.init.DDLootFunctions;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault @ParametersAreNonnullByDefault
public class CopyMonsterMealFunction extends LootItemConditionalFunction {
    public static final ResourceLocation ID = new ResourceLocation(DungeonsDelight.MOD_ID, "copy_meal");

    private CopyMonsterMealFunction(LootItemCondition[] conditions) {
        super(conditions);
    }

    public static LootItemConditionalFunction.Builder<?> builder() {
        return simpleBuilder(CopyMonsterMealFunction::new);
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        BlockEntity tile = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
        if (tile instanceof MonsterPotBlockEntity) {
            CompoundTag tag = ((MonsterPotBlockEntity) tile).writeMeal(new CompoundTag());
            if (!tag.isEmpty()) {
                stack.addTagElement("BlockEntityTag", tag);
            }
        }
        return stack;
    }

    @Override
    public LootItemFunctionType getType() {
        return DDLootFunctions.COPY_MONSTER_MEAL.get();
    }

    public static class Serializer extends LootItemConditionalFunction.Serializer<CopyMonsterMealFunction> {
        @Override
        public CopyMonsterMealFunction deserialize(JsonObject json, JsonDeserializationContext context, LootItemCondition[] conditions) {
            return new CopyMonsterMealFunction(conditions);
        }
    }
}
