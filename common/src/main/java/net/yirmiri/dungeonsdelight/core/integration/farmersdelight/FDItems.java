package net.yirmiri.dungeonsdelight.core.integration.farmersdelight;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.item.Item;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDTiers;
import net.yirmiri.dungeonsdelight.core.integration.DDIntegration;

import java.util.function.Supplier;

public class FDItems {
    public static final Supplier<Item> FLINT_CLEAVER = register("flint_cleaver", () -> new CleaverItem(0.66F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed() - 0.1F, DungeonsDelight.CONFIG.getCleaverChargeMultiplier() - 0.15F, DDTiers.FLINT, DDProperties.ItemP.FLINT));

    public static Supplier<Item> register(String id, Supplier<Item> supplier) {
        return Services.REGISTRY.registerItem(DDIntegration.FD_ID, id, supplier);
    }

    public static void load() {
    }
}
