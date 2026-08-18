package net.yirmiri.dungeonsdelight.core.integration.subterrous;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.integration.DDIntegration;

import java.util.function.Supplier;

public class STItems {
    public static final Supplier<Item> WOLFRAM_CLEAVER = register("wolfram_cleaver", () -> new CleaverItem(0.75F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed() - 0.2F, 0.75F, Tiers.valueOf("WOLFRAM"), DDProperties.ItemP.WOLFRAM));

    public static Supplier<Item> register(String id, Supplier<Item> supplier) {
        return Services.REGISTRY.registerItem(DDIntegration.ST_ID, id, supplier);
    }

    public static void load() {
    }
}
