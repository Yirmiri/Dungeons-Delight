package net.yirmiri.dungeonsdelight.integration.twilightforest;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.integration.common.INItem;
import net.yirmiri.dungeonsdelight.integration.util.INProperties;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

public class TFItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DungeonsDelight.MOD_ID);

    //MISC
    public static final RegistryObject<Item> BUG_CHOPS = ITEMS.register("bug_chops", () -> new INItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.BUG_CHOPS));
    public static final RegistryObject<Item> FRIED_BUG_CHOPS = ITEMS.register("fried_bug_chops", () -> new INItem(IntegrationIds.TWILIGHTFOREST, INProperties.ItemP.FRIED_BUG_CHOPS));
}
