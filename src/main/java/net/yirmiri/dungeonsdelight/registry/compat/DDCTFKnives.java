package net.yirmiri.dungeonsdelight.registry.compat;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.item.compat.CompatKnifeItem;
import net.yirmiri.dungeonsdelight.item.compat.twilightforest.FieryKnifeItem;
import net.yirmiri.dungeonsdelight.item.compat.twilightforest.KnightmetalKnifeItem;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import net.yirmiri.dungeonsdelight.util.compat.DDCProperties;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import twilightforest.util.TwilightItemTier;

public class DDCTFKnives {
    //KNIVES
    public static final RegistryObject<Item> FIERY_KNIFE = DDItems.ITEMS.register("fiery_knife", () -> new FieryKnifeItem(TwilightItemTier.FIERY, 0.5F, -2.0F, DDCProperties.ItemP.FIERY_KNIFE));
    public static final RegistryObject<Item> KNIGHTMETAL_KNIFE = DDItems.ITEMS.register("knightmetal_knife", () -> new KnightmetalKnifeItem(TwilightItemTier.KNIGHTMETAL, 0.5F, -2.0F, new Item.Properties()));
    public static final RegistryObject<Item> IRONWOOD_KNIFE = DDItems.ITEMS.register("ironwood_knife", () -> new CompatKnifeItem(DDUtil.TF_ID, TwilightItemTier.IRONWOOD, 0.5F, -2.0F, new Item.Properties()));
    public static final RegistryObject<Item> STEELEAF_KNIFE = DDItems.ITEMS.register("steeleaf_knife", () -> new CompatKnifeItem(DDUtil.TF_ID, TwilightItemTier.STEELEAF, 0.5F, -2.0F, new Item.Properties()));

    public static void register() {
    }
}

