package net.yirmiri.dungeonsdelight.integration.fishy_fiesta;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.integration.common.INBiteableItem;
import net.yirmiri.dungeonsdelight.integration.util.INProperties;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

import java.util.function.Supplier;

public class FFItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, DungeonsDelight.MOD_ID);

    //GENERIC FOODS
    public static final Supplier<Item> LUTEFISK = ITEMS.register("lutefisk", () -> new INBiteableItem(IntegrationIds.FISHY_FIESTA, INProperties.ItemP.LUTEFISK, 64, true));
}
