package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import vectorwing.farmersdelight.common.utility.RegistryUtils;

import java.util.List;

public class DDDataComponents {
    public static final RegistryUtils.EnchantmentEffectComponents ENCHANTMENT = RegistryUtils.createEnchantmentEffectComponents(DungeonsDelight.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> RICOCHET = ENCHANTMENT.registerComponentType(
            "ricochet", builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ALL_PARAMS).listOf()));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> SERRATED_STRIKE = ENCHANTMENT.registerComponentType(
            "serrated_strike", builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ALL_PARAMS).listOf()));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> LIFE_GRASP = ENCHANTMENT.registerComponentType(
            "life_grasp", builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ALL_PARAMS).listOf()));
}
