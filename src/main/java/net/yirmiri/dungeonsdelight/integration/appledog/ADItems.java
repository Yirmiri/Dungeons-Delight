package net.yirmiri.dungeonsdelight.integration.appledog;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.integration.common.INCandiedDogAppleItem;
import net.yirmiri.dungeonsdelight.integration.util.INProperties;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

public class ADItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DungeonsDelight.MOD_ID);

    //MISC
    public static final RegistryObject<Item> SCULK_DOGAPPLE = ITEMS.register("sculk_dogapple", () -> new INCandiedDogAppleItem(IntegrationIds.APPLEDOG, INProperties.ItemP.SCULK_DOGAPPLE, 5, false, true, false));
}
