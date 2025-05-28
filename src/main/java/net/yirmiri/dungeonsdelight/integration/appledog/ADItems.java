package net.yirmiri.dungeonsdelight.integration.appledog;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.integration.common.INCandiedDogAppleItem;
import net.yirmiri.dungeonsdelight.integration.util.INProperties;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

import java.util.function.Supplier;

public class ADItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, DungeonsDelight.MOD_ID);

    //MISC
    public static final Supplier<Item> SCULK_DOGAPPLE = ITEMS.register("sculk_dogapple", () -> new INCandiedDogAppleItem(IntegrationIds.APPLEDOG, INProperties.ItemP.SCULK_DOGAPPLE, 5, false, true, false));
    public static final Supplier<Item> SCULK_CATBLUEBERRY = ITEMS.register("sculk_catblueberry", () -> new INCandiedDogAppleItem(IntegrationIds.APPLEDOG, INProperties.ItemP.SCULK_DOGAPPLE, 5, false, true, true));
}
