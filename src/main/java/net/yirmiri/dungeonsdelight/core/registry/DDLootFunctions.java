package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.misc.CopyMonsterMealFunction;

import java.util.function.Supplier;

public class DDLootFunctions {
    public static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTIONS = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, DungeonsDelight.MOD_ID);

    public static final Supplier<LootItemFunctionType> COPY_MONSTER_MEAL = LOOT_FUNCTIONS.register("copy_monster_meal", () -> new LootItemFunctionType(new CopyMonsterMealFunction.Serializer()));
}
