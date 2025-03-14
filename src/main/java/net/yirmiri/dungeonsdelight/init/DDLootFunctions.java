package net.yirmiri.dungeonsdelight.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.util.misc.CopyMonsterMealFunction;

public class DDLootFunctions {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTIONS = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE.key(), DungeonsDelight.MOD_ID);

    public static final RegistryObject<LootItemFunctionType> COPY_MONSTER_MEAL = LOOT_FUNCTIONS.register("copy_monster_meal", () -> new LootItemFunctionType(new CopyMonsterMealFunction.Serializer()));
}
