package net.yirmiri.dungeonsdelight.datagen;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDDataComponents;

public class DDEnchantments {
    public static final ResourceKey<Enchantment> RICOCHET = key("ricochet");
    public static final ResourceKey<Enchantment> SERRATED_STRIKE = key("serrated_strike");

    public static void bootstrap(BootstrapContext<Enchantment> ctx) {
        HolderGetter<Item> items = ctx.lookup(Registries.ITEM);

        register(ctx, RICOCHET, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(DDTags.ItemT.CLEAVER_ENCHANTABLE),
                        3, 3,
                        Enchantment.dynamicCost(15, 9), Enchantment.dynamicCost(50, 8),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .withEffect(DDDataComponents.RICOCHET.get(),
                        new AddValue(LevelBasedValue.perLevel(1.0F, 1.0F))));

        register(ctx, SERRATED_STRIKE, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DDTags.ItemT.CLEAVER_ENCHANTABLE),
                        5, 1,
                        Enchantment.dynamicCost(10, 5), Enchantment.dynamicCost(25, 5),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .withEffect(DDDataComponents.SERRATED_STRIKE.get(),
                        new AddValue(LevelBasedValue.perLevel(1.0F, 1.0F))));
    }


    private static void register(BootstrapContext<Enchantment> ctx, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        ctx.register(key, builder.build(key.location()));
    }

    private static ResourceKey<Enchantment> key(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, RunicLib.customid(DungeonsDelight.MOD_ID, id));
    }
}
