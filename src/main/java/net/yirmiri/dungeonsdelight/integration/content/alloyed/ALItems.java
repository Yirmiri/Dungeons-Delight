package net.yirmiri.dungeonsdelight.integration.content.alloyed;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDMaterials;
import net.yirmiri.dungeonsdelight.integration.common.*;
import net.yirmiri.dungeonsdelight.integration.util.INProperties;
import net.yirmiri.dungeonsdelight.integration.util.INUtil;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

import java.util.function.Supplier;

public class ALItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, DungeonsDelight.MOD_ID);

    //TOOLS
    public static final Supplier<Item> STEEL_CLEAVER = ITEMS.register("steel_cleaver", () -> new INCleaverItem(IntegrationIds.ALLOYED, 1.25F, DDMaterials.DDCMaterials.STEEL, 2.0F, -3.0F, DDProperties.ItemP.GENERIC_1));
}
